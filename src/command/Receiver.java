package command;

import java.math.BigDecimal;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import classiDB.Book;
import classiDB.CD;
import classiDB.DBBookStore;
import strategy.PaymentStrategy;
import viewHome.LoginPage;
import visitor.ShoppingCart;

/**
 * Classe che contiene l'esecuzione materiale dei comandi.
 * 
 * @author Eugenia Esposito
 *
 */
public class Receiver {
	
	/**
	 * Metodo che ritorna la tabella contenente l'elenco degli utenti.
	 * 
	 * @return la tabella con l'elenco utenti
	 */
	public DefaultTableModel showUserList() {
		DBBookStore D = DBBookStore.getInstance();
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		Vector<String> colNames = new Vector<String>();
		try {
			D.openConnection();
			//effettuo una query per visualizzare nome, cognome e username di tutti gli utenti
			D.rs = D.st.executeQuery("SELECT nome, cognome, username FROM account;"); 
			ResultSetMetaData metaData = D.rs.getMetaData();
			int colCount = metaData.getColumnCount();
			for(int col = 1; col <= colCount; col++) {
				colNames.add(metaData.getColumnName(col)); //memorizzo l'intestazione delle colonne
			}
			while(D.rs.next()) {
				Vector<Object> v = new Vector<Object>();
				for(int colIndex = 1; colIndex <= colCount; colIndex++) {
					v.add(D.rs.getObject(colIndex)); //aggiungo il valore della riga corrente per ogni colonna
				}
				data.add(v); //aggiungo l'intera riga al vector di vector
			}
			D.closeConnection(); 
		} catch (SQLException e) {
			System.out.println(e);
		}
		return new DefaultTableModel(data, colNames); //ritorno la tabella
	}
	
	/**
	 * Metodo che inserisce <code>B</code> o <code>C</code> nel database.
	 * 
	 * @param B il libro da aggiungere
	 * @param C il CD da aggiungere
	 * @throws SQLIntegrityConstraintViolationException se si è violato un vincolo di integrità
	 * @throws SQLException se ci sono altri errori nel database
	 */
	public void addProduct(Book B, CD C) throws SQLIntegrityConstraintViolationException, SQLException {
		DBBookStore D = DBBookStore.getInstance();
		try {
			D.openConnection();
			if(B.getEAN() != null) {
				//inserisco nella tabella product l'ean, la data di pubblicazione e il prezzo
				D.st.executeUpdate("INSERT INTO `bookstore`.`product` (`EAN`,`pubblicazione`,`prezzo`) VALUES "
						+ "('" + B.getEAN() + "','" + B.getPubblicazione() + "','" + B.getPrezzo() + "');");
				//inserisco nella tabella book ean, titolo, autore, editore, pagine
				D.st.executeUpdate("INSERT INTO `bookstore`.`book` (`EAN_book`,`titolo`,`autore`,`editore`,`pagine`) VALUES "
						+ "('" + B.getEAN() + "','" + B.getTitolo() + "','" + B.getAutore() + "','" + B.getEditore() + "','" + B.getPagine() + "');");
			} else if(C.getEAN() != null) {
				//inserisco nella tabella product ean, data di pubblicazione, prezzo
				D.st.executeUpdate("INSERT INTO `bookstore`.`product` (`EAN`,`pubblicazione`,`prezzo`) VALUES "
						+ "('" + C.getEAN() + "','" + C.getPubblicazione() + "','" + C.getPrezzo() + "');");
				//inserisco nella tabella cd ean, album, artista, etichetta, tracce
				D.st.executeUpdate("INSERT INTO `bookstore`.`cd` (`EAN_cd`,`album`,`artista`,`etichetta`,`tracks`) VALUES "
						+ "('" + C.getEAN() + "','" + C.getAlbum() + "','" + C.getArtista() + "','" + C.getEtichetta() + "','" + C.getTracks() + "');");
			}
			D.closeConnection();
		} catch(SQLIntegrityConstraintViolationException s) {
			System.out.println(s);
			throw(s);
		} catch(SQLException e) {
			System.out.println(e);
			throw(e);
		}
	}
	
