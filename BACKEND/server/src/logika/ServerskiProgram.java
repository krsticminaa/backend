package logika;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerskiProgram {

	public void pokreniServer() {
		try {
			ServerSocket serverskiSoket = new ServerSocket(9999);
			System.out.println("Server je pokrenut");
			
			while(true) {
				System.out.println("Ceka se novi klijent");
				Socket klijent = serverskiSoket.accept();
				System.out.println("Klijent se povezao");
				//uradi nesto sa klijentom
				
				ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(klijent);
				okz.start();
			}
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}