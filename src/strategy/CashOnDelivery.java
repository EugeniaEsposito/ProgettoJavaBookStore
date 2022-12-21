package strategy;

import java.math.BigDecimal;

/**
 * Classe che implementa <code>PaymentStrategy</code> e rappresenta
 * il metodo di pagamento relativo al contrassegno.
 * 
 * @author Eugenia Esposito
 * @see PaymentStrategy
 *
 */
public class CashOnDelivery implements PaymentStrategy {

	/**
	 * Costruttore vuoto
	 */
	public CashOnDelivery() { }
	
	@Override
	public void pay(BigDecimal total) {
		total = total.add(new BigDecimal(3.50));
		System.out.println("Pagato con contrassegno: € " + total);
	}
}
