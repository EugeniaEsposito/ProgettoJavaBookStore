package classiDB;

import visitor.*;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * La classe <code>Book</code> implementa l'interfaccia <code>Product</code> 
 * e corrisponde all'omonima tabella del database.
 * 
 * @author Eugenia Esposito
 * @see Product
 */
public class Book implements Product {
	private String EAN;
	private String titolo;
	private String autore;
	private String editore;
	private int pagine;
	private BigDecimal prezzo;
	private Date pubblicazione;
	
	/**
	 * Costruttore della classe <code>Book</code>
	 * 
	 * @param EAN l'EAN del libro
	 * @param titolo il titolo del libro
	 * @param autore l'autore del libro
	 * @param editore l'editore del libro
	 * @param pagine il numero di pagine del libro
	 * @param prezzo il prezzo del libro espresso in <code>BigDecimal</code>
	 * @param pubblicazione la data di pubblicazione del libro
	 */
	public Book(String EAN, String titolo, String autore, String editore, int pagine, BigDecimal prezzo, Date pubblicazione) {
		this.EAN = EAN;
		this.titolo = titolo;
		this.autore = autore;
		this.editore = editore;
		this.pagine = pagine;
		this.prezzo = prezzo;
		this.pubblicazione = pubblicazione;
	}
	
	/**
	 * Costruttore vuoto che servirà in alcuni casi per inizializzare una variabile di tipo <code>Book</code>
	 */
	public Book() {	}
	
	/**
	 * Costruttore che verrà utilizzato soltanto per recuperare le informazioni dal carrello
	 * per poter calcolare successivamente il totale.
	 * 
	 * @param ean l'EAN del libro
	 * @param prezzo il prezzo del libro
	 */
	public Book(String ean, BigDecimal prezzo) {
		this.EAN = ean;
		this.prezzo = prezzo;
	}

	/**
	 * Metodo <code>get</code> che ritorna l'EAN
	 * 
	 * @return l'EAN del libro
	 */
	public String getEAN() {
		return this.EAN;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna il titolo
	 * 
	 * @return il titolo del libro
	 */
	public String getTitolo() {
		return this.titolo;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna il nome dell'autore
	 * 
	 * @return l'autore del libro
	 */
	public String getAutore() {
		return this.autore;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna l'editore
	 * 
	 * @return l'editore del libro
	 */
	public String getEditore() {
		return this.editore;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna il numero di pagine
	 * 
	 * @return il numero di pagine del libro
	 */
	public int getPagine() {
		return this.pagine;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna il prezzo
	 * 
	 * @return il prezzo del libro
	 */
	public BigDecimal getPrezzo() {
		return this.prezzo;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna la data di pubblicazione
	 * 
	 * @return la data di pubblicazione del libro
	 */
	public Date getPubblicazione() {
		return this.pubblicazione;
	}

	@Override
	public BigDecimal accept(CartVisitor visitor) {
		return visitor.visit(this);
	}
}
