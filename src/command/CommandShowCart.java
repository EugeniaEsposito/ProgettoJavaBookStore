package command;

/**
 * Classe che implementa <code>CommandInterface</code> e definisce
 * il comportamento del metodo da eseguire.
 * 
 * @author Eugenia Esposito
 * @see CommandInterface
 *
 */
public class CommandShowCart implements CommandInterface {
	private Receiver receiver;
	
	/**
	 * Costruttore che inizializza il receiver.
	 */
	public CommandShowCart() {
		receiver = new Receiver();
	}

	@Override
	public Object execute() {
		try {
			return receiver.showCart();
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
