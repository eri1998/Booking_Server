package com.comtrade.sistemskaOperacija.soba;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.SobaSlike;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class SlikeSobaSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		SobaSlike photo = (SobaSlike) object;
		IBroker ib = new Broker();
		ib.sacuvaj(photo);

	}

}
