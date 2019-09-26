package com.comtrade.sistemskaOperacija.soba;

import java.sql.SQLException;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.domen.Soba;

import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class VratiPoslednjuSobuSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		List<OpstiDomen> list = (List<OpstiDomen>) object;
		IBroker ib = new Broker();
		list.addAll(ib.list(new Soba()));

	}

}
