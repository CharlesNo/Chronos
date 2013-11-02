/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : Temps.java
 * 
 * Créé le 31 oct. 2013 à 13:34:59
 * 
 * Auteur : Charles NEAU
 */
package modele;

import java.util.ArrayList;
import java.util.Date;

/* _________________________________________________________ */
/**
 * The Class Temps.
 * Cette classe contient les différents temps envoyés en signal.
 * Il peut en contenir plusieurs et c'est là que l'entraineur devra
 * choisir les temps corrects.
 * 
 * Cette classe est static, les données qu'elle contient ne sont interesssant
 * que sur le court terme.
 * 
 * @author Charles NEAU
 */
public class Temps
{
	/** The date debut. */
	private static Date				dateDebut;
	/** The dates. */
	private static ArrayList<Date>	dates	= new ArrayList<Date>();

	/**
	 * Restart.
	 */
	public void restart()
	{
		dateDebut = new Date();
		dates = new ArrayList<Date>();
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ dateDebut.
	 * 
	 * @return la valeur du champ dateDebut.
	 */
	public static Date getDateDebut()
	{
		return dateDebut;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ dates.
	 * 
	 * @return la valeur du champ dates.
	 */
	public static ArrayList<Date> getDates()
	{
		return dates;
	}

	/* _________________________________________________________ */
	/**
	 * Gets the temps.
	 * 
	 * @return the temps
	 */
	public static ArrayList<Long> getTemps()
	{
		final ArrayList<Long> temps = new ArrayList<Long>();
		final long time = dateDebut.getTime();
		for (final Date date : dates)
		{
			temps.add(date.getTime() - time);
		}
		return temps;
	}

	/* _________________________________________________________ */
	/**
	 * Modifie la valeur du cmap dateDebut.
	 * 
	 * @param dateDebut
	 *            la valeur à placer dans le champ dateDebut.
	 */
	public static void setDateDebut(final Date dateDebut)
	{
		Temps.dateDebut = dateDebut;
	}

	/* _________________________________________________________ */
	/**
	 * Modifie la valeur du cmap dates.
	 * 
	 * @param dates
	 *            la valeur à placer dans le champ dates.
	 */
	public static void setDates(final ArrayList<Date> dates)
	{
		Temps.dates = dates;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier Temps.java.
 * /*_________________________________________________________
 */
