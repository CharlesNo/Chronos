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
import java.util.Observable;
import android.app.Activity;
import android.widget.ArrayAdapter;

/* _________________________________________________________ */
/**
 * The Class Modele.
 * Cette classe contient la liste des Athletes.
 * 
 * @author Charles NEAU
 */
public class Modele extends Observable
{
	/** The athletes. */
	private final ArrayList<Athlete>	athletes;
	/** The adapter. */
	private final ArrayAdapter<Athlete>	adapter;

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
	 * 
	 * @param activity
	 *            the activity
	 */
	public Modele(final Activity activity)
	{
		athletes = new ArrayList<Athlete>();
		adapter = new ArrayAdapter<Athlete>(activity.getBaseContext(),
				android.R.layout.simple_list_item_1, athletes);
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

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ adapter.
	 * 
	 * @return la valeur du champ adapter.
	 */
	public ArrayAdapter<Athlete> getAdapter()
	{
		return adapter;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier Modele.java.
 * /*_________________________________________________________
 */
