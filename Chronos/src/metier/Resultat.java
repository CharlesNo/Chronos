/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : Resultat.java
 * 
 * Créé le 31 oct. 2013 à 13:12:58
 * 
 * Auteur : Charles NEAU
 */
package metier;

import java.util.Date;

/* _________________________________________________________ */
/**
 * The Class Resultat.
 * 
 * @author Charles NEAU
 */
public class Resultat
{
	/** The chrono. */
	private final Double	chrono;
	/** La date. */
	private final Date		date;
	/** The distance. */
	private final Float		distance;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new resultat.
	 * 
	 * @param date
	 *            the date
	 * @param chrono
	 *            the chrono
	 * @param distance
	 *            the distance
	 */
	public Resultat(final Date date, final Double chrono, final Float distance)
	{
		super();
		this.date = date;
		this.chrono = chrono;
		this.distance = distance;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ chrono.
	 * 
	 * @return la valeur du champ chrono.
	 */
	public Double getChrono()
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
	public Float getDistance()
	{
		return distance;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier Resultat.java.
 * /*_________________________________________________________
 */
