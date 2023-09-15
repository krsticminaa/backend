package domen;

public class Korisnik {

	private String JMBG;
	private String ime;
	private String prezime;
	private String email;
	private String password;
	
	public Korisnik() {
		// TODO Auto-generated constructor stub
	}

	public Korisnik(String JMBG, String ime, String prezime, String email, String password) {
		super();
		this.JMBG = JMBG;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.password = password;
	}

	public String getJMBG() {
		return JMBG;
	}

	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

