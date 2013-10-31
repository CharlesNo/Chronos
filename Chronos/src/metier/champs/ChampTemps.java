/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : ChampTemps.java
 * 
 * Créé le 31 oct. 2013 à 14:09:21
 * 
 * Auteur : Charles NEAU
 */
package metier.champs;

import java.util.Date;

/* _________________________________________________________ */
/**
 * The Class ChampTemps.
 * TODO Cette classe doit retourner une {@link String} lisible d'une date.
 * 
 * @author Charles NEAU
 */
public class ChampTemps
{
	/** The value. */
	private Double	value;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new champ temps.
	 * 
	 * @param value
	 *            the value
	 */
	public ChampTemps(final Double value)
	{
		super();
		setValue(value);
	}

	/* _________________________________________________________ */
	/**
	 * Instantiates a new champ temps.
	 * 
	 * @param dateDebut
	 *            the date debut
	 * @param dateFin
	 *            the date fin
	 */
	public ChampTemps(final Date dateDebut, final Date dateFin)
	{
		super();
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ value.
	 * 
	 * @return la valeur du champ value.
	 */
	public Double getValue()
	{
		return value;
	}

	/* _________________________________________________________ */
	/**
	 * Modifie la valeur du cmap value.
	 * 
	 * @param value
	 *            la valeur à placer dans le champ value.
	 */
	public void setValue(final Double value)
	{
		this.value = value;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier ChampTemps.java.
 * /*_________________________________________________________
 */
