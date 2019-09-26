package com.comtrade.kontrolerPL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.comtrade.domen.Adresa;
import com.comtrade.domen.Drzava;
import com.comtrade.domen.Grad;
import com.comtrade.domen.Hotel;
import com.comtrade.domen.HotelPretragaVrednosti;
import com.comtrade.domen.Soba;
import com.comtrade.domen.SobaSlike;
import com.comtrade.domen.TipSoba;
import com.comtrade.domen.User;
import com.comtrade.domen.Usluge;
import com.comtrade.domen.photos.Photo;
import com.comtrade.domen.pretraga.Pretraga;
import com.comtrade.domen.rezervacije.Rezervacije;
import com.comtrade.opstaSistemskaOperacija.grad.GradSO;
import com.comtrade.opstaSistemskaOperacija.grad.VratiGradHotela;
import com.comtrade.opstaSistemskaOperacija.grad.VratiGradSO;
import com.comtrade.sistemskaOperacija.soba.FullSobeSO;
import com.comtrade.sistemskaOperacija.soba.SlikeSobaSO;
import com.comtrade.sistemskaOperacija.soba.SobaSO;
import com.comtrade.sistemskaOperacija.soba.VratiPoslednjuSobuSO;
import com.comtrade.sistemskaOperacija.soba.VratiSobuSO;
import com.comtrade.sistemskeOperacije.OpstaSistemskaOperacija;
import com.comtrade.sistemskeOperacije.adresa.AdresaSO;
import com.comtrade.sistemskeOperacije.adresa.VratiAdresuHotela;
import com.comtrade.sistemskeOperacije.adresa.VratiAdresuSO;
import com.comtrade.sistemskeOperacije.drzava.DrzavaSO;
import com.comtrade.sistemskeOperacije.drzava.VratiDrzavuHotela;
import com.comtrade.sistemskeOperacije.drzava.VratiDrzavuSO;
import com.comtrade.sistemskeOperacije.hotel.HoteliFullSO;

import com.comtrade.sistemskeOperacije.hotel.VratiHotelSO;
import com.comtrade.sistemskeOperacije.hotel.VratiSveHotele;
import com.comtrade.sistemskeOperacije.hotel.insertHotelSO;
import com.comtrade.sistemskeOperacije.user.UserLoginSO;
import com.comtrade.sistemskeOperacije.user.UserSO;
import com.comtrade.so.photo.SlikeSO;
import com.comtrade.so.photo.VratiSlikuSO;

import com.comtrade.so.rezervacije.SacuvajRezervacijuSO;
import com.comtrade.so.rezervacije.VratiPoslednjuRezervaciju;
import com.comtrade.so.rezervacije.VratiRezervacijuSO;
import com.comtrade.so.sobaa.SobaaSO;
import com.comtrade.so.usluge.UslugeSO;
import com.comtrade.transferKlasa.TransferKlasa;

public class KontrolerPL {
	private static volatile KontrolerPL instanca;

	private KontrolerPL() {

	}

	public static KontrolerPL getInstanca() {
		if (instanca == null) {
			synchronized (KontrolerPL.class) {
				if (instanca == null) {
					instanca = new KontrolerPL();
				}
			}
		}
		return instanca;
	}

	public void registrujKorisnika(User user) {
		OpstaSistemskaOperacija opstaSistemskaOperacija = new UserSO();
		opstaSistemskaOperacija.izvrsiSistemskeOperacije(user);

	}

	public User login(User user1) {
		List<User> list = new ArrayList<>();
		list.add(user1);
		OpstaSistemskaOperacija opstaSistemskaOperacija = new UserLoginSO();
		opstaSistemskaOperacija.izvrsiSistemskeOperacije(list);
		return list.get(1);

	}

	public Drzava sacuvajDrzavu(Drzava drzava) {

		OpstaSistemskaOperacija opstaSistemskaOperacija = new DrzavaSO();
		opstaSistemskaOperacija.izvrsiSistemskeOperacije(drzava);
		return drzava;

	}

	public Grad sacuvajGrad(Grad grad) {
		OpstaSistemskaOperacija opstaSistemskaOperacija = new GradSO();
		opstaSistemskaOperacija.izvrsiSistemskeOperacije(grad);
		return grad;

	}

	public List<Drzava> vratiDrzavu() {
		List<Drzava> list = new ArrayList<>();
		OpstaSistemskaOperacija opstaSistemskaOperacija = new VratiDrzavuSO();
		opstaSistemskaOperacija.izvrsiSistemskeOperacije(list);

		return list;
	}

	public List<Grad> vratiGrad() {
		List<Grad> listGrad = new ArrayList<>();
		OpstaSistemskaOperacija opstaSistemskaOperacija = new VratiGradSO();
		opstaSistemskaOperacija.izvrsiSistemskeOperacije(listGrad);
		return listGrad;
	}

	public Adresa upisiAdresu(Adresa adresa) {
		OpstaSistemskaOperacija opstaSistemskaOperacija = new AdresaSO();
		opstaSistemskaOperacija.izvrsiSistemskeOperacije(adresa);
		return adresa;

	}

	public List<Adresa> vratiAdresu() {
		List<Adresa> listAdresa = new ArrayList<>();
		OpstaSistemskaOperacija opstaSistemskaOperacija = new VratiAdresuSO();
		opstaSistemskaOperacija.izvrsiSistemskeOperacije(listAdresa);
		return listAdresa;
	}

