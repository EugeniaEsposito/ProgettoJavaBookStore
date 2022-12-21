package viewHome;

import utility.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Classe che rappresenta la homepage dell'applicazione.
 * 
 * @author Eugenia Esposito
 *
 */
public class Home {
	private JFrame fHome;
	
	/**
	 * Metodo main che rende visibile la finestra di Home e fa partire quindi l'esecuzione dell'applicazione.
	 * 
	 * @param args argomenti della linea di comando
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.fHome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Costruttore della classe che inizializza la finestra Home con tutte le sue componenti.
	 */
	public Home() {
		initialize();
	}

	private void initialize() {
		fHome = new JFrame();
		fHome.setTitle("Home");
		fHome.setBounds(100, 100, 420, 260);
		fHome.getContentPane().setBackground(Utility.getNewBlu());
		fHome.getContentPane().setForeground(Color.WHITE);
		fHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fHome.getContentPane().setLayout(null);
		
		JLabel labelImg = new JLabel("");
		labelImg.setIcon(Utility.getImgLogo());
		labelImg.setBounds(24, 10, 64, 64);
		fHome.getContentPane().add(labelImg);
		
		JLabel labelLogin = new JLabel("HOME");
		labelLogin.setFont(new Font("Verdana", Font.BOLD, 16));
		labelLogin.setForeground(Color.WHITE);
		labelLogin.setBounds(180, 32, 60, 16);
		fHome.getContentPane().add(labelLogin);
		
		JButton buttonLogin = new JButton("Accedi");
		buttonLogin.setFont(new Font("Verdana", Font.BOLD, 12));
		buttonLogin.setBackground(Color.WHITE);
		buttonLogin.setForeground(Utility.getNewBlu());
		buttonLogin.setBounds(150, 100, 115, 30);
		fHome.getContentPane().add(buttonLogin);
		buttonLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginPage login = new LoginPage();
				fHome.setVisible(false); //rendo invisibile la finestra della home
				login.setVisible(true); //rendo visibile la finestra del login
			}
		});
		
		JButton buttonSignUp = new JButton("Registrati");
		buttonSignUp.setFont(new Font("Verdana", Font.BOLD, 12));
		buttonSignUp.setBackground(Color.WHITE);
		buttonSignUp.setForeground(Utility.getNewBlu());
		buttonSignUp.setBounds(150, 160, 115, 30);
		fHome.getContentPane().add(buttonSignUp);
		buttonSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				SignUpPage signUp = new SignUpPage();
				fHome.setVisible(false); //rendo invisibile la finestra della home
				signUp.setVisible(true); //rendo visibile la finestra della registrazione
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
				Home window = new Home();
				window.fHome.setVisible(true);
			} else {
				Home window = new Home();
				window.fHome.setVisible(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
