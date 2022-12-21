package viewHome;

import classiDB.Account;
import classiDB.DBBookStore;
import utility.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.*;

/**
 * Classe che rappresenta la schermata di registrazione.
 * 
 * @author Eugenia Esposito
 *
 */
public class SignUpPage {
	private JFrame fSignUp;
	private JTextField tfNome;
	private JTextField tfCognome;
	private JTextField tfUsername;
	private JTextField tfEmail;
	private JTextField tfPassword;
	private JTextField tfRipPassword;
	
	/**
	 * Costruttore della classe che inizializza tutte le componenti della schermata.
	 */
	public SignUpPage() {
		initialize();
	}
	
	private void initialize() {
		DBBookStore D = DBBookStore.getInstance();
		
		fSignUp = new JFrame();
		fSignUp.setTitle("Registrazione");
		fSignUp.setBounds(100, 100, 420, 480);
		fSignUp.getContentPane().setBackground(Utility.getNewBlu());
		fSignUp.getContentPane().setForeground(Color.WHITE);
		fSignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fSignUp.getContentPane().setLayout(null);
		
		JLabel labelImg = new JLabel("");
		labelImg.setIcon(Utility.getImgLogo());
		labelImg.setBounds(24, 10, 64, 64);
		fSignUp.getContentPane().add(labelImg);
		
		JLabel labelReg= new JLabel("REGISTRAZIONE");
		labelReg.setFont(new Font("Verdana", Font.BOLD, 16));
		labelReg.setForeground(Color.WHITE);
		labelReg.setBounds(180, 32, 150, 16);
		fSignUp.getContentPane().add(labelReg);
		
		JLabel labelNome = new JLabel("Nome*");
		labelNome.setFont(new Font("Verdana", Font.PLAIN, 13));
		labelNome.setForeground(Color.WHITE);
		labelNome.setBounds(24, 110, 100, 25);
		fSignUp.getContentPane().add(labelNome);
		
		tfNome = new JTextField();
		tfNome.setBounds(145, 110, 220, 25);
		tfNome.setFont(new Font("Verdana", Font.PLAIN, 13));
		fSignUp.getContentPane().add(tfNome);
		
		JLabel labelCognome = new JLabel("Cognome*");
		labelCognome.setFont(new Font("Verdana", Font.PLAIN, 13));
		labelCognome.setForeground(Color.WHITE);
		labelCognome.setBounds(24, 150, 100, 25);
		fSignUp.getContentPane().add(labelCognome);
		
		tfCognome = new JTextField();
		tfCognome.setBounds(145, 150, 220, 25);
		tfCognome.setFont(new Font("Verdana", Font.PLAIN, 13));
		fSignUp.getContentPane().add(tfCognome);
		
		JLabel labelEmail = new JLabel("Email*");
		labelEmail.setFont(new Font("Verdana", Font.PLAIN, 13));
		labelEmail.setForeground(Color.WHITE);
		labelEmail.setBounds(24, 190, 100, 25);
		fSignUp.getContentPane().add(labelEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(145, 190, 220, 25);
		tfEmail.setFont(new Font("Verdana", Font.PLAIN, 13));
		fSignUp.getContentPane().add(tfEmail);
		
		JLabel labelUsername = new JLabel("Username*");
		labelUsername.setFont(new Font("Verdana", Font.PLAIN, 13));
		labelUsername.setForeground(Color.WHITE);
		labelUsername.setBounds(24, 230, 100, 25);
		fSignUp.getContentPane().add(labelUsername);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(145, 230, 220, 25);
		tfUsername.setFont(new Font("Verdana", Font.PLAIN, 13));
		fSignUp.getContentPane().add(tfUsername);
		
		JLabel labelPassword = new JLabel("Password*");
		labelPassword.setFont(new Font("Verdana", Font.PLAIN, 13));
		labelPassword.setForeground(Color.WHITE);
		labelPassword.setBounds(24, 270, 100, 25);
		fSignUp.getContentPane().add(labelPassword);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(145, 270, 220, 25);
		tfPassword.setFont(new Font("Verdana", Font.PLAIN, 13));
		fSignUp.getContentPane().add(tfPassword);
		
		JLabel labelRipPassword = new JLabel("Ripeti Password*");
		labelRipPassword.setFont(new Font("Verdana", Font.PLAIN, 13));
		labelRipPassword.setForeground(Color.WHITE);
		labelRipPassword.setBounds(24, 310, 120, 25);
		fSignUp.getContentPane().add(labelRipPassword);
		
		tfRipPassword = new JPasswordField();
		tfRipPassword.setBounds(145, 310, 220, 25);
		tfRipPassword.setFont(new Font("Verdana", Font.PLAIN, 13));
		fSignUp.getContentPane().add(tfRipPassword);
		
		JLabel labelRequired = new JLabel("* Campi obbligatori.");
		labelRequired.setFont(new Font("Verdana", Font.PLAIN, 10));
		labelRequired.setForeground(Color.WHITE);
		labelRequired.setBounds(24, 340, 210, 25);
		fSignUp.getContentPane().add(labelRequired);
		
		JButton btnBack = new JButton("Indietro");
		btnBack.setBackground(Color.WHITE);
		btnBack.setForeground(Utility.getNewBlu());
		btnBack.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBack.setBounds(60, 390, 115, 30);
		fSignUp.getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Home H = new Home();
				fSignUp.dispose(); //chiudo la finestra di registrazione
				H.setVisible(true); //rendo visibile la finestra della home
			}
		});
		
		JButton btnSignUp = new JButton("Registrati");
		btnSignUp.setBackground(Color.WHITE);
		btnSignUp.setForeground(Utility.getNewBlu());
		btnSignUp.setFont(new Font("Verdana", Font.BOLD, 12));
		btnSignUp.setBounds(240, 390, 115, 30);
		fSignUp.getContentPane().add(btnSignUp);
		btnSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tfPassword.getText().equals(tfRipPassword.getText())) { //controllo che i due campi delle password siano uguali
					if(!tfNome.getText().isBlank() && !tfCognome.getText().isBlank()
							&& !tfUsername.getText().isBlank() && !tfEmail.getText().isBlank()
							&& !tfPassword.getText().isBlank() && !tfRipPassword.getText().isBlank()) { //se tutti i campi non sono vuoti
						try {
							Account A = new Account(tfNome.getText(), tfCognome.getText(), tfUsername.getText(), tfEmail.getText(), tfPassword.getText());
							try {
								D.createAccount(A); //creo l'account
								JOptionPane.showMessageDialog(fSignUp, "Registrazione effettuata!", "Successo", 0, Utility.getImgSuccess());
								LoginPage log = new LoginPage();
								fSignUp.setVisible(false); //rendo invisibile la finestra di registrazione
								log.setVisible(true); //rendo visibile la finestra di login
							} catch (SQLIntegrityConstraintViolationException s) {
								JOptionPane.showMessageDialog(fSignUp, "Username/email già esistente!", "Errore", 0, Utility.getImgError());
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(fSignUp, "Errore.", "Errore", 0, Utility.getImgError());
							}
						} catch (HeadlessException h) {
							JOptionPane.showMessageDialog(fSignUp, "Errore.", "Errore", 0, Utility.getImgError());
						}
					} else {
						JOptionPane.showMessageDialog(fSignUp, "Tutti i campi sono obbligatori!", "Errore", 0, Utility.getImgError());
					}
				} else {
					JOptionPane.showMessageDialog(fSignUp, "Le password non coincidono!", "Errore", 0, Utility.getImgError());
				}
			}
		});
	}
	
	/**
	 * Metodo che rende visibile o invisibile la finestra.
	 * 
	 * @param bool <code>true</code> per rendere la finestra visibile, <code>false</code> altrimenti
	 */
	public void setVisible(boolean bool) {
		try {
			if(bool == true) {
				SignUpPage window = new SignUpPage();
				window.fSignUp.setVisible(true);
			} else {
				SignUpPage window = new SignUpPage();
				window.fSignUp.setVisible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
