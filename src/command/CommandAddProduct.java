package command;

import classiDB.Book;
import classiDB.CD;

/**
 * Classe che implementa <code>CommandInterface</code> e definisce
 * il comportamento del metodo da eseguire.
 * 
 * @author Eugenia Esposito
 * @see CommandInterface
 *
 */
public class CommandAddProduct implements CommandInterface {
	private Receiver receiver;
	private Book B;
	private CD C;
	
	/**
	 * Costruttore che inizializza il receiver e
	 * i parametri che servono per l'esecuzione del comando.
	 * 
	 * @param B il libro da aggiungere
	 * @param C il CD da aggiungere
	 */
	public CommandAddProduct(Book B, CD C) {
		receiver = new Receiver();
		this.B = B;
		this.C = C;
	}
	
	@Override
	public Object execute() {
		try {
			receiver.addProduct(B, C);
		} catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
