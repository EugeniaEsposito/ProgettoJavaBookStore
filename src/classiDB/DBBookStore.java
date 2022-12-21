package classiDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Classe utilizzata per la connessione col database.
 * 
 * @author Eugenia Esposito
 */
public class DBBookStore {
	
	/**
	 * Variabile di tipo <code>Connection</code> che rappresenta
	 * la connessione col database
	 */
	public Connection conn;
	
	/**
	 * Variabile di tipo <code>Statement</code> utilizzata
	 * per eseguire delle istruzioni SQL
	 */
	public Statement st;
	
	/**
	 * Variabile di tipo <code>ResultSet</code> utilizzata
	 * per memorizzare una tabella che rappresenta il risultato di una query SQL
	 */
	public ResultSet rs;
	
	private static DBBookStore instance;
	
	private DBBookStore() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo che implementa il pattern Singleton.
	 * Se la classe è stata già istanziata, ritorna quella istanza,
	 * altrimenti crea una nuova istanza e la ritorna.
	 * 
	 * @return l'istanza della classe
	 */
	public static DBBookStore getInstance() {
		if(instance == null)
			instance = new DBBookStore();
		return instance;
	}
	
	/**
	 * Metodo che apre la connessione col database
	 */
	public void openConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "admin97");
			st = conn.createStatement();
		} catch (SQLException s) {
			System.out.println(s);
		}
	}
	
	/**
	 * Metodo che chiude la connessione col database
	 */
	public void closeConnection() {
		try {
			conn.close();
			st.close();
		} catch (SQLException s) {
			System.out.println(s);
		}
	}
	
	/**
	 * Metodo che inserisce nel database i dati dell'account da creare
	 * 
	 * @param A l'account da creare
	 * @throws SQLIntegrityConstraintViolationException se è stato violato un vincolo di integrità
	 * @throws SQLException se ci sono errori generici sul database
	 */
	public void createAccount(Account A) throws SQLIntegrityConstraintViolationException, SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			openConnection();
			st.executeUpdate("INSERT INTO `bookstore`.`account` (`username`, `nome`, `cognome`, `email`, `password`) VALUES "
					+ "('" + A.getUsername() + "','" + A.getNome() + "','" + A.getCognome() + "','" + A.getEmail() + "','" + A.getPassword() + "');");
			closeConnection();
		} catch (SQLIntegrityConstraintViolationException s) {
			System.out.println(s);
			throw(s);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			throw(e);
		}
	}
}