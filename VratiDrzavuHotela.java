package com.comtrade.sistemskeOperacije.drzava;

import java.sql.SQLException;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Drzava;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class VratiDrzavuHotela extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		List<Drzava> list = (List<Drzava>) object;
		Drzava drzava = list.get(0);
		IBroker ib = new Broker();
		List<Drzava> list2 = ib.vratiDrzavuHotela(drzava);
		list.clear();
		list.addAll(list2);
	}

}
