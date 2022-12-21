package command;

import java.math.BigDecimal;

import strategy.PaymentStrategy;

/**
 * Classe che implementa <code>CommandInterface</code> e definisce
 * il comportamento del metodo da eseguire.
 * 
 * @author Eugenia Esposito
 * @see CommandInterface
 *
 */
public class CommandPay implements CommandInterface {
	private Receiver receiver;
	private PaymentStrategy paymentMethod;
	private BigDecimal total;
	private int idCart;
	
	/**
	 * Costruttore che inizializza il receiver e
	 * i parametri che servono per l'esecuzione del comando.
	 * 
	 * @param paymentMethod il metodo di pagamento scelto
	 * @param total il totale da pagare
	 * @param idCart l'id del carrello
	 */
	public CommandPay(PaymentStrategy paymentMethod, BigDecimal total, int idCart) {
		receiver = new Receiver();
		this.paymentMethod = paymentMethod;
		this.total = total;
		this.idCart = idCart;
	}

	@Override
	public Object execute() {
		try {
			receiver.pay(paymentMethod, total, idCart);
		} catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
