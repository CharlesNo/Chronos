package vue;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;
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
		setContentView(R.layout.activity_main_activity_chronos);
		lvListe = (ListView) findViewById(R.id.listAthlete);
		final Button addAthlete = (Button) findViewById(R.id.bouttonAddAthlete);
		final ControlerListeAthlete controler = new ControlerListeAthlete(this);
		addAthlete.setOnClickListener(controler);
		lvListe.setOnItemClickListener(controler);
		lvListe.setOnItemLongClickListener(controler);	
		
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
