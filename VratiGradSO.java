package com.comtrade.opstaSistemskaOperacija.grad;

import java.sql.SQLException;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Grad;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class VratiGradSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		List<OpstiDomen> list = (List<OpstiDomen>) object;
		IBroker ibroker = new Broker();
		list.addAll(ibroker.list(new Grad()));

	}

}
