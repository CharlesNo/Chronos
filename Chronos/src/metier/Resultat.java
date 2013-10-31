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
	/** La date. */
	private Date	date;
	/** The chrono. */
	private Double	chrono;
	/** The distance. */
	private Float	distance;

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
		setDate(date);
		setChrono(chrono);
		setDistance(distance);
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
	 * Modifie la valeur du cmap date.
	 * 
	 * @param date
	 *            la valeur à placer dans le champ date.
	 */
	public void setDate(final Date date)
	{
		this.date = date;
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
	 * Modifie la valeur du cmap chrono.
	 * 
	 * @param chrono
	 *            la valeur à placer dans le champ chrono.
	 */
	public void setChrono(final Double chrono)
	{
		this.chrono = chrono;
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

	/* _________________________________________________________ */
	/**
	 * Modifie la valeur du cmap distance.
	 * 
	 * @param distance
	 *            la valeur à placer dans le champ distance.
	 */
	public void setDistance(final Float distance)
	{
		this.distance = distance;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier Resultat.java.
 * /*_________________________________________________________
 */
