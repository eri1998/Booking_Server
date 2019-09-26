package com.comtrade.sistemskeOperacije.hotel;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Hotel;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class insertHotelSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		Hotel hotel = (Hotel) object;
		IBroker ibroker = new Broker();
		ibroker.sacuvaj(hotel);

	}

}
