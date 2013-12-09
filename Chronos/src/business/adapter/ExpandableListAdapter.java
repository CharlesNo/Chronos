/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : ExpandableListAdapter.java
 * 
 * Créé le 6 nov. 2013 à 12:38:22
 * 
 * Auteur : Jerome POINAS
 */
package business.adapter;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import view.controler.ControlerExpandableList;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import business.Athlete;
import business.Performance;
import com.chronos.R;

/* _________________________________________________________ */
/**
 * @author Jerome POINAS
 *         Adapter de la liste extensible.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter
{
	/** The activity. */
	private final Activity							activity;
	/**
	 * Map des athletes et de leur resultats
	 */
	private final Map<Athlete, List<Performance>>	resultsCollection;
	/**
	 * La liste des athletes
	 */
	private final List<Athlete>						listeAthlete;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new expandable list adapter.
	 * 
	 * @param context
	 *            the context
	 * @param athletes
	 *            Liste des athletes.
	 * @param mesResultats
	 *            La map athlete/Performance.
	 */
	public ExpandableListAdapter(final Activity context,
			final List<Athlete> athletes,
			final Map<Athlete, List<Performance>> mesResultats)
	{
		activity = context;
		resultsCollection = mesResultats;
		listeAthlete = athletes;
	}

	/* _________________________________________________________ */
	/**
	 * @param groupPosition
	 *            La position du group.
	 * @param childPosition
	 *            La position du child.
	 * @return L'objet se trouvant à cet emplacement.
	 * @see android.widget.ExpandableListAdapter#getChild(int, int)
	 */
	@Override
	public Object getChild(final int groupPosition, final int childPosition)
	{
		return getResultsCollection().get(getListeAthlete().get(groupPosition))
				.get(childPosition);
	}

	/* _________________________________________________________ */
	/**
	 * @param groupPosition
	 *            La position du group.
	 * @param childPosition
	 *            La position du child.
	 * @return L'id de l'objet se trouvant à cet emplacement.
	 * @see android.widget.ExpandableListAdapter#getChildId(int, int)
	 */
	@Override
	public long getChildId(@SuppressWarnings("unused") final int groupPosition,
			final int childPosition)
	{
		return childPosition;
	}

	/* _________________________________________________________ */
	/**
	 * @param groupPosition
	 *            La position du group.
	 * @param childPosition
	 *            La position du child.
	 * @param isLastChild
	 *            True si c'est le dernier child.
	 * @param convertView
	 *            La view.
	 * @param parent
	 *            Son group d'appartenance.
	 * @return La view correspondante au child.
	 * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean,
	 *      android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			@SuppressWarnings("unused") final boolean isLastChild,
			View convertView, @SuppressWarnings("unused") final ViewGroup parent)
	{
		final Performance perf = (Performance) getChild(groupPosition,
				childPosition);
		final LayoutInflater inflater = getActivity().getLayoutInflater();
		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.child_item, null);
		}
		final TextView item = (TextView) convertView
				.findViewById(R.id.resultat);
		final ImageView delete = (ImageView) convertView
				.findViewById(R.id.deleteImage);
		if (delete != null)
		{
			delete.setOnClickListener(new ControlerExpandableList(this,
					groupPosition, childPosition));
		}
		item.setText(perf.toString());
		return convertView;
	}

	/* _________________________________________________________ */
	/**
	 * @param groupPosition
	 *            La position du group.
	 * @return Le nombre de child.
	 * @see android.widget.ExpandableListAdapter#getChildrenCount(int)
	 */
	@Override
	public int getChildrenCount(final int groupPosition)
	{
		final int i = getResultsCollection().get(
				getListeAthlete().get(groupPosition)).size();
		return i;
	}

	/* _________________________________________________________ */
	/**
	 * @param groupPosition
	 *            La position du group.
	 * @return Le group.
	 * @see android.widget.ExpandableListAdapter#getGroup(int)
	 */
	@Override
	public Object getGroup(final int groupPosition)
	{
		return getListeAthlete().get(groupPosition);
	}

	/* _________________________________________________________ */
	/**
	 * @return Le nombre de group total.
	 * @see android.widget.ExpandableListAdapter#getGroupCount()
	 */
	@Override
	public int getGroupCount()
	{
		return getListeAthlete().size();
	}

	/* _________________________________________________________ */
	/**
	 * @param groupPosition
	 *            La position du group.
	 * @return L'id du group recherché.
	 * @see android.widget.ExpandableListAdapter#getGroupId(int)
	 */
	@Override
	public long getGroupId(final int groupPosition)
	{
		return groupPosition;
	}

	/* _________________________________________________________ */
	/**
	 * @param groupPosition
	 *            La position du group.
	 * @param isExpanded
	 *            True si le groupe est étendu.
	 * @param convertView
	 *            La view.
	 * @param parent
	 *            Son group d'appartenance.
	 * @return La view correspondant au group.
	 * @see android.widget.ExpandableListAdapter#getGroupView(int, boolean,
	 *      android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getGroupView(final int groupPosition,
			@SuppressWarnings("unused") final boolean isExpanded,
			View convertView, @SuppressWarnings("unused") final ViewGroup parent)
	{
		final Athlete athlete = (Athlete) getGroup(groupPosition);
		if (convertView == null)
		{
			final LayoutInflater infalInflater = (LayoutInflater) getActivity()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.group_item, null);
		}
		final TextView item = (TextView) convertView
				.findViewById(R.id.resultat);
		item.setTypeface(null, Typeface.BOLD);
		item.setText(athlete.getName() + " " + athlete.getFirstName());
		return convertView;
	}

	/* _________________________________________________________ */
	/**
	 * @return .
	 * @see android.widget.ExpandableListAdapter#hasStableIds()
	 */
	@Override
	public boolean hasStableIds()
	{
		return true;
	}

	/* _________________________________________________________ */
	/**
	 * @param groupPosition
	 *            La position du group.
	 * @param childPosition
	 *            La position du child.
	 * @return True si le child est selectionnable.
	 * @see android.widget.ExpandableListAdapter#isChildSelectable(int, int)
	 */
	@Override
	public boolean isChildSelectable(
			@SuppressWarnings("unused") final int groupPosition,
			@SuppressWarnings("unused") final int childPosition)
	{
		return true;
	}

	/* _________________________________________________________ */
	/**
	 * @param athleteToRemove
	 *            L'athlete à supprimer.
	 */
	public void remove(final Athlete athleteToRemove)
	{
		getListeAthlete().remove(athleteToRemove);
		getResultsCollection().remove(athleteToRemove);
	}

	/* _________________________________________________________ */
	/**
	 * @param athlete
	 *            L'athlete à ajouter.
	 */
	public void add(final Athlete athlete)
	{
		getListeAthlete().add(athlete);
		Collections.sort(getListeAthlete());
		getResultsCollection().put(athlete, athlete.getPerformances());
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ activity.
	 * 
	 * @return la valeur du champ activity.
	 */
	public Activity getActivity()
	{
		return activity;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ resultsCollection.
	 * 
	 * @return la valeur du champ resultsCollection.
	 */
	public Map<Athlete, List<Performance>> getResultsCollection()
	{
		return resultsCollection;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ listeAthlete.
	 * 
	 * @return la valeur du champ listeAthlete.
	 */
	public List<Athlete> getListeAthlete()
	{
		return listeAthlete;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier ExpandableListAdapter.java.
 * /*_________________________________________________________
 */
