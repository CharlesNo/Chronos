package view.controler;

import utility.Constantes;
import utility.wifiConnection.ClientStartTcp;
import view.ActivityListAthlete;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.chronos.R;

/* _________________________________________________________ */
/**
 * The Class ControlerListAthlete.
 * 
 * @author Jerome Poinas
 */
public class ControlerChrono implements OnClickListener
{
	/** The activity. */
	private final Activity				activity;
	/** Le chronometre */
	private final business.Chronometer	chronos;

	/**
	 * Instantiates a new view.controler fen chrono.
	 * 
	 * @param activityChronos
	 *            the activity chronos
	 */
	public ControlerChrono(final Activity activityChronos)
	{
		activity = activityChronos;
		chronos = (business.Chronometer) activity
				.findViewById(R.id.chronometer);
	}

	/* _________________________________________________________ */
	/**
	 * On click.
	 * 
	 * @param source
	 *            the source
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(final View source)
	{
		if (source == activity.findViewById(R.id.btStart))
		{
			chronos.start();
		}
		if (source == activity.findViewById(R.id.btStop))
		{
			chronos.stop();
			final Intent intent = new Intent(activity,
					ActivityListAthlete.class);
			final Bundle objetbunble = new Bundle();
			objetbunble.putString(
					Constantes.BUNDLETIME,
					String.valueOf(SystemClock.elapsedRealtime()
							- chronos.getBase()));
			intent.putExtras(objetbunble);
			activity.startActivity(intent);
		}
		if (source == activity.findViewById(R.id.connect))
		{
			final Button connect = (Button) activity.findViewById(R.id.connect);
			if (connect.getText().equals(Constantes.CONNECTED))// Si on est pas
																// connecté
			{
				try
				{
					ClientStartTcp.closeConnection();
					final ClientStartTcp connexion = new ClientStartTcp("192.168.1.131",
							8888, activity);
					final Thread thread = new Thread(connexion);
					thread.start();
				}
				catch (final Exception e)
				{
					e.printStackTrace();
				}
			}
			else
			/* On se deconnecte */{
				final Button connection = (Button) activity
						.findViewById(R.id.connect);
				connection.setText(Constantes.CONNECTED);
				ClientStartTcp.closeConnection();
			}
		}
	}
}
