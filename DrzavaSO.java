package com.comtrade.sistemskeOperacije.drzava;

import java.sql.SQLException;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Drzava;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class DrzavaSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		Drzava drzava = (Drzava) object;
		IBroker ibroker = new Broker();
		ibroker.sacuvaj(drzava);

	}

}
