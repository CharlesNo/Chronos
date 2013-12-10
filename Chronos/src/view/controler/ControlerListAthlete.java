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

import persistence.DatabaseExport;
import persistence.DatabaseHandler;
import utility.Constantes;
import utility.Formatter;
import view.activity.ActivityListAthlete;
import view.dialog.DialogFragmentSettings;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;
import business.Athlete;
import business.DialogDistance;
import business.Manager;
import business.exceptions.InvalidFirstNameException;
import business.exceptions.InvalidNameException;
import com.chronos.R;

/* _________________________________________________________ */
/**
 * The Class ControlerListAthlete.
 * 
 * @author Charles NEAU
 */
@SuppressLint("DefaultLocale")
@SuppressWarnings("unused")
public class ControlerListAthlete implements OnClickListener, Constantes
{
	/** The activity. */
	private final Activity				activity;
	/** The lv liste. */
	private final ExpandableListView	lvListe;
	/** The business. */
	private final Manager				model;
	/** La base de données */
	final DatabaseHandler				database;
	/** Le temps de l'athlete */
	private final long					tempsChrono;
	/** The dialog distance. */
	private final DialogDistance		dialogDistance;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new view.controler liste athlete.
	 * 
	 * @param activity
	 *            the activity
	 * @param model
	 *            the model
	 * @param dialogDistance
	 *            the dialog distance
	 * @param database
	 *            the database
	 * @param tempsChrono
	 *            the time
	 */
	public ControlerListAthlete(final Activity activity, final Manager model,
			final DialogDistance dialogDistance,
			final DatabaseHandler database, final long tempsChrono)
	{
		this.activity = activity;
		this.model = model;
		this.dialogDistance = dialogDistance;
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
		switch (arg0.getId())
		{
		// *****************BOUTON AJOUTER ATHLETE ET
		// MODIFICATION*********************//
			case R.id.bouttonAddAthlete:
				final Button add = (Button) activity
						.findViewById(R.id.bouttonAddAthlete);
				final EditText champNom = (EditText) activity
						.findViewById(R.id.editTextNom);
				final EditText champPrenom = (EditText) activity
						.findViewById(R.id.editTextPrenom);
				if (add.getText().equals(Constantes.ADD))// Ajout d'un nouvel
															// athlete
				{
					if (!champNom.getText().toString().trim()
							.equals(Constantes.EMPTY)
							&& !champPrenom.getText().toString().trim()
									.equals(Constantes.EMPTY))
					{
						try
						{
							// Traitement sur la mise en forme du prénom.
							final String nom = champPrenom.getText().toString()
									.trim();
							final String nomModifié = Formatter
									.firstLetterUpperCase(nom);
							final Athlete athlete = new Athlete(
									Formatter.ToUpperCase(champNom.getText()
											.toString().trim()), nomModifié);
							model.getAdapter().add(athlete);
							model.getAdapter().notifyDataSetChanged();
							lvListe.setAdapter(model.getAdapter());
							database.addAthlete(athlete);
						}
						catch (final InvalidNameException e)
						{
							Toast.makeText(activity, Constantes.EMPTYNAME,
									Toast.LENGTH_SHORT).show();
						}
						catch (final InvalidFirstNameException e)
						{
							Toast.makeText(activity, Constantes.EMPTYFIRSTNAME,
									Toast.LENGTH_SHORT).show();
						}
						champNom.setText(Constantes.EMPTY);
						champPrenom.setText(Constantes.EMPTY);
					}
					else
					{
						Toast.makeText(activity, Constantes.REQUESTINFORM,
								Toast.LENGTH_SHORT).show();
					}
				}
				else
				// Modification du nom et du prenom d'un athlete existant
				{
					final Athlete athleteSelected = (Athlete) lvListe
							.getItemAtPosition(ActivityListAthlete
									.getRemovePos());
					Athlete athlete;
					if (!champNom.getText().toString().trim()
							.equals(Constantes.EMPTY)
							&& !champPrenom.getText().toString().trim()
									.equals(Constantes.EMPTY))
					{
						try
						{
							// Traitement sur la mise en forme du prénom.
							final String nom = champPrenom.getText().toString()
									.trim();
							final String nomModifié = Formatter
									.firstLetterUpperCase(nom);
							athlete = new Athlete(
									Formatter.ToUpperCase(champNom.getText()
											.toString().trim()), nomModifié,
									athleteSelected.getPerformances());
							// *********** Suppression de athlete selected
							// *******************//
							database.deleteAthlete(athleteSelected);
							model.getAdapter().remove(athleteSelected);
							model.getAdapter().notifyDataSetChanged();
							// ************** Ajout du nouvel athlete
							// **********************//
							model.getAdapter().add(athlete);
							model.getAdapter().notifyDataSetChanged();
							lvListe.setAdapter(model.getAdapter());
							database.addAthlete(athlete);
						}
						catch (final InvalidNameException e)
						{
							Toast.makeText(activity, Constantes.EMPTYNAME,
									Toast.LENGTH_SHORT).show();
						}
						catch (final InvalidFirstNameException e)
						{
							Toast.makeText(activity, Constantes.EMPTYFIRSTNAME,
									Toast.LENGTH_SHORT).show();
						}
						champNom.setText(Constantes.EMPTY);
						champPrenom.setText(Constantes.EMPTY);
						add.setText(Constantes.ADD);
					}
					else
					{
						Toast.makeText(activity, Constantes.REQUESTINFORM,
								Toast.LENGTH_SHORT).show();
					}
				}
				break;
			// *****************BOUTON SETTINGS*********************//
			case R.id.settings:
				final DialogFragmentSettings dialog = new DialogFragmentSettings(
						activity, dialogDistance);
				break;
			// *****************BOUTON EXPORTER*********************//
			case R.id.Exporter:
				DatabaseExport.exportDataBase(activity);
				Toast.makeText(activity, Constantes.DATABASEEXPORTED,
						Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier ControlerListAthlete.java.
 * /*_________________________________________________________
 */
