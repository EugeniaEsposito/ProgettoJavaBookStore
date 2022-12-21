package visitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che utilizza il pattern Visitor per calcolare il totale da pagare.
 * 
 * @author Eugenia Esposito
 *
 */
public class ShoppingCart {
	List<Product> products;
	private int idCart;
	
	/**
	 * Costruttore della classe.
	 * 
	 * @param id l'id del carrello
	 */
	public ShoppingCart(int id) {
		this.products = new ArrayList<Product>();
		idCart = id;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna l'id del carrello.
	 * 
	 * @return l'id del carrello
	 */
	public int getId() {
		return this.idCart;
	}
	
	/**
	 * Metodo che aggiunge <code>item</code> alla lista.
	 * 
	 * @param item il prodotto da aggiungere alla lista
	 */
	public void addProduct(Product item) {
		this.products.add(item);
	}
	
	/**
	 * Metodo che rimuove il prodotto alla posizione specificata.
	 * 
	 * @param index l'indice dell'elemento da rimuovere
	 */
	public void removeProduct(int index) {
		this.products.remove(index);
	}
	
	/**
	 * Metodo che rimuove tutti i prodotti dalla lista.
	 */
	public void removeAll() {
		this.products.removeAll(products);
	}
	
	/**
	 * Metodo che calcola il totale degli elementi inseriti nella lista.
	 * 
	 * @return il costo totale dei prodotti nella lista 
	 */
	public BigDecimal calculateTotal() {
		CartVisitor visitor = new CartVisitorImpl();
		BigDecimal sum = new BigDecimal(0);
		for(Product product: products) {
			sum = sum.add(product.accept(visitor)); //utilizzo del pattern visitor
		}
		return sum;
	}
}
