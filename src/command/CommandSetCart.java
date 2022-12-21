package command;

/**
 * Classe che implementa <code>CommandInterface</code> e definisce
 * il comportamento del metodo da eseguire.
 * 
 * @author Eugenia Esposito
 * @see CommandInterface
 *
 */
public class CommandSetCart implements CommandInterface {
	private Receiver receiver;
	private int idCart;
	
	/**
	 * Costruttore che inizializza il receiver e
	 * il parametro che serve per l'esecuzione del comando.
	 * 
	 * @param idCart l'id del carrello
	 */
	public CommandSetCart(int idCart) {
		receiver = new Receiver();
		this.idCart = idCart;
	}

	@Override
	public Object execute() {
		return receiver.setCart(idCart);
	}

}
