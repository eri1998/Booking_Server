package com.comtrade.sistemskeOperacije.adresa;

import java.sql.SQLException;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Adresa;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class VratiAdresuHotela extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		List<Adresa> list = (List<Adresa>) object;
		Adresa adresa = list.get(0);
		IBroker ib = new Broker();
		List<Adresa> list2 = ib.vratiAdresuHotela(adresa);
		list.clear();
		list.addAll(list2);
	}

}
