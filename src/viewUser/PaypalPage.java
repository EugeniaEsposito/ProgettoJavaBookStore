package viewUser;

import utility.*;
import visitor.ShoppingCart;
import strategy.Paypal;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Classe che rappresenta la schermata relativa all'inserimento dei dati del conto paypal.
 * 
 * @author Eugenia Esposito
 *
 */
public class PaypalPage extends JFrame {
	private JPanel pPaypal;
	private UserOperations Operations;
	private JTextField tfEmail;
	private JTextField tfPassword;
	private ShoppingCart Cart;
	
	/**
	 * Costruttore che inizializza tutte le componenti della schermata.
	 * 
	 * @param ShoppingCart il carrello per recuperare le informazioni sul totale da pagare
	 */
	public PaypalPage(ShoppingCart ShoppingCart) {
		Cart = ShoppingCart;
		Operations = new UserOperations();
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Paypal");
		setBounds(400, 300, 300, 160);
				
		pPaypal = new JPanel();
		pPaypal.setLayout(null);
		setContentPane(pPaypal);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblEmail.setBounds(15, 15, 100, 20);
		pPaypal.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Verdana", Font.PLAIN, 12));
		tfEmail.setBounds(120, 15, 150, 20);
		pPaypal.add(tfEmail); 
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblPassword.setBounds(15, 45, 100, 20);
		pPaypal.add(lblPassword);
		
		tfPassword = new JPasswordField();
		tfPassword.setFont(new Font("Verdana", Font.PLAIN, 12));
		tfPassword.setBounds(120, 45, 150, 20);
		pPaypal.add(tfPassword);
		
		JButton btnCancel = new JButton("Annulla");
		btnCancel.setFont(new Font("Verdana", Font.BOLD, 12));
		btnCancel.setBounds(60, 80, 90, 25);
		pPaypal.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PaypalPage.this.dispose(); //chiudo la finestra del paypal
			}
		});
		
		JButton btnOk = new JButton("OK");
		btnOk.setFont(new Font("Verdana", Font.BOLD, 12));
		btnOk.setBounds(160, 80, 60, 25);
		pPaypal.add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!tfEmail.getText().isBlank() && !tfPassword.getText().isBlank()) { //se i campi non sono vuoti
					Operations.pay(new Paypal(tfEmail.getText(), tfPassword.getText()), Cart.calculateTotal(), Cart.getId()); //effettuo il pagamento
					JOptionPane.showMessageDialog(pPaypal, "Pagamento di € " + Cart.calculateTotal() + " con paypal effettuato!", "Successo", JOptionPane.INFORMATION_MESSAGE, Utility.getImgSuccess());
					PaypalPage.this.dispose(); //chiudo la finestra
				} else {
					JOptionPane.showMessageDialog(pPaypal, "Compilare entrambi i campi!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
				}
			}
		});
	}
}
