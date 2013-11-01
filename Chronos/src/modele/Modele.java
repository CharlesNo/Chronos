/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : Modele.java
 * 
 * Créé le 31 oct. 2013 à 13:24:23
 * 
 * Auteur : Charles NEAU
 */
package modele;

import java.util.ArrayList;

/* _________________________________________________________ */
/**
 * The Class Modele.
 * 
 * @author Charles NEAU
 */
public class Modele
{
	/** The athletes. */
	private final ArrayList<Athlete>	athletes;

	/* _________________________________________________________ */
	/**
	 * Gets the.
	 * 
	 * @param arg0
	 *            the arg0
	 * @return the athlete
	 * @see java.util.ArrayList#get(int)
	 */
	public Athlete get(final int arg0)
	{
		return athletes.get(arg0);
	}

	/* _________________________________________________________ */
	/**
	 * Size.
	 * 
	 * @return the int
	 * @see java.util.ArrayList#size()
	 */
	public int size()
	{
		return athletes.size();
	}

	/* _________________________________________________________ */
	/**
	 * Instantiates a new manager.
	 */
	public Modele()
	{
		athletes = new ArrayList<Athlete>();
	}

	/* _________________________________________________________ */
	/**
	 * Adds the.
	 * 
	 * @param object
	 *            the object
	 * @return true, if successful
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean add(final Athlete object)
	{
		return getAthletes().add(object);
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ athletes.
	 * 
	 * @return la valeur du champ athletes.
	 */
	public ArrayList<Athlete> getAthletes()
	{
		return athletes;
	}

	/* _________________________________________________________ */
	/**
	 * Start.
	 */
	public void start()
	{
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier Modele.java.
 * /*_________________________________________________________
 */
