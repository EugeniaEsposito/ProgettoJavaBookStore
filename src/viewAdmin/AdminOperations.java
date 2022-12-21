package viewAdmin;

import javax.swing.table.DefaultTableModel;

import classiDB.Book;
import classiDB.CD;
import command.CommandAddProduct;
import command.CommandDeleteProduct;
import command.CommandEditProduct;
import command.CommandFindProduct;
import command.CommandShowProductList;
import command.CommandShowUserList;
import command.Invoker;

/**
 * Classe che rappresenta le operazioni che può effettuare l'admin.
 * 
 * @author Eugenia Esposito
 *
 */
public class AdminOperations {
	private Invoker invoker;
	
	/**
	 * Metodo che utilizza l'invoker per richiamare il comando
	 * e ritornare la tabella contenente l'elenco degli utenti.
	 * 
	 * @return la tabella con l'elenco degli utenti
	 * @see Invoker
	 * @see CommandShowUserList
	 */
	public DefaultTableModel showUserList() {
		invoker = new Invoker(new CommandShowUserList());
		return (DefaultTableModel) invoker.execute();
	}
	
	/**
	 * Metodo che utilizza l'invoker per richiamare il comando
	 * e ritornare il prodotto trovato.
	 * 
	 * @param ean l'ean del prodotto da trovare
	 * @return l'oggetto trovato
	 * @see Invoker
	 * @see CommandFindProduct
	 */
	public Object findProduct(String ean) {
		invoker = new Invoker(new CommandFindProduct(ean));
		return invoker.execute();
	}
	
	/**
	 * Metodo che utilizza l'invoker per richiamare il comando
	 * e modificare il prodotto.
	 * 
	 * @param product il prodotto con i valori modificati
	 * @see Invoker
	 * @see CommandEditProduct
	 */
	public void editProduct(Object product) {
		invoker = new Invoker(new CommandEditProduct(product));
		invoker.execute();
	}
	
	/**
	 * Metodo che utilizza l'invoker per richiamare il comando
	 * ed eliminare il prodotto.
	 * 
	 * @param ean l'EAN del prodotto da eliminare
	 * @see Invoker
	 * @see CommandDeleteProduct
	 */
	public void deleteProduct(String ean) {
		invoker = new Invoker(new CommandDeleteProduct(ean));
		invoker.execute();
	}
	
	/**
	 * Metodo che utilizza l'invoker per richiamare il comando
	 * e aggiungere il prodotto.
	 * 
	 * @param B il libro da aggiungere
	 * @param C il CD da aggiungere
	 * @see Invoker
	 * @see CommandAddProduct
	 */
	public void addProduct(Book B, CD C) {
		invoker = new Invoker(new CommandAddProduct(B, C));
		invoker.execute();
	}
	
	/**
	 * Metodo che utilizza l'invoker per richiamare il comando
	 * e ritorna la tabella contenente il catalogo dei prodotti in base a <code>product</code>.
	 * 
	 * @param product la categoria di prodotto da visualizzare
	 * @return la tabella con il catalogo dei prodotti di tipo <code>product</code>
	 * @see Invoker
	 * @see CommandShowProductList
	 */
	public DefaultTableModel showProductList(String product) {
		invoker = new Invoker(new CommandShowProductList(product));
		return (DefaultTableModel) invoker.execute();
	}
}
