package com.comtrade.nit;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
import com.comtrade.kontrolerPL.KontrolerPL;
import com.comtrade.operacije.Operacije;
import com.comtrade.transferKlasa.TransferKlasa;

public class KlijentNit extends Thread {
	private Socket socket;

	public void setSocket(Socket socket) {
		this.socket = socket;

	}

	public void run() {

		while (true) {

			try {
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
				TransferKlasa transferKlasa = (TransferKlasa) objectInputStream.readObject();
				obradaKlijenta(transferKlasa);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void obradaKlijenta(TransferKlasa transferKlasa) {
		TransferKlasa transferKlasa1 = new TransferKlasa();
		switch (transferKlasa.getOperacija()) {
		case Operacije.SACUVAJ_KORISNIKA:
			User user = (User) transferKlasa.getKlijent_object_request();
			KontrolerPL.getInstanca().registrujKorisnika(user);
			transferKlasa1.setPoruka_response("  Uspesno sacuvan korisnik ");
			posaljiResponse(transferKlasa1);

			break;
		case Operacije.LOGIN:
			User user1 = (User) transferKlasa.getKlijent_object_request();
			User userBaza = KontrolerPL.getInstanca().login(user1);
			TransferKlasa transferKlasa3 = new TransferKlasa();
			transferKlasa3.setServer_object_response(userBaza);
			posaljiResponse(transferKlasa3);

			break;

		case Operacije.SACUVAJ_DRZAVU:
			Drzava drzava = (Drzava) transferKlasa.getKlijent_object_request();
			Drzava drzava1 = KontrolerPL.getInstanca().sacuvajDrzavu(drzava);
			TransferKlasa transferKlasa5 = new TransferKlasa();
			transferKlasa5.setServer_object_response(drzava1);
			posaljiResponse(transferKlasa5);

			break;
		case Operacije.VRATI_DRZAVU:

			List<Drzava> list = KontrolerPL.getInstanca().vratiDrzavu();
			TransferKlasa transferKlasa6 = new TransferKlasa();
			transferKlasa6.setServer_object_response(list);
			posaljiResponse(transferKlasa6);

			break;
		case Operacije.SACUVAJ_GRAD:
			Grad grad = (Grad) transferKlasa.getKlijent_object_request();
			Grad grad1 = KontrolerPL.getInstanca().sacuvajGrad(grad);
			TransferKlasa transferKlasa7 = new TransferKlasa();
			transferKlasa7.setServer_object_response(grad1);
			posaljiResponse(transferKlasa7);

			break;
		case Operacije.VRATI_GRAD:

			List<Grad> listGrad = KontrolerPL.getInstanca().vratiGrad();
			TransferKlasa transferKlasa8 = new TransferKlasa();
			transferKlasa8.setServer_object_response(listGrad);
			posaljiResponse(transferKlasa8);

			break;
		case Operacije.SACUVAJ_ADRESU:
			Adresa adresa = (Adresa) transferKlasa.getKlijent_object_request();

			Adresa adresa1 = KontrolerPL.getInstanca().upisiAdresu(adresa);
			TransferKlasa transferKlasa9 = new TransferKlasa();
			transferKlasa9.setServer_object_response(adresa1);
			posaljiResponse(transferKlasa9);
			break;
		case Operacije.VRATI_ADRESU:
			List<Adresa> listAdresa = KontrolerPL.getInstanca().vratiAdresu();
			TransferKlasa transferKlasa10 = new TransferKlasa();
			transferKlasa10.setServer_object_response(listAdresa);
			posaljiResponse(transferKlasa10);
			break;
		case Operacije.SACUVAJ_HOTEL:
			Hotel hotel = (Hotel) transferKlasa.getKlijent_object_request();
			Hotel hotel1 = KontrolerPL.getInstanca().sacuvajHotel(hotel);
			TransferKlasa transferKlasa4 = new TransferKlasa();
			transferKlasa4.setServer_object_response(hotel1);
			posaljiResponse(transferKlasa4);

			break;
		case Operacije.VRATI_HOTEL:

			List<Hotel> listHotel = KontrolerPL.getInstanca().vratiHotel();
			TransferKlasa transferKlasa11 = new TransferKlasa();
			transferKlasa11.setServer_object_response(listHotel);
			posaljiResponse(transferKlasa11);

			break;
		case Operacije.SACUVAJ_SOBU:
			TipSoba soba = (TipSoba) transferKlasa.getKlijent_object_request();
			KontrolerPL.getInstanca().sacuvajSobu(soba);
			TransferKlasa transferKlasa12 = new TransferKlasa();

			posaljiResponse(transferKlasa12);

			break;
		case Operacije.VRATI_SOBU:

			List<TipSoba> listSoba = KontrolerPL.getInstanca().vratiSobu();
			TransferKlasa transferKlasa13 = new TransferKlasa();
			transferKlasa13.setServer_object_response(listSoba);
			posaljiResponse(transferKlasa13);

			break;
		case Operacije.SACUVAJ_SOBUU:
			Soba sobaa = (Soba) transferKlasa.getKlijent_object_request();
			KontrolerPL.getInstanca().upisiSobu(sobaa);
			TransferKlasa transferKlasa14 = new TransferKlasa();

			posaljiResponse(transferKlasa14);

			break;
		case Operacije.SACUVAJ_USLUGE:
			Usluge usluge = (Usluge) transferKlasa.getKlijent_object_request();
			KontrolerPL.getInstanca().upisiUsluge(usluge);
			TransferKlasa transferKlasa15 = new TransferKlasa();

			posaljiResponse(transferKlasa15);

			break;
		case Operacije.SACUVAJ_SLIKE:
			Photo photo = (Photo) transferKlasa.getKlijent_object_request();
			KontrolerPL.getInstanca().sacuvajSlike(photo);
			TransferKlasa transferKlasa16 = new TransferKlasa();

			posaljiResponse(transferKlasa16);

			break;
		case Operacije.VRATI_SLIKU:
			Hotel hotel21 = (Hotel) transferKlasa.getKlijent_object_request();
			List<Hotel> list2 = KontrolerPL.getInstanca().vratiSliku(hotel21);

			TransferKlasa transferKlasa17 = new TransferKlasa();
			transferKlasa17.setServer_object_response(list2);
			posaljiResponse(transferKlasa17);

			break;
		case Operacije.VRATI_SVE_HOTELE:
			List<Hotel> list22 = KontrolerPL.getInstanca().vratiSveHotele();
			TransferKlasa tc18 = new TransferKlasa();

			tc18.setServer_object_response(list22);
			posaljiResponse(tc18);
			break;
		
		case Operacije.HOTEL_ADRESA:

			Adresa adresaa = (Adresa) transferKlasa.getKlijent_object_request();
			List<Adresa> listAdresaa = KontrolerPL.getInstanca().adresuHotela(adresaa);
			TransferKlasa tc20 = new TransferKlasa();
			tc20.setServer_object_response(listAdresaa);
			posaljiResponse(tc20);

			break;
		case Operacije.GRAD_HOTEL:

			Grad gradd = (Grad) transferKlasa.getKlijent_object_request();
			List<Grad> listGradd = KontrolerPL.getInstanca().gradHotela(gradd);
			TransferKlasa tc21 = new TransferKlasa();
			tc21.setServer_object_response(listGradd);
			posaljiResponse(tc21);

			break;
		case Operacije.DRZAVA_HOTEL:

			Drzava drzavaHotel = (Drzava) transferKlasa.getKlijent_object_request();
			List<Drzava> listDrzava = KontrolerPL.getInstanca().drzavaHotela(drzavaHotel);
			TransferKlasa tc22 = new TransferKlasa();
			tc22.setServer_object_response(listDrzava);
			posaljiResponse(tc22);

			break;
		
		
	
		
		
		
		case Operacije.HOTEL_PRETRAGA_FULL:
			HotelPretragaVrednosti pretragaVrednosti = (HotelPretragaVrednosti) transferKlasa
					.getKlijent_object_request();
			List<Hotel> pretragaList = KontrolerPL.getInstanca().pretragaFull(pretragaVrednosti);
			TransferKlasa tc33 = new TransferKlasa();
			tc33.setServer_object_response(pretragaList);
			posaljiResponse(tc33);

			break;

		case Operacije.SACUVAJ_REZERVACIJU:
			Rezervacije rezervacija = (Rezervacije) transferKlasa.getKlijent_object_request();
			KontrolerPL.getInstanca().sacuvajRezervaciju(rezervacija);
			TransferKlasa transferKlasa38 = new TransferKlasa();
			
			transferKlasa.setServer_object_response(rezervacija);
			posaljiResponse(transferKlasa38);
			break;

		case Operacije.SACUVAJ_SLIKE_SOBA:
			SobaSlike ss = (SobaSlike) transferKlasa.getKlijent_object_request();
			KontrolerPL.getInstanca().sacuvajSlikeSoba(ss);
			TransferKlasa transferKlasa34 = new TransferKlasa();
			posaljiResponse(transferKlasa34);

			break;
		case Operacije.VRATI_POSLEDNJU_SOBU:
			List<Soba> ss2 = KontrolerPL.getInstanca().vratiPoslednjuSobu();
			TransferKlasa transferKlasa35 = new TransferKlasa();
			transferKlasa35.setServer_object_response(ss2);
			posaljiResponse(transferKlasa35);

			break;
		case Operacije.VRATI_FULL_SOBE:
			Soba sobaaa = (Soba) transferKlasa.getKlijent_object_request();
			List<Soba> pretragaSobaList = KontrolerPL.getInstanca().pretragaFullSobe(sobaaa);
			TransferKlasa tc36 = new TransferKlasa();
			tc36.setServer_object_response(pretragaSobaList);
			posaljiResponse(tc36);

			break;

		case Operacije.VRATI_REZERVACIJE:
			List<Rezervacije> listaRezervacija = KontrolerPL.getInstanca().vratiRezervacije();
			TransferKlasa transferKlasa37 = new TransferKlasa();
			transferKlasa37.setServer_object_response(listaRezervacija);
			transferKlasa37.setPoruka_response("Uspesna rezervacija");
			posaljiResponse(transferKlasa37);
			break;
		case Operacije.VRATI_POSLEDNJU_REZERVACIJU:
			TransferKlasa tc38 = new TransferKlasa();
			Rezervacije rezervacije = (Rezervacije) transferKlasa.getKlijent_object_request();
			rezervacije = KontrolerPL.getInstanca().vratiPoslednjuRezervaciju();
			tc38.setServer_object_response(rezervacije);
			posaljiResponse(tc38);
			break;
		default:

			break;
		}

	}

	private void posaljiResponse(TransferKlasa transferKlasa) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(transferKlasa);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
