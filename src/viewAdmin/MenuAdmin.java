package viewAdmin;

import utility.*;
import viewHome.Home;
import viewHome.LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe che rappresenta la schermata del menu dell'admin.
 * 
 * @author Eugenia Esposito
 *
 */
public class MenuAdmin extends JFrame {
	private JPanel pAdmin;
	
	/**
	 * Costruttore che inizializza tutte le componenti della schermata.
	 */
	public MenuAdmin() {
		initialize();
	}
	
	private void initialize() {
		setBounds(100, 100, 480, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Menu Admin");
		
		pAdmin = new JPanel();
		pAdmin.setBackground(Utility.getNewBlu());
		pAdmin.setForeground(Color.WHITE);
		pAdmin.setLayout(null);
		setContentPane(pAdmin);
		
		JLabel labelImg = new JLabel("");
		labelImg.setIcon(Utility.getImgLogo());
		labelImg.setBounds(24, 10, 64, 64);
		pAdmin.add(labelImg);
		
		JLabel labelHello = new JLabel("Ciao " + LoginPage.getUsername() + "!");
		labelHello.setForeground(Color.WHITE);
		labelHello.setFont(new Font("Verdana", Font.BOLD, 16));
		labelHello.setBounds(187, 10, 200, 60);
		pAdmin.add(labelHello);
		
		JButton btnAdd = new JButton("Aggiungi");
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setForeground(Utility.getNewBlu());
		btnAdd.setFont(new Font("Verdana", Font.BOLD, 12));
		btnAdd.setBounds(100, 100, 100, 30);
		pAdmin.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddProductPage addProduct = new AddProductPage();
				MenuAdmin.this.setVisible(false); //rendo invisibile la schermata del menu
				addProduct.setVisible(true); //rendo visibile la schermata dell'inserimento
			}
		});
		
		JButton btnEdit = new JButton("Modifica");
		btnEdit.setBackground(Color.WHITE);
		btnEdit.setForeground(Utility.getNewBlu());
		btnEdit.setFont(new Font("Verdana", Font.BOLD, 12));
		btnEdit.setBounds(100, 150, 100, 30);
		pAdmin.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditProductPage editPage = new EditProductPage();
				MenuAdmin.this.setVisible(false); //rendo invisibile la schermata del menu
				editPage.setVisible(true); //rendo visibile la schermata della modifica
			}
		});
		
		JButton btnDelete = new JButton("Elimina");
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setForeground(Utility.getNewBlu());
		btnDelete.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDelete.setBounds(100, 200, 100, 30);
		pAdmin.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeleteProductPage pDelete = new DeleteProductPage();
				MenuAdmin.this.setVisible(false); //rendo invisibile la schermata del menu
				pDelete.setVisible(true); //rendo visibile la schermata dell'eliminazione
			}
		});
		
		JButton btnCollection = new JButton("Catalogo");
		btnCollection.setBackground(Color.WHITE);
		btnCollection.setForeground(Utility.getNewBlu());
		btnCollection.setFont(new Font("Verdana", Font.BOLD, 12));
		btnCollection.setBounds(280, 100, 100, 30);
		pAdmin.add(btnCollection);
		btnCollection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProductListPage pList = new ProductListPage();
				MenuAdmin.this.setVisible(false); //rendo invisibile la schermata del menu
				pList.setVisible(true); //rendo visibile la schermata del catalogo dei prodotti
			}
		});
		
		JButton btnUsers = new JButton("Utenti");
		btnUsers.setBackground(Color.WHITE);
		btnUsers.setForeground(Utility.getNewBlu());
		btnUsers.setFont(new Font("Verdana", Font.BOLD, 12));
		btnUsers.setBounds(280, 150, 100, 30);
		pAdmin.add(btnUsers);
		btnUsers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserList uList = new UserList();
				MenuAdmin.this.setVisible(false); //rendo invisibile la schermata del menu
				uList.setVisible(true); //rendo visibile la schermata dell'elenco utenti
			}
		});
		
		JButton btnLogout = new JButton("Esci");
		btnLogout.setBackground(Color.WHITE);
		btnLogout.setForeground(Utility.getNewBlu());
		btnLogout.setFont(new Font("Verdana", Font.BOLD, 12));
		btnLogout.setBounds(280, 200, 100, 30);
		pAdmin.add(btnLogout);
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Home H = new Home();
				MenuAdmin.this.dispose(); //chiudo la schermata del menu
				H.setVisible(true); //rendo visibile la schermata della home
			}
		});
	}
}
