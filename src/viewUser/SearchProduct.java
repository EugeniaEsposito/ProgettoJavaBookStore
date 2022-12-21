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
 * Classe che rappresenta la schermata relativa alla ricerca di un prodotto.
 * 
 * @author Eugenia Esposito
 *
 */
public class SearchProduct  extends JFrame{
	private JPanel pSearch;
	private UserOperations Operations;
	private String word;
	private String tipo;
	private int idcart = 0;
	
	
	/**
	 * Costruttore che inizializza tutte le componenti della schermata.
	 * 
	 * @param wordS parola da ricercare
	 * @param tipoS tipo del prodotto che si intende ricercare
	 */
	public SearchProduct(String wordS, String tipoS) {
		Operations = new UserOperations();
		word = wordS;
		tipo = tipoS;
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Risultato Ricerca");
		setBounds(100, 100, 780, 450);
		
		ImageIcon cart = new ImageIcon(SearchProduct.class.getResource("/images/cart.png")); //icona del carrello
		DBBookStore D = DBBookStore.getInstance();
		
		pSearch = new JPanel();
		pSearch.setBackground(Utility.getNewBlu());
		pSearch.setForeground(Color.WHITE);
		pSearch.setLayout(null);
		setContentPane(pSearch);
		
		JLabel lblTitle = new JLabel("Risultati per '" + word + "' in '" + tipo + "'");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 14));
		lblTitle.setBounds(20, 10, 300, 20);
		pSearch.add(lblTitle);
		
		JScrollPane scrollP = new JScrollPane();
		scrollP.setBounds(20, 35, 720, 300);
		pSearch.add(scrollP);
		
		JTable table = new JTable(Operations.searchWord(word, tipo)) { //visualizzo la tabella con le corrispondenze trovate
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(135);
		table.getColumnModel().getColumn(3).setPreferredWidth(35);
		table.getColumnModel().getColumn(4).setPreferredWidth(25);
		table.getColumnModel().getColumn(5).setPreferredWidth(60);
		table.getColumnModel().getColumn(6).setPreferredWidth(25);
		table.setFont(new Font("Verdana", Font.PLAIN, 11));
		scrollP.setViewportView(table);
		
		JButton btnBack = new JButton("Indietro");
		btnBack.setForeground(Utility.getNewBlu());
		btnBack.setBackground(Color.WHITE);
		btnBack.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBack.setBounds(180, 360, 100, 30);
		pSearch.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserMenu mU = new UserMenu();
				SearchProduct.this.dispose(); //chiudo la finestra relativa alla ricerca
				mU.setVisible(true); //rendo visibile la schermata del menu utente
			}
		});
		
		JButton btnAdd = new JButton("Aggiungi al carrello");
		btnAdd.setForeground(Utility.getNewBlu());
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setFont(new Font("Verdana", Font.BOLD, 12));
		btnAdd.setBounds(330, 360, 180, 30);
		pSearch.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int[] rows = table.getSelectedRows(); //salvo le righe selezionate
				if(rows.length == 0) { //se non ci sono righe selezionate
					JOptionPane.showMessageDialog(pSearch, "Nessun prodotto selezionato!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
				} else { //se ci sono righe selezionate
					Cart cart = new Cart(LoginPage.getUsername());
					try {
						D.openConnection();
						D.rs = D.st.executeQuery("SELECT idcart FROM cart WHERE user = '" + cart.getUsername() + "' AND pagato = 0;"); //cerco un carrello di quell'utente non ancora pagato
						if(!D.rs.next()) { //se non lo trovo
							D.st.executeUpdate("INSERT INTO `bookstore`.`cart` (`user`) VALUES "
									+ "('"+ cart.getUsername() + "');", Statement.RETURN_GENERATED_KEYS); //creo un nuovo carrello
							D.rs = D.st.getGeneratedKeys();
							if(D.rs.next())
								idcart = D.rs.getInt(1); //salvo l'id del carrello
						} else //se lo trovo
							idcart = D.rs.getInt(1); //salvo l'id del carrello
						for(int r: rows) { //scorro l'array delle righe selezionate
							String ean = (String) table.getModel().getValueAt(r, 0);
							CartDetail cartD = new CartDetail(idcart, ean);
							D.st.executeUpdate("INSERT INTO `bookstore`.`cart_detail` (`idcart`,`EAN_product`) VALUES "
									+ "('"+ cartD.getIdCart() +"','"+ cartD.getEan() +"');"); //inserisco nel carrello il prodotto selezionato
						}
						D.closeConnection();
						JOptionPane.showMessageDialog(pSearch, "Prodotto/i aggiunto/i al carrello!", "Successo", JOptionPane.INFORMATION_MESSAGE, Utility.getImgSuccess());
						table.clearSelection(); //deseleziono le righe
					} catch(SQLException e1) {
						System.out.println(e1);
						JOptionPane.showMessageDialog(pSearch, "Errore", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
					}
				}
			}
		});
		
		JButton btnCart = new JButton(cart);
		btnCart.setForeground(Color.WHITE);
		btnCart.setBackground(null);
		btnCart.setBorder(null);
		btnCart.setBounds(560, 360, 32, 32);
		pSearch.add(btnCart);
		btnCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShoppingCartPage cartP = new ShoppingCartPage(idcart);
				SearchProduct.this.dispose(); //chiudo la schermata della ricerca
				cartP.setVisible(true); //rendo visibile la schermata del carrello
			}
		});
	}
}
