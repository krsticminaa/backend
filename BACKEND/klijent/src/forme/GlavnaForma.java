package forme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logika.Komunikacija;
import logika.Osvezi;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GlavnaForma extends JFrame {

	private JPanel contentPane;
	private JTextField textBrojKarata;
	private JTextField textBrojPreostalihKarata;
	private JTextField textImeKorisnika;
	
	String email;
	Komunikacija komunikacija;

	public void setEmail(String email) {
		this.email = email;
		textImeKorisnika.setText(email);
	}

	/**
	 * Create the frame.
	 */
	public GlavnaForma(Komunikacija komunikacija, String email) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				String zahtev = "ODJAVA__IZLAZ";
				try {
					komunikacija.posaljiZahtev(zahtev);
					String odgovor = komunikacija.primiOdgovor();
					
					String[] niz = odgovor.split("__");
					String uspesno = niz[0];
					String poruka = niz[1];
					
					if(uspesno.equals("OK")) {
						JOptionPane.showMessageDialog(null, poruka);
					}else {
						JOptionPane.showMessageDialog(null, poruka);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		this.komunikacija = komunikacija;
		this.email = email;
		
		setTitle("Rezervacija karata za Svetsko prvenstvo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 322);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.setSize(700,500);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.CYAN);
		
		JLabel lblNewLabel = new JLabel("Unesite broj karata koje zelite da rezervisete:");
		lblNewLabel.setBounds(10, 195, 240, 13);
		contentPane.add(lblNewLabel);
		
		textBrojKarata = new JTextField();
		textBrojKarata.setBounds(240, 192, 153, 19);
		contentPane.add(textBrojKarata);
		textBrojKarata.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Vrsta karate:");
		lblNewLabel_1.setBounds(65, 145, 77, 13);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"VIP", "Standardna"}));
		comboBox.setBounds(240, 141, 153, 21);
		contentPane.add(comboBox);
		
		JButton btnPoruciKartu = new JButton("Porucite karte");
		btnPoruciKartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//uradi validaciju da ne moze vise od 4 ako je standardna ako je vip onda 1
				int brojKarata = Integer.parseInt(textBrojKarata.getText());
				String tip = (String)comboBox.getSelectedItem();
				String zahtev = "REZERVISI__"+brojKarata+":::"+tip+":::"+email;
				try {
					komunikacija.posaljiZahtev(zahtev);
					String odgovor = komunikacija.primiOdgovor();
					
					String[] niz = odgovor.split("__");
					String uspesno = niz[0];
					String poruka = niz[1];
					
					if(uspesno.equals("OK")) {
						
						JOptionPane.showMessageDialog(null, poruka);
						prikazi();
					}else {
						JOptionPane.showMessageDialog(null, poruka);
					}

				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		btnPoruciKartu.setBounds(190, 239, 240, 34);
		contentPane.add(btnPoruciKartu);
		
		JLabel lblNewLabel_2 = new JLabel("Raspolozivot:");
		lblNewLabel_2.setBounds(370, 80, 108, 13);
		contentPane.add(lblNewLabel_2);
		
		textBrojPreostalihKarata = new JTextField();
		textBrojPreostalihKarata.setEditable(false);
		textBrojPreostalihKarata.setBounds(488, 77, 130, 19);
		contentPane.add(textBrojPreostalihKarata);
		textBrojPreostalihKarata.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Ime korisnika");
		lblNewLabel_3.setBounds(10, 80, 77, 13);
		contentPane.add(lblNewLabel_3);
		
		textImeKorisnika = new JTextField();
		textImeKorisnika.setEditable(false);
		textImeKorisnika.setBounds(97, 77, 153, 19);
		contentPane.add(textImeKorisnika);
		textImeKorisnika.setColumns(10);
		
		textImeKorisnika.setText(email);
		
		JButton btnNewButton = new JButton("Obrisite rezervacije");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String zahtev = "OBRISI__"+"zabrisanje"+":::"+email;
				try {
					komunikacija.posaljiZahtev(zahtev);
					String odgovor = komunikacija.primiOdgovor();
					
					String[] niz = odgovor.split("__");
					String uspesno = niz[0];
					String poruka = niz[1];
					
					if(uspesno.equals("OK")) {
						
						JOptionPane.showMessageDialog(null, poruka);
					}else {
						JOptionPane.showMessageDialog(null, poruka);
					}

				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(190, 356, 240, 34);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Kliknite da biste pogledali rezervaciju");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prikazi();
			}
		});
		btnNewButton_1.setBounds(189, 297, 241, 34);
		contentPane.add(btnNewButton_1);
		

	}
	
	public void pokreniOsvezivanje() {
		Osvezi o = new Osvezi(this);
		o.start();
	}
	
	
	public void osveziBrojKarata() {
		String zahtev = "VRATI_BROJ_KARATA__poTipovima";
		try {
			komunikacija.posaljiZahtev(zahtev);
			String odgovor = komunikacija.primiOdgovor();
			
			String[] niz = odgovor.split("__");
			String uspesno = niz[0];
			String poruka = niz[1];
			
			if(uspesno.equals("OK")) {
				
				textBrojPreostalihKarata.setText(poruka);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void prikazi() {
		String zahtev = "PRIKAZI__"+"zaprikaz"+":::"+email;
		try {
			komunikacija.posaljiZahtev(zahtev);
			String odgovor = komunikacija.primiOdgovor();
			
			String[] niz = odgovor.split("__");
			String uspesno = niz[0];
			String poruka = niz[1];
			
			if(uspesno.equals("OK")) {
				String[] podaci = poruka.split(":::");
				
				String podaciZaFAjl = "Ime: " + podaci[0] + System.lineSeparator();
				podaciZaFAjl += "Prezime: " + podaci[1] + System.lineSeparator();
				podaciZaFAjl += "Rezervisano Obicnih karata: " + podaci[2] + System.lineSeparator();
				podaciZaFAjl += "Rezervisano VIP karata: " + podaci[3] + System.lineSeparator();
				
				PrintWriter writer = new PrintWriter(email+".txt", "UTF-8");
				writer.println(podaciZaFAjl);
				
				writer.close();
				JOptionPane.showMessageDialog(null, "Vasu rezervacija se nalazi u sačuvanom tekstualnom fajlu");
			}else {
				JOptionPane.showMessageDialog(null, poruka);
			}

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
}
