package view.activity;

import utility.Constantes;
import utility.wifiConnection.ClientStartTcp;
import utility.wifiConnection.ClientStopTcp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import com.chronos.R;

public class ActivitySynchronysing extends Activity
{
	/** Splash screen timer. */
	private final int	timerSynchronisation	= 2000;
	/** Le temps brute du chronometre reçu par ActivityChronos. */
	private long		tempsChrono;

	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.synchronisation_splash_screen);
		/*************** On recupere les données du bundle envoyées par ActivityChonos *********/
		final Bundle objetbunble = getIntent().getExtras();
		if ((objetbunble != null)
				&& objetbunble.containsKey(Constantes.BUNDLETIME))
		{
			final String temps = getIntent().getStringExtra(
					Constantes.BUNDLETIME);
			tempsChrono = Long.parseLong(temps);
		}
		/************* Synchronisation *****************/
		final Intent intent = new Intent(this, ActivityListAthlete.class);
		final Bundle objetbunbleToSend = new Bundle();
		// On retire les temps de transite de la trame
		objetbunbleToSend.putString(
				Constantes.BUNDLETIME,
				String.valueOf(tempsChrono - ClientStopTcp.chronoTimeStop
						- ClientStartTcp.chronoTimeStart));
		intent.putExtras(objetbunbleToSend);
		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				startActivity(intent);
				finish();
			}
		}, timerSynchronisation);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_synchronysing, menu);
		return true;
	}
}
