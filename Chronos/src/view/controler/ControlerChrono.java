package view.controler;

import utility.Constantes;
import utility.wifiConnection.ClientStartTcp;
import utility.wifiConnection.ClientStopTcp;
import view.ActivityListAthlete;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
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
			final Button btStop = (Button) activity.findViewById(R.id.btStop);
			btStop.setEnabled(true);
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
			final ProgressBar wait = (ProgressBar) activity
					.findViewById(R.id.progressBar1);
			final TextView log = (TextView) activity.findViewById(R.id.textlog);
			wait.setVisibility(View.VISIBLE);
			final Button connect = (Button) activity.findViewById(R.id.connect);
			if (connect.getText().equals(Constantes.CONNECTED))// Si on est pas
																// connecté
			{
				try
				{
					final Button connection = (Button) activity
							.findViewById(R.id.connect);
					connection.setText(Constantes.DISCONNECTED);
					log.append(Constantes.WAITINGFORCONNECTION);
					ClientStartTcp.closeConnection();
					final ClientStartTcp connexion = new ClientStartTcp(
							"192.168.1.131", 8888, activity);
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
				wait.setVisibility(View.GONE);
				log.append(Constantes.INTERRUPTEDSTART);
			}
		}
		if (source == activity.findViewById(R.id.connect2))
		{
			final ProgressBar wait = (ProgressBar) activity
					.findViewById(R.id.progressBar1);
			final TextView log = (TextView) activity.findViewById(R.id.textlog);
			wait.setVisibility(View.VISIBLE);
			final Button connect = (Button) activity
					.findViewById(R.id.connect2);
			if (connect.getText().equals(Constantes.CONNECTEDSTOP))// Si on est
																	// pas
																	// connecté
			{
				try
				{
					final Button connection = (Button) activity
							.findViewById(R.id.connect2);
					connection.setText(Constantes.DISCONNECTEDSTOP);
					log.append(Constantes.WAITINGFORCONNECTION);
					ClientStopTcp.closeConnection();
					final ClientStopTcp connexion = new ClientStopTcp(
							"192.168.1.118", 8888, activity);
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
						.findViewById(R.id.connect2);
				connection.setText(Constantes.CONNECTEDSTOP);
				ClientStopTcp.closeConnection();
				wait.setVisibility(View.GONE);
				log.append(Constantes.INTERRUPTEDSTOP);
			}
		}
	}
}
