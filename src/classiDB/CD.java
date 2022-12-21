package classiDB;

import visitor.*;

import java.math.BigDecimal;
import java.sql.Date;


/**
 * La classe <code>CD</code> implementa l'interfaccia <code>Product</code>
 * e corrisponde all'omonima tabella del database.
 * 
 * @author Eugenia Esposito
 * @see Product
 */
public class CD implements Product {
	private String EAN;
	private String album;
	private String artista;
	private String etichetta;
	private BigDecimal prezzo;
	private Date pubblicazione;
	private int tracks;
	
	/**
	 * Costruttore della classe <code>CD</code>
	 * 
	 * @param EAN l'EAN del CD
	 * @param album il nome dell'album del CD
	 * @param artista l'artista del CD
	 * @param etichetta l'etichetta discografica del CD
	 * @param prezzo il prezzo del CD
	 * @param pubblicazione la data di pubblicazione del CD
	 * @param tracks il numero di tracce presenti all'interno del CD
	 */
	public CD(String EAN, String album, String artista, String etichetta, BigDecimal prezzo, Date pubblicazione, int tracks) {
		this.EAN = EAN;
		this.album = album;
		this.artista = artista;
		this.etichetta = etichetta;
		this.prezzo = prezzo;
		this.pubblicazione = pubblicazione;
		this.tracks = tracks;
	}
	
	/**
	 * Costruttore vuoto che servirà in alcuni casi per inizializzare una variabile di tipo <code>CD</code>
	 */
	public CD() { }
	
	/**
	 * Costruttore che verrà utilizzato soltanto per recuperare le informazioni dal carrello
	 * per poter calcolare successivamente il totale.
	 * 
	 * @param ean l'EAN del CD
	 * @param prezzo il prezzo del CD
	 */
	public CD(String ean, BigDecimal prezzo) {
		this.EAN = ean;
		this.prezzo = prezzo;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna l'EAN
	 * 
	 * @return l'EAN del CD
	 */
	public String getEAN() {
		return this.EAN;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna il nome dell'album
	 * 
	 * @return l'album del CD
	 */
	public String getAlbum() {
		return this.album;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna il nome dell'artista
	 * 
	 * @return l'artista del CD
	 */
	public String getArtista() {
		return this.artista;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna l'etichetta discografica
	 * 
	 * @return l'etichetta discografica del CD
	 */
	public String getEtichetta() {
		return this.etichetta;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna il prezzo
	 * 
	 * @return il prezzo del CD
	 */
	public BigDecimal getPrezzo() {
		return this.prezzo;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna la data di pubblicazione
	 * 
	 * @return la data di pubblicazione del CD
	 */
	public Date getPubblicazione() {
		return this.pubblicazione;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna il numero di tracce
	 * 
	 * @return il numero di tracce presenti nel CD
	 */
	public int getTracks() {
		return this.tracks;
	}

	@Override
	public BigDecimal accept(CartVisitor visitor) {
		return visitor.visit(this);
	}
}
