package classiDB;

/** 
 * La classe <code>Account</code> corrisponde all'omonima tabella del database.
 * 
 * @author Eugenia Esposito
 */
public class Account {
	private String nome;
	private String cognome;
	private String username;
	private String email;
	private String password;
	
	/**
	 * Costruttore della classe <code>Account</code>
	 * 
	 * @param nome il nome dell'utente
	 * @param cognome il cognome dell'utente
	 * @param username l'username dell'utente
	 * @param email l'email dell'utente
	 * @param password la password dell'utente
	 */
	public Account(String nome, String cognome, String username, String email, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna il nome
	 * 
	 * @return il nome dell'utente
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna il cognome
	 * 
	 * @return il cognome dell'utente
	 */
	public String getCognome() {
		return this.cognome;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna l'username
	 * 
	 * @return l'username dell'utente
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna l'email
	 * 
	 * @return l'email dell'utente
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna la password
	 * 
	 * @return la password dell'utente
	 */
	public String getPassword() {
		return this.password;
	}
}
