package vue;

import modele.Chronometer;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;

import com.chronos.R;
import com.chronos.R.layout;

import controleur.ControlerFenChrono;

/**
 * The Class ActivityChronos.
 */
public class ActivityChronos extends Activity
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
		setContentView(R.layout.activity_chronometre);
		
		final Button btStart = (Button) findViewById(R.id.btStart);
		final Button btStop = (Button) findViewById(R.id.btStop);
		final ControlerFenChrono controler = new ControlerFenChrono(this);
		btStart.setOnClickListener(controler);
		btStop.setOnClickListener(controler);
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
