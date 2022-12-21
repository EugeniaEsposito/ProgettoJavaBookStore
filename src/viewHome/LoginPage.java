package viewHome;

import utility.*;
import viewAdmin.MenuAdmin;
import viewUser.UserMenu;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe che rappresenta la schermata di login.
 * 
 * @author Eugenia Esposito
 *
 */
public class LoginPage {
	private JFrame fLogin;
	private static JTextField tfUsername;
	private static JTextField tfPassword;
	
	/**
	 * Costruttore della classe che inizializza tutte le componenti della schermata.
	 */
	public LoginPage() {
		initialize();
	}
	
	private void initialize() {
		fLogin = new JFrame();
		fLogin.setTitle("Login");
		fLogin.setBounds(100, 100, 450, 330);
		fLogin.getContentPane().setBackground(Utility.getNewBlu());
		fLogin.getContentPane().setForeground(Color.WHITE);
		fLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fLogin.getContentPane().setLayout(null);
		
		JLabel labelImg = new JLabel("");
		labelImg.setIcon(Utility.getImgLogo());
		labelImg.setBounds(24, 10, 64, 64);
		fLogin.getContentPane().add(labelImg);
		
		JLabel labelLogin = new JLabel("LOGIN");
		labelLogin.setFont(new Font("Verdana", Font.BOLD, 16));
		labelLogin.setForeground(Color.WHITE);
		labelLogin.setBounds(200, 32, 60, 16);
		fLogin.getContentPane().add(labelLogin);
		
		JLabel labelUsername = new JLabel("Username");
		labelUsername.setFont(new Font("Verdana", Font.PLAIN, 13));
		labelUsername.setForeground(Color.WHITE);
		labelUsername.setBounds(24, 110, 100, 25);
		fLogin.getContentPane().add(labelUsername);
		
		JLabel labelPassword = new JLabel("Password");
		labelPassword.setFont(new Font("Verdana", Font.PLAIN, 13));
		labelPassword.setForeground(Color.WHITE);
		labelPassword.setBounds(24, 160, 100, 25);
		fLogin.getContentPane().add(labelPassword);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(130, 110, 220, 25);
		tfUsername.setFont(new Font("Verdana", Font.PLAIN, 13));
		fLogin.getContentPane().add(tfUsername);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(130, 160, 220, 25);
		tfPassword.setFont(new Font("Verdana", Font.PLAIN, 13));
		fLogin.getContentPane().add(tfPassword);
		
		JButton btnLogin = new JButton("Accedi");
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setForeground(Utility.getNewBlu());
		btnLogin.setFont(new Font("Verdana", Font.BOLD, 12));
		btnLogin.setBounds(250, 225, 115, 30);
		fLogin.getContentPane().add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Login log = Login.getInstance();
				if(log.auth(tfUsername.getText(), tfPassword.getText()) == 1) { //se è admin
					MenuAdmin mA = new MenuAdmin();
					mA.setVisible(true); //rendo visibile la finestra del menu admin
					fLogin.dispose(); //chiudo la finestra di login
				} else if(log.auth(tfUsername.getText(), tfPassword.getText()) == 2) { //se è utente
					UserMenu mU = new UserMenu();
					mU.setVisible(true); //rendo visibile la finestra del menu utente
					fLogin.dispose(); //chiudo la finestra di login
				} else if(log.auth(tfUsername.getText(), tfPassword.getText()) == -1) { //se non è stato trovato alcun riscontro
					JOptionPane.showMessageDialog(fLogin, "Username e/o password non corretti", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
				}
			}
		});
		
		JButton btnBack = new JButton("Indietro");
		btnBack.setBackground(Color.WHITE);
		btnBack.setForeground(Utility.getNewBlu());
		btnBack.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBack.setBounds(85, 225, 115, 30);
		fLogin.getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Home H = new Home();
				fLogin.dispose(); //chiudo la pagina di login
				H.setVisible(true); //rendo visibile la home
			}
		});
	}
	
	/**
	 * Metodo <code>get</code> che ritorna l'username di chi si è loggato.
	 *  
	 * @return l'username di chi ha effettuato il login
	 */
	public static String getUsername() {
		return tfUsername.getText();
	}
	
	/**
	 * Metodo che rende visibile o invisibile la finestra.
	 * 
	 * @param bool <code>true</code> per rendere la finestra visibile, <code>false</code> altrimenti
	 */
	public void setVisible(boolean bool) {
		try {
			if(bool == true) {
				LoginPage window = new LoginPage();
				window.fLogin.setVisible(true);
			} else {
				LoginPage window = new LoginPage();
				window.fLogin.setVisible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
