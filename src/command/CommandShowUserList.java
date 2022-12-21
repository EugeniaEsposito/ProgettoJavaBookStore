package command;

/**
 * Classe che implementa <code>CommandInterface</code> e definisce
 * il comportamento del metodo da eseguire.
 * 
 * @author Eugenia Esposito
 * @see CommandInterface
 *
 */
public class CommandShowUserList implements CommandInterface {
	private Receiver receiver;
	
	/**
	 * Costruttore che inizializza il receiver.
	 */
	public CommandShowUserList() {
		receiver = new Receiver();
	}
	
	@Override
	public Object execute() {
		try {
			return receiver.showUserList();
		} catch (Exception e) {
			return null;
		}
	}
}
