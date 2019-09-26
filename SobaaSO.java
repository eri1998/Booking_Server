package com.comtrade.so.sobaa;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Soba;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class SobaaSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		Soba soba = (Soba) object;
		IBroker ib = new Broker();
		ib.sacuvaj(soba);

	}

}
