package strategy;

import java.math.BigDecimal;

/**
 * Classe che implementa <code>PaymentStrategy</code> e rappresenta
 * il metodo di pagamento relativo al paypal.
 * 
 * @author Eugenia Esposito
 * @see PaymentStrategy
 *
 */
public class Paypal implements PaymentStrategy {
	private String email;
	private String password;
	
	/**
	 * Costruttore della classe.
	 * 
	 * @param email l'email relativa al conto paypal
	 * @param password la password del conto paypal
	 */
	public Paypal(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	@Override
	public void pay(BigDecimal total) {
		System.out.println("Pagato con paypal: € "+ total);
	}
}
