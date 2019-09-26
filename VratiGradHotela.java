package com.comtrade.opstaSistemskaOperacija.grad;

import java.sql.SQLException;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Grad;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class VratiGradHotela extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		List<Grad> list = (List<Grad>) object;
		Grad grad = list.get(0);
		IBroker ib = new Broker();
		List<Grad> list2 = ib.vratiGradHotela(grad);
		list.clear();
		list.addAll(list2);
	}

}
