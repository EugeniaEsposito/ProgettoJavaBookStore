package viewUser;

import classiDB.DBBookStore;
import utility.*;
import viewHome.Home;
import viewHome.LoginPage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Classe che rappresenta la schermata del menu utente.
 * 
 * @author Eugenia Esposito
 *
 */
public class UserMenu extends JFrame {
	private JPanel pUser;

	/**
	 * Costruttore che inizializza tutte le componenti della schermata.
	 */
	public UserMenu() {
		initialize();
	}
	
	private void initialize() {
		setBounds(100, 100, 460, 280);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Menu Utente");
				
		DBBookStore D = DBBookStore.getInstance();
		
		pUser = new JPanel();
		pUser.setBackground(new Color(3, 7, 100));
		pUser.setForeground(Color.WHITE);
		pUser.setLayout(null);
		setContentPane(pUser);
		
		JLabel labelImg = new JLabel("");
		labelImg.setIcon(Utility.getImgLogo());
		labelImg.setBounds(24, 10, 64, 64);
		pUser.add(labelImg);
		
		JLabel labelHello = new JLabel("Ciao " + LoginPage.getUsername() + "!");
		labelHello.setForeground(Color.WHITE);
		labelHello.setFont(new Font("Verdana", Font.BOLD, 16));
		labelHello.setBounds(187, 10, 200, 60);
		pUser.add(labelHello);
		
		JButton btnCollection = new JButton("Catalogo");
		btnCollection.setBackground(Color.WHITE);
		btnCollection.setForeground(Utility.getNewBlu());
		btnCollection.setFont(new Font("Verdana", Font.BOLD, 12));
		btnCollection.setBounds(100, 100, 100, 30);
		pUser.add(btnCollection);
		btnCollection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProductListUser pListU = new ProductListUser();
				UserMenu.this.dispose(); //chiudo la schermata del menu utente
				pListU.setVisible(true); //rendo visibile la schermata del catalogo
			}
		});
		
		JButton btnSearch = new JButton("Cerca");
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setForeground(Utility.getNewBlu());
		btnSearch.setFont(new Font("Verdana", Font.BOLD, 12));
		btnSearch.setBounds(100, 160, 100, 30);
		pUser.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] opz = {"Annulla", "Cerca"};
				
				JPanel p = new JPanel();
				
				JRadioButton radioBook = new JRadioButton("Libro");
				radioBook.setBackground(null);
				radioBook.setSelected(true);
				p.add(radioBook);
				
				JRadioButton radioCD = new JRadioButton("CD");
				radioCD.setBackground(null);
				p.add(radioCD);
				
				ButtonGroup group = new ButtonGroup();
				group.add(radioBook);
				group.add(radioCD);
				
				JTextField tfWord = new JTextField();
		
				Object[] params = {p, tfWord};
		
				int choise = JOptionPane.showOptionDialog(pUser, params, "Ricerca", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opz, null);
				if(choise == 1) {
					SearchProduct search = null;
					if(radioBook.isSelected()) { //se è selezionato libro
						search = new SearchProduct(tfWord.getText(), "Libri"); //inizializzo la pagina di ricerca col tipo libri
					} else if(radioCD.isSelected()) { //se è selezionato CD
						search = new SearchProduct(tfWord.getText(), "CD"); //inizializzo la pagina di ricerca col tipo CD
					}
					UserMenu.this.dispose(); //chiudo la schermata del menu utente
					search.setVisible(true); //rendo visibile la schermata della ricerca
				}
			}
		});
		
		JButton btnCart = new JButton("Carrello");
		btnCart.setBackground(Color.WHITE);
		btnCart.setForeground(Utility.getNewBlu());
		btnCart.setFont(new Font("Verdana", Font.BOLD, 12));
		btnCart.setBounds(270, 100, 100, 30);
		pUser.add(btnCart);
		btnCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = 0;
				try {
					D.openConnection();
					D.rs = D.st.executeQuery("SELECT idcart FROM cart WHERE user = '" + LoginPage.getUsername() + "' AND pagato = 0;"); //cerco un carrello dell'utente non ancora pagato
					if(D.rs.next()) { //se lo trovo
						id = D.rs.getInt(1); //salvo l'id del carrello
					}
					D.closeConnection();
				} catch(SQLException e1) {
					System.out.println(e1);
				}
				ShoppingCartPage cart = new ShoppingCartPage(id); //inizializzo la pagina del carrello con l'id
				UserMenu.this.dispose(); //chiudo la schermata del menu utente
				cart.setVisible(true); //rendo visibile la schermata del carrello
			}
		});
		
		JButton btnLogout = new JButton("Esci");
		btnLogout.setBackground(Color.WHITE);
		btnLogout.setForeground(Utility.getNewBlu());
		btnLogout.setFont(new Font("Verdana", Font.BOLD, 12));
		btnLogout.setBounds(270, 160, 100, 30);
		pUser.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Home H = new Home();
				UserMenu.this.dispose(); //chiudo la schermata del menu utente
				H.setVisible(true); //rendo visibile la schermata della home
			}
		});
	}
}