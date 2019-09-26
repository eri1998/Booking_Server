package com.comtrade.sistemskaOperacija.soba;

import java.sql.SQLException;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Soba;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class FullSobeSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		List<Soba> list = (List<Soba>) object;
		Soba idHotela = list.get(0);
		list.add(idHotela);
		IBroker ib = new Broker();
		List<Soba> list2 = ib.pretragaSobeFull(list);
		list.clear();
		list.addAll(list2);

	}

}
