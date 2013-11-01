/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : InvalideNomException.java
 * 
 * Créé le 31 oct. 2013 à 11:02:40
 * 
 * Auteur : Jerome POINAS
 */
package modele.exception;

/* _________________________________________________________ */
/**
 * The Class InvalideNomException.
 * 
 * @author Jerome POINAS
 *         Charles NEAU
 */
public class InvalideNomException extends Exception
{
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 7196272913377656762L;

	/* _________________________________________________________ */
	/**
	 * Constructeur compet de l'modele.exception "nom invalide".
	 * 
	 * @param message
	 *            Le message remonté de l'modele.exception.
	 */
	public InvalideNomException(final String message)
	{
		super(message);
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier InvalideNomException.java.
 * /*_________________________________________________________
 */
