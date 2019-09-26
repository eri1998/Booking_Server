package com.comtrade.broker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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
import com.comtrade.domen.rezervacije.Rezervacije;
import com.comtrade.konekcija.Konekcija;
import com.mysql.cj.util.StringUtils;

public class Broker implements IBroker {

	@Override
	public void sacuvaj(OpstiDomen opstiDomen) {
		String sql = " insert into " + opstiDomen.nazivTabele() + "" + opstiDomen.nazivKolona() + ""
				+ opstiDomen.vrednostKolona();
		;
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			preparedStatement = opstiDomen.vrati(preparedStatement);
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public User login(User user) throws SQLException {
		String sql = "select * from" + user.nazivTabele() + " where username = ? and password = ? and email =  ?";
		User user1 = new User();
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);

			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getEmail());

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				user1.setUsername(resultSet.getString("username"));
				user1.setEmail(resultSet.getString("email"));
				user1.setPassword(resultSet.getString("password"));
				user1.setStatus(resultSet.getString("status"));
				user1.setId_user(resultSet.getInt("id_user"));
				user1.setFullName(resultSet.getString("full_name"));

			}
		} catch (SQLException e) {
			throw e;
		}

		return user1;
	}

	@Override
	public List<OpstiDomen> list(OpstiDomen opstiDomen) {
		String sql = "select * from" + " " + opstiDomen.nazivTabele() + " " + "ORDER BY " + " " + opstiDomen.getID()
				+ " " + "DESC LIMIT 1";
		List<OpstiDomen> list = null;
		try {

			PreparedStatement preparedStatemnet = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			ResultSet resultSet = preparedStatemnet.executeQuery();
			list = opstiDomen.vratiSelect(resultSet);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	

	@Override
	public List<Hotel> vratiSlike(Hotel hotel) {
		String sql = "SELECT slike.urlSlike from slike where slike.idHotel=" + hotel.getId_hotel();
		List<Hotel> list = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Hotel hotel2 = new Hotel();
				hotel2.getUrlSlika().add(resultSet.getString("urlSlike"));
				list.add(hotel2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Adresa> vratiAdresuHotela(Adresa adresa) {

		String sql = "SELECT * FROM hotel INNER JOIN adresa on hotel.id_adresa = adresa.id_adresa where hotel.naziv LIKE '"
				+ adresa.getKljucnaRec() + "%'";
		List<Adresa> list = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Adresa adresa1 = new Adresa();
				adresa1.setUlica(resultSet.getString("ulica"));
				adresa1.setBroj(resultSet.getString("broj"));
				list.add(adresa1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Grad> vratiGradHotela(Grad grad) {

		String sql = "SELECT * FROM adresa INNER JOIN grad ON adresa.id_grad = grad.id_grad where adresa.ulica ='"
				+ grad.getUlica() + "' and adresa.broj = '" + grad.getBroj() + "'";
		List<Grad> list = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Grad grad1 = new Grad();
				grad1.setNaziv(resultSet.getString("nazivGrada"));
				list.add(grad1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Drzava> vratiDrzavuHotela(Drzava drzava) {
		List<Drzava> list = new ArrayList<>();
		String sql = "SELECT * FROM drzava INNER JOIN grad ON drzava.id_drzava = grad.id_drzava where grad.nazivGrada = '"
				+ drzava.getNazivGrada() + "'";
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Drzava drzava1 = new Drzava();
				drzava1.setNaziv(resultSet.getString("nazivDrzave"));
				list.add(drzava1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	

	

	

	



	@Override
	public List<Hotel> pretragaFull(HotelPretragaVrednosti pretragaVrednosti) {
		String sql = "SELECT * FROM Hotel h " + "INNER JOIN adresa a on h.id_adresa = a.id_adresa "
				+ "INNER JOIN grad g on a.id_grad = g.id_grad " + "INNER JOIN drzava d on d.id_drzava = g.id_grad "
				+ "INNER JOIN usluge u on u.id_hotel = h.id_hotel " + "INNER JOIN slike s on s.idHotel = h.id_hotel "
				+ "WHERE 1=1 ";

		if (!StringUtils.isEmptyOrWhitespaceOnly(pretragaVrednosti.getHotela())) {
			sql += " AND h.naziv LIKE '" + pretragaVrednosti.getHotela() + "%'";
		}

		if (!StringUtils.isEmptyOrWhitespaceOnly(pretragaVrednosti.getDrzava())) {
			sql += " AND d.nazivDrzave LIKE '" + pretragaVrednosti.getDrzava() + "%'";
		}

		if (!StringUtils.isEmptyOrWhitespaceOnly(pretragaVrednosti.getGrad())) {
			sql += " AND g.nazivGrada LIKE '" + pretragaVrednosti.getGrad() + "%'";
		}
		

		List<Integer> brojZvezdica = pretragaVrednosti.getBrojZvezdica();

		if (brojZvezdica.size() > 0) {
			int brojZvezdicaSize = pretragaVrednosti.getBrojZvezdica().size();
			String brojZvezdicaValue = "";

			for (int i = 0; i < brojZvezdicaSize; i++) {
				brojZvezdicaValue += pretragaVrednosti.getBrojZvezdica().get(i);

				if (i < brojZvezdicaSize - 1) {
					brojZvezdicaValue += ", ";
				}
			}

			sql += " AND h.brojZvezdica IN (" + brojZvezdicaValue + ")";
		}
if(pretragaVrednosti.getBazen() == 1 ) {
	sql += " AND u.swimming_pool = 1 ";
}
if(pretragaVrednosti.getAirportShuttle()==1) {
	sql+= " AND u,airport_shuttle = 1 "; 
}
if(pretragaVrednosti.getDorucak() == 1){
	sql+= " AND u.dorucak = 1 ";
}
if(pretragaVrednosti.getBar() == 1) {
	sql+= " AND u.bar = 1 ";
}
if(pretragaVrednosti.getInternet() == 1) {
	sql+= " AND u.internet = Yes,free ";
}
if(pretragaVrednosti.getFitness() == 1) {
	sql+= " AND u.fitnessCenter = 1 ";
}
if(pretragaVrednosti.getNaPlazi() == 1) {
	sql+= " AND u.beach = 1 ";
}
if(pretragaVrednosti.getRestoran() == 1) {
	sql+= " AND u.restaurant = 1 ";
}
if(pretragaVrednosti.getSpa() == 1) {
	sql+= " AND u.spa = 1 ";
}
if(pretragaVrednosti.getParking() == 1) {
	sql+= " AND u.parking = Yes,free ";
}
		List<Hotel> list = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			Hotel hotel1 = null;
			while (resultSet.next()) {
				int idResults = resultSet.getInt(1);
				if (list.isEmpty()) {
					hotel1 = new Hotel();
					hotel1.setBrojZvezdica(resultSet.getInt("brojZvezdica"));
					hotel1.setNaziv(resultSet.getString("naziv"));
					hotel1.setId_hotel(resultSet.getInt("id_hotel"));
					hotel1.setNazivGrada(resultSet.getString("nazivGrada"));
					hotel1.setNazivDrzave(resultSet.getString("nazivDrzave"));
					hotel1.setUlica(resultSet.getString("ulica"));
					hotel1.setBroj(resultSet.getString("broj"));
					hotel1.getUrlSlika().add(resultSet.getString("urlSlike"));
					list.add(hotel1);

				} else if (idResults == hotel1.getId_hotel()) {
					hotel1.getUrlSlika().add(resultSet.getString("urlSlike"));
				} else if (idResults != hotel1.getId_hotel()) {
					hotel1 = new Hotel();
					hotel1.setBrojZvezdica(resultSet.getInt("brojZvezdica"));
					hotel1.setNaziv(resultSet.getString("naziv"));
					hotel1.setId_hotel(resultSet.getInt("id_hotel"));
					hotel1.setNazivGrada(resultSet.getString("nazivGrada"));
					hotel1.setNazivDrzave(resultSet.getString("nazivDrzave"));
					hotel1.setUlica(resultSet.getString("ulica"));
					hotel1.setBroj(resultSet.getString("broj"));
					hotel1.getUrlSlika().add(resultSet.getString("urlSlike"));
					list.add(hotel1);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
	// private void izvuciPodatkeIzResultSeta(Hotel hotel1, ResultSet resultSet,
	// List<Hotel> list) throws SQLException {

	@Override
	public List<Soba> pretragaSobeFull(List<Soba> list) {

		String sql = "SELECT * FROM soba left join slikesoba on soba.idSoba=slikesoba.idSobe inner join tipsoba on soba.idTipSobe=tipsoba.idTipSoba where tipsoba.id_hotel="
				+ list.get(0).getIdHotela();
		List<Soba> list2 = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			Soba soba1 = null;
			while (resultSet.next()) {
				int idResults = resultSet.getInt(1);
				if (list2.isEmpty()) {
					soba1 = new Soba();
					soba1.setId_soba(resultSet.getInt("idSoba"));
					soba1.setNazivSobe(resultSet.getString("nazivSobe"));
					soba1.setBrojSoba(resultSet.getInt("brojSoba"));
					soba1.setPovrsina(resultSet.getString("PovrsinaSobe"));
					soba1.setCena(resultSet.getDouble("cenaNoci"));
					soba1.setSprat(resultSet.getInt("sprat"));
					soba1.setBrSobe(resultSet.getInt("brSobe"));
					soba1.getUrlSlika().add(resultSet.getString("slikaUrl"));
					soba1.setIdHotela(resultSet.getInt("id_hotel"));
					list2.add(soba1);

				} else if (idResults == soba1.getId_soba()) {
					soba1.getUrlSlika().add(resultSet.getString("slikaUrl"));
					soba1.setBrSobe(resultSet.getInt("brSobe"));
				} else {
					soba1 = new Soba();
					soba1.setId_soba(resultSet.getInt("idSoba"));
					soba1.setNazivSobe(resultSet.getString("nazivSobe"));
					soba1.setBrojSoba(resultSet.getInt("brojSoba"));
					soba1.setPovrsina(resultSet.getString("PovrsinaSobe"));
					soba1.setCena(resultSet.getDouble("cenaNoci"));
					soba1.setSprat(resultSet.getInt("sprat"));
					soba1.setBrSobe(resultSet.getInt("brSobe"));
					soba1.getUrlSlika().add(resultSet.getString("slikaUrl"));
					list2.add(soba1);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list2;

	}

	public List<OpstiDomen> vratiListuRezervacijaVlasnik(Rezervacije rezervacije) {
		String sql = "select * from " + rezervacije.nazivTabele() + " ORDER BY " + rezervacije.datumOdlaska() + " ASC";
		List<OpstiDomen> list = null;
		try {
			PreparedStatement preparedStatement = Konekcija.getInstanca().getConnection().prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			list = rezervacije.vratiSelect(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

//}
}
