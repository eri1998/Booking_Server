package com.comtrade.sistemskeOperacije.user;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.User;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class UserSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) {
		User user = (User) object;
		IBroker ibroker = new Broker();
		ibroker.sacuvaj(user);

	}

}
