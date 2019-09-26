package com.comtrade.so.rezervacije;

import java.sql.SQLException;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.rezervacije.Rezervacije;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class VratiPoslednjuRezervaciju extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		List<Rezervacije> list = (List<Rezervacije>) object;

		IBroker ib = new Broker();
		list.add((Rezervacije) ib.list(new Rezervacije()));

	}

}
