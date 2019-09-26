package com.comtrade.sistemskeOperacije.hotel;

import java.sql.SQLException;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Hotel;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class VratiHotelSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		List<OpstiDomen> listHotel = (List<OpstiDomen>) object;
		IBroker ibroker = new Broker();
		listHotel.addAll(ibroker.list(new Hotel()));
	}

}
