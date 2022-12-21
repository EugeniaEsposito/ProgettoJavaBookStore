package command;

/**
 * Classe che effettua l'invocazione del comando.
 *  
 * @author Eugenia Esposito
 *
 */
public class Invoker {
	CommandInterface command;
	
	/**
	 * Costruttore con il parametro <code>command</code> che viene 
	 * utilizzato per incapsulare il comando da eseguire.
	 * 
	 * @param command il comando da eseguire
	 */
	public Invoker(CommandInterface command) {
		this.command = command;
	}
	
	/**
	 * Metodo utilizzato per l'esecuzione del comando.
	 * 
	 * @return il risultato dell'esecuzione
	 */
	public Object execute() {
		return command.execute();
	}
}