	/**
	 * Metodo che ricerca all'interno del database un determinato prodotto e,
	 * se lo trova lo restituisce, altrimenti ritorna null.
	 * 
	 * @param EAN l'EAN del prodotto da ricercare
	 * @return <code>Book</code> se il prodotto trovato è un libro,
	 * <code>CD</code> se il prodotto trovato è un CD, <code>null</code> se non ci sono riscontri.
	 * @throws SQLException se si verificano errori nel database
	 */
	public Object findProduct(String EAN) throws SQLException {
		DBBookStore D = DBBookStore.getInstance();
		try {
			D.openConnection();
			//eseguo una query facendo il join tra product e book e cercando l'ean
			D.rs = D.st.executeQuery("SELECT p.EAN, b.titolo, b.autore, b.editore, b.pagine, p.pubblicazione, p.prezzo "
					+ "FROM product p, book b WHERE p.EAN = b.EAN_book and p.EAN = '" + EAN + "';");
			if(D.rs.next()) //se c'è una riga significa che il prodotto trovato è un libro
				//ritorno il libro
				return new Book(D.rs.getString("EAN"), D.rs.getString("titolo"), D.rs.getString("autore"), D.rs.getString("editore"), 
						D.rs.getInt("pagine"), D.rs.getBigDecimal("prezzo"), D.rs.getDate("pubblicazione"));
			else { //se la query non da risultato
				//eseguo una query facendo il join tra product e cd e cercando l'ean
				D.rs = D.st.executeQuery("SELECT p.EAN, c.album, c.artista, c.etichetta, c.tracks, p.pubblicazione, p.prezzo "
						+ "FROM product p, cd c WHERE p.EAN = c.EAN_cd and p.EAN = '" + EAN + "';");
				if(D.rs.next()) //se c'è una riga significa che il prodotto trovato è un cd
					//ritorno il cd
					return new CD(D.rs.getString("EAN"), D.rs.getString("album"), D.rs.getString("artista"), D.rs.getString("etichetta"), 
							D.rs.getBigDecimal("prezzo"), D.rs.getDate("pubblicazione"), D.rs.getInt("tracks"));
			}
			D.closeConnection();
		} catch(SQLException e) {
			System.out.println(e);
			throw(e);
		}
		return null; //se nessuna query ha dato risultato e quindi l'ean da ricercare non è presente nel database, ritorno null
	}
	
	/**
	 * Metodo che aggiorna il database con i nuovi dati relativi a <code>product</code>.
	 * 
	 * @param product il prodotto da modificare
	 * @throws SQLException se si verificano errori nel database
	 */
	public void editProduct(Object product) throws SQLException {
		DBBookStore D = DBBookStore.getInstance();
		try {
			D.openConnection();
			if(product.getClass().getSimpleName().equals("Book")) { //se il prodotto è un libro
				Book B = (Book)product; //effettuo il cast a book
				//aggiorno la tabella product
				D.st.executeUpdate("UPDATE product SET pubblicazione = '" + B.getPubblicazione() + "', "
						+ "prezzo = '" + B.getPrezzo() + "' WHERE EAN = '" + B.getEAN() + "';");
				//aggiorno la tabella book
				D.st.executeUpdate("UPDATE book SET titolo = '" + B.getTitolo() + "', autore = '" + B.getAutore() + "', "
						+ "editore = '" + B.getEditore() + "', pagine = '" + B.getPagine() + "' WHERE EAN_book = '" + B.getEAN() + "';");
			} else if(product.getClass().getSimpleName().equals("CD")) { //se il prodotto è un cd
				CD C = (CD)product; //effettuo il cast a cd
				//aggiorno la tabella product
				D.st.executeUpdate("UPDATE product SET pubblicazione = '" + C.getPubblicazione() + "', "
						+ "prezzo = '" + C.getPrezzo() + "' WHERE EAN = '" + C.getEAN() + "';");
				//aggiorno la tabella cd
				D.st.executeUpdate("UPDATE cd SET album = '" + C.getAlbum() + "', artista = '" + C.getArtista() + "', "
						+ "etichetta = '" + C.getEtichetta() + "', tracks = '" + C.getTracks() + "' WHERE EAN_cd = '" + C.getEAN() + "';");
			}
			D.closeConnection();
		} catch(SQLException e) {
			System.out.println(e);
			throw(e);
		}
	}
	
