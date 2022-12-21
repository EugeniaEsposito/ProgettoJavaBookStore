package classiDB;

/**
 * La classe <code>CartDetail</code> corrisponde all'omonima tabella del database.
 * 
 * @author Eugenia Esposito
 */
public class CartDetail {
	private int idCart;
	private String ean;
	
	/**
	 * Costruttore della classe <code>CartDetail</code>
	 * 
	 * @param idCart l'id del carrello
	 * @param ean l'EAN del prodotto all'interno del carrello
	 */
	public CartDetail(int idCart, String ean) {
		this.idCart = idCart;
		this.ean = ean;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna l'id del carrello
	 * 
	 * @return l'id del carrello
	 */
	public int getIdCart() {
		return this.idCart;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna l'EAN del prodotto
	 * 
	 * @return l'EAN del prodotto nel carrello
	 */
	public String getEan() {
		return this.ean;
	}
}
