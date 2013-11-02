package vue;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chronos.R;
import controleur.ControlerListeAthlete;

/**
 * La classe Main activity.
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
		
		//On récupère l'objet Bundle envoyé par l'autre Activity
        Bundle objetbunble  = this.getIntent().getExtras();
 
        //On récupère les données du Bundle
        if (objetbunble != null && objetbunble.containsKey("temps")) {
        	String temps = this.getIntent().getStringExtra("temps");
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
}