	/**
	 * Metodo che elimina dal database un determinato prodotto.
	 * 
	 * @param ean l'EAN del prodotto da eliminare
	 * @throws SQLIntegrityConstraintViolationException se si è violato un vincolo di integrità
	 * @throws SQLException se si verificano errori nel database
	 */
	public void deleteProduct(String ean) throws SQLIntegrityConstraintViolationException, SQLException {
		DBBookStore D = DBBookStore.getInstance();
		try {
			D.openConnection();
			//eseguo la query per eliminare il prodotto con quell'ean
			D.st.executeUpdate("DELETE FROM product WHERE EAN = '" + ean + "';");
			D.closeConnection();
		} catch(SQLIntegrityConstraintViolationException s) {
			System.out.println(s);
			throw(s);
		} catch(SQLException e) {
			System.out.println(e);
			throw(e);
		}
	}
	
	/**
	 * Metodo che ritorna la tabella contenente i prodotti della categoria <code>product</code>.
	 * 
	 * @param product la categoria dei prodotti da visualizzare
	 * @return la tabella con i prodotti di una categoria
	 */
	public DefaultTableModel showProductList(String product) {
		DBBookStore D = DBBookStore.getInstance();
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		Vector<String> colNames = new Vector<String>();
		try {
			D.openConnection();
			if(product.equals("Book")) //se la categoria è book
				//effettuo la query per selezionare tutti i libri
				D.rs = D.st.executeQuery("SELECT EAN, titolo, autore, editore, pagine, pubblicazione, prezzo "
					+ "FROM product p, book b WHERE p.EAN = b.EAN_book");
			else if(product.equals("CD")) //se la categoria è cd
				//effettuo la query per selezionare tutti i cd
				D.rs = D.st.executeQuery("SELECT EAN, album, artista, etichetta, tracks, pubblicazione, prezzo "
						+ "FROM product p, cd c WHERE p.EAN = c.EAN_cd");
			ResultSetMetaData metaData = D.rs.getMetaData();
			int colCount = metaData.getColumnCount();
			for(int col = 1; col <= colCount; col++) {
				colNames.add(metaData.getColumnName(col)); //memorizzo l'intestazione delle colonne
			}
			while(D.rs.next()) {
				Vector<Object> v = new Vector<Object>();
				for(int colIndex = 1; colIndex <= colCount; colIndex++) {
					v.add(D.rs.getObject(colIndex)); //aggiungo il valore di una cella per ogni colonna
				}
				data.add(v); //aggiungo l'intera riga al vector di vector
			}
			D.closeConnection(); 
		} catch (SQLException e) {
			System.out.println(e);
		}
		return new DefaultTableModel(data, colNames); //ritorno la tabella
	}
	
	/**
	 * Metodo che effettua una ricerca all'interno del database e
	 * ritorna la tabella con il risultato.
	 * 
	 * @param word la parola da ricercare
	 * @param tipo la categoria di prodotto da ricercare
	 * @return la tabella con il risultato della ricerca
	 */
	public DefaultTableModel searchWord(String word, String tipo) {
		DBBookStore D = DBBookStore.getInstance();
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		Vector<String> colNames = new Vector<String>();
		try {
			D.openConnection();
			if(tipo.equals("Libri")) //se la categoria è libri
				//eseguo la query nella tabella opportuna per ricercare la parola nei campi titolo, autore ed editore
				D.rs = D.st.executeQuery("SELECT EAN, titolo, autore, editore, pagine, pubblicazione, prezzo "
						+ "FROM product p, book b WHERE p.EAN = b.EAN_book AND "
						+ "(titolo LIKE '%" + word + "%' OR autore LIKE '%" + word + "%' OR editore LIKE '%" + word + "%');");
			else if(tipo.equals("CD")) //se la categoria è cd
				//eseguo la query nella tabella opportuna per ricercare la parola nei campi album, artista, etichetta
				D.rs = D.st.executeQuery("SELECT EAN, album, artista, etichetta, tracks, pubblicazione, prezzo "
						+ "FROM product p, cd c WHERE p.EAN = c.EAN_cd AND "
						+ "(album LIKE '%" + word + "%' OR artista LIKE '%" + word + "%' OR etichetta LIKE '%" + word + "%');");
			ResultSetMetaData metaData = D.rs.getMetaData();
			int colCount = metaData.getColumnCount();
			for(int col = 1; col <= colCount; col++)
				colNames.add(metaData.getColumnName(col)); //memorizzo l'intestazione delle colonne
			while(D.rs.next()) {
				Vector<Object> v = new Vector<Object>();
				for(int colIndex = 1; colIndex <= colCount; colIndex++)
					v.add(D.rs.getObject(colIndex)); //aggiungo il valore della cella della riga corrente per ogni colonna
				data.add(v); //aggiungo l'intera riga al vector di vector
			}
			D.closeConnection();
		} catch(SQLException e) {
			System.out.println(e);
		}
		return new DefaultTableModel(data, colNames); //ritorno la tabella
	}
	
