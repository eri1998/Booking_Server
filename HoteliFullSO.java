package com.comtrade.sistemskeOperacije.hotel;

import java.sql.SQLException;
import java.util.List;

import com.comtrade.broker.Broker;
import com.comtrade.broker.IBroker;
import com.comtrade.domen.Hotel;
import com.comtrade.domen.HotelPretragaVrednosti;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;

public class HoteliFullSO extends OpstaSistemskaOperacija {

	@Override
	protected void izvrsiKonkretnuOperaciju(Object object) throws SQLException {
		HotelPretragaVrednosti pretragaVrednosti = (HotelPretragaVrednosti) object;
		IBroker ib = new Broker();
		List<Hotel> list2 = ib.pretragaFull(pretragaVrednosti);
		pretragaVrednosti.getPretragaRezultat().clear();
		pretragaVrednosti.getPretragaRezultat().addAll(list2);

	}

}
