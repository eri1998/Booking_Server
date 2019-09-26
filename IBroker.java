package com.comtrade.broker;

import java.sql.SQLException;
import java.util.List;

import com.comtrade.domen.Adresa;
import com.comtrade.domen.Drzava;
import com.comtrade.domen.Grad;
import com.comtrade.domen.Hotel;
import com.comtrade.domen.HotelPretragaVrednosti;
import com.comtrade.domen.OpstiDomen;
import com.comtrade.domen.Soba;
import com.comtrade.domen.User;
import com.comtrade.domen.photos.Photo;
import com.comtrade.domen.pretraga.Pretraga;

public interface IBroker {
	public void sacuvaj(OpstiDomen opstiDomen);

	public User login(User user) throws SQLException;

	List<OpstiDomen> list(OpstiDomen opstiDomen);

	List<Hotel> vratiSlike(Hotel hotel);

	List<Adresa> vratiAdresuHotela(Adresa adresa);

	List<Grad> vratiGradHotela(Grad grad);

	List<Drzava> vratiDrzavuHotela(Drzava drzava);

	List<Hotel> pretragaFull(HotelPretragaVrednosti pretragaVrednosti);

	List<Soba> pretragaSobeFull(List<Soba> list);
}
