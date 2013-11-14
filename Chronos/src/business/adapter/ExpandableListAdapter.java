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

import java.util.List;
import java.util.Map;
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
	 * 
	 */
	private final Map<Athlete, List<Performance>>	resultsCollection;
	/**
	 * 
	 */
	private final List<Athlete>						listeAthlete;

	/* _________________________________________________________ */
	/**
	 * @param context
	 * @param athletes
	 * @param mesResultats
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
	 * @param childPosition
	 * @return
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
	 * @param childPosition
	 * @return
	 * @see android.widget.ExpandableListAdapter#getChildId(int, int)
	 */
	@Override
	public long getChildId(final int groupPosition, final int childPosition)
	{
		return childPosition;
	}

	/* _________________________________________________________ */
	/**
	 * @param groupPosition
	 * @param childPosition
	 * @param isLastChild
	 * @param convertView
	 * @param parent
	 * @return
	 * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean,
	 *      android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			final boolean isLastChild, View convertView, final ViewGroup parent)
	{
		final Performance perf = (Performance) getChild(groupPosition,
				childPosition);
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
				public void onClick(final View v)
				{
					final AlertDialog.Builder builder = new AlertDialog.Builder(
							context);
					builder.setMessage("Supprimer ce temps ?");
					builder.setCancelable(false);
					builder.setPositiveButton("Valider",
							new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(
										final DialogInterface dialog,
										final int id)
								{
									final List<Performance> child = resultsCollection
											.get(listeAthlete
													.get(groupPosition));
									child.remove(childPosition);
									notifyDataSetChanged();
								}
							});
					builder.setNegativeButton("Annuler",
							new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(
										final DialogInterface dialog,
										final int id)
								{
									dialog.cancel();
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
	 * @return
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
	 * @return
	 * @see android.widget.ExpandableListAdapter#getGroup(int)
	 */
	@Override
	public Object getGroup(final int groupPosition)
	{
		return listeAthlete.get(groupPosition);
	}

	/* _________________________________________________________ */
	/**
	 * @return
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
	 * @return
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
	 * @param isExpanded
	 * @param convertView
	 * @param parent
	 * @return
	 * @see android.widget.ExpandableListAdapter#getGroupView(int, boolean,
	 *      android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getGroupView(final int groupPosition, final boolean isExpanded,
			View convertView, final ViewGroup parent)
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
	 * @return
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
	 * @param childPosition
	 * @return
	 * @see android.widget.ExpandableListAdapter#isChildSelectable(int, int)
	 */
	@Override
	public boolean isChildSelectable(final int groupPosition,
			final int childPosition)
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
	}

	/* _________________________________________________________ */
	/**
	 * @param athlete
	 *            L'athlete à ajouter.
	 */
	public void add(final Athlete athlete)
	{
		listeAthlete.add(athlete);
		resultsCollection.put(athlete, athlete.getPerformances());
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier ExpandableListAdapter.java.
 * /*_________________________________________________________
 */
