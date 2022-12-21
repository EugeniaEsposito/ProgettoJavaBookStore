package command;

/**
 * Classe che implementa <code>CommandInterface</code> e definisce
 * il comportamento del metodo da eseguire.
 * 
 * @author Eugenia Esposito
 * @see CommandInterface
 *
 */
public class CommandSearchWord implements CommandInterface {
	private Receiver receiver;
	private String word;
	private String tipo;
	
	/**
	 * Costruttore che inizializza il receiver e
	 * i parametri che servono per l'esecuzione del comando.
	 * 
	 * @param word la parola da ricercare
	 * @param tipo la categoria di prodotto da ricercare
	 */
	public CommandSearchWord(String word, String tipo) {
		receiver = new Receiver();
		this.word = word;
		this.tipo = tipo;
	}

	@Override
	public Object execute() {
		try {
			return receiver.searchWord(word, tipo);
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
