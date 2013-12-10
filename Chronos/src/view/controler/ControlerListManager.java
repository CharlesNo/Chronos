/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : ControlerListManager.java
 * 
 * Créé le 10 déc. 2013 à 08:50:22
 * 
 * Auteur : Jerome POINAS
 */
package view.controler;

import view.activity.ActivityListAthlete;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ExpandableListView;

/* _________________________________________________________ */
/**
 * @author Jerome POINAS
 * 
 */
public class ControlerListManager implements OnItemLongClickListener
{
	/**
	 * La liste des athletes.
	 */
	private final ExpandableListView	lvListe;

	/* _________________________________________________________ */
	/**
	 * @param lvListe
	 *            La liste des athletes.
	 */
	public ControlerListManager(final ExpandableListView lvListe)
	{
		this.lvListe = lvListe;
	}

	/* _________________________________________________________ */
	/**
	 * On item long click.
	 * 
	 * @param arg0
	 *            the arg0
	 * @param arg1
	 *            the arg1
	 * @param position
	 *            the position
	 * @param id
	 *            the id
	 * @return true, if successful
	 * @see android.widget.AdapterView.OnItemLongClickListener#onItemLongClick(android.widget.AdapterView,
	 *      android.view.View, int, long)
	 */
	@Override
	public boolean onItemLongClick(
			@SuppressWarnings("unused") final AdapterView<?> arg0,
			@SuppressWarnings("unused") final View arg1, final int position,
			final long id)
	{
		if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_GROUP)
		{
			ActivityListAthlete.setPositionItem(position);
			lvListe.showContextMenu();
		}
		return true;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier ControlerListManager.java.
 * /*_________________________________________________________
 */
