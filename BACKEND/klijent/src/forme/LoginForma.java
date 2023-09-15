package forme;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logika.Komunikacija;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;

public class LoginForma extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	private JTextField textPassword;
	static LoginForma frame;
	
	
	Komunikacija komunikacija;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JTextField textJMBG;
	private JTextField textIme;
	private JTextField textPrezime;
	private JTextField textEmailRegistracija;

	private JTextField textSifraRegistracija;
	private JButton btnNewButton_1;
	private JTextField textusername;

	/**
	 * Launch the application.
	 */
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginForma();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
	}

	/**
	 * Create the frame.
	 */
	public LoginForma() {
		
		
		
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				String zahtev = "ODJAVA__Odjava sa sistema";
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
		setResizable(false);
		setTitle("Prvi korak do karata");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 443, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setSize(500,700);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.PINK);
		
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(160, 51, 196, 13);
		contentPane.add(lblNewLabel);
		
		textUsername = new JTextField();
		textUsername.setBounds(160, 74, 196, 19);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Lozinka");
		lblNewLabel_1.setBounds(160, 103, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		textPassword = new JTextField();
		textPassword.setBounds(160, 126, 196, 19);
		contentPane.add(textPassword);
		textPassword.setColumns(10);
		
		JButton btnNewButton = new JButton("Prijavite se");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textUsername.getText();
				String sifra = textPassword.getText();
				
				String zahtev = "LOGIN__"+username+":::"+sifra;
				
				try {
					komunikacija.posaljiZahtev(zahtev);
					String odgovor = komunikacija.primiOdgovor();
					
					String[] niz = odgovor.split("__");
					String uspesno = niz[0];
					String poruka = niz[1];
					
					if(uspesno.equals("OK")) {
						JOptionPane.showMessageDialog(null, poruka);
						GlavnaForma gf = new GlavnaForma(komunikacija, username);
						gf.pokreniOsvezivanje();
						gf.setVisible(true);
						frame.setVisible(false);
					}else {
						JOptionPane.showMessageDialog(null, poruka);
					}
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(186, 155, 137, 19);
		contentPane.add(btnNewButton);
		
		lblNewLabel_2 = new JLabel("Vec imate nalog?");
		lblNewLabel_2.setBounds(212, 10, 105, 13);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("JMBG");
		lblNewLabel_3.setBounds(160, 225, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Ime");
		lblNewLabel_4.setBounds(160, 259, 175, 13);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Prezime");
		lblNewLabel_5.setBounds(156, 295, 45, 13);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Email");
		lblNewLabel_6.setBounds(160, 336, 45, 13);
		contentPane.add(lblNewLabel_6);
		
		
		
		lblNewLabel_7 = new JLabel("Sifra");
		lblNewLabel_7.setBounds(160, 424, 69, 13);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Username");
		lblNewLabel_8.setBounds(156, 388, 113, 13);
		contentPane.add(lblNewLabel_8);
		
		textJMBG = new JTextField();
		textJMBG.setBounds(156, 236, 200, 19);
		contentPane.add(textJMBG);
		textJMBG.setColumns(10);
		
		textIme = new JTextField();
		textIme.setBounds(156, 273, 196, 19);
		contentPane.add(textIme);
		textIme.setColumns(10);
		
		textPrezime = new JTextField();
		textPrezime.setBounds(156, 318, 196, 19);
		contentPane.add(textPrezime);
		textPrezime.setColumns(10);
		
		textEmailRegistracija = new JTextField();
		textEmailRegistracija.setBounds(160, 359, 196, 19);
		contentPane.add(textEmailRegistracija);
		textEmailRegistracija.setColumns(10);
		
		textSifraRegistracija = new JTextField();
		textSifraRegistracija.setBounds(160, 447, 196, 19);
		contentPane.add(textSifraRegistracija);
		textSifraRegistracija.setColumns(10);
		
		textusername = new JTextField();
		textusername.setBounds(160, 405, 200, 19);
		contentPane.add(textusername);
		textusername.setColumns(10);
		
		btnNewButton_1 = new JButton("Registrujte se");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String jmbg = textJMBG.getText();
				if(!(textJMBG.getText().length()==13)) {
					JOptionPane.showMessageDialog(null, "Neispravan JMBG");
					return;
				}
				
				String ime = textIme.getText();
				String prezime = textPrezime.getText();
				String email = textEmailRegistracija.getText();
				if(!(textEmailRegistracija.getText().contains("@"))){
					JOptionPane.showMessageDialog(null, "Neispravan Email");
					return;
				}
				String username = textusername.getText();
				String sifra = textSifraRegistracija.getText();
				
				String zahtev = "REGISTRACIJA__"+jmbg+":::"+ime+":::"+prezime+":::"+email+":::"+username+":::"+sifra;
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
		btnNewButton_1.setBounds(188, 476, 147, 21);
		contentPane.add(btnNewButton_1);
		
		
		
		
		
		JLabel lblNewLabel_9 = new JLabel("Registrujte se ukoliko nemate nalog:");
		lblNewLabel_9.setBounds(10, 202, 249, 13);
		contentPane.add(lblNewLabel_9);
		
		
		komunikacija = new Komunikacija();
		try {
			komunikacija.poveziSe();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Neuspesno povezivanje.");
			System.exit(0);

			e.printStackTrace();
		}
	}
}