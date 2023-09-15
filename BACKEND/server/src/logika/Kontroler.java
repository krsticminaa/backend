package logika;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;

import db.Baza;

public class Kontroler {

	private static Kontroler kontroler;
	Baza db;
	LinkedList<String> listaUlogovanihKorisnika;
	
	private Kontroler() {
		db = new Baza();
		listaUlogovanihKorisnika = new LinkedList<String>();
	}
	
	public boolean daLiJeKorisnikUlogovan(String email) {
		return listaUlogovanihKorisnika.contains(email);
	}
	
	public void dodajKorisnika(String email) {
		listaUlogovanihKorisnika.add(email);
	}
	
	public void obrisiKorisnika(String email) {
		listaUlogovanihKorisnika.remove(email);
	}
	
	
	public static Kontroler getKontroler() {
		if(kontroler == null)
			kontroler = new Kontroler();
		
		return kontroler;
	}



	public boolean login(String email, String sifra) {
		
		db.otvoriKonekciju();
		boolean loginUspesan = db.ulogujKorisnika(email, sifra);
		
		db.zatvoriKonekciju();
		
		return loginUspesan;
	}

	public boolean registruj(String jmbgRegistracija, String imeRegistracija, String prezimeRegistracija,
			String emailRegistracija,String usernameRegistracija, String sifraRegistracija) {
		
		boolean uspesno = false;
		
		db.otvoriKonekciju();
		try {
			db.registruj(jmbgRegistracija, imeRegistracija, prezimeRegistracija, emailRegistracija,usernameRegistracija, sifraRegistracija);
			uspesno = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.zatvoriKonekciju();
		
		
		return uspesno;
	}

	public boolean daLiKorisnikSaUsernameomPostoji(String usernameRegistracija){
		db.otvoriKonekciju();
		boolean postoji = db.proveri(usernameRegistracija);
		
		db.zatvoriKonekciju();
		
		return postoji;
	}

	public int vratiBrojStandardnihKarata(String tipKarte) {
		db.otvoriKonekciju();
		int broj = db.vratiBrojStandardnihKarata(tipKarte);
		
		db.zatvoriKonekciju();
		
		return broj;
	}

	public String vratiJMBG(String emailRezervacija) {
		db.otvoriKonekciju();
		String jmbg = db.vratiJmbg(emailRezervacija);
		
		db.zatvoriKonekciju();
		
		return jmbg;
	}

	public boolean rezervisi(String brojKarata, String tipKarte, String jmbg) {
		boolean uspesno = false;
		
		db.otvoriKonekciju();
		try {
			db.rezervisi(brojKarata, tipKarte, jmbg);
			uspesno = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.zatvoriKonekciju();
		
		
		return uspesno;
	}

	public int vratiBrojKarataZaKorisnika(String jmbg) {
		db.otvoriKonekciju();
		int broj = db.vratiBrojStandardnihKarataPoKorisnika(jmbg);
		
		db.zatvoriKonekciju();
		
		return broj;
	}

	public boolean obrisi(String jmbgBrisanje) {
		boolean uspesno = false;
		
		db.otvoriKonekciju();
		try {
			db.obrisi(jmbgBrisanje);
			uspesno = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.zatvoriKonekciju();
		
		
		return uspesno;
	}

	public String prikazi(String jmbgPrikaz) {
		db.otvoriKonekciju();
		String odgovor = db.prikaziPodatke(jmbgPrikaz);
		
		db.zatvoriKonekciju();
		
		return odgovor;
	}
}