package viewUser;

import classiDB.DBBookStore;
import strategy.*;
import utility.*;
import visitor.ShoppingCart;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Classe che rappresenta la schermata relativa al carrello.
 * 
 * @author Eugenia Esposito
 *
 */
public class ShoppingCartPage extends JFrame {
	private JPanel pCart;
	private UserOperations Operations;
	private int id;
	
	/**
	 * Costruttore che inizializza tutte le componenti della schermata.
	 * 
	 * @param idcart l'id del carrello che si vuole visualizzare
	 */
	public ShoppingCartPage(int idcart) {
		id = idcart;
		Operations = new UserOperations();
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Carrello");
		setBounds(100, 100, 550, 380);
		
		DBBookStore D = DBBookStore.getInstance();
		ShoppingCart Cart = Operations.setCart(id); //inserisco le informazioni relative al carrello con quell'id
		
		pCart = new JPanel();
		pCart.setForeground(Color.WHITE);
		pCart.setBackground(Utility.getNewBlu());
		pCart.setLayout(null);
		setContentPane(pCart);
		
		JLabel lblYourCart = new JLabel("Il tuo carrello");
		lblYourCart.setForeground(Color.WHITE);
		lblYourCart.setFont(new Font("Verdana", Font.BOLD, 14));
		lblYourCart.setBounds(24, 10, 150, 20);
		pCart.add(lblYourCart);
				
		JScrollPane scrollP = new JScrollPane();
		scrollP.setBounds(24, 40, 480, 200);
		pCart.add(scrollP);
		
		JTable table = new JTable(Operations.showCart()) { //visualizzo la tabella con i prodotti nel carrello
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(new Font("Verdana", Font.PLAIN, 11));
		table.setTableHeader(null);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(140);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		table.setRowHeight(20);
		pCart.add(table);
		scrollP.setViewportView(table);
		
		JLabel lblTot = new JLabel("*Totale: € " + Cart.calculateTotal());
		lblTot.setForeground(Color.WHITE);
		lblTot.setFont(new Font("Verdana", Font.BOLD, 13));
		lblTot.setBounds(375, 250, 150, 20);
		pCart.add(lblTot);
		
		JLabel lblDiscount = new JLabel("*Sconto 5% se libro > € 20.00");
		lblDiscount.setForeground(Color.WHITE);
		lblDiscount.setFont(new Font("Verdana", Font.PLAIN, 9));
		lblDiscount.setBounds(24, 242, 200, 10);
		pCart.add(lblDiscount);
		
		JLabel lblDiscount2 = new JLabel("Sconto 10% se CD > € 20.00");
		lblDiscount2.setForeground(Color.WHITE);
		lblDiscount2.setFont(new Font("Verdana", Font.PLAIN, 9));
		lblDiscount2.setBounds(31, 252, 200, 10);
		pCart.add(lblDiscount2);
		
		JButton btnBack = new JButton("Indietro");
		btnBack.setForeground(Utility.getNewBlu());
		btnBack.setBackground(Color.WHITE);
		btnBack.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBack.setBounds(49, 280, 100, 30);
		pCart.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserMenu mU = new UserMenu();
				ShoppingCartPage.this.dispose(); //chiudo la pagina del carrello
				mU.setVisible(true); //rendo visibile la pagina del menu utente
			}
		});
		
		JButton btnRemove = new JButton("Rimuovi");
		btnRemove.setForeground(Utility.getNewBlu());
		btnRemove.setBackground(Color.WHITE);
		btnRemove.setFont(new Font("Verdana", Font.BOLD, 12));
		btnRemove.setBounds(215, 280, 100, 30);
		pCart.add(btnRemove);
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRowCount() == 0) //se non è selezionata nessuna riga
					JOptionPane.showMessageDialog(pCart, "Nessun prodotto selezionato!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
				else {
					int row = table.getSelectedRow(); //salvo la riga selezionata
					Cart.removeProduct(row); //rimuovo il prodotto dal carrello
					int id = (int) table.getValueAt(row, 0);
					try {
						D.openConnection();
						D.st.executeUpdate("DELETE FROM cart_detail WHERE idcart_detail = "+ id +";"); //elimino il prodotto dal database
						D.closeConnection();
					} catch(SQLException e1) {
						System.out.println(e1);
						JOptionPane.showMessageDialog(pCart, "Errore", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
					}
					((DefaultTableModel)table.getModel()).removeRow(row); //aggiorno la tabella
					lblTot.setText("Totale: € " + Cart.calculateTotal()); //aggiorno la label col totale
					JOptionPane.showMessageDialog(pCart, "Prodotto eliminato!", "Successo", JOptionPane.INFORMATION_MESSAGE, Utility.getImgSuccess());
				}
			}
		});
		
		JButton btnPay = new JButton("Paga");
		btnPay.setForeground(Utility.getNewBlu());
		btnPay.setBackground(Color.WHITE);
		btnPay.setFont(new Font("Verdana", Font.BOLD, 12));
		btnPay.setBounds(375, 280, 100, 30);
		pCart.add(btnPay);
		btnPay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getRowCount() == 0) //se non ci sono prodotti nel carrello
					JOptionPane.showMessageDialog(pCart, "Nessun prodotto nel carrello!", "Carrello vuoto", 0, Utility.getImgError());
				else {
					JLabel lbl = new JLabel("Seleziona metodo di pagamento.");
					JPanel panel = new JPanel();
					panel.add(lbl);
					Object[] obj = {"Annulla", "Carta di Credito", "Paypal", "Contrassegno"};
					int choise = JOptionPane.showOptionDialog(null, panel, "Pagamento", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, obj, null);
					if(choise == 0 || choise == JOptionPane.CLOSED_OPTION) { //annulla
						
					} else {
						if(choise == 1) { //se ho scelto di pagare con carta di credito
							CreditCardPage CreditPage = new CreditCardPage(Cart); //inizializzo la pagina
							CreditPage.setVisible(true); //rendo visibile la pagina della carta di credito
							CreditPage.addWindowListener(new WindowAdapter() {
								@Override
								public void windowClosed(WindowEvent e) { //quando chiudo la finestra
									if(payed(Cart.getId())) { //se ho concluso il pagamento
										table.setModel(Operations.showCart()); //aggiorno la tabella che risulterà vuota
										Cart.removeAll(); //rimuovo tutti i prodotti dal carrello
										lblTot.setText("Totale: € "+ Cart.calculateTotal()); //aggiorno la label del totale
									}
								}
							});
						} else if(choise == 2) { //se ho scelto di pagare con paypal
							PaypalPage PayPal = new PaypalPage(Cart); //inizializzo la pagina
							PayPal.setVisible(true); //rendo visibile la pagina del paypal
							PayPal.addWindowListener(new WindowAdapter() {
								@Override
								public void windowClosed(WindowEvent e) { //quando chiudo la finestra
									if(payed(Cart.getId())) { //se ho concluso il pagamento
										table.setModel(Operations.showCart()); //aggiorno la tabella che risulterà vuota
										Cart.removeAll(); //rimuovo tutti i prodotti dal carrello
										lblTot.setText("Totale: € "+ Cart.calculateTotal()); //aggiorno la label del totale
									}
								}
							});
						} else if(choise == 3) { //se ho scelto di pagare con contrassegno
							BigDecimal newTot = Cart.calculateTotal().add(new BigDecimal(3.50)); //sommo 3.50 al totale da pagare
							JLabel lblCash = new JLabel("Contrassegno: € 3.50");
							JLabel lblNewTot = new JLabel("Totale da pagare alla consegna: € "+ newTot +". Procedere?");
							Object[] obj1 = {lblCash, lblNewTot};
							Object[] opt = {"OK", "Annulla"};
							int res = JOptionPane.showOptionDialog(null, obj1, "Contrassegno", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opt, null);
							if(res == JOptionPane.OK_OPTION) { //se confermo
								Operations.pay(new CashOnDelivery(), Cart.calculateTotal(), Cart.getId()); //effettuo il pagamento
								JOptionPane.showMessageDialog(null, "Pagamento alla consegna confermato!", "Successo", JOptionPane.INFORMATION_MESSAGE, Utility.getImgSuccess());
								table.setModel(Operations.showCart()); //aggiorno la tabella
								Cart.removeAll(); //rimuovo tutti i prodotti dal carrello
								lblTot.setText("Totale: € "+ Cart.calculateTotal()); //aggiorno la label del totale
							}
						}
						
					}
				}
			}
		});
	}
	
	/**
	 * Metodo che controlla se il carrello corrispondente a <code>id</code> è stato pagato.
	 * Ritorna true in caso affermativo, false altrimenti.
	 * @param id l'id del carrello
	 * @return <code>true</code> se ho effettuato il pagamento, <code>false</code> altrimenti
	 */
	public boolean payed(int id) {
		boolean bool = false;
		DBBookStore D = DBBookStore.getInstance();
		try {
			D.openConnection();
			D.rs = D.st.executeQuery("SELECT idcart FROM cart WHERE idcart = " + id + " AND pagato = 1;"); //cerco quel carrello per controllare se è stato pagato
			if(D.rs.next())
				bool = true;
			D.closeConnection();
		} catch(SQLException e) {
			System.out.println(e);
		}
		return bool;
	}
}
