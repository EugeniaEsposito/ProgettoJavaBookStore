package viewAdmin;

import classiDB.Book;
import classiDB.CD;
//import classiDB.DBBookStore;
import utility.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
//import java.sql.SQLException;
//import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

/**
 * Classe che rappresenta la finestra relativa all'inserimento di un prodotto.
 * 
 * @author Eugenia Esposito
 *
 */
public class AddProductPage extends JFrame {
	private JPanel pAddP;
	private AdminOperations Operations;
	private JTextField tfEAN;
	private JTextField tfTitleAlbum;
	private JTextField tfAuthorArtist;
	private JTextField tfPublisherRecord;
	private JTextField tfPagesTracks;
	private JTextField tfPrice;
	private JDateChooser date;

	/**
	 * Costruttore che inizializza tutte le componenti della schermata.
	 */
	public AddProductPage() {
		Operations = new AdminOperations();
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Aggiungi Prodotto");
		setBounds(100, 100, 405, 395);
						
		Vector<JLabel> labelsBook = new Vector<JLabel>();
		Vector<JLabel> labelsCD = new Vector<JLabel>();
		Vector<JTextField> vectorTF = new Vector<JTextField>();
		
		pAddP = new JPanel();
		pAddP.setForeground(Color.WHITE);
		pAddP.setBackground(Utility.getNewBlu());
		pAddP.setLayout(null);
		setContentPane(pAddP);
		
		JLabel lblAddP = new JLabel("Inserimento");
		lblAddP.setForeground(Color.WHITE);
		lblAddP.setFont(new Font("Verdana", Font.BOLD, 16));
		lblAddP.setBounds(135, 10, 150, 20);
		pAddP.add(lblAddP);
		
		JRadioButton radioBook = new JRadioButton("Libro");
		radioBook.setBounds(110, 40, 100, 30);
		radioBook.setBackground(null);
		radioBook.setForeground(Color.WHITE);
		radioBook.setSelected(true);
		pAddP.add(radioBook);
		
		JRadioButton radioCD = new JRadioButton("CD");
		radioCD.setBounds(260, 40, 100, 30);
		radioCD.setBackground(null);
		radioCD.setForeground(Color.WHITE);
		pAddP.add(radioCD);
		
		ButtonGroup group = new ButtonGroup();
		group.add(radioBook);
		group.add(radioCD);
		
		JLabel lblEAN = new JLabel("EAN");
		lblEAN.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblEAN.setForeground(Color.WHITE);
		lblEAN.setBounds(24, 80, 100, 20);
		pAddP.add(lblEAN);
		
		tfEAN = new JTextField();
		vectorTF.add(tfEAN);
		tfEAN.setFont(new Font("Verdana", Font.PLAIN, 13));
		tfEAN.setBounds(150, 80, 200, 20);
		pAddP.add(tfEAN);
		
		JLabel lblTitle = new JLabel("Titolo");
		labelsBook.add(lblTitle);
		lblTitle.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(24, 110, 100, 20);
		pAddP.add(lblTitle);
		
		JLabel lblAlbum = new JLabel("Album");
		labelsCD.add(lblAlbum);
		lblAlbum.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblAlbum.setForeground(Color.WHITE);
		lblAlbum.setBounds(24, 110, 100, 20);
		lblAlbum.setVisible(false);
		pAddP.add(lblAlbum);
		
		tfTitleAlbum = new JTextField();
		vectorTF.add(tfTitleAlbum);
		tfTitleAlbum.setFont(new Font("Verdana", Font.PLAIN, 13));
		tfTitleAlbum.setBounds(150, 110, 200, 20);
		pAddP.add(tfTitleAlbum);
		
		JLabel lblAuthor = new JLabel("Autore");
		labelsBook.add(lblAuthor);
		lblAuthor.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblAuthor.setForeground(Color.WHITE);
		lblAuthor.setBounds(24, 140, 100, 20);
		pAddP.add(lblAuthor);
		
		JLabel lblArtist = new JLabel("Artista");
		labelsCD.add(lblArtist);
		lblArtist.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblArtist.setForeground(Color.WHITE);
		lblArtist.setBounds(24, 140, 100, 20);
		lblArtist.setVisible(false);
		pAddP.add(lblArtist);
		
		tfAuthorArtist = new JTextField();
		vectorTF.add(tfAuthorArtist);
		tfAuthorArtist.setFont(new Font("Verdana", Font.PLAIN, 13));
		tfAuthorArtist.setBounds(150, 140, 200, 20);
		pAddP.add(tfAuthorArtist);
		
		JLabel lblPublisher = new JLabel("Editore");
		labelsBook.add(lblPublisher);
		lblPublisher.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblPublisher.setForeground(Color.WHITE);
		lblPublisher.setBounds(24, 170, 100, 20);
		pAddP.add(lblPublisher);
		
		JLabel lblRecord = new JLabel("Etichetta");
		labelsCD.add(lblRecord);
		lblRecord.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblRecord.setForeground(Color.WHITE);
		lblRecord.setBounds(24, 170, 100, 20);
		lblRecord.setVisible(false);
		pAddP.add(lblRecord);
		
		tfPublisherRecord = new JTextField();
		vectorTF.add(tfPublisherRecord);
		tfPublisherRecord.setFont(new Font("Verdana", Font.PLAIN, 13));
		tfPublisherRecord.setBounds(150, 170, 200, 20);
		pAddP.add(tfPublisherRecord);
		
		JLabel lblPages = new JLabel("Pagine");
		labelsBook.add(lblPages);
		lblPages.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblPages.setForeground(Color.WHITE);
		lblPages.setBounds(24, 200, 100, 20);
		pAddP.add(lblPages);
		
		JLabel lblTracks = new JLabel("#Tracce");
		labelsCD.add(lblTracks);
		lblTracks.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblTracks.setForeground(Color.WHITE);
		lblTracks.setBounds(24, 200, 100, 20);
		lblTracks.setVisible(false);
		pAddP.add(lblTracks);
		
		tfPagesTracks = new JTextField();
		vectorTF.add(tfPagesTracks);
		tfPagesTracks.setFont(new Font("Verdana", Font.PLAIN, 13));
		tfPagesTracks.setBounds(150, 200, 50, 20);
		pAddP.add(tfPagesTracks);
		
		JLabel lblRelease = new JLabel("Pubblicazione");
		lblRelease.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblRelease.setForeground(Color.WHITE);
		lblRelease.setBounds(24, 230, 100, 20);
		pAddP.add(lblRelease);
		
		date = new JDateChooser();
		date.setDateFormatString("yyyy-MM-dd");
		date.setFont(new Font("Verdana", Font.PLAIN, 13));
		date.setBounds(150, 230, 120, 20);
		pAddP.add(date);
		
		JLabel lblPrice = new JLabel("Prezzo");
		lblPrice.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setBounds(24, 260, 100, 20);
		pAddP.add(lblPrice);
		
		tfPrice = new JTextField();
		vectorTF.add(tfPrice);
		tfPrice.setFont(new Font("Verdana", Font.PLAIN, 13));
		tfPrice.setBounds(150, 260, 50, 20);
		pAddP.add(tfPrice);
		
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Utility.clearTF(vectorTF, date); //pulisco tutti i campi
				if(e.getSource() == radioBook) { //se il radio button selezionato è quello del libro
					Utility.setVisibleLbl(labelsBook, true); //rendo visibili le label relative al libro
					Utility.setVisibleLbl(labelsCD, false); //rendo invisibili le label relative al CD
				} else if(e.getSource() == radioCD) { //se il radio button selezionato è quello del CD
					Utility.setVisibleLbl(labelsBook, false); //rendo invisibili le label relativi al libro
					Utility.setVisibleLbl(labelsCD, true); //rendo visibili le label relative al CD
				}
			}
		};
		
		radioBook.addActionListener(listener);
		radioCD.addActionListener(listener);
		
		JButton btnBack = new JButton("Indietro");
		btnBack.setFont(new Font("Verdana", Font.BOLD, 12));
		btnBack.setBackground(Color.WHITE);
		btnBack.setForeground(Utility.getNewBlu());
		btnBack.setBounds(24, 300, 100, 30);
		pAddP.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuAdmin mA = new MenuAdmin();
				AddProductPage.this.dispose(); //chiudo la pagina dell'inserimento
				mA.setVisible(true); //rendo visibile la pagina del menu admin
			}
		});
		
		JButton btnAdd = new JButton("Aggiungi");
		btnAdd.setFont(new Font("Verdana", Font.BOLD, 12));
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setForeground(Utility.getNewBlu());
		btnAdd.setBounds(250, 300, 100, 30);
		pAddP.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Book B = new Book(); //inizializzo una variabile book
				CD C = new CD(); //inizializzo una variabile cd
				if(Utility.isEAN(tfEAN.getText())) { //controllo se l'EAN inserito è valido
					if(Utility.isBigDecimal(tfPrice.getText())) { //se il prezzo è un bigdecimal
						if(!tfEAN.getText().isBlank() && !tfTitleAlbum.getText().isBlank() && !tfAuthorArtist.getText().isBlank()
								&& !tfPublisherRecord.getText().isBlank() && !tfPagesTracks.getText().isBlank() && !tfPrice.getText().isBlank()
								&& date.getDate() != null) { //se tutti i campi non sono vuoti
							if(Utility.isNumber(tfPagesTracks.getText())) { //se il campo è un numero
								java.sql.Date d = new java.sql.Date(date.getDate().getTime());
								BigDecimal price = new BigDecimal(tfPrice.getText());
								if(radioBook.isSelected()) { //se è selezionato il radio button relativo al libro
									B = new Book(tfEAN.getText(), tfTitleAlbum.getText(), tfAuthorArtist.getText(), tfPublisherRecord.getText(), Integer.parseInt(tfPagesTracks.getText()), price, d);
								} else if(radioCD.isSelected()) { //se è selezionato il radio button relativo al cd
									C = new CD(tfEAN.getText(), tfTitleAlbum.getText(), tfAuthorArtist.getText(), tfPublisherRecord.getText(), price, d, Integer.parseInt(tfPagesTracks.getText()));
								}
								try {
									Operations.addProduct(B, C); //aggiungo il prodotto
									JOptionPane.showMessageDialog(pAddP, "Inserimento effettuato!", "Successo", JOptionPane.INFORMATION_MESSAGE, Utility.getImgSuccess());
									Utility.clearTF(vectorTF, date); //pulisco i campi
								} catch(Exception e1) {
									JOptionPane.showMessageDialog(pAddP, "Errore", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
								}
							} else { //se il campo pagine/tracks non è un numero
								String lbl = null;
								if(lblPages.isEnabled())
									lbl = lblPages.getText();
								else if(lblTracks.isEnabled())
									lbl = lblTracks.getText();
								JOptionPane.showMessageDialog(pAddP, "Campo " + lbl + " non valido!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
							}
						} else {
							JOptionPane.showMessageDialog(pAddP, "Tutti i campi sono obbligatori", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
						}
					} else {
						JOptionPane.showMessageDialog(pAddP, "Il prezzo deve essere un numero!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
					}
				} else {
					JOptionPane.showMessageDialog(pAddP, "EAN non valido!", "Errore", JOptionPane.ERROR_MESSAGE, Utility.getImgError());
				}
			}
		});
	}
}
