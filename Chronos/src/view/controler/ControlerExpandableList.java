package view.controler;

import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import business.Athlete;
import business.Performance;
import business.adapter.ExpandableListAdapter;

/* _________________________________________________________ */
/**
 * The Class ControlerExpandableList.
 * 
 * @author Jerome POINAS
 */
public final class ControlerExpandableList implements OnClickListener
{
	/** The athlete. */
	private final Athlete				athlete;
	/** The perf. */
	private final Performance			perf;
	/** The expandable list adapter. */
	private final ExpandableListAdapter	adapter;
	/** The child position. */
	private final int					childPosition;

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ childPosition.
	 * 
	 * @return la valeur du champ childPosition.
	 */
	public int getChildPosition()
	{
		return childPosition;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ groupPosition.
	 * 
	 * @return la valeur du champ groupPosition.
	 */
	public int getGroupPosition()
	{
		return groupPosition;
	}

	/** The group position. */
	private final int	groupPosition;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new test.
	 * 
	 * @param expandableListAdapter
	 *            the expandable list adapter
	 * @param groupPosition
	 *            the group position
	 * @param childPosition
	 *            the child position
	 */
	public ControlerExpandableList(
			final ExpandableListAdapter expandableListAdapter,
			final int groupPosition, final int childPosition)
	{
		adapter = expandableListAdapter;
		this.groupPosition = groupPosition;
		this.childPosition = childPosition;
		athlete = (Athlete) expandableListAdapter.getGroup(groupPosition);
		perf = (Performance) expandableListAdapter.getChild(groupPosition,
				childPosition);
	}

	/* _________________________________________________________ */
	/**
	 * On click.
	 * 
	 * @param v
	 *            the v
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@SuppressWarnings("unused")
	@Override
	public void onClick(final View v)
	{
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				getAdapter().getActivity());
		builder.setMessage("Supprimer ce temps ?");
		builder.setCancelable(false);
		builder.setNegativeButton("Annuler",
				new ControlerNegativeDialogInterface());
		builder.setPositiveButton("Valider",
				new ControlerPositiveDialogInterface(this));
		final AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ athlete.
	 * 
	 * @return la valeur du champ athlete.
	 */
	public Athlete getAthlete()
	{
		return athlete;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ perf.
	 * 
	 * @return la valeur du champ perf.
	 */
	public Performance getPerf()
	{
		return perf;
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
