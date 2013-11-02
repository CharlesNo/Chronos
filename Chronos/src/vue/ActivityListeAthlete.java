package vue;

import java.util.Observable;
import java.util.Observer;
import modele.Modele;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.chronos.R;
import controleur.ControlerListeAthlete;

/**
 * La classe Main activity.
 * 
 * @author Jerome POINAS
 *         Charles NEAU
 * 
 */
public class ActivityListeAthlete extends Activity implements Observer
{
	/** The lv liste. */
	private ListView	lvListe;
	/** The button ajouter. */
	private Button		buttonAjouter;

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
		setContentView(R.layout.activity_main_activity_chronos);
		/* Initialisation des attributs */
		lvListe = (ListView) findViewById(R.id.listAthlete);
		buttonAjouter = (Button) findViewById(R.id.bouttonAddAthlete);
		/* Creation du modele et ajout en tant qu'observeur */
		final Modele modele = new Modele(this);
		modele.addObserver(this);
		/* Creation du controleur */
		final ControlerListeAthlete controler = new ControlerListeAthlete(this,
				modele);
		/* Ajout du controleur en tant que Listener */
		buttonAjouter.setOnClickListener(controler);
		lvListe.setOnItemClickListener(controler);
		lvListe.setOnItemLongClickListener(controler);
		/* Creation du bundle de récupération des données */
		createBundle();
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
			final TextView champsTemps = (TextView) findViewById(R.id.tempsChrono);
			champsTemps.setText(temps);
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
