package com.comtrade.konekcija;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Konekcija {
	private static volatile Konekcija instanca;
	private Connection connection;

	private Konekcija() {
		ucitajDriver();
	}

	private void ucitajDriver() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Konekcija getInstanca() {
		if (instanca == null) {
			synchronized (Konekcija.class) {
				if (instanca == null) {
					instanca = new Konekcija();
				}
			}
		}
		return instanca;
	}

	public void pokreniTransakciju() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/booking", "root", "");
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void potvrdiTransakciju() {
		try {
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ponistiTransakciju() {
		try {
			connection.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void zatvoriKonekciju() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

}
