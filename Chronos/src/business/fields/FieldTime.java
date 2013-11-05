/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : FieldTime.java
 * 
 * Créé le 31 oct. 2013 à 14:09:21
 * 
 * Auteur : Charles NEAU
 */
package business.fields;

import java.util.Date;

/* _________________________________________________________ */
/**
 * The Class FieldTime.
 * TODO Cette classe doit retourner une {@link String} lisible d'une date.
 * 
 * @author Charles NEAU
 */
public class FieldTime
{
	/** The value. */
	private long	value;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new champ temps.
	 * 
	 * @param value
	 *            the value
	 */
	public FieldTime(final long value)
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
	public FieldTime(final Date dateDebut, final Date dateFin)
	{
		super();
		setValue(dateFin.getTime() - dateDebut.getTime());
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ value.
	 * 
	 * @return la valeur du champ value.
	 */
	public long getValue()
	{
		return value;
	}

	/* _________________________________________________________ */
	/**
	 * Modifie la valeur du cmap value.
	 * 
	 * @param l
	 *            la valeur à placer dans le champ value.
	 */
	public void setValue(final long l)
	{
		value = l;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier FieldTime.java.
 * /*_________________________________________________________
 */
