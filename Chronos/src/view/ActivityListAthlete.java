package view;

import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;
import persistence.DatabaseHandler;
import view.controler.ControlerListAthlete;
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
import android.widget.TextView;
import android.widget.Toast;
import business.Athlete;
import business.Model;
import business.Performance;
import com.chronos.R;

/**
 * La classe Main activity.
 * 
 * @author Jerome POINAS
 *         Charles NEAU
 * 
 */
public class ActivityListAthlete extends Activity implements Observer
{
	/** */
	private static int			removePos;
	/** The lv liste. */
	private ExpandableListView	lvListe;
	/** The button ajouter. */
	private Button				buttonAjouter;
	/** Temps chrono */
	private long				tempsChrono;
	/** La base de données */
	private DatabaseHandler		database;
	/** Le model */
	private Model				model;

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
		/* Base de données */
		database = DatabaseHandler.getInstance(getBaseContext());
		/* Creation du business et ajout en tant qu'observeur */
		model = new Model(this, database);
		model.addObserver(this);
		/* Creation du bundle de récupération des données */
		createBundle();
		/* Creation du view.controler */
		final ControlerListAthlete controler = new ControlerListAthlete(this,
				model, database, tempsChrono);
		/* Ajout du view.controler en tant que Listener */
		buttonAjouter.setOnClickListener(controler);
		lvListe.setOnItemLongClickListener(controler);
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
		if ((objetbunble != null) && objetbunble.containsKey("temps"))
		{
			final String temps = getIntent().getStringExtra("temps");
			tempsChrono = Long.parseLong(temps);
			final TextView champsTemps = (TextView) findViewById(R.id.tempsChrono);
			champsTemps.setText(miseEnForme(tempsChrono));
		}
	}

	/**
	 * Methode de mise en forme du rendu chronometre.
	 * 
	 * @param timeElapsed
	 *            the time elapsed
	 * @return the string
	 */
	public String miseEnForme(final long timeElapsed)
	{
		final DecimalFormat df = new DecimalFormat("00");
		int remaining = (int) (timeElapsed % (3600 * 1000));
		final int minutes = remaining / (60 * 1000);
		remaining = remaining % (60 * 1000);
		final int seconds = remaining / 1000;
		remaining = remaining % (1000);
		final int milliseconds = ((((int) timeElapsed % 1000) / 10));
		final StringBuilder builder = new StringBuilder();
		builder.append(df.format(minutes)).append("'");
		builder.append(df.format(seconds)).append("\"");
		builder.append(df.format(milliseconds));
		return builder.toString();
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
			menu.setHeaderTitle("Gestion");
			final String[] menuItems = { "Modifier", "Lié temps", "Supprimer" };
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
		if (item.getTitle().equals("Modifier"))
		{
			modifier();
			return true;
		}
		if (item.getTitle().equals("Lié temps"))
		{
			lieTemps();
			return true;
		}
		if (item.getTitle().equals("Supprimer"))
		{
			supprimer();
			return true;
		}
		return false;
	}

	/* _________________________________________________________ */
	/**
	 */
	private void modifier()
	{
		final EditText champNom = (EditText) findViewById(R.id.editTextNom);
		final EditText champPrenom = (EditText) findViewById(R.id.editTextPrenom);
		final Button modif = (Button) findViewById(R.id.bouttonAddAthlete);
		final Athlete athleteSelected = (Athlete) lvListe
				.getItemAtPosition(removePos);
		champNom.setText(athleteSelected.getName());
		champPrenom.setText(athleteSelected.getFirstName());
		modif.setText("Modifier");
	}

	/* _________________________________________________________ */
	/**
	 */
	private void lieTemps()
	{
		// On recupere l'athlete selectionné
		final Athlete athleteSelected = (Athlete) lvListe
				.getItemAtPosition(removePos);
		final Performance performance = new Performance(athleteSelected,
				tempsChrono, 100);
		athleteSelected.getPerformances().add(performance);
		// Mise a jour de la base de données.
		database.addPerformance(athleteSelected, performance);
		Toast.makeText(this, "Performance associée.", Toast.LENGTH_SHORT)
				.show();
	}

	/* _________________________________________________________ */
	/**
	 */
	private void supprimer()
	{
		final AlertDialog.Builder adb = new AlertDialog.Builder(this);
		adb.setTitle("Suppression d'un athlète");
		adb.setMessage("Êtes vous sûr de vouloir le supprimer ? ");
		final int positionToRemove = removePos;
		adb.setNegativeButton("Annuler", null);
		adb.setPositiveButton("Ok", new AlertDialog.OnClickListener()
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
	 * Update.
	 * 
	 * @param arg0
	 *            the arg0
	 * @param arg1
	 *            the arg1
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@SuppressWarnings("unused")
	@Override
	public void update(final Observable arg0, final Object arg1)
	{
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
		removePos = position;
	}
}
