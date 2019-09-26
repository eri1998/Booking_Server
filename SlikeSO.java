package com.comtrade.so.photo;

import java.sql.SQLException;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.photos.Photo;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class SlikeSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		Photo photo = (Photo) object;
		IBroker ib = new Broker();
		ib.sacuvaj(photo);

	}

}
