package viewUser;

import classiDB.Cart;
import classiDB.CartDetail;
import classiDB.DBBookStore;
import utility.*;
import viewHome.LoginPage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Classe che rappresenta la schermata relativa al catalogo dei prodotti.
 * 
 * @author Eugenia Esposito
 *
 */
public class ProductListUser extends JFrame {
	private JPanel pPListU;
	private UserOperations Operations;
	private int idcart = 0;
	
	/**
	 * Costruttore che inizializza tutte le componenti della schermata.
	 */
	public ProductListUser() {
		Operations = new UserOperations();
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Catalogo");
		setBounds(100, 100, 780, 470);
		
		ImageIcon cart = new ImageIcon(ProductListUser.class.getResource("/images/cart.png")); //icona del carrello
		DBBookStore D = DBBookStore.getInstance();
		
		pPListU = new JPanel();
		pPListU.setForeground(Color.WHITE);
		pPListU.setBackground(Utility.getNewBlu());
		pPListU.setLayout(null);
		setContentPane(pPListU);

		JLabel lblBook = new JLabel("Libri");
		lblBook.setForeground(Color.WHITE);
		lblBook.setFont(new Font("Verdana", Font.BOLD, 14));
		lblBook.setBounds(350, 10, 100, 20);
		pPListU.add(lblBook);
		
		JLabel lblCD = new JLabel("CD");
		lblCD.setForeground(Color.WHITE);
		lblCD.setFont(new Font("Verdana", Font.BOLD, 14));
		lblCD.setBounds(355, 195, 100, 20);
		pPListU.add(lblCD);
		
		JScrollPane scrollPbook = new JScrollPane();
		scrollPbook.setBounds(20, 35, 720, 150);
		pPListU.add(scrollPbook);
		
		JTable tableBook = new JTable(Operations.showProductList("Book")) { //visualizzo la tabella contenente i libri
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableBook.getTableHeader().setReorderingAllowed(false);
		tableBook.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableBook.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableBook.getColumnModel().getColumn(2).setPreferredWidth(135);
		tableBook.getColumnModel().getColumn(3).setPreferredWidth(35);
		tableBook.getColumnModel().getColumn(4).setPreferredWidth(25);
		tableBook.getColumnModel().getColumn(5).setPreferredWidth(60);
		tableBook.getColumnModel().getColumn(6).setPreferredWidth(25);
		tableBook.setFont(new Font("Verdana", Font.PLAIN, 11));
		scrollPbook.setViewportView(tableBook);
		
		JScrollPane scrollPcd = new JScrollPane();
		scrollPcd.setBounds(20, 220, 720, 150);
		pPListU.add(scrollPcd);
		
		JTable tableCD = new JTable(Operations.showProductList("CD")) { //visualizzo la tabella contenente i CD
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableCD.getTableHeader().setReorderingAllowed(false);
		tableCD.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableCD.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableCD.getColumnModel().getColumn(2).setPreferredWidth(135);
		tableCD.getColumnModel().getColumn(3).setPreferredWidth(35);
		tableCD.getColumnModel().getColumn(4).setPreferredWidth(25);
		tableCD.getColumnModel().getColumn(5).setPreferredWidth(60);
		tableCD.getColumnModel().getColumn(6).setPreferredWidth(25);
		tableCD.setFont(new Font("Verdana", Font.PLAIN, 11));
		scrollPcd.setViewportView(tableCD);
		
		JButton btnBack = new JButton("Indietro");
		btnBack.setForeground(Utility.getNewBlu());
		btnBack.setBackground(Color.WHITE);
		btnBack.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBack.setBounds(180, 385, 100, 30);
		pPListU.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserMenu mU = new UserMenu();
				ProductListUser.this.dispose(); //chiudo la finestra del catalogo
				mU.setVisible(true); //rendo visibile il menu utente 
			}
		});
		
		JButton btnAdd = new JButton("Aggiungi al carrello");
		btnAdd.setForeground(Utility.getNewBlu());
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setFont(new Font("Verdana", Font.BOLD, 12));
		btnAdd.setBounds(330, 385, 180, 30);
		pPListU.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] rowsB = tableBook.getSelectedRows(); //salvo le righe selezionate della tabella libri
				int[] rowsC = tableCD.getSelectedRows(); //salvo le righe selezionate della tabella CD
				if(rowsB.length == 0 && rowsC.length == 0) { //se non è stato selezionato nessun prodotto
					JOptionPane.showMessageDialog(pPListU, "Nessun prodotto selezionato!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
				} else { //se ci sono righe selezionate
					Cart cart = new Cart(LoginPage.getUsername()); //creo un carrello e lo collego all'utente
					try {
						D.openConnection();
						D.rs = D.st.executeQuery("SELECT idcart FROM cart WHERE user = '" + cart.getUsername() + "' AND pagato = 0;"); //cerco un carrello di quell'utente non ancora pagato
						if(!D.rs.next()) { //se non lo trovo
							D.st.executeUpdate("INSERT INTO `bookstore`.`cart` (`user`) VALUES "
									+ "('"+ cart.getUsername() + "');", Statement.RETURN_GENERATED_KEYS); //creo un nuovo carrello
							D.rs = D.st.getGeneratedKeys();
							if(D.rs.next())
								idcart = D.rs.getInt(1);
						} else //se lo trovo
							idcart = D.rs.getInt(1); //salvo l'id del carrello trovato
						if(rowsB.length != 0) { //se ci sono righe selezionate della tabella libri
							for(int row: rowsB) { //scorro l'array
								String ean = (String) tableBook.getModel().getValueAt(row, 0);
								CartDetail cartD = new CartDetail(idcart, ean);
								D.st.executeUpdate("INSERT INTO `bookstore`.`cart_detail` (`idcart`,`EAN_product`) VALUES "
										+ "('"+ cartD.getIdCart() +"','"+ cartD.getEan() +"');"); //inserisco nel carrello il libro selezionato
							}
						}
						if(rowsC.length != 0) { //se ci sono righe selezionate della tabella CD
							for(int row: rowsC) { //scorro l'array
								String ean = (String) tableCD.getModel().getValueAt(row, 0);
								CartDetail cartD = new CartDetail(idcart, ean);
								D.st.executeUpdate("INSERT INTO `bookstore`.`cart_detail` (`idcart`,`EAN_product`) VALUES "
										+ "('"+ cartD.getIdCart() +"','"+ cartD.getEan() +"');"); //inserisco nel carrello il CD
							}
						}
						D.closeConnection();
						JOptionPane.showMessageDialog(pPListU, "Prodotto/i aggiunto/i al carrello!", "Successo", JOptionPane.INFORMATION_MESSAGE, Utility.getImgSuccess());
						tableBook.clearSelection(); //deseleziono le righe
						tableCD.clearSelection(); //deseleziono le righe
					} catch(SQLException e1) {
						System.out.println(e1);
						JOptionPane.showMessageDialog(pPListU, "Errore", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
					}
				}
			}
		});
		
		JButton btnCart = new JButton(cart);
		btnCart.setForeground(Color.WHITE);
		btnCart.setBackground(null);
		btnCart.setBorder(null);
		btnCart.setBounds(560, 385, 32, 32);
		pPListU.add(btnCart);
		btnCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShoppingCartPage cartP = new ShoppingCartPage(idcart);
				ProductListUser.this.dispose(); //chiude la pagina del catalogo
				cartP.setVisible(true); //rende visibile la pagina del carrello
			}
		});
	}
}
