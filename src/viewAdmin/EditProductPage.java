package viewAdmin;

import classiDB.Book;
import classiDB.CD;
import utility.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

/**
 * Classe che rappresenta la finestra relativa alla modifica di un prodotto.
 * @author Eugenia Esposito
 *
 */
public class EditProductPage extends JFrame {
	private JPanel pEdit;
	private AdminOperations Operations;
	private JTextField tfEAN;
	private JTextField tfTitleAlbum;
	private JTextField tfAuthorArtist;
	private JTextField tfPublisherRecord;
	private JTextField tfPagesTracks;
	private JTextField tfPrice;
	private JDateChooser date;
	private boolean find = false;
	
	/**
	 * Costruttore che inizializza tutte le componenti della schermata.
	 */
	public EditProductPage() {
		Operations = new AdminOperations();
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Modifica Prodotto");
		setBounds(100, 100, 420, 395);
		
		ImageIcon cerca = new ImageIcon(EditProductPage.class.getResource("/images/search.png")); //icona della lente
		
		Vector<JLabel> labelsBook = new Vector<JLabel>();
		Vector<JLabel> labelsCD = new Vector<JLabel>();
		Vector <JTextField> vTF = new Vector<>();
		
		pEdit = new JPanel();
		pEdit.setForeground(Color.WHITE);
		pEdit.setBackground(Utility.getNewBlu());
		pEdit.setLayout(null);
		setContentPane(pEdit);
		
		JLabel lblEditTitle = new JLabel("Modifica");
		lblEditTitle.setForeground(Color.WHITE);
		lblEditTitle.setFont(new Font("Verdana", Font.BOLD, 16));
		lblEditTitle.setBounds(170, 10, 100, 20);
		pEdit.add(lblEditTitle);
		
		JLabel lblProduct = new JLabel("Tipo");
		lblProduct.setForeground(Color.WHITE);
		lblProduct.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblProduct.setBounds(24, 60, 100, 20);
		pEdit.add(lblProduct);
		
		String[] products = {"Libro", "CD"};
		JComboBox<String> box = new JComboBox<>(products);
		box.setFont(new Font("Verdana", Font.PLAIN, 13));
		box.setBounds(150, 60, 100, 20);
		pEdit.add(box);
		box.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) box.getSelectedItem();
				switch(s) {
					case "Libro": //se è selezionato libro
						Utility.setVisibleLbl(labelsBook, true); //rendo visibili le label relative al libro
						Utility.setVisibleLbl(labelsCD, false); //rendo invisibili le label relative al CD
						Utility.clearTF(vTF, date); //pulisco i campi
						tfEAN.setEnabled(true); //abilito il campo dell'EAN
						break;
					case "CD": //se è selezionato CD
						Utility.setVisibleLbl(labelsCD, true); //rendo visibili le label relative al CD
						Utility.setVisibleLbl(labelsBook, false); //rendo invisibili le label relative al libro
						Utility.clearTF(vTF, date); //pulisco i campi
						tfEAN.setEnabled(true); //abilito il campo dell'EAN
						break;
				}
			}
		});
		
		JLabel lblEan = new JLabel("EAN");
		lblEan.setForeground(Color.WHITE);
		lblEan.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblEan.setBounds(24, 90, 100, 20);
		pEdit.add(lblEan);
		
		tfEAN = new JTextField();
		vTF.add(tfEAN);
		tfEAN.setFont(new Font("Verdana", Font.PLAIN, 13));
		tfEAN.setBounds(150, 90, 200, 20);
		pEdit.add(tfEAN);
		
		JButton btnFind = new JButton(cerca);
		btnFind.setForeground(Color.WHITE);
		btnFind.setBackground(null);
		btnFind.setBorder(null);
		btnFind.setBounds(360, 90, 20, 20);
		pEdit.add(btnFind);
		btnFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Object product = Operations.findProduct(tfEAN.getText()); //cerco il prodotto con quell'EAN
					if(box.getSelectedIndex() == 0) { //se è selezionato libro
						if(product == null || !product.getClass().getSimpleName().equals("Book")) //se non trovo il libro
							JOptionPane.showMessageDialog(pEdit, "EAN non trovato!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
						else { //se trovo il libro, inserisco i dati nei campi relativi e li abilito alla modifica
							Book B = (Book)product;
							tfTitleAlbum.setText(B.getTitolo());
							tfAuthorArtist.setText(B.getAutore());
							tfPublisherRecord.setText(B.getEditore());
							tfPagesTracks.setText(String.valueOf(B.getPagine()));
							date.setDate(B.getPubblicazione());
							tfPrice.setText(String.valueOf(B.getPrezzo()));
							enableTF();
							find = true; //imposto find a true per indicare che ho trovato il libro
						}
					} else if(box.getSelectedIndex() == 1) { //se è selezionato CD
						if(product == null || !product.getClass().getSimpleName().equals("CD")) //se non trovo il CD
							JOptionPane.showMessageDialog(pEdit, "EAN non trovato!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
						else { //se trovo il CD, inserisco i dati nei campi relativi e li abilito alla modifica
							CD C = (CD)product;
							tfTitleAlbum.setText(C.getAlbum());
							tfAuthorArtist.setText(C.getArtista());
							tfPublisherRecord.setText(C.getEtichetta());
							tfPagesTracks.setText(String.valueOf(C.getTracks()));
							date.setDate(C.getPubblicazione());
							tfPrice.setText(String.valueOf(C.getPrezzo()));
							enableTF();
							find = true; //imposto find a true per indicare che ho trovato il CD
						}
					}
				} catch(Exception e1) {
					System.out.println(e1);
					JOptionPane.showMessageDialog(pEdit, "Errore", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
				}
			}
		});
		
		JLabel lblTitle = new JLabel("Titolo");
		labelsBook.add(lblTitle);
		lblTitle.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(24, 120, 100, 20);
		pEdit.add(lblTitle);
		
		JLabel lblAlbum = new JLabel("Album");
		labelsCD.add(lblAlbum);
		lblAlbum.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblAlbum.setForeground(Color.WHITE);
		lblAlbum.setBounds(24, 120, 100, 20);
		lblAlbum.setVisible(false);
		pEdit.add(lblAlbum);
		
		tfTitleAlbum = new JTextField();
		vTF.add(tfTitleAlbum);
		tfTitleAlbum.setFont(new Font("Verdana", Font.PLAIN, 13));
		tfTitleAlbum.setBounds(150, 120, 200, 20);
		tfTitleAlbum.setEnabled(false);
		pEdit.add(tfTitleAlbum);
		
		JLabel lblAuthor = new JLabel("Autore");
		labelsBook.add(lblAuthor);
		lblAuthor.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblAuthor.setForeground(Color.WHITE);
		lblAuthor.setBounds(24, 150, 100, 20);
		pEdit.add(lblAuthor);
		
		JLabel lblArtist = new JLabel("Artista");
		labelsCD.add(lblArtist);
		lblArtist.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblArtist.setForeground(Color.WHITE);
		lblArtist.setBounds(24, 150, 100, 20);
		lblArtist.setVisible(false);
		pEdit.add(lblArtist);
		
		tfAuthorArtist = new JTextField();
		vTF.add(tfAuthorArtist);
		tfAuthorArtist.setFont(new Font("Verdana", Font.PLAIN, 13));
		tfAuthorArtist.setBounds(150, 150, 200, 20);
		tfAuthorArtist.setEnabled(false);
		pEdit.add(tfAuthorArtist);
		
		JLabel lblPublisher = new JLabel("Editore");
		labelsBook.add(lblPublisher);
		lblPublisher.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblPublisher.setForeground(Color.WHITE);
		lblPublisher.setBounds(24, 180, 100, 20);
		pEdit.add(lblPublisher);
		
		JLabel lblRecord = new JLabel("Etichetta");
		labelsCD.add(lblRecord);
		lblRecord.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblRecord.setForeground(Color.WHITE);
		lblRecord.setBounds(24, 180, 100, 20);
		lblRecord.setVisible(false);
		pEdit.add(lblRecord);
		
		tfPublisherRecord = new JTextField();
		vTF.add(tfPublisherRecord);
		tfPublisherRecord.setFont(new Font("Verdana", Font.PLAIN, 13));
		tfPublisherRecord.setBounds(150, 180, 200, 20);
		tfPublisherRecord.setEnabled(false);
		pEdit.add(tfPublisherRecord);
		
		JLabel lblPages = new JLabel("Pagine");
		labelsBook.add(lblPages);
		lblPages.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblPages.setForeground(Color.WHITE);
		lblPages.setBounds(24, 210, 100, 20);
		pEdit.add(lblPages);
		
		JLabel lblTracks = new JLabel("#Tracce");
		labelsCD.add(lblTracks);
		lblTracks.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblTracks.setForeground(Color.WHITE);
		lblTracks.setBounds(24, 210, 100, 20);
		lblTracks.setVisible(false);
		pEdit.add(lblTracks);
		
		tfPagesTracks = new JTextField();
		vTF.add(tfPagesTracks);
		tfPagesTracks.setFont(new Font("Verdana", Font.PLAIN, 13));
		tfPagesTracks.setBounds(150, 210, 50, 20);
		tfPagesTracks.setEnabled(false);
		pEdit.add(tfPagesTracks);
		
		JLabel lblRelease = new JLabel("Pubblicazione");
		lblRelease.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblRelease.setForeground(Color.WHITE);
		lblRelease.setBounds(24, 240, 100, 20);
		pEdit.add(lblRelease);
		
		date = new JDateChooser();
		date.setDateFormatString("yyyy-MM-dd");
		date.setFont(new Font("Verdana", Font.PLAIN, 13));
		date.setBounds(150, 240, 120, 20);
		date.setEnabled(false);
		pEdit.add(date);
		
		JLabel lblPrice = new JLabel("Prezzo");
		lblPrice.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setBounds(24, 270, 100, 20);
		pEdit.add(lblPrice);
		
		tfPrice = new JTextField();
		vTF.add(tfPrice);
		tfPrice.setFont(new Font("Verdana", Font.PLAIN, 13));
		tfPrice.setBounds(150, 270, 50, 20);
		tfPrice.setEnabled(false);
		pEdit.add(tfPrice);
		
		JButton btnBack = new JButton("Indietro");
		btnBack.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBack.setBackground(Color.WHITE);
		btnBack.setForeground(Utility.getNewBlu());
		btnBack.setBounds(24, 310, 100, 30);
		pEdit.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuAdmin mA = new MenuAdmin();
				EditProductPage.this.dispose(); //chiudo la pagina relativa alla modifica 
				mA.setVisible(true); //rendo visibile la pagina del menu admin
			}
		});
		
		JButton btnEdit = new JButton("Modifica");
		btnEdit.setFont(new Font("Verdana", Font.BOLD, 12));
		btnEdit.setBackground(Color.WHITE);
		btnEdit.setForeground(Utility.getNewBlu());
		btnEdit.setBounds(270, 310, 100, 30);
		pEdit.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object product = null; //inizializzo la variabile
				if(find == false) { //se non ho trovato nessun prodotto
					JOptionPane.showMessageDialog(pEdit, "Nessun prodotto da modificare!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
				} else { //se c'è un prodotto da modificare
					if(!tfTitleAlbum.getText().isBlank() && !tfAuthorArtist.getText().isBlank()
							&& !tfPublisherRecord.getText().isBlank() && !tfPagesTracks.getText().isBlank() && !tfPrice.getText().isBlank()
							&& date.getDate() != null) { //se tutti i campi non sono vuoti
						java.sql.Date d = new java.sql.Date(date.getDate().getTime());
						if(Utility.isNumber(tfPagesTracks.getText())) { //se il campo è un numero
							int page_track = Integer.parseInt(tfPagesTracks.getText());
							if(Utility.isBigDecimal(tfPrice.getText())) { //se il prezzo è bigdecimal
								BigDecimal price = new BigDecimal(tfPrice.getText());
								if(box.getSelectedIndex() == 0) { //se è selezionato libro
									product = new Book(tfEAN.getText(), tfTitleAlbum.getText(), tfAuthorArtist.getText(), tfPublisherRecord.getText(), page_track, price, d);
								} else if(box.getSelectedIndex() == 1) { //se è selezionato CD
									product = new CD(tfEAN.getText(), tfTitleAlbum.getText(), tfAuthorArtist.getText(), tfPublisherRecord.getText(), price, d, page_track);
								}
								try {
									Operations.editProduct(product); //modifico il prodotto
									JOptionPane.showMessageDialog(pEdit, "Modifica effettuata!", "Successo", JOptionPane.INFORMATION_MESSAGE, Utility.getImgSuccess());
									Utility.clearTF(vTF, date);
								} catch(Exception e1) {
									JOptionPane.showMessageDialog(pEdit, "Errore", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
								}
							} else
								JOptionPane.showMessageDialog(pEdit, "Campo Prezzo non valido!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
						} else {
							String lbl = null;
							if(lblPages.isEnabled())
								lbl = lblPages.getText();
							else if(lblTracks.isEnabled())
								lbl = lblTracks.getText();
							JOptionPane.showMessageDialog(pEdit, "Campo " + lbl + " non valido!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
						}
					} else
						JOptionPane.showMessageDialog(pEdit, "Compilare tutti i campi!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
				}
			}
		});
	}
	
	/**
	 * Metodo che abilita tutti i campi tranne quello dell'EAN che lo rende non modificabile.
	 */
	private void enableTF() {
		tfEAN.setEnabled(false);
		tfTitleAlbum.setEnabled(true);
		tfAuthorArtist.setEnabled(true);
		tfPublisherRecord.setEnabled(true);
		tfPagesTracks.setEnabled(true);
		date.setEnabled(true);
		tfPrice.setEnabled(true);
	}
}
