package viewUser;

import java.math.BigDecimal;

import javax.swing.table.DefaultTableModel;

import command.CommandPay;
import command.CommandSearchWord;
import command.CommandSetCart;
import command.CommandShowCart;
import command.CommandShowProductList;
import command.CommandShowUserList;
import command.Invoker;
import strategy.PaymentStrategy;
import visitor.ShoppingCart;

/**
 * Classe che rappresenta le operazioni che può effettuare l'utente.
 * 
 * @author Eugenia Esposito
 *
 */
public class UserOperations {
	private Invoker invoker;
	
	/**
	 * Metodo che utilizza l'invoker per richiamare il comando
	 * e ritorna la tabella contenente i prodotti del tipo <code>product</code>.
	 * 
	 * @param product la categoria di prodotto da visualizzare 
	 * @return la tabella con i prodotti di tipo <code>product</code>
	 * @see Invoker
	 * @see CommandShowProductList
	 */
	public DefaultTableModel showProductList(String product) {
		invoker = new Invoker(new CommandShowProductList(product));
		return (DefaultTableModel) invoker.execute();
	}
	
	/**
	 * Metodo che utilizza l'invoker per richiamare il comando
	 * e ritorna la tabella contenente i prodotti che corrispondono ai criteri di ricerca.
	 * 
	 * @param word la parola da ricercare
	 * @param tipo la categoria del prodotto da ricercare
	 * @return la tabella con i prodotti che rispettano i criteri di ricerca
	 * @see Invoker
	 * @see CommandSearchWord
	 */
	public DefaultTableModel searchWord(String word, String tipo) {
		invoker = new Invoker(new CommandSearchWord(word, tipo));
		return (DefaultTableModel) invoker.execute();
	}
	
	/**
	 * Metodo che utilizza l'invoker per richiamare il comando
	 * e ritorna le info del carrello.
	 * 
	 * @param idCart l'id del carrello
	 * @return il carrello con i prodotti che si intendono acquistare
	 * @see Invoker
	 * @see CommandSetCart
	 */
	public ShoppingCart setCart(int idCart) {
		invoker = new Invoker(new CommandSetCart(idCart));
		return (ShoppingCart) invoker.execute();
	}
	
	/**
	 * Metodo che utilizza l'invoker per richiamare il comando
	 * e ritorna la tabella contenente i prodotti nel carrello.
	 * 
	 * @return la tabella con i prodotti nel carrello
	 * @see Invoker
	 * @see CommandShowCart
	 */
	public DefaultTableModel showCart() {
		invoker = new Invoker(new CommandShowCart());
		return (DefaultTableModel) invoker.execute();
	}
	
	/**
	 * Metodo che utilizza l'invoker per richiamare il comando
	 * ed effettuare il pagamento.
	 * 
	 * @param paymentMethod il metodo di pagamento da utilizzare
	 * @param total il totale da pagare
	 * @param idCart l'id del carrello
	 * @see Invoker
	 * @see CommandPay
	 */
	public void pay(PaymentStrategy paymentMethod, BigDecimal total, int idCart) {
		invoker = new Invoker(new CommandPay(paymentMethod, total, idCart));
		invoker.execute();
	}
}
