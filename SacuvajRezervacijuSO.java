package com.comtrade.so.rezervacije;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.rezervacije.Rezervacije;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class SacuvajRezervacijuSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		Rezervacije rezervacija = (Rezervacije) object;
		IBroker ib = new Broker();
		ib.sacuvaj(rezervacija);

	}

}
