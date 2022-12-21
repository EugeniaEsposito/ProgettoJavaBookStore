package visitor;

import classiDB.Book;
import classiDB.CD;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe che implementa <code>CartVisitor</code> e definisce le operazioni di visita sui prodotti.
 * 
 * @author Eugenia Esposito
 *
 */
public class CartVisitorImpl implements CartVisitor {

	@Override
	public BigDecimal visit(Book b) {
		BigDecimal cost = new BigDecimal(0);
		BigDecimal discount = new BigDecimal(0.05); //5%
		if(b.getPrezzo().compareTo(new BigDecimal(20)) == 1) //se è maggiore di 20
			cost = (b.getPrezzo().subtract(b.getPrezzo().multiply(discount))).setScale(2, RoundingMode.CEILING); //applico lo sconto del 5%
		else
			cost = b.getPrezzo();
		//System.out.println(cost);
		return cost;
	}

	@Override
	public BigDecimal visit(CD c) {
		BigDecimal cost = new BigDecimal(0);
		BigDecimal discount = new BigDecimal(0.1); //10%
		if(c.getPrezzo().compareTo(new BigDecimal(20)) == 1) //se è maggiore di 20
			cost = (c.getPrezzo().subtract(c.getPrezzo().multiply(discount))).setScale(2, RoundingMode.CEILING); //applico lo sconto del 10%
		else
			cost = c.getPrezzo();
		//System.out.println(cost);
		return cost;
	}
}
