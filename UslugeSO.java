package com.comtrade.so.usluge;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Usluge;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class UslugeSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		Usluge usluge = (Usluge) object;
		IBroker ib = new Broker();
		ib.sacuvaj(usluge);

	}

}
