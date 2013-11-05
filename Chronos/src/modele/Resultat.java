/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : Resultat.java
 * 
 * Créé le 31 oct. 2013 à 13:12:58
 * 
 * Auteur : Charles NEAU
 */
package modele;

import java.util.Date;

/* _________________________________________________________ */
/**
 * The Class Resultat.
 * Un {@link Resultat} est un le résultat d'une performance.
 * Il contient le temps du chronomètre, la distance parcouru,
 * ainsi que la date de la création de ce temps.
 * 
 * @author Charles NEAU
 */
public class Resultat
{
	/** The chrono. */
	private final long	chrono;
	/** La date. */
	private final Date	date;
	/** The distance. */
	private final int	distance;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new resultat.
	 * 
	 * @param chrono
	 *            the chrono
	 * @param distance
	 *            the distance
	 */
	public Resultat(final long chrono, final int distance)
	{
		super();
		date = new Date();
		this.chrono = chrono;
		this.distance = distance;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ chrono.
	 * 
	 * @return la valeur du champ chrono.
	 */
	public long getChrono()
	{
		return chrono;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ date.
	 * 
	 * @return la valeur du champ date.
	 */
	public Date getDate()
	{
		return date;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ distance.
	 * 
	 * @return la valeur du champ distance.
	 */
	public int getDistance()
	{
		return distance;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier Resultat.java.
 * /*_________________________________________________________
 */
