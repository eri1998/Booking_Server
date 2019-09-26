package com.comtrade.sistemskeOperacije.adresa;

import java.sql.SQLException;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Adresa;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class VratiAdresuSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		List<OpstiDomen> listAdresa = (List<OpstiDomen>) object;
		IBroker ibroker = new Broker();
		listAdresa.addAll(ibroker.list(new Adresa()));
	}

}
