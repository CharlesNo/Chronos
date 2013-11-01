/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : Manager.java
 * 
 * Créé le 31 oct. 2013 à 13:24:23
 * 
 * Auteur : Charles NEAU
 */
package modele;

import java.util.ArrayList;

/* _________________________________________________________ */
/**
 * The Class Manager.
 * 
 * @author Charles NEAU
 */
public class Manager
{
	/** The athletes. */
	private final ArrayList<Athlete>	athletes	= new ArrayList<Athlete>();

	/* _________________________________________________________ */
	/**
	 * Instantiates a new manager.
	 */
	public Manager()
	{
		// TODO Auto-generated constructor stub
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
		return athletes.add(object);
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
 * Fin du fichier Manager.java.
 * /*_________________________________________________________
 */
