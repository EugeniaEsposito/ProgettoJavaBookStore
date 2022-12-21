package command;

/**
 * Classe che implementa <code>CommandInterface</code> e definisce
 * il comportamento del metodo da eseguire.
 * 
 * @author Eugenia Esposito
 * @see CommandInterface
 *
 */
public class CommandEditProduct implements CommandInterface {
	private Receiver receiver;
	private Object product;
	
	/**
	 * Costruttore che inizializza il receiver e
	 * il parametro che serve per l'esecuzione del comando.
	 * 
	 * @param product il prodotto da modificare
	 */
	public CommandEditProduct(Object product) {
		receiver = new Receiver();
		this.product = product;
	}
	
	@Override
	public Object execute() {
		try {
			receiver.editProduct(product);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
