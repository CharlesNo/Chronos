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
import persistence.DatabaseHandler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
	/**
	 * Le contexte.
	 */
	private final Activity							context;
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
	 * @param context
	 *            Le contexte.
	 * @param athletes
	 *            Liste des athletes.
	 * @param mesResultats
	 *            La map athlete/Performance.
	 */
	public ExpandableListAdapter(final Activity context,
			final List<Athlete> athletes,
			final Map<Athlete, List<Performance>> mesResultats)
	{
		this.context = context;
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
		return resultsCollection.get(listeAthlete.get(groupPosition)).get(
				childPosition);
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
		final Athlete athlete = (Athlete) getGroup(groupPosition);
		final LayoutInflater inflater = context.getLayoutInflater();
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
			delete.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(@SuppressWarnings("unused") final View v)
				{
					final AlertDialog.Builder builder = new AlertDialog.Builder(
							context);
					builder.setMessage("Supprimer ce temps ?");
					builder.setCancelable(false);
					builder.setNegativeButton("Annuler",
							new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(
										final DialogInterface dialog,
										@SuppressWarnings("unused") final int id)
								{
									dialog.cancel();
								}
							});
					builder.setPositiveButton("Valider",
							new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(
										@SuppressWarnings("unused") final DialogInterface dialog,
										@SuppressWarnings("unused") final int id)
								{
									final List<Performance> child = resultsCollection
											.get(listeAthlete
													.get(groupPosition));
									child.remove(childPosition);
									DatabaseHandler.getInstance(context)
											.removePerformance(athlete, perf);
									notifyDataSetChanged();
								}
							});
					final AlertDialog alertDialog = builder.create();
					alertDialog.show();
				}
			});
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
		final int i = resultsCollection.get(listeAthlete.get(groupPosition))
				.size();
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
		return listeAthlete.get(groupPosition);
	}

	/* _________________________________________________________ */
	/**
	 * @return Le nombre de group total.
	 * @see android.widget.ExpandableListAdapter#getGroupCount()
	 */
	@Override
	public int getGroupCount()
	{
		return listeAthlete.size();
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
			final LayoutInflater infalInflater = (LayoutInflater) context
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
		listeAthlete.remove(athleteToRemove);
		resultsCollection.remove(athleteToRemove);
	}

	/* _________________________________________________________ */
	/**
	 * @param athlete
	 *            L'athlete à ajouter.
	 */
	public void add(final Athlete athlete)
	{
		listeAthlete.add(athlete);
		Collections.sort(listeAthlete);
		resultsCollection.put(athlete, athlete.getPerformances());
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier ExpandableListAdapter.java.
 * /*_________________________________________________________
 */
