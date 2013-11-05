/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : InvalidFirstNameException.java
 * 
 * Créé le 31 oct. 2013 à 11:07:06
 * 
 * Auteur : Jerome POINAS
 */
package business.exceptions;

/* _________________________________________________________ */
/**
 * The Class InvalidFirstNameException.
 * 
 * @author Jerome POINAS
 *         Charles NEAU
 */
public class InvalidFirstNameException extends Exception
{
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 5240206574393809628L;

	/* _________________________________________________________ */
	/**
	 * Constructeur compet de l'business.exceptions "prenom invalide".
	 * 
	 * @param message
	 *            Le message remonté de l'business.exceptions.
	 */
	public InvalidFirstNameException(final String message)
	{
		super(message);
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier InvalidFirstNameException.java.
 * /*_________________________________________________________
 */
