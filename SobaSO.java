package com.comtrade.sistemskaOperacija.soba;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.TipSoba;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class SobaSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		TipSoba soba = (TipSoba) object;
		IBroker ib = new Broker();
		ib.sacuvaj(soba);

	}

}
