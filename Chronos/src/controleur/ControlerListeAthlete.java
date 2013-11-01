/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : ControlerListeAthlete.java
 * 
 * Créé le 1 nov. 2013 à 19:07:04
 * 
 * Auteur : Charles NEAU
 */
package controleur;

import java.util.ArrayList;
import java.util.List;
import modele.Athlete;
import vue.ActivityAddAthlete;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.chronos.R;

/* _________________________________________________________ */
/**
 * The Class ControlerListeAthlete.
 * 
 * @author Charles NEAU
 */
public class ControlerListeAthlete implements OnItemClickListener,
		OnItemLongClickListener, OnClickListener
{
	/** The activity. */
	private final Activity				activity;
	/** The lv liste. */
	private final ListView				lvListe;
	/** The adapter. */
	private final ArrayAdapter<Athlete>	adapter;
	/** The mes athletes. */
	List<Athlete>						mesAthletes;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new controler liste athlete.
	 * 
	 * @param activity
	 *            the activity
	 */
	public ControlerListeAthlete(final Activity activity)
	{
		this.activity = activity;
		lvListe = (ListView) activity.findViewById(R.id.listAthlete);
		mesAthletes = new ArrayList<Athlete>();
		adapter = new ArrayAdapter<Athlete>(activity.getBaseContext(),
				android.R.layout.simple_list_item_1, mesAthletes);
	}

	/* _________________________________________________________ */
	/**
	 * On item click.
	 * 
	 * @param parent
	 *            The AdapterView where the click happened.
	 * @param view
	 *            The view within the AdapterView that was clicked (this
	 *            will be a view provided by the adapter)
	 * @param position
	 *            The position of the view in the adapter.
	 * @param id
	 *            The row id of the item that was clicked.
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView,
	 *      android.view.View, int, long)
	 */
	@SuppressWarnings("unused")
	@Override
	public void onItemClick(final AdapterView<?> parent, final View view,
			final int position, final long id)
	{
		// On recupere l'athlete selectionné
		final Athlete athleteSelected = (Athlete) lvListe
				.getItemAtPosition(position);
		// Boite de dialogue
		final AlertDialog.Builder adb = new AlertDialog.Builder(activity);
		adb.setTitle("Sélection Item");
		adb.setMessage("Votre choix : " + athleteSelected.getPrenom() + " "
				+ athleteSelected.getNom());
		adb.setPositiveButton("Ok", null);
		adb.show();
	}

	/* _________________________________________________________ */
	/**
	 * On item long click.
	 * 
	 * @param arg0
	 *            the arg0
	 * @param arg1
	 *            the arg1
	 * @param arg2
	 *            the arg2
	 * @param arg3
	 *            the arg3
	 * @return true, if successful
	 * @see android.widget.AdapterView.OnItemLongClickListener#onItemLongClick(android.widget.AdapterView,
	 *      android.view.View, int, long)
	 */
	@SuppressWarnings("unused")
	@Override
	public boolean onItemLongClick(final AdapterView<?> arg0, final View arg1,
			final int arg2, final long arg3)
	{
		final AlertDialog.Builder adb = new AlertDialog.Builder(activity);
		adb.setTitle("Suppression d'un athlete");
		adb.setMessage("Etes vous sure de vouloir le supprimer ? ");
		final int positionToRemove = arg2;
		adb.setNegativeButton("Cancel", null);
		adb.setPositiveButton("Ok", new AlertDialog.OnClickListener()
		{
			@Override
			public void onClick(final DialogInterface dialog, final int which)
			{
				adapter.remove((Athlete) lvListe
						.getItemAtPosition(positionToRemove));
			}
		});
		adb.show();
		return true;
	}

	/* _________________________________________________________ */
	/**
	 * On click.
	 * 
	 * @param arg0
	 *            the arg0
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(final View arg0)
	{
		final Intent intent = new Intent(activity, ActivityAddAthlete.class);
		activity.startActivity(intent);
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier ControlerListeAthlete.java.
 * /*_________________________________________________________
 */
