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

/**
 * Classe che rappresenta la finestra relativa al catalogo dei prodotti.
 * 
 * @author Eugenia Esposito
 *
 */
public class ProductListPage extends JFrame{
	private JPanel pPList;
	private AdminOperations Operations;
	
	/**
	 * Costruttore che  inizializza tutte le componenti della schermata.
	 */
	public ProductListPage() {
		Operations = new AdminOperations();
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Catalogo");
		setBounds(100, 100, 800, 470);
				
		pPList = new JPanel();
		pPList.setForeground(Color.WHITE);
		pPList.setBackground(Utility.getNewBlu());
		pPList.setLayout(null);
		setContentPane(pPList);

		JLabel lblBook = new JLabel("Libri");
		lblBook.setForeground(Color.WHITE);
		lblBook.setFont(new Font("Verdana", Font.BOLD, 14));
		lblBook.setBounds(350, 10, 100, 20);
		pPList.add(lblBook);
		
		JLabel lblCD = new JLabel("CD");
		lblCD.setForeground(Color.WHITE);
		lblCD.setFont(new Font("Verdana", Font.BOLD, 14));
		lblCD.setBounds(355, 195, 100, 20);
		pPList.add(lblCD);
		
		JScrollPane scrollPbook = new JScrollPane();
		scrollPbook.setBounds(20, 35, 740, 150);
		pPList.add(scrollPbook);
		
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
		scrollPcd.setBounds(20, 220, 740, 150);
		pPList.add(scrollPcd);
		
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
		btnBack.setBounds(320, 385, 100, 30);
		pPList.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuAdmin mA = new MenuAdmin();
				ProductListPage.this.dispose(); //chiudo la pagina del catalogo
				mA.setVisible(true); //rendo visibile la pagina del menu admin
			}
		});
	}
}
