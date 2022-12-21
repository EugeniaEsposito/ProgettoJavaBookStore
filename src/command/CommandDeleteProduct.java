package command;

/**
 * Classe che implementa <code>CommandInterface</code> e definisce
 * il comportamento del metodo da eseguire.
 * 
 * @author Eugenia Esposito
 * @see CommandInterface
 *
 */
public class CommandDeleteProduct implements CommandInterface {
	private Receiver receiver;
	private String ean;
	
	/**
	 * Costruttore che inizializza il receiver e
	 * il parametro che serve per l'esecuzione del comando.
	 * 
	 * @param ean l'EAN del prodotto da cancellare
	 */
	public CommandDeleteProduct(String ean) {
		receiver = new Receiver();
		this.ean = ean;
	}

	@Override
	public Object execute() {
		try {
			receiver.deleteProduct(ean);
		} catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
