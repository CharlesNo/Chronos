/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : ActivityAddAthlete.java
 * 
 * Créé le 31 oct. 2013 à 14:45:37
 * 
 * Auteur : Jerome POINAS
 */
package vue;

import com.example.chronos.R;
import controleur.ControlerAddAthlete;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

/* _________________________________________________________ */
/**
 * @author Jerome POINAS
 * 
 */
public class ActivityAddAthlete extends Activity
{
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
		setContentView(R.layout.activity_add_athlete);
		final Button valider = (Button) findViewById(R.id.buttonValiderForm);
		valider.setOnClickListener(new ControlerAddAthlete(this));
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
/* _________________________________________________________ */
/*
 * Fin du fichier ActivityAddAthlete.java.
 * /*_________________________________________________________
 */
