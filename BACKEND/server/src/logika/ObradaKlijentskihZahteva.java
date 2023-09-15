package logika;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ObradaKlijentskihZahteva extends Thread{

	Socket soket;
	PrintStream izlaz = null;
	BufferedReader ulaz = null;
	boolean kraj = false;
	public final int BROJ_STANDARDNIIH = 15;
	public final int BROJ_VIP = 5;
	String emailKorisnikaUlogovanog = null;

	public ObradaKlijentskihZahteva(Socket soket) {
		super();
		this.soket = soket;
	}
	
	@Override
	public void run() {
		try {
			while(!kraj) {
				String zahtev = primiZahtev();
				
				String[] niz = zahtev.split("__");
				
				String odgovor = "";
				
				String operacija = niz[0];
				String podaci = niz[1];
				
				switch (operacija) {
				case "LOGIN":
					String[] podaciLogin = podaci.split(":::");
					String email = podaciLogin[0];
					String sifra = podaciLogin[1];
					
					if(Kontroler.getKontroler().daLiJeKorisnikUlogovan(email)) {
						odgovor = "GRESKA__Korisnik je vec ulogovan!!!";
						break;
					}
					
					boolean uspesnoUlogovan = Kontroler.getKontroler().login(email, sifra);
					
					if(uspesnoUlogovan) {
						this.emailKorisnikaUlogovanog = email;
						odgovor = "OK__Uspeno logovanje!!!";
						Kontroler.getKontroler().dodajKorisnika(email);
					}else {
						odgovor = "GRESKA__Neuspeno logovanje!!!";
					}
					
					break;
				case "REGISTRACIJA":
					String[] podaciRegistracija = podaci.split(":::");
					String jmbgRegistracija = podaciRegistracija[0];
					String imeRegistracija = podaciRegistracija[1];
					String prezimeRegistracija = podaciRegistracija[2];
					String emailRegistracija = podaciRegistracija[3];
					String usernameRegistracija = podaciRegistracija[4];
					String sifraRegistracija = podaciRegistracija[5];
					
					if(Kontroler.getKontroler().daLiKorisnikSaUsernameomPostoji(usernameRegistracija)) {
						odgovor = "GRESKA__Korisnik sa tim usernameom postoji";
						break;
					}
					
					boolean uspesnoRegistraovan = Kontroler.getKontroler().registruj(jmbgRegistracija, imeRegistracija, prezimeRegistracija, emailRegistracija, usernameRegistracija,sifraRegistracija);
					
					if(uspesnoRegistraovan) {
						odgovor = "OK__Uspena registracija!!!";
					}else {
						odgovor = "GRESKA__Neuspena registracija!!!";
					}
					
					break;
					
				case "VRATI_BROJ_KARATA":
					int brojObicnihKarata = Kontroler.getKontroler().vratiBrojStandardnihKarata("Standardna");
					int brojVIPKarata = Kontroler.getKontroler().vratiBrojStandardnihKarata("VIP");
					
					int obicnih = BROJ_STANDARDNIIH - brojObicnihKarata;
					int vip = BROJ_VIP - brojVIPKarata;
					
					odgovor = "OK__Obicnih: "+obicnih +" VIP: " + vip;
					break;
				case "REZERVISI":
					String[] podaciRezervacija = podaci.split(":::");
					String brojKarata = podaciRezervacija[0];
					String tipKarte = podaciRezervacija[1];
					String emailRezervacija = podaciRezervacija[2];
					System.out.println(emailRezervacija);
					
					String jmbg = Kontroler.getKontroler().vratiJMBG(emailRezervacija);
					
					int brojS = Kontroler.getKontroler().vratiBrojKarataZaKorisnika(jmbg);
					if(4 - brojS - Integer.parseInt(brojKarata) < 0) {
						odgovor = "GRESKA__Prekoracili ste dozvoljen broj rezervacija";
						break;
					}
					
					if(tipKarte.equals("VIP")) {
						int brojVIPKarataUkupno = Kontroler.getKontroler().vratiBrojStandardnihKarata("VIP");

						if(Integer.parseInt(brojKarata) > BROJ_VIP - brojVIPKarataUkupno) {
							odgovor = "GRESKA__Prekoracili ste dozvoljen broj rezervacija za VIP";
							break;
						}
					}
					
					
					boolean uspesnoRezervisano = Kontroler.getKontroler().rezervisi(brojKarata, tipKarte, jmbg);
					
					if(uspesnoRezervisano) {
						odgovor = "OK__Uspeno rezervisano!!!";
					}else {
						odgovor = "GRESKA__Neuspena rezervacija!!!";
					}
					
					break;
					
				case "OBRISI":
					String[] podaciBrisanje = podaci.split(":::");
					String emailBrisanje = podaciBrisanje[1];
					
					String jmbgBrisanje = Kontroler.getKontroler().vratiJMBG(emailBrisanje);
					
					boolean uspesnoObrisano = Kontroler.getKontroler().obrisi(jmbgBrisanje);
					
					if(uspesnoObrisano) {
						odgovor = "OK__Uspeno obrisane rezervacje!!!";
					}else {
						odgovor = "GRESKA__Neuspeno obrisane rezervacije!!!";
					}
					
					break;
				case "PRIKAZI":
					String[] podaciPRikaz = podaci.split(":::");
					String emailPrikaz = podaciPRikaz[1];
					
					String jmbgPrikaz = Kontroler.getKontroler().vratiJMBG(emailPrikaz);
					
					odgovor = Kontroler.getKontroler().prikazi(jmbgPrikaz);
					
					break;
				case "ODJAVA":
					kraj = true;
					if(emailKorisnikaUlogovanog != null)
						Kontroler.getKontroler().obrisiKorisnika(this.emailKorisnikaUlogovanog);
					
					odgovor = "OK__Uspesno odjavljen sa sistema";
					
					break;

				default:
					break;
				}
				
				System.out.println(operacija + odgovor);
				posaljiODgovor(odgovor);
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Klijent poslao zahtev");
		//primimo zahtev od klijenta
		
		
		//obradi zahtev
		
		
		//posaljemo odgovor
		
		
		//LOGIN__email:::sofijaSlikeInsta
		//REGISTRACIJA__ime:::prezime:::jmbg:::email:::password
		
		
		
		//OK ILI GRESKA__poruka
	}
	
	
	public void posaljiODgovor(String zahtev) throws IOException {
		if(izlaz == null) {
			izlaz = new PrintStream(soket.getOutputStream());
		}
		
		izlaz.println(zahtev);
		izlaz.flush();

	}
	
	public String primiZahtev() throws IOException {
		
		ulaz = new BufferedReader(new InputStreamReader(soket.getInputStream()));
		
		return ulaz.readLine();
	}
}
