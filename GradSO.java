package com.comtrade.opstaSistemskaOperacija.grad;

import java.sql.SQLException;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Drzava;
import com.comtrade.domen.Grad;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class GradSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		Grad grad = (Grad) object;
		IBroker ibroker = new Broker();
		ibroker.sacuvaj(grad);

	}

}
