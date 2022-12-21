package viewHome;

import classiDB.DBBookStore;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe che implementa il pattern Singleton ed è utilizzata per l'autenticazione.
 * 
 * @author Eugenia Esposito
 *
 */
public class Login {
	private static Login instance;
	
	private Login() {}
	
	/**
	 * Metodo che implementa il pattern Singleton.
	 * Se la classe è stata già istanziata, ritorna quella istanza,
	 * altrimenti crea una nuova istanza e la ritorna.
	 * 
	 * @return l'istanza della classe
	 */
	public static Login getInstance() {
		if(instance == null)
			instance = new Login();
		return instance;
	}
	
	/**
	 * Metodo che permette l'autenticazione.
	 * 
	 * @param usernameEntered l'username da controllare
	 * @param passwordEntered la password da controllare
	 * @return <code>1</code> se <code>usernameEntered</code> e <code>passwordEntered</code> corrispondono all'account dell'admin,
	 * <code>2</code> se <code>usernameEntered</code> e <code>passwordEntered</code> corrispondono all'account di un utente,
	 * <code>-1</code> se <code>usernameEntered</code> e <code>passwordEntered</code> non corrispondono ad alcun account presente nel database
	 */
	public int auth(String usernameEntered, String passwordEntered) {
		boolean esito = false, esitoAdmin = false;
		try {
			DBBookStore D = DBBookStore.getInstance();
			Class.forName("com.mysql.cj.jdbc.Driver");
			D.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","admin97");
			D.st = D.conn.createStatement();
			D.rs = D.st.executeQuery("SELECT * FROM account WHERE BINARY username = '" + usernameEntered + "' "
					+ "AND BINARY password = '" + passwordEntered + "'"); //eseguo la query per trovare l'account
			while(D.rs.next()) {
				esito = true; //se trovo l'account imposto esito a true
				if(D.rs.getInt("admin") == 1)
					esitoAdmin = true; //se quell'account è dell'admin imposto anche esitoAdmin a true
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		if(esito == true && esitoAdmin == true) { //se trovo l'account ed è dell'admin
			return 1;
		}
		if(esito == true && esitoAdmin == false) { //se trovo l'account ed è di un utente
			return 2;
		}
		return -1; //se l'account non è stato trovato
	}
}
