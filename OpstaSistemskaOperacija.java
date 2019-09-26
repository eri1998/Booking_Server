package com.comtrade.sistemskeOperacije;

import java.sql.SQLException;

import com.comtrade.konekcija.Konekcija;

public abstract class OpstaSistemskaOperacija {
	public void izvrsiSistemskeOperacije(Object object) {
		try {
			pokreniTransakciju();
			izvrsiKonkretnuOperaciju(object);
			potvrdiTransakciju();

		} catch (Exception e) {
			ponistiTransakciju();
		} finally {
			zatvoriKonekciju();
		}
	}

	private void pokreniTransakciju() {

		Konekcija.getInstanca().pokreniTransakciju();
	}

	protected abstract void izvrsiKonkretnuOperaciju(Object object) throws SQLException;

	private void potvrdiTransakciju() {
		Konekcija.getInstanca().potvrdiTransakciju();

	}

	private void ponistiTransakciju() {

		Konekcija.getInstanca().ponistiTransakciju();
	}

	private void zatvoriKonekciju() {

		Konekcija.getInstanca().zatvoriKonekciju();
	}

}
