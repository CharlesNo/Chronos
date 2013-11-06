/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : Performance.java
 * 
 * Créé le 31 oct. 2013 à 13:12:58
 * 
 * Auteur : Charles NEAU
 */
package business;

import java.util.Date;

/* _________________________________________________________ */
/**
 * The Class Performance.
 * Un {@link Performance} est un le résultat d'une performance.
 * Il contient le temps du chronomètre, la distance parcouru,
 * ainsi que la date de la création de ce temps.
 * 
 * @author Charles NEAU
 */
public class Performance
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
	public Performance(final long chrono, final int distance)
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

	/* _________________________________________________________ */
	/**
	 * @return Description performance.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		final StringBuilder build = new StringBuilder();
		build.append("Le ").append(date).append(", ");
		build.append(chrono).append(" (").append(distance).append("m)");
		return build.toString();
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier Performance.java.
 * /*_________________________________________________________
 */
