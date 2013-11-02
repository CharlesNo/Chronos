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

import modele.Athlete;
import modele.Modele;
import modele.exception.InvalideNomException;
import modele.exception.InvalidePrenomException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.chronos.R;

/* _________________________________________________________ */
/**
 * The Class ControlerListeAthlete.
 * 
 * @author Charles NEAU
 */
@SuppressWarnings("unused")
public class ControlerListeAthlete implements OnItemClickListener,
		OnItemLongClickListener, OnClickListener
{
	/** The activity. */
	private final Activity	activity;
	/** The lv liste. */
	private final ListView	lvListe;
	/** The modele. */
	private final Modele	modele;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new controler liste athlete.
	 * 
	 * @param activity
	 *            the activity
	 * @param modele
	 *            the modele
	 */
	public ControlerListeAthlete(final Activity activity, final Modele modele)
	{
		this.activity = activity;
		this.modele = modele;
		lvListe = (ListView) activity.findViewById(R.id.listAthlete);
		lvListe.setAdapter(modele.getAdapter());
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
				modele.getAdapter().remove(
						(Athlete) lvListe.getItemAtPosition(positionToRemove));
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
		final EditText champNom = (EditText) activity
				.findViewById(R.id.editTextNom);
		final EditText champPrenom = (EditText) activity
				.findViewById(R.id.editTextPrenom);
		if (!champNom.getText().toString().equals("")
				&& !champPrenom.getText().toString().equals(""))
		{
			try
			{
				final Athlete athlete = new Athlete(champNom.getText()
						.toString(), champPrenom.getText().toString());
				modele.getAdapter().add(athlete);
				modele.getAdapter().notifyDataSetChanged();
			}
			catch (final InvalideNomException e)
			{
				Toast.makeText(activity, "Le nom est vide", Toast.LENGTH_SHORT)
						.show();
			}
			catch (final InvalidePrenomException e)
			{
				Toast.makeText(activity, "Le prenom est vide",
						Toast.LENGTH_SHORT).show();
			}
			champNom.setText("");
			champPrenom.setText("");
		}
		else
		{
			Toast.makeText(activity,
					"Veuillez renseigner le nom et le prenom de l'athlete.",
					Toast.LENGTH_SHORT).show();
		}
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier ControlerListeAthlete.java.
 * /*_________________________________________________________
 */
