/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : InvalidePrenomException.java
 * 
 * Créé le 31 oct. 2013 à 11:07:06
 * 
 * Auteur : Jerome POINAS
 */
package exception;

/* _________________________________________________________ */
/**
 * The Class InvalidePrenomException.
 * 
 * @author Jerome POINAS
 *         Charles NEAU
 */
public class InvalidePrenomException extends Exception
{
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 5240206574393809628L;

	/* _________________________________________________________ */
	/**
	 * Constructeur compet de l'exception "prenom invalide".
	 * 
	 * @param message
	 *            Le message remonté de l'exception.
	 */
	public InvalidePrenomException(final String message)
	{
		super(message);
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier InvalidePrenomException.java.
 * /*_________________________________________________________
 */
