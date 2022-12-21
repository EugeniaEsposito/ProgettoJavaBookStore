package visitor;

import java.math.BigDecimal;

import classiDB.Book;
import classiDB.CD;

/**
 * Interfaccia utilizzata per il pattern Visitor e che
 * viene implementata dalle classi <code>Book</code> e <code>CD</code>.
 * 
 * @author Eugenia Esposito
 * @see Book
 * @see CD
 *
 */
public interface Product {
	/**
	 * Metodo che prende in input un visitor per effettuare la visita su un prodotto.
	 * Ritorna il costo del prodotto visitato.
	 * 
	 * @param visitor interfaccia che permette di visitare il prodotto
	 * @return il costo del prodotto che è stato visitato
	 * @see CartVisitor
	 * @see CartVisitorImpl
	 */
	public BigDecimal accept(CartVisitor visitor);
}
