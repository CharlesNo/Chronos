/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : InvalidNameException.java
 * 
 * Créé le 31 oct. 2013 à 11:02:40
 * 
 * Auteur : Jerome POINAS
 */
package business.exceptions;

/* _________________________________________________________ */
/**
 * The Class InvalidNameException.
 * 
 * @author Jerome POINAS
 *         Charles NEAU
 */
public class InvalidNameException extends Exception
{
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 7196272913377656762L;

	/* _________________________________________________________ */
	/**
	 * Constructeur compet de l'business.exceptions "nom invalide".
	 * 
	 * @param message
	 *            Le message remonté de l'business.exceptions.
	 */
	public InvalidNameException(final String message)
	{
		super(message);
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier InvalidNameException.java.
 * /*_________________________________________________________
 */
