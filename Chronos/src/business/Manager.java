/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : Manager.java
 * 
 * Créé le 31 oct. 2013 à 13:24:23
 * 
 * Auteur : Charles NEAU
 */
package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import persistence.DatabaseHandler;
import android.app.Activity;
import business.adapter.ExpandableListAdapter;

/* _________________________________________________________ */
/**
 * The Class Manager.
 * Cette classe contient la liste des Athletes.
 * 
 * @author Charles NEAU
 */
public class Manager extends Observable
{
	/** The athletes. */
	private List<Athlete>							athletes		= new ArrayList<Athlete>();
	/** The adapter. */
	private final ExpandableListAdapter				adapter;
	/** La map des resultat par athletes. */
	private final Map<Athlete, List<Performance>>	mesResultats	= new HashMap<Athlete, List<Performance>>();

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
	 * @param database
	 *            the database
	 */
	public Manager(final Activity activity, final DatabaseHandler database)
	{
		athletes = database.getAllAthletes();
		initResultats();
		adapter = new ExpandableListAdapter(activity, athletes, mesResultats);
	}

	/* _________________________________________________________ */
	/**
	 * Initialisation de tous les resultats des athletes.
	 */
	private void initResultats()
	{
		for (final Athlete athlete : athletes)
		{
			mesResultats.put(athlete, athlete.getPerformances());
		}
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
	public List<Athlete> getAthletes()
	{
		return athletes;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ adapter.
	 * 
	 * @return la valeur du champ adapter.
	 */
	public ExpandableListAdapter getAdapter()
	{
		return adapter;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier Manager.java.
 * /*_________________________________________________________
 */
