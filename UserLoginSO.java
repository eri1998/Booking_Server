package com.comtrade.sistemskeOperacije.user;

import java.sql.SQLException;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.User;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class UserLoginSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		List<User> list = (List<User>) object;
		User user = list.get(0);
		IBroker ibroker = new Broker();
		User userBaza = ibroker.login(user);
		list.add(userBaza);

	}

}
