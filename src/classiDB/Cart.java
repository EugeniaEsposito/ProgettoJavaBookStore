package classiDB;

/**
 * La classe <code>Cart</code> corrisponde all'omonima tabella del database.
 * 
 * @author Eugenia Esposito
 */
public class Cart {
	private String username;
	
	/**
	 * Costruttore della classe <code>Cart</code>
	 * 
	 * @param username l'username dell'utente a cui appartiene il carrello
	 */
	public Cart(String username) {
		this.username = username;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna l'username
	 * 
	 * @return l'username dell'utente
	 */
	public String getUsername() {
		return this.username;
	}
}
