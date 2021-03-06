package view.activity;

import persistence.DatabaseHandler;
import utility.Constantes;
import utility.Formatter;
import view.controler.ControlerListAthlete;
import view.controler.ControlerListManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import business.Athlete;
import business.DialogDistance;
import business.Manager;
import business.Performance;
import com.chronos.R;

/**
 * La classe Main activity.
 * 
 * @author Jerome POINAS
 *         Charles NEAU
 * 
 */
public class ActivityListAthlete extends Activity implements Constantes
{
	/** */
	private static int			removePos;
	/** */
	private static int			distanceRecord	= 100;
	/** The lv liste. */
	private ExpandableListView	lvListe;
	/** The button ajouter. */
	private Button				buttonAjouter;
	/** Temps chrono */
	private long				tempsChrono;
	/** La base de données */
	private DatabaseHandler		database;
	/** Le model */
	private Manager				model;
	/** The dialogDistance. */
	private DialogDistance		dialogDistance;

	/* _________________________________________________________ */
	/**
	 * On create.
	 * 
	 * @param savedInstanceState
	 *            the saved instance state
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		/* Initialisation de l'activity */
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liste_athlete);
		/* Initialisation des attributs */
		lvListe = (ExpandableListView) findViewById(R.id.listAthleteExpandable);
		buttonAjouter = (Button) findViewById(R.id.bouttonAddAthlete);
		final ImageView exporter = (ImageView) findViewById(R.id.Exporter);
		final ImageView settings = (ImageView) findViewById(R.id.settings);
		/* Base de données */
		database = DatabaseHandler.getInstance(getBaseContext());
		/* Creation du business et ajout en tant qu'observeur */
		dialogDistance = new DialogDistance();
		model = new Manager(this, database);
		/* Creation du bundle de récupération des données */
		createBundle();
		/* Creation du view.controler */
		final ControlerListAthlete controler = new ControlerListAthlete(this,
				model, dialogDistance, database, tempsChrono);
		final ControlerListManager controlerList = new ControlerListManager(
				lvListe);
		/* Ajout du view.controler en tant que Listener */
		buttonAjouter.setOnClickListener(controler);
		settings.setOnClickListener(controler);
		exporter.setOnClickListener(controler);
		lvListe.setOnItemLongClickListener(controlerList);
		registerForContextMenu(lvListe);
	}

	/**
	 * Creates the bundle.
	 */
	private void createBundle()
	{
		// On récupère l'objet Bundle envoyé par l'autre Activity
		final Bundle objetbunble = getIntent().getExtras();
		// On récupère les données du Bundle
		if ((objetbunble != null)
				&& objetbunble.containsKey(Constantes.BUNDLETIME))
		{
			final String temps = getIntent().getStringExtra(
					Constantes.BUNDLETIME);
			tempsChrono = Long.parseLong(temps);
			final TextView champsTemps = (TextView) findViewById(R.id.tempsChrono);
			champsTemps.setText(Formatter.miseEnForme(tempsChrono));
		}
	}

	/* _________________________________________________________ */
	/**
	 * On create options menu.
	 * 
	 * @param menu
	 *            the menu
	 * @return true, if successful
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_chronos, menu);
		return true;
	}

	/* _________________________________________________________ */
	/**
	 * @param menu
	 *            ContextMenu.
	 * @param v
	 *            La view.
	 * @param menuInfo
	 *            ContextMenuInfo.
	 * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu,
	 *      android.view.View, android.view.ContextMenu.ContextMenuInfo)
	 */
	@Override
	public void onCreateContextMenu(final ContextMenu menu, final View v,
			@SuppressWarnings("unused") final ContextMenuInfo menuInfo)
	{
		if (v.getId() == R.id.listAthleteExpandable)
		{
			menu.setHeaderTitle(Constantes.MANAGETITLE);
			final String[] menuItems = { Constantes.MODIFY, Constantes.ADDPERF,
					Constantes.DELETE };
			for (int i = 0; i < menuItems.length; i++)
			{
				menu.add(Menu.NONE, i, i, menuItems[i]);
			}
		}
	}

	/* _________________________________________________________ */
	/**
	 * @param item
	 *            L'objet selectionné.
	 * @return Boolean action réalisée.
	 * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onContextItemSelected(final MenuItem item)
	{
		if (item.getTitle().equals(Constantes.MODIFY))
		{
			modifier();
			return true;
		}
		if (item.getTitle().equals(Constantes.ADDPERF))
		{
			lieTemps();
			return true;
		}
		if (item.getTitle().equals(Constantes.DELETE))
		{
			supprimer();
			return true;
		}
		return false;
	}

	/* _________________________________________________________ */
	/**
	 * Modification d'un athlete.
	 */
	private void modifier()
	{
		final EditText champNom = (EditText) findViewById(R.id.editTextNom);
		final EditText champPrenom = (EditText) findViewById(R.id.editTextPrenom);
		final Button modif = (Button) findViewById(R.id.bouttonAddAthlete);
		final Athlete athleteSelected = (Athlete) lvListe
				.getItemAtPosition(getRemovePos());
		champNom.setText(athleteSelected.getName());
		champPrenom.setText(athleteSelected.getFirstName());
		modif.setText(Constantes.MODIFY);
	}

	/* _________________________________________________________ */
	/**
	 * Ajout d'une performance à un athlète.
	 */
	private void lieTemps()
	{
		// On recupere l'athlete selectionné
		final Athlete athleteSelected = (Athlete) lvListe
				.getItemAtPosition(getRemovePos());
		final Performance performance = new Performance(athleteSelected,
				tempsChrono, distanceRecord);
		athleteSelected.getPerformances().add(performance);
		// Mise a jour de la base de données.
		database.addPerformance(athleteSelected, performance);
		Toast.makeText(this, Constantes.ASSOCIATEDPERF, Toast.LENGTH_SHORT)
				.show();
	}

	/* _________________________________________________________ */
	/**
	 * Suppression d'un athlète.
	 */
	private void supprimer()
	{
		final AlertDialog.Builder adb = new AlertDialog.Builder(this);
		adb.setTitle(Constantes.DELETETITLE);
		adb.setMessage(Constantes.VALIDATEDELETE);
		final int positionToRemove = getRemovePos();
		adb.setNegativeButton(Constantes.CANCEL, null);
		adb.setPositiveButton(Constantes.VALIDATE,
				new AlertDialog.OnClickListener()
				{
					@Override
					public void onClick(
							@SuppressWarnings("unused") final DialogInterface dialog,
							@SuppressWarnings("unused") final int which)
					{
						final Athlete athleteToRemove = (Athlete) lvListe
								.getItemAtPosition(positionToRemove);
						database.deleteAthlete(athleteToRemove);
						model.getAdapter().remove(athleteToRemove);
						model.getAdapter().notifyDataSetChanged();
					}
				});
		adb.show();
	}

	/* _________________________________________________________ */
	/**
	 * Methode qui permet de modifier la position de l'item.
	 * 
	 * @param position
	 *            La position de l'item.
	 */
	public static void setPositionItem(final int position)
	{
		ActivityListAthlete.removePos = position;
	}

	/* _________________________________________________________ */
	/**
	 * Methode qui permet de modifier la distance d'enregistrment.
	 * 
	 * @param distance
	 *            La distance d'enregistrment.
	 */
	public static void setRecordDistance(final int distance)
	{
		distanceRecord = distance;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ removePos.
	 * 
	 * @return la valeur du champ removePos.
	 */
	public static int getRemovePos()
	{
		return removePos;
	}
}