	/**
	 * Metodo che inizializza <code>ShoppingCart</code> con tutti i prodotti che ci sono nel carrello.
	 * 
	 * @param idCart l'id del carrello
	 * @return l'istanza della classe <code>ShoppingCart</code> contenente i dati del carrello <code>idCart</code>
	 */
	public ShoppingCart setCart(int idCart) {
		ShoppingCart Cart = new ShoppingCart(idCart);
		Vector<Book> books = bookCart();
		for(int i=0; i<countProductCart()[0]; i++) //ciclo sul numero dei libri presenti nel carrello
			Cart.addProduct(new Book(books.elementAt(i).getEAN(), books.elementAt(i).getPrezzo())); //aggiungo il libro
		Vector<CD> cds = cdCart();
		for(int i=0; i<countProductCart()[1]; i++) //ciclo sul numero di cd presenti nel carrello
			Cart.addProduct(new CD(cds.elementAt(i).getEAN(), cds.elementAt(i).getPrezzo())); //aggiungo il cd
		return Cart;
	}
	
	/**
	 * Metodo che ritorna un array contenente il numero di libri e il numero di cd presenti nel carrello.
	 * 
	 * @return il numero di libri e cd nel carrello
	 */
	public int[] countProductCart() {
		DBBookStore D = DBBookStore.getInstance();
		int[] count = new int[2];
		try {
			D.openConnection();
			//eseguo una query che conta i libri nel carrello dell'utente corrente
			D.rs = D.st.executeQuery("SELECT COUNT(*) FROM cart_detail cd, cart c, book b "
					+ "WHERE cd.idcart = c.idcart AND cd.EAN_product = b.EAN_book "
					+ "AND user = '" + LoginPage.getUsername() + "' AND c.pagato = 0;");
			while(D.rs.next()) //se ce ne sono
				count[0] = D.rs.getInt(1); //salvo la quantità nella prima posizione dell'array
			//eseguo una query che conta i cd nel carrello dell'utente corrente
			D.rs = D.st.executeQuery("SELECT COUNT(*) FROM cart_detail cd, cart c, cd cc "
					+ "WHERE cd.idcart = c.idcart AND cd.EAN_product = cc.EAN_cd "
					+ "AND user = '" + LoginPage.getUsername() + "' AND c.pagato = 0;");
			while(D.rs.next()) //se ce ne sono
				count[1] = D.rs.getInt(1); //salvo la quantità nella seconda posizione dell'array
			D.closeConnection();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return count; //ritorno l'array
	}
	
	/**
	 * Metodo che ritorna i libri presenti nel carrello di un utente.
	 * 
	 * @return un vector con i libri nel carrello
	 */
	public Vector<Book> bookCart() {
		DBBookStore D = DBBookStore.getInstance();
		Vector<Book> books = new Vector<Book>();
		try {
			D.openConnection();
			//eseguo la query per trovare i libri presenti nel carrello di un utente
			D.rs = D.st.executeQuery("SELECT p.EAN, p.prezzo FROM cart c, cart_detail cd, product p, book b "
					+ "WHERE c.idcart = cd.idcart AND cd.EAN_product = p.EAN AND p.EAN = b.EAN_book "
					+ "AND c.user ='" + LoginPage.getUsername() + "' AND c.pagato = 0;");
			while(D.rs.next()) { //finchè ci sono delle righe
				String ean = D.rs.getString(1);
				BigDecimal prezzo = D.rs.getBigDecimal(2);
				books.add(new Book(ean, prezzo)); //aggiungo il libro al vector
			}
			D.closeConnection();
		} catch(SQLException e) {
			System.out.println(e);
		}
		return books; //ritorno il vector contenente tutti i libri nel carrello
	}
	
	/**
	 * Metodo che restituisce i cd presenti nel carrello di un utente.
	 * 
	 * @return un vector contenente i cd nel carrello
	 */
	public Vector<CD> cdCart() {
		DBBookStore D = DBBookStore.getInstance();
		Vector<CD> cds = new Vector<CD>();
		try {
			D.openConnection();
			//eseguo la query per trovare i cd presenti nel carrello di un utente
			D.rs = D.st.executeQuery("SELECT p.EAN, p.prezzo FROM cart c, cart_detail cd, product p, cd cc "
					+ "WHERE c.idcart = cd.idcart AND cd.EAN_product = p.EAN AND p.EAN = cc.EAN_cd "
					+ "AND c.user = '" + LoginPage.getUsername() + "' AND c.pagato = 0;");
			while(D.rs.next()) {
				String ean = D.rs.getString(1);
				BigDecimal prezzo = D.rs.getBigDecimal(2);
				cds.add(new CD(ean, prezzo)); //aggiungo il cd al vector
			}
			D.closeConnection();
		} catch(SQLException e) {
			System.out.println(e);
		}
		return cds; //ritorno il vector con tutti i cd che ci sono nel carrello
	}
	
	/**
	 * Metodo che ritorna la tabella contenente tutti i prodotti presenti nel carrello.
	 * 
	 * @return la tabella con i prodotti nel carrello
	 */
	public DefaultTableModel showCart() {
		DBBookStore D = DBBookStore.getInstance();
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		Vector<String> colNames = new Vector<String>();
		try {
			D.openConnection();
			//eseguo la query per visualizzare tutti i prodotti nel carrello dell'utente corrente
			D.rs = D.st.executeQuery("SELECT cd.idcart_detail, b.titolo, b.autore, p.prezzo "
					+ "FROM cart_detail cd, cart c, book b, product p "
					+ "WHERE cd.idcart = c.idcart AND cd.EAN_product = p.EAN AND p.EAN = b.EAN_book "
					+ "AND user = '" + LoginPage.getUsername() + "' AND c.pagato = 0 "
					+ "UNION "
					+ "SELECT cd.idcart_detail, cc.album, cc.artista, p.prezzo "
					+ "FROM cart_detail cd, cart c, cd cc, product p "
					+ "WHERE cd.idcart = c.idcart AND cd.EAN_product = p.EAN AND p.EAN = cc.EAN_cd "
					+ "AND user = '" + LoginPage.getUsername() + "' AND c.pagato = 0;");
			ResultSetMetaData metaData = D.rs.getMetaData();
			int colCount = metaData.getColumnCount();
			for(int col = 1; col <= colCount; col++) {
				colNames.add(metaData.getColumnName(col));
			}
			while(D.rs.next()) {
				Vector<Object> v = new Vector<Object>();
				for(int colIndex = 1; colIndex <= colCount; colIndex++) {
					v.add(D.rs.getObject(colIndex));
				}
				data.add(v);
			}
			D.closeConnection(); 
		} catch (SQLException e) {
			System.out.println(e);
		}
		return new DefaultTableModel(data, colNames);
	}
	
	/**
	 * Metodo che esegue il pagamento.
	 * 
	 * @param paymentMethod il metodo di pagamento scelto
	 * @param total il totale da pagare
	 * @param idCart l'id del carrello
	 * @throws SQLException se ci sono errori nel database
	 * @see PaymentStrategy
	 */
	public void pay(PaymentStrategy paymentMethod, BigDecimal total, int idCart) throws SQLException {
		paymentMethod.pay(total); //eseguo il pagamento
		DBBookStore D = DBBookStore.getInstance();
		try {
			D.openConnection();
			//aggiorno il database impostando pagato a 1 per indicare che quel carrello è stato pagato
			D.st.executeUpdate("UPDATE cart SET pagato = 1 WHERE idcart = " + idCart + ";");
			D.closeConnection();
		} catch(SQLException e) {
			System.out.println(e);
		}
	}
}
