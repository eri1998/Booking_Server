package com.comtrade.sistemskeOperacije.adresa;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Adresa;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class AdresaSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		Adresa adresa = (Adresa) object;
		IBroker ibroker = new Broker();
		ibroker.sacuvaj(adresa);

	}

}
