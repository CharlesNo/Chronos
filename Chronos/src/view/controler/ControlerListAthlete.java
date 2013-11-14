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
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.NumberPicker;
import android.widget.Toast;
import business.Athlete;
import business.Model;
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
		OnClickListener
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
	/** Position temporaire pour supprimer l'athlete en cours */
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
		switch(arg0.getId())
		{
		//*****************BOUTON AJOUTER ATHLETE ET MODIFICATION*********************//
		case R.id.bouttonAddAthlete :
		
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
					//*********** Suppression de athlete selected *******************//
					database.deleteAthlete(athleteSelected);
					model.getAdapter().remove(athleteSelected);
					model.getAdapter().notifyDataSetChanged();
					//************** Ajout du nouvel athlete **********************//
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
			//*****************BOUTON SETTINGS*********************//
		case R.id.settings :
			
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setTitle("Réglage de la distance");
			
			builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			               //Traitement
			           }
			       });
			builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			               //Traitement
			           }
			       });
			
			builder.setView(activity.getLayoutInflater().inflate(R.layout.distance_picker_view, null));
			AlertDialog dialog = builder.create();
			
			//**********Parametrage du numberPicker****************//
			//NumberPicker picker = (NumberPicker) activity.findViewById(R.id.pickerDistance);
			//String[] tabValeurs = new String[3];
			//	tabValeurs[0] = Integer.toString(50);
			//	tabValeurs[1] = Integer.toString(100);
			//	tabValeurs[2] = Integer.toString(200);
			//picker.setDisplayedValues(tabValeurs);
			//picker.setWrapSelectorWheel(true);
			//******************************************************//
			
			dialog.show();
			
		}
			
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
}
/* _________________________________________________________ */
/*
 * Fin du fichier ControlerListAthlete.java.
 * /*_________________________________________________________
 */
