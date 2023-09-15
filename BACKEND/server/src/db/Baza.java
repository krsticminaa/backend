package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Baza {

	Connection konekcija;
	
	public void ucitajDrajver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Drajver nije ucitan");
			e.printStackTrace();
		}  
	}
	
	public void otvoriKonekciju() {
		try {
			konekcija = DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/rmt","root","");
		} catch (SQLException e) {
			System.err.println("Konekcija nije uspostavljena");
			e.printStackTrace();
		} 

	}
	
	public void zatvoriKonekciju() {
		try {
			konekcija.close();
		} catch (SQLException e) {
			System.err.println("Konekcija nije zatvorena");
			e.printStackTrace();
		}
	}

	public boolean ulogujKorisnika(String username, String sifra) {
		String upit = "SELECT * FROM korisnik WHERE username='"+username+"' AND password='"+sifra+"'";
		
		try {
			Statement statement = konekcija.createStatement();
			
			ResultSet rs = statement.executeQuery(upit);
			
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public void registruj(String jmbgRegistracija, String imeRegistracija, String prezimeRegistracija,
			String emailRegistracija,String usernameRegistracija, String sifraRegistracija) throws SQLException {
		String upit = "INSERT INTO korisnik(JMBG,ime,prezime,email,username,password) VALUES (?,?,?,?,?,?)";
		
		PreparedStatement ps = konekcija.prepareStatement(upit);
		ps.setString(1, jmbgRegistracija);
		ps.setString(2, imeRegistracija);
		ps.setString(3, prezimeRegistracija);
		ps.setString(4, emailRegistracija);
		ps.setString(5, usernameRegistracija);
		ps.setString(6, sifraRegistracija);
		
		ps.executeUpdate();
		
	}

	public boolean proveri(String usernameRegistracija) {
		String upit = "SELECT * FROM korisnik WHERE username='"+ usernameRegistracija+"' ";
		
		try {
			Statement statement = konekcija.createStatement();
			
			ResultSet rs = statement.executeQuery(upit);
			
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public int vratiBrojStandardnihKarata(String tipKarte) {
		String upit = "SELECT SUM(brojKarata) as ukupno FROM rezervacija WHERE tipKarte= '"+tipKarte+"'";
		
		try {
			Statement statement = konekcija.createStatement();
			
			ResultSet rs = statement.executeQuery(upit);
			
			while(rs.next()) {
				return rs.getInt("ukupno");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	public String vratiJmbg(String emailRezervacija) {
		String upit = "SELECT JMBG FROM korisnik WHERE username='"+emailRezervacija+"' ";
		
		try {
			Statement statement = konekcija.createStatement();
			
			ResultSet rs = statement.executeQuery(upit);
			
			while(rs.next()) {
				return rs.getString("JMBG");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}

	public void rezervisi(String brojKarata, String tipKarte, String jmbg) throws SQLException {

		String upit = "INSERT INTO rezervacija(JMBG,brojKarata,tipKarte) VALUES (?,?,?)";
		
		PreparedStatement ps = konekcija.prepareStatement(upit);
		ps.setString(1, jmbg);
		ps.setInt(2, Integer.parseInt(brojKarata));
		ps.setString(3, tipKarte);
		
		ps.executeUpdate();
		
	}

	public int vratiBrojStandardnihKarataPoKorisnika(String jmbg) {
		String upit = "SELECT SUM(brojKarata) as ukupno FROM rezervacija WHERE JMBG = '"+jmbg+"'";
		
		try {
			Statement statement = konekcija.createStatement();
			
			ResultSet rs = statement.executeQuery(upit);
			
			while(rs.next()) {
				return rs.getInt("ukupno");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	public void obrisi(String jmbgBrisanje) throws SQLException {
		String upit = "DELETE FROM rezervacija WHERE JMBG = ?";
		
		PreparedStatement ps = konekcija.prepareStatement(upit);
		ps.setString(1, jmbgBrisanje);
		ps.executeUpdate();
		
	}

	public String prikaziPodatke(String jmbgPrikaz) {
		String upit = "SELECT * FROM korisnik k JOIN rezervacija r ON k.JMBG = r.JMBG WHERE k.JMBG='"+jmbgPrikaz+"' ";
		
		try {
			Statement statement = konekcija.createStatement();
			
			ResultSet rs = statement.executeQuery(upit);
			String ime ="";
			String prezime ="";
			int brojStandardnihKarata = 0;
			int brojVIPKarata = 0;

			while(rs.next()) {
				ime = rs.getString("ime");
				prezime = rs.getString("prezime");
				if(rs.getString("tipKarte").equals("VIP")) {
					brojVIPKarata += rs.getInt("brojKarata");
				}
				
				
				if(rs.getString("tipKarte").equals("Standardna")) {
					brojStandardnihKarata += rs.getInt("brojKarata");
				}
			}
			
			return "OK__"+ime+":::"+prezime+":::"+brojStandardnihKarata+":::"+brojVIPKarata;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "GRESKA__Doslo je do greske pri generisanju podataka";
	}
}