package logika;

import forme.GlavnaForma;

public class Osvezi extends Thread{

	GlavnaForma gf;

	public Osvezi(GlavnaForma gf) {
		super();
		this.gf = gf;
	};
	
	
	@Override
	public void run() {

		while(true) {
			gf.osveziBrojKarata();
			System.out.println("Osvezeno");
			try {
				this.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
