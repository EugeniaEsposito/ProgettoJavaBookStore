package command;

/**
 * Interfaccia generica per l'esecuzione del comando.
 * 
 * @author Eugenia Esposito
 *
 */
public interface CommandInterface {
	/**
	 * Metodo che esegue il comando e ne ritorna il risultato.
	 * 
	 * @return il risultato dell'esecuzione
	 */
	public Object execute();
}
