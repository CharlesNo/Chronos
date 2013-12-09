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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Locale;
import persistence.DatabaseHandler;
import utility.Constantes;
import view.ActivityListAthlete;
import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import business.Athlete;
import business.Manager;
import business.dialog.DialogFragmentSettings;
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
		OnClickListener, OnValueChangeListener, Constantes
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
	/** Position temporaire pour supprimer l'athlete en cours */
	private int							removePos;
	/** Position du picker */
	private int							itemSelected	= 5;

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
	public ControlerListAthlete(final Activity activity, final Manager model,
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
							final String nomModifié = firstLetterUpperCase(nom);
							final Athlete athlete = new Athlete(champNom
									.getText().toString()
									.toUpperCase(Locale.getDefault()),
									nomModifié);
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
							.getItemAtPosition(removePos);
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
							final String nomModifié = firstLetterUpperCase(nom);
							athlete = new Athlete(champNom.getText().toString()
									.trim().toUpperCase(), nomModifié,
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
						activity, this, itemSelected);
				break;
			case R.id.Exporter:
				exportDataBase();
				Toast.makeText(activity, Constantes.DATABASEEXPORTED,
						Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
	}

	/* _________________________________________________________ */
	/**
	 */
	private void exportDataBase()
	{
		final List<Athlete> maListeAthlete = DatabaseHandler.getInstance(
				activity.getApplicationContext()).getAllAthletes();
		final String data = "";
		final File myFile = new File(Environment.getExternalStorageDirectory()
				+ File.separator + "chronos", "database.txt"); // on déclare
																// notre futur
																// fichier
		final File myDir = new File(Environment.getExternalStorageDirectory()
				+ File.separator + "chronos"); // pour créer le repertoire dans
												// lequel on va mettre notre
												// fichier
		Boolean success = true;
		if (!myDir.exists())
		{
			success = myDir.mkdir(); // On crée le répertoire (s'il n'existe
										// pas!!)
		}
		if (success)
		{
			FileOutputStream output;
			try
			{
				output = new FileOutputStream(myFile, false);
				try
				{
					final Writer out = new BufferedWriter(
							new OutputStreamWriter(output, "UTF-8"));
					for (final Athlete athlete : maListeAthlete)
					{
						out.write(athlete.toString());
						out.flush();
					}
					out.close();
				}
				catch (final IOException e)
				{
					e.printStackTrace();
				}
				try
				{
					output.close();
				}
				catch (final IOException e)
				{
					e.printStackTrace();
				}
			}
			catch (final FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			Log.e("TEST", "Erreur de création de dossier");
		}
	}

	/* _________________________________________________________ */
	/**
	 * Méthode qui permet de mettre la premiere lettre de la chaine de character
	 * passée en paramètre.
	 * 
	 * @param nom
	 *            La chaine de character a mettre en forme.
	 * @return La chaine de character modifiée.
	 */
	private String firstLetterUpperCase(final String nom)
	{
		final char first = nom.charAt(0);
		System.out.println(first);
		String firstLetter = Character.toString(first);
		firstLetter = firstLetter.toUpperCase(Locale.getDefault());
		System.out.println(firstLetter);
		// Substring pour enlever la premiere lettre
		final String resteChaine = nom.substring(1);
		return firstLetter.concat(resteChaine);
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
	public boolean onItemLongClick(final AdapterView<?> arg0, final View arg1,
			final int position, final long id)
	{
		if (ExpandableListView.getPackedPositionType(id) == ExpandableListView.PACKED_POSITION_TYPE_GROUP)
		{
			removePos = position;
			ActivityListAthlete.setPositionItem(position);
			lvListe.showContextMenu();
		}
		return true;
	}

	/* _________________________________________________________ */
	/**
	 * @see android.widget.NumberPicker.OnValueChangeListener#onValueChange(android.widget.NumberPicker,
	 *      int, int)
	 */
	@Override
	public void onValueChange(final NumberPicker picker, final int oldVal,
			final int newVal)
	{
		final TextView champsDistance = (TextView) activity
				.findViewById(R.id.textView1);
		final String[] values = picker.getDisplayedValues();
		champsDistance.setText("Distance d'enregistrement : (" + values[newVal]
				+ "m)");
		ActivityListAthlete.setRecordDistance(Integer.parseInt(values[newVal]));
		itemSelected = picker.getValue();
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier ControlerListAthlete.java.
 * /*_________________________________________________________
 */
