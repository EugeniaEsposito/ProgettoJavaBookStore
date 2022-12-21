package utility;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

/**
 * Classe di appoggio contenente alcune funzioni utilizzate da altre classi.
 * 
 * @author Eugenia
 *
 */
public class Utility {
	private static Color newBlu = new Color(3, 7, 100);
	private static ImageIcon imgLogo = new ImageIcon(Utility.class.getResource("/images/book-cd.png"));
	private static ImageIcon errore = new ImageIcon(Utility.class.getResource("/images/error.png"));
	private static ImageIcon successo = new ImageIcon(Utility.class.getResource("/images/success.png"));
	
	/**
	 * Metodo <code>get</code> che ritorna il colore rgb(3, 7, 100)
	 * 
	 * @return il colore con codice rgb(3, 7, 100)
	 */
	public static Color getNewBlu() {
		return newBlu;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna il logo dell'applicazione
	 * 
	 * @return l'immagine che funge da logo per l'applicazione
	 */
	public static ImageIcon getImgLogo() {
		return imgLogo;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna l'immagine di errore
	 * 
	 * @return l'immagine di errore
	 */
	public static ImageIcon getImgError() {
		return errore;
	}
	
	/**
	 * Metodo <code>get</code> che ritorna l'immagine di successo
	 * 
	 * @return l'immagine di successo
	 */
	public static ImageIcon getImgSuccess() {
		return successo;
	}
	
	/**
	 * Metodo che controlla se <code>s</code> è un numero.
	 * Ritorna <code>true</code> se lo è, <code>false</code> altrimenti.
	 * 
	 * @param s stringa da controllare
	 * @return <code>true</code> se <code>s</code> è un numero,
	 * <code>false</code> se <code>s</code> non è un numero
	 */
	public static boolean isNumber(String s) {
		if(s.contains("+") || s.contains("-"))
			return false;
		for(int i=0; i<s.length(); i++) {
			if(!Character.isDigit(s.charAt(i)))
				return false;
		}
		return true;
	}
	
	/**
	 * Metodo che controlla se <code>s</code> è un BigDecimal.
	 * Ritorna <code>true</code> se lo è, <code>false</code> altrimenti.
	 * 
	 * @param s stringa da controllare
	 * @return <code>true</code> se <code>s</code> è BigDecimal, <code>false</code> altrimenti
	 */
	public static boolean isBigDecimal(String s) {
		if(s.contains("+") || s.contains("-"))
			return false;
		try {
			new BigDecimal(s);
		} catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	/**
	 * Metodo che controlla se <code>s</code> è un EAN.
	 * Ritorna <code>true</code> se lo è, <code>false</code> altrimenti.
	 * 
	 * @param s stringa da controllare
	 * @return <code>true</code> se <code>s</code> è un EAN, <code>false</code> altrimenti.
	 */
	public static boolean isEAN(String s) {
		if(s.length() == 13) { //se la lunghezza della stringa è uguale a 13 (l'EAN è composto da 13 cifre)
			for(int i=0; i<s.length(); i++) {
				if(!Character.isDigit(s.charAt(i))) //controlla ogni carattere se è un numero
					return false;
			}
			return true;
		} else
			return false;
	}
	
	/**
	 * Metodo che controlla se <code>s</code> è un numero di carta di credito.
	 * Ritorna <code>true</code> se lo è, <code>false</code> altrimenti.
	 * 
	 * @param s stringa da controllare
	 * @return <code>true</code> se <code>s</code> è un numero di carta, <code>false</code> altrimenti.
	 */
	public static boolean isNumberCard(String s) {
		if(s.length() == 16) { //il numero di una carta di credito è composta da 16 cifre
			for(int i=0; i<s.length(); i++) {
				if(!Character.isDigit(s.charAt(i)))
					return false;
			}
			return true;
		} else
			return false;
	}
	
	/**
	 * Metodo che rende <code>v</code> visibile o invisibile in base a <code>b</code>.
	 *  
	 * @param v vector di JLabel
	 * @param b true per rendere <code>v</code> visibile, false altrimenti
	 */
	public static void setVisibleLbl(Vector<JLabel> v, boolean b) {
		v.forEach((i) -> i.setVisible(b));
	}
	
	/**
	 * Metodo che imposta a <code>null</code> il testo di <code>v</code> e la data di <code>d</code>
	 * 
	 * @param v vector di JTextField da impostare a null
	 * @param d data da impostare a null
	 */
	public static void clearTF(Vector<JTextField> v, JDateChooser d) {
		v.forEach((i) -> i.setText(null));
		d.setCalendar(null);
	}
}
