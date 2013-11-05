package view;

import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;
import persistence.DatabaseHandler;
import view.controler.ControlerListAthlete;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import business.Model;
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
	/** The lv liste. */
	private ListView	lvListe;
	/** The button ajouter. */
	private Button		buttonAjouter;
	/** Temps chrono */
	private long		tempsChrono;

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
		lvListe = (ListView) findViewById(R.id.listAthlete);
		buttonAjouter = (Button) findViewById(R.id.bouttonAddAthlete);
		/* Base de données */
		final DatabaseHandler database = new DatabaseHandler(getBaseContext());
		/* Creation du business et ajout en tant qu'observeur */
		final Model model = new Model(this, database);
		model.addObserver(this);
		/* Creation du bundle de récupération des données */
		createBundle();
		/* Creation du view.controler */
		final ControlerListAthlete controler = new ControlerListAthlete(this,
				model, database, tempsChrono);
		/* Ajout du view.controler en tant que Listener */
		buttonAjouter.setOnClickListener(controler);
		lvListe.setOnItemClickListener(controler);
		lvListe.setOnItemLongClickListener(controler);
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
		builder.append(df.format(minutes)).append(":");
		builder.append(df.format(seconds)).append(":");
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
		// TODO Auto-generated method stub
	}
}
