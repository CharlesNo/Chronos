package vue;

import java.util.ArrayList;
import java.util.List;
import modele.Athlete;
import modele.exception.InvalideNomException;
import modele.exception.InvalidePrenomException;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.example.chronos.R;
import controleur.ControlerListeAthlete;

/**
 * La classe Main activity.
 * C'est en quelque sorte le "main",
 * le point d'entrée du programme.
 * 
 * @author Jerome POINAS
 *         Charles NEAU
 * 
 */
public class ActivityListeAthlete extends Activity
{
	/** The mes athletes. */
	List<Athlete>	mesAthletes	= new ArrayList<Athlete>();
	/** The lv liste. */
	ListView		lvListe;

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
		super.onCreate(savedInstanceState);
		final ControlerListeAthlete controler = new ControlerListeAthlete(this);
		setContentView(R.layout.activity_main_activity_chronos);
		lvListe = (ListView) findViewById(R.id.listAthlete);
		final Button addAthlete = (Button) findViewById(R.id.bouttonAddAthlete);
		lvListe.setOnItemClickListener(controler);
		lvListe.setOnItemLongClickListener(controler);
		bundleInit();
		lvListe.setAdapter(new ArrayAdapter<Athlete>(getBaseContext(),
				android.R.layout.simple_list_item_1, mesAthletes));
		addAthlete.setOnClickListener(controler);
	}

	/**
	 * On recupere les données provenant de ActivityAddAthlete si il y en a.
	 */
	private void bundleInit()
	{
		final Bundle objetbunble;
		objetbunble = getIntent().getExtras();
		// On récupère les données du Bundle
		if ((objetbunble != null) && objetbunble.containsKey("Nom")
				&& objetbunble.containsKey("Prenom"))
		{
			final String nomAthlete = getIntent().getStringExtra("Nom");
			final String prenomAthlete = getIntent().getStringExtra("Prenom");
			try
			{
				final Athlete athlete = new Athlete(nomAthlete, prenomAthlete);
				mesAthletes.add(athlete);
			}
			catch (final InvalideNomException e)
			{
				Toast.makeText(getBaseContext(), e.getMessage(),
						Toast.LENGTH_SHORT).show();
			}
			catch (final InvalidePrenomException e)
			{
				Toast.makeText(getBaseContext(), e.getMessage(),
						Toast.LENGTH_SHORT).show();
			}
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
}
