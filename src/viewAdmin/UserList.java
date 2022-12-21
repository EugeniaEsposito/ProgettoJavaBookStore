package viewAdmin;

import utility.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 * Classe che rappresenta la schermata relativa all'elenco degli utenti.
 * 
 * @author Eugenia Esposito
 *
 */
public class UserList extends JFrame {
	private JPanel pUsers;
	private AdminOperations Operations;

	/**
	 * Costruttore che inizializza tutte le componenti della schermata.
	 */
	public UserList() {
		Operations = new AdminOperations();
		initialize();
	}
	
	private void initialize() {		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Elenco Utenti");
		setBounds(100, 100, 520, 370);
		
		pUsers = new JPanel();
		pUsers.setForeground(Color.WHITE);
		pUsers.setBackground(Utility.getNewBlu());
		pUsers.setLayout(null);
		setContentPane(pUsers);
		
		JLabel labelUsers = new JLabel("Utenti");
		labelUsers.setForeground(Color.WHITE);
		labelUsers.setFont(new Font("Verdana", Font.BOLD, 16));
		labelUsers.setLabelFor(pUsers);
		labelUsers.setHorizontalAlignment(SwingConstants.CENTER);
		labelUsers.setBounds(195, 10, 100, 20);
		pUsers.add(labelUsers);
		
		JScrollPane scrollP = new JScrollPane();
		scrollP.setBounds(40, 40, 420, 210);
		pUsers.add(scrollP);
		
		JTable table = new JTable(Operations.showUserList()); //visualizzo la tabella contenente gli utenti
		((DefaultTableModel)table.getModel()).removeRow(0); //elimino la riga relativa all'admin
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		scrollP.setViewportView(table);
		
		JButton btnBack = new JButton("Indietro");
		btnBack.setBackground(Color.WHITE);
		btnBack.setForeground(Utility.getNewBlu());
		btnBack.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBack.setBounds(40, 280, 100, 30);
		pUsers.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuAdmin mA = new MenuAdmin();
				UserList.this.dispose(); //chiudo la finestra dell'elenco utenti
				mA.setVisible(true); //rendo visibile la finestra del menu admin
			}
		});
	}
}