	public Hotel sacuvajHotel(Hotel hotel) {
		OpstaSistemskaOperacija opstaSistemskaOperacija = new insertHotelSO();
		opstaSistemskaOperacija.izvrsiSistemskeOperacije(hotel);
		return hotel;
	}

	public List<Hotel> vratiHotel() {
		List<Hotel> listHotel = new ArrayList<>();
		OpstaSistemskaOperacija opstaSistemskaOperacija = new VratiHotelSO();
		opstaSistemskaOperacija.izvrsiSistemskeOperacije(listHotel);
		return listHotel;
	}

	public TipSoba sacuvajSobu(TipSoba soba) {
		OpstaSistemskaOperacija opstaSistemskaOperacija = new SobaSO();
		opstaSistemskaOperacija.izvrsiSistemskeOperacije(soba);
		return soba;

	}

	public List<TipSoba> vratiSobu() {
		List<TipSoba> listSoba = new ArrayList<>();
		OpstaSistemskaOperacija oso = new VratiSobuSO();
		oso.izvrsiSistemskeOperacije(listSoba);
		return listSoba;
	}

	public void upisiSobu(Soba sobaa) {
		OpstaSistemskaOperacija opstaSistemskaOperacija = new SobaaSO();
		opstaSistemskaOperacija.izvrsiSistemskeOperacije(sobaa);

	}

	public void upisiUsluge(Usluge usluge) {
		OpstaSistemskaOperacija opstaSistemskaOperacija = new UslugeSO();
		opstaSistemskaOperacija.izvrsiSistemskeOperacije(usluge);

	}

	public void sacuvajSlike(Photo photo) {
		OpstaSistemskaOperacija oso = new SlikeSO();
		oso.izvrsiSistemskeOperacije(photo);

	}

	public List<Hotel> vratiSliku(Hotel hotel21) {
		List<Hotel> list = new ArrayList<>();
		list.add(hotel21);
		OpstaSistemskaOperacija oso = new VratiSlikuSO();
		oso.izvrsiSistemskeOperacije(list);
		return list;

	}

	public List<Hotel> vratiSveHotele() {
		List<Hotel> list = new ArrayList<>();
		OpstaSistemskaOperacija oso = new VratiSveHotele();
		oso.izvrsiSistemskeOperacije(list);
		return list;
	}

	

	public List<Adresa> adresuHotela(Adresa adresaa) {
		List<Adresa> list = new ArrayList<>();
		list.add(adresaa);
		OpstaSistemskaOperacija operacija = new VratiAdresuHotela();
		operacija.izvrsiSistemskeOperacije(list);
		return list;
	}

	public List<Grad> gradHotela(Grad gradd) {
		List<Grad> list = new ArrayList<>();
		list.add(gradd);
		OpstaSistemskaOperacija oso = new VratiGradHotela();
		oso.izvrsiSistemskeOperacije(list);
		return list;
	}

	public List<Drzava> drzavaHotela(Drzava drzavaHotel) {
		List<Drzava> list = new ArrayList<>();
		list.add(drzavaHotel);
		OpstaSistemskaOperacija oso = new VratiDrzavuHotela();
		oso.izvrsiSistemskeOperacije(list);
		return list;
	}

	

	

	public List<Hotel> pretragaFull(HotelPretragaVrednosti pretragaVrednosti) {
	
		OpstaSistemskaOperacija oso = new HoteliFullSO();
		oso.izvrsiSistemskeOperacije(pretragaVrednosti);
		return pretragaVrednosti.getPretragaRezultat();
	}

	public void sacuvajRezervaciju(Rezervacije rezervacija) {
		OpstaSistemskaOperacija opstaSistemskaOperacija = new SacuvajRezervacijuSO();
		opstaSistemskaOperacija.izvrsiSistemskeOperacije(rezervacija);

	}

	public Rezervacije vratiPoslednjuRezervaciju() {
		List<Rezervacije> list = new ArrayList<Rezervacije>();
		OpstaSistemskaOperacija opstaSistemskaOperacija = new VratiPoslednjuRezervaciju();
		opstaSistemskaOperacija.izvrsiSistemskeOperacije(list);
		return list.get(0);

	}

	public void sacuvajSlikeSoba(SobaSlike ss) {
		OpstaSistemskaOperacija oso = new SlikeSobaSO();
		oso.izvrsiSistemskeOperacije(ss);

	}

	public List<Soba> vratiPoslednjuSobu() {
		List<Soba> listSoba = new ArrayList<>();
		OpstaSistemskaOperacija oso = new VratiPoslednjuSobuSO();
		oso.izvrsiSistemskeOperacije(listSoba);
		return listSoba;

	}

	public List<Soba> pretragaFullSobe(Soba sobaaa) {
		List<Soba> list = new ArrayList<Soba>();
		list.add(sobaaa);
		OpstaSistemskaOperacija oso = new FullSobeSO();
		oso.izvrsiSistemskeOperacije(list);
		return list;
	}

	public List<Rezervacije> vratiRezervacije() {
		List<Rezervacije> list = new ArrayList<>();
		OpstaSistemskaOperacija opstaSistemskaOperacija = new VratiRezervacijuSO();
		opstaSistemskaOperacija.izvrsiSistemskeOperacije(list);
		return list;
	}

}
