package logika;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Komunikacija {
	
	Socket klijent;
	PrintStream izlaz = null;
	BufferedReader ulaz = null;

	
	public void poveziSe() throws UnknownHostException, IOException {
		klijent = new Socket("localhost", 9999);

	}
	
	public void posaljiZahtev(String zahtev) throws IOException {
		if(izlaz == null) {
			izlaz = new PrintStream(klijent.getOutputStream());
		}
		
		izlaz.println(zahtev);
		izlaz.flush();

	}
	
	public String primiOdgovor() throws IOException {
		
		ulaz = new BufferedReader(new InputStreamReader(klijent.getInputStream()));
		
		return ulaz.readLine();
	}
}
