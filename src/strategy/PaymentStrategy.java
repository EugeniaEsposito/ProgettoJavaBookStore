package strategy;

import java.math.BigDecimal;

/**
 * Interfaccia che implementa il pattern Strategy
 * 
 * @author Eugenia Esposito
 */
public interface PaymentStrategy {
	/**
	 * Metodo utilizzato per il pagamento. 
	 * 
	 * @param total totale da pagare
	 */
	public void pay(BigDecimal total);
}
