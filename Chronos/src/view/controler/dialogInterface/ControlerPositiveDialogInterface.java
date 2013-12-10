package view.controler.dialogInterface;

import java.util.List;
import persistence.DatabaseHandler;
import view.controler.ControlerExpandableList;
import android.content.DialogInterface;
import business.Athlete;
import business.Performance;
import business.adapter.ExpandableListAdapter;

/* _________________________________________________________ */
/**
 * @author Jerome POINAS
 * 
 *         La classe ControlerPositiveDialogInterface permettant de gérer les
 *         évenement sur la boite de dialogue (bouton "valider").
 * 
 */
public final class ControlerPositiveDialogInterface implements
		DialogInterface.OnClickListener
{
	/** The controler expandable list. */
	private final ControlerExpandableList	controlerExpandableList;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new controler positive dialog interface.
	 * 
	 * @param controlerExpandableList
	 *            the controler expandable list
	 */
	public ControlerPositiveDialogInterface(
			final ControlerExpandableList controlerExpandableList)
	{
		this.controlerExpandableList = controlerExpandableList;
	}

	/* _________________________________________________________ */
	/**
	 * On click.
	 * 
	 * @param dialog
	 *            the dialog
	 * @param id
	 *            the id
	 * @see android.content.DialogInterface.OnClickListener#onClick(android.content.DialogInterface,
	 *      int)
	 */
	@SuppressWarnings("unused")
	@Override
	public void onClick(final DialogInterface dialog, final int id)
	{
		final ExpandableListAdapter adapter = controlerExpandableList
				.getAdapter();
		final Athlete athlete = controlerExpandableList.getAthlete();
		final Performance perf = controlerExpandableList.getPerf();
		final int childPosition = controlerExpandableList.getChildPosition();
		final int groupPosition = controlerExpandableList.getGroupPosition();
		final List<Performance> child = adapter.getResultsCollection().get(
				adapter.getListeAthlete().get(groupPosition));
		child.remove(childPosition);
		DatabaseHandler.getInstance(adapter.getActivity()).removePerformance(
				athlete, perf);
		adapter.notifyDataSetChanged();
	}
}
