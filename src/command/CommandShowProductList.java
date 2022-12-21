package command;

/**
 * Classe che implementa <code>CommandInterface</code> e definisce
 * il comportamento del metodo da eseguire.
 * 
 * @author Eugenia Esposito
 * @see CommandInterface
 *
 */
public class CommandShowProductList implements CommandInterface {
	private Receiver receiver;
	private String product;
	
	/**
	 * Costruttore che inizializza il receiver e
	 * il parametro che serve per l'esecuzione del comando.
	 * 
	 * @param product la categoria del prodotto che si vuole visualizzare
	 */
	public CommandShowProductList(String product) {
		receiver = new Receiver();
		this.product = product;
	}
	
	@Override
	public Object execute() {
		try {
			return receiver.showProductList(product);
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
