package visitor;

import classiDB.Book;
import classiDB.CD;

import java.math.BigDecimal;

/**
 * Interfaccia che rappresenta il visitor che effettua la "visita".
 * 
 * @author Eugenia Esposito
 *
 */
public interface CartVisitor {
	
	/**
	 * Metodo che visita <code>b</code> e ne ritorna il costo.
	 * 
	 * @param b il libro da "visitare"
	 * @return il costo del libro
	 */
	public BigDecimal visit(Book b);
	
	/**
	 * Metodo che visita <code>c</code> e ne ritorna il costo.
	 * 
	 * @param c il CD da "visitare"
	 * @return il costo del CD
	 */
	public BigDecimal visit(CD c);
}
