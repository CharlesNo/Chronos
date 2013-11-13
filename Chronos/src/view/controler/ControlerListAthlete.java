/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : ControlerListAthlete.java
 * 
 * Créé le 1 nov. 2013 à 19:07:04
 * 
 * Auteur : Charles NEAU
 */
package view.controler;

import persistence.DatabaseHandler;
import view.ActivityListAthlete;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;
import business.Athlete;
import business.Model;
import business.Performance;
import business.exceptions.InvalidFirstNameException;
import business.exceptions.InvalidNameException;
import com.chronos.R;

/* _________________________________________________________ */
/**
 * The Class ControlerListAthlete.
 * 
 * @author Charles NEAU
 */
@SuppressWarnings("unused")
public class ControlerListAthlete implements OnItemLongClickListener,
		OnClickListener, OnChildClickListener
{
	/** The activity. */
	private final Activity				activity;
	/** The lv liste. */
	private final ExpandableListView	lvListe;
	/** The business. */
	private final Model					model;
	/** La base de données */
	final DatabaseHandler				database;
	/** Le temps de l'athlete */
	private final long					tempsChrono;
	private int							removePos;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new view.controler liste athlete.
	 * 
	 * @param activity
	 *            the activity
	 * @param model
	 *            the model
	 * @param database
	 *            the database
	 * @param tempsChrono
	 *            the time
	 */
	public ControlerListAthlete(final Activity activity, final Model model,
			final DatabaseHandler database, final long tempsChrono)
	{
		this.activity = activity;
		this.model = model;
		this.database = database;
		this.tempsChrono = tempsChrono;
		lvListe = (ExpandableListView) activity
				.findViewById(R.id.listAthleteExpandable);
		lvListe.setAdapter(model.getAdapter());
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
		final Button add = (Button) activity
				.findViewById(R.id.bouttonAddAthlete);
		final EditText champNom = (EditText) activity
				.findViewById(R.id.editTextNom);
		final EditText champPrenom = (EditText) activity
				.findViewById(R.id.editTextPrenom);
		if (add.getText().equals("Ajouter"))
		{
			if (!champNom.getText().toString().equals("")
					&& !champPrenom.getText().toString().equals(""))
			{
				try
				{
					final Athlete athlete = new Athlete(champNom.getText()
							.toString(), champPrenom.getText().toString());
					model.getAdapter().add(athlete);
					model.getAdapter().notifyDataSetChanged();
					lvListe.setAdapter(model.getAdapter());
					database.addAthlete(athlete);
				}
				catch (final InvalidNameException e)
				{
					Toast.makeText(activity, "Le nom est vide",
							Toast.LENGTH_SHORT).show();
				}
				catch (final InvalidFirstNameException e)
				{
					Toast.makeText(activity, "Le prenom est vide",
							Toast.LENGTH_SHORT).show();
				}
				champNom.setText("");
				champPrenom.setText("");
			}
			else
			{
				Toast.makeText(
						activity,
						"Veuillez renseigner le nom et le prenom de l'athlete.",
						Toast.LENGTH_SHORT).show();
			}
		}
		else
		{
			final Athlete athleteSelected = (Athlete) lvListe
					.getItemAtPosition(removePos);
			Athlete athlete;
			try
			{
				athlete = new Athlete(champNom.getText().toString(),
						champPrenom.getText().toString(),
						athleteSelected.getPerformances());
				/*********** Suppression de athlete selected *******************/
				database.deleteAthlete(athleteSelected);
				model.getAdapter().remove(athleteSelected);
				model.getAdapter().notifyDataSetChanged();
				/**************Ajout du nouvelle athlete **********************/
				model.getAdapter().add(athlete);
				model.getAdapter().notifyDataSetChanged();
				lvListe.setAdapter(model.getAdapter());
				database.addAthlete(athlete);
			}
			catch (final InvalidNameException e)
			{
				Toast.makeText(activity, "Le nom est vide", Toast.LENGTH_SHORT)
						.show();
			}
			catch (final InvalidFirstNameException e)
			{
				Toast.makeText(activity, "Le prenom est vide",
						Toast.LENGTH_SHORT).show();
			}
			champNom.setText("");
			champPrenom.setText("");
		}
		add.setText("Ajouter");
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
			final int position, final long id)
	{
		if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_GROUP){
			removePos = position;
			ActivityListAthlete.setPositionItem(position);
			lvListe.showContextMenu();
		}
		return true;
	}

	@Override
	public boolean onChildClick(ExpandableListView arg0, View arg1, int groupPos,
			int childPos, long i) {
		deleteChild(groupPos,childPos);
		return true;
	}

	private void deleteChild(final int groupPos, final int childPos) {
		final AlertDialog.Builder adb = new AlertDialog.Builder(activity);
		adb.setTitle("Suppression du temps");
		adb.setMessage("Êtes vous sûr de vouloir supprimer ce temps ? ");
		adb.setNegativeButton("Annuler", null);
		adb.setPositiveButton("Ok", new AlertDialog.OnClickListener()
		{
			@Override
			public void onClick(final DialogInterface dialog, final int which)
			{
				final Athlete athlete = (Athlete) lvListe
						.getItemAtPosition(groupPos);
				athlete.getPerformances().remove(childPos);
				//enregistrement database a faire
				model.getAdapter().notifyDataSetChanged();
			}
		});
		adb.show();
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier ControlerListAthlete.java.
 * /*_________________________________________________________
 */
