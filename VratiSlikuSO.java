package com.comtrade.so.photo;

import java.sql.SQLException;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Hotel;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.domen.photos.Photo;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class VratiSlikuSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		List<Hotel> list = (List<Hotel>) object;
		Hotel hotel = list.get(0);
		IBroker ib = new Broker();
		List<Hotel> list1 = ib.vratiSlike(hotel);
		list.clear();
		list.addAll(list1);
	}

}
