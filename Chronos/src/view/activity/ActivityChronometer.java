package view.activity;

import utility.Constantes;
import view.controler.ControlerChrono;
import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.chronos.R;

/**
 * The Class ActivityChronometer.
 */
public class ActivityChronometer extends Activity
{
	/** Les logs de la connexion */
	TextView	textlog;
	/** Bouton de (dé)connexion Départ */
	Button		buttonConnect;
	/** Bouton de (dé)connexion Arrivée */
	Button		buttonConnect2;
	/** ProgressBar */
	ProgressBar	wait;

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
		btStop.setEnabled(false);
		buttonConnect = (Button) findViewById(R.id.connect);
		buttonConnect2 = (Button) findViewById(R.id.connect2);
		wait = (ProgressBar) findViewById(R.id.progressBar1);
		wait.setVisibility(View.GONE);
		textlog = (TextView) findViewById(R.id.textlog);
		textlog.setText(Constantes.INITCONNECTION);
		textlog.setMovementMethod(new ScrollingMovementMethod());
		// add Eventlisteners
		final ControlerChrono controler = new ControlerChrono(this);
		btStart.setOnClickListener(controler);
		btStop.setOnClickListener(controler);
		buttonConnect.setOnClickListener(controler);
		buttonConnect2.setOnClickListener(controler);
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
