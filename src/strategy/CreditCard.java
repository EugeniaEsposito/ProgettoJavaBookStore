package strategy;

import java.math.BigDecimal;

/**
 * Classe che implementa <code>PaymentStrategy</code> e rappresenta
 * il metodo di pagamento relativo alla carta di credito.
 * 
 * @author Eugenia Esposito
 * @see PaymentStrategy
 *
 */
public class CreditCard implements PaymentStrategy {
	private String nome;
	private String numeroCarta;
	private String cvv;
	private String scadenza;

	/**
	 * Costruttore della classe.
	 * 
	 * @param nome l'intestatario della carta di credito
	 * @param numeroCarta il numero della carta
	 * @param cvv il CVV della carta
	 * @param scadenza la data di scadenza della carta
	 */
	public CreditCard(String nome, String numeroCarta, String cvv, String scadenza) {
		this.nome = nome;
		this.numeroCarta = numeroCarta;
		this.cvv = cvv;
		this.scadenza = scadenza;
	}
	
	@Override
	public void pay(BigDecimal total) {
		System.out.println("Pagato con carta di credito: € " + total);
	}
}
