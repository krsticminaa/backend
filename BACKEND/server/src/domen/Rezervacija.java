package domen;

public class Rezervacija {

	private int id;
	private Korisnik korisnik;
	private int brojKarata;
	private String tipKarte;
	
	public Rezervacija() {
		// TODO Auto-generated constructor stub
	}

	public Rezervacija(int id, Korisnik korisnik, int brojKarata, String tipKarte) {
		super();
		this.id = id;
		this.korisnik = korisnik;
		this.brojKarata = brojKarata;
		this.tipKarte = tipKarte;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public int getBrojKarata() {
		return brojKarata;
	}

	public void setBrojKarata(int brojKarata) {
		this.brojKarata = brojKarata;
	}

	public String getTipKarte() {
		return tipKarte;
	}

	public void setTipKarte(String tipKarte) {
		this.tipKarte = tipKarte;
	}
	
}
