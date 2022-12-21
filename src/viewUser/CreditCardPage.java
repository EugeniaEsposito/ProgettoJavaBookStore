package viewUser;

import utility.*;
import visitor.ShoppingCart;
import strategy.CreditCard;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Classe che rappresenta la schermata relativa all'inserimento dei dati della carta di credito.
 * 
 * @author Eugenia Esposito
 *
 */
public class CreditCardPage extends JFrame {
	private JPanel pCreditCard;
	private UserOperations Operations;
	private ShoppingCart Cart;
	
	/**
	 * Costruttore che inizializza tutte le componenti della schermata.
	 * 
	 * @param ShoppingCart il carrello per recuperare le informazioni sul totale da pagare
	 */
	public CreditCardPage(ShoppingCart ShoppingCart) {
		Cart = ShoppingCart;
		Operations = new UserOperations();
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Carta di Credito");
		setBounds(400, 300, 270, 220);
				
		pCreditCard = new JPanel();
		pCreditCard.setLayout(null);
		setContentPane(pCreditCard);
		
		JTextField tfName = new JTextField("Nome");
		tfName.setFont(new Font("Verdana", Font.PLAIN, 12));
		tfName.setForeground(Color.GRAY);
		tfName.setBounds(25, 15, 170, 20);
		pCreditCard.add(tfName);
		tfName.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(tfName.getText().isBlank()) {
					tfName.setForeground(Color.GRAY);
					tfName.setText("Nome");
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(tfName.getText().equals("Nome")) {
					tfName.setForeground(Color.BLACK);
					tfName.setText("");
				}
			}
		});
		
		JTextField tfNumber = new JTextField("Numero Carta");
		tfNumber.setFont(new Font("Verdana", Font.PLAIN, 12));
		tfNumber.setForeground(Color.GRAY);
		tfNumber.setBounds(25, 45, 170, 20);
		pCreditCard.add(tfNumber);
		tfNumber.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(tfNumber.getText().isBlank()) {
					tfNumber.setForeground(Color.GRAY);
					tfNumber.setText("Numero Carta");
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(tfNumber.getText().equals("Numero Carta")) {
					tfNumber.setForeground(Color.BLACK);
					tfNumber.setText("");
				}
			}
		});
		
		JTextField tfCvv = new JTextField("CVV");
		tfCvv.setFont(new Font("Verdana", Font.PLAIN, 12));
		tfCvv.setForeground(Color.GRAY);
		tfCvv.setBounds(25, 75, 30, 20);
		pCreditCard.add(tfCvv);
		tfCvv.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(tfCvv.getText().isBlank()) {
					tfCvv.setForeground(Color.GRAY);
					tfCvv.setText("CVV");
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(tfCvv.getText().equals("CVV")) {
					tfCvv.setForeground(Color.BLACK);
					tfCvv.setText("");
				}
			}
		});
		
		JComboBox<Integer> months = new JComboBox<>();
		for(int i=1; i<=12; i++)
			months.addItem(i);
		months.setFont(new Font("Verdana", Font.PLAIN, 12));
		months.setSelectedItem(LocalDate.now().getMonthValue());
		months.setBounds(25, 105, 50, 20);
		pCreditCard.add(months);
		
		JComboBox<Integer> years = new JComboBox<>();
		for(int i=LocalDate.now().getYear(); i<LocalDate.now().getYear()+20; i++)
			years.addItem(i); //inserisco gli anni a partire da quello corrente
		years.setFont(new Font("Verdana", Font.PLAIN, 12));
		years.setSelectedItem(0);
		years.setBounds(85, 105, 70, 20);
		pCreditCard.add(years);
		
		JButton btnCancel = new JButton("Annulla");
		btnCancel.setFont(new Font("Verdana", Font.BOLD, 12));
		btnCancel.setBounds(50, 140, 90, 25);
		pCreditCard.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreditCardPage.this.dispose(); //chiudo la pagina corrente
			}
		});
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("Verdana", Font.BOLD, 12));
		btnOk.setBounds(150, 140, 60, 25);
		pCreditCard.add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!tfName.getText().equals("Nome") && !tfNumber.getText().equals("Numero Carta") && !tfCvv.getText().equals("CVV")) { //se tutti i campi sono compilati
					if(((int)years.getSelectedItem() == LocalDate.now().getYear() && (int)months.getSelectedItem() >= LocalDate.now().getMonthValue())
							|| (int)years.getSelectedItem() > LocalDate.now().getYear()) { //se è una data di scadenza valida
						String expiry = (String.valueOf(months.getSelectedItem())).concat("/").concat(String.valueOf(years.getSelectedItem()));
						if(tfCvv.getText().length() == 3 && Utility.isNumber(tfCvv.getText())) { //se la lunghezza del cvv è 3 ed è un numero
							if(Utility.isNumberCard(tfNumber.getText())) { //se il campo è un numero di carta valido
								Operations.pay(new CreditCard(tfName.getText(), tfNumber.getText(), tfCvv.getText(), expiry), Cart.calculateTotal(), Cart.getId()); //effettuo il pagamento
								JOptionPane.showMessageDialog(pCreditCard, "Pagamento di € "+ Cart.calculateTotal() +" con carta di credito effettuato!", "Successo", JOptionPane.INFORMATION_MESSAGE, Utility.getImgSuccess());
								CreditCardPage.this.dispose(); //chiudo la finestra
							} else {
								JOptionPane.showMessageDialog(pCreditCard, "Numero di carta non valido!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
							}
						} else {
							JOptionPane.showMessageDialog(pCreditCard, "CVV non valido!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
						}
					} else {
						JOptionPane.showMessageDialog(pCreditCard, "Data di scadenza non valida!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
					}
				} else {
					JOptionPane.showMessageDialog(pCreditCard, "Compilare tutti i campi!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
				}
			}
		});
	}
}
