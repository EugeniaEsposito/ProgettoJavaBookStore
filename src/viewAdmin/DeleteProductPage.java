package viewAdmin;

import utility.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Classe che rappresenta la finestra relativa alla rimozione di un prodotto.
 * 
 * @author Eugenia Esposito
 *
 */
public class DeleteProductPage extends JFrame {
	private JPanel pDelete;
	private AdminOperations Operations;
	
	/**
	 * Costruttore che inizializza tutte le componenti della schermata.
	 */
	public DeleteProductPage() {
		Operations = new AdminOperations();
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Catalogo");
		setBounds(100, 100, 780, 470);
				
		pDelete = new JPanel();
		pDelete.setForeground(Color.WHITE);
		pDelete.setBackground(Utility.getNewBlu());
		pDelete.setLayout(null);
		setContentPane(pDelete);

		JLabel lblBook = new JLabel("Libri");
		lblBook.setForeground(Color.WHITE);
		lblBook.setFont(new Font("Verdana", Font.BOLD, 14));
		lblBook.setBounds(350, 10, 100, 20);
		pDelete.add(lblBook);
		
		JLabel lblCD = new JLabel("CD");
		lblCD.setForeground(Color.WHITE);
		lblCD.setFont(new Font("Verdana", Font.BOLD, 14));
		lblCD.setBounds(355, 195, 100, 20);
		pDelete.add(lblCD);
		
		JScrollPane scrollPbook = new JScrollPane();
		scrollPbook.setBounds(20, 35, 720, 150);
		pDelete.add(scrollPbook);
		
		JTable tableBook = new JTable(Operations.showProductList("Book")) { //visualizzo la tabella contenente tutti i libri
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
		pDelete.add(scrollPcd);
		
		JTable tableCD = new JTable(Operations.showProductList("CD")) { //visualizzo la tabella contenente tutti i CD
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
		btnBack.setBounds(200, 385, 100, 30);
		pDelete.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuAdmin mA = new MenuAdmin();
				DeleteProductPage.this.dispose(); //chiudo la pagina relativa all'eliminazione
				mA.setVisible(true); //rendo visibile il menu admin
			}
		});
		
		JButton btnDelete = new JButton("Elimina");
		btnDelete.setForeground(Utility.getNewBlu());
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setFont(new Font("Verdana", Font.BOLD, 12));
		btnDelete.setBounds(450, 385, 100, 30);
		pDelete.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowB = tableBook.getSelectedRow();
				int rowC = tableCD.getSelectedRow();
				if(rowB != -1 && rowC != -1) { //se sono selezionate righe da entrambe le tabelle
					JOptionPane.showMessageDialog(pDelete, "Selezionare soltanto una riga!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
				} else if(rowB != -1) { //se è selezionata la riga nella tabella dei libri
					String ean = (String)tableBook.getValueAt(rowB, 0); //salvo l'ean del libro selezionato
					try {
						Operations.deleteProduct(ean); //elimino il libro
						JOptionPane.showMessageDialog(pDelete, "Libro eliminato!", "Successo", JOptionPane.INFORMATION_MESSAGE, Utility.getImgSuccess());
						((DefaultTableModel)tableBook.getModel()).removeRow(rowB); //aggiorno la tabella
					} catch(Exception e1) {
						JOptionPane.showMessageDialog(pDelete, "Errore2", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
					}
				} else if(rowC != -1) { //se è selezionata la riga nella tabella dei CD
					String ean = (String)tableCD.getValueAt(rowC, 0); //salvo l'ean del CD selezionato
					try {
						Operations.deleteProduct(ean); //elimino il CD
						JOptionPane.showMessageDialog(pDelete, "CD eliminato!", "Successo", JOptionPane.INFORMATION_MESSAGE, Utility.getImgSuccess());
						((DefaultTableModel)tableCD.getModel()).removeRow(rowC); //aggiorno la tabella
					} catch(Exception e1) {
						JOptionPane.showMessageDialog(pDelete, "Errore2", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
					}
				} else if(rowB == -1 && rowC == -1) //se nessuna riga è selezionata
					JOptionPane.showMessageDialog(pDelete, "Nessuna riga selezionata!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
			}
		});
	}
}