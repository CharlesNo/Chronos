/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : Athlete.java
 * 
 * Créé le 31 oct. 2013 à 10:54:05
 * 
 * Auteur : Jerome POINAS
 */
package metier;

import java.util.ArrayList;
import exception.InvalideNomException;
import exception.InvalidePrenomException;

/* _________________________________________________________ */
/**
 * La classe Athlete
 * 
 * @author Jerome POINAS
 *         Charles NEAU
 */
public class Athlete
{
	/**
	 * Le nom de famille de l'athlete.
	 */
	private String						nom;
	/**
	 * Le prenom de l'athlete.
	 */
	private String						prenom;
	/**
	 * Les resultats.
	 */
	private final ArrayList<Resultat>	resultats	= new ArrayList<Resultat>();

	/* _________________________________________________________ */
	/**
	 * Constructeur complet d'un athlete.
	 * 
	 * @param nom
	 *            Le nom de l'athlete.
	 * @param prenom
	 *            Le prenom de l'athlete.
	 * @throws InvalideNomException
	 *             Exception le nom est null.
	 * @throws InvalidePrenomException
	 *             Exception le prenom est null.
	 */
	public Athlete(final String nom, final String prenom)
			throws InvalideNomException, InvalidePrenomException
	{
		setNom(nom);
		setPrenom(prenom);
	}

	/**
	 * Methode qui permet de retourner le nom de l'athlete.
	 * 
	 * @return Le nom de l'athlete.
	 */
	public String getNom()
	{
		return nom;
	}

	/**
	 * Methode qui permet de retourner le prenom de l'athlete.
	 * 
	 * @return Le prenom de l'athlete.
	 */
	public String getPrenom()
	{
		return prenom;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ resultats.
	 * 
	 * @return la valeur du champ resultats.
	 */
	public ArrayList<Resultat> getResultats()
	{
		return resultats;
	}

	/**
	 * Methode qui permet de modifier le nom de l'athlete.
	 * 
	 * @param nom
	 *            Le nom de l'athlete.
	 * @throws InvalideNomException
	 *             Exception le nom est null.
	 */
	public void setNom(final String nom) throws InvalideNomException
	{
		if (nom == null)
		{
			throw new InvalideNomException("Le nom est invalide");
		}
		this.nom = nom;
	}

	/**
	 * Methode qui permet de modifier le prenom de l'athlete.
	 * 
	 * @param prenom
	 *            Le prenom de l'athlete.
	 * @throws InvalidePrenomException
	 *             Exception le prenom est null.
	 */
	public void setPrenom(final String prenom) throws InvalidePrenomException
	{
		if (prenom == null)
		{
			throw new InvalidePrenomException("Le prenom est invalide");
		}
		this.prenom = prenom;
	}

	/* _________________________________________________________ */
	/**
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		builder.append(prenom).append(" ").append(nom);
		return builder.toString();
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier Athlete.java.
 * /*_________________________________________________________
 */
