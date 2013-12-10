/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : ClientStopTcp.java
 * 
 * Créé le 27 nov. 2013 à 14:42:30
 * 
 * Auteur : Jerome POINAS
 */
package utility.wifiConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import utility.Constantes;
import view.activity.ActivityListAthlete;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.chronos.R;

/**
 * The Class ClientStartTcp.
 */
public class ClientStopTcp implements Runnable
{
	/** The tcp socket. */
	private static Socket	tcpSocket;
	/** The in buff. */
	private BufferedReader	inBuff;
	/** The host. */
	private String			host	= Constantes.EMPTY;
	/** The port. */
	private int				port	= 80;
	/** L'activité */
	private final Activity	activity;
	/** Boolean run */
	static boolean			run		= true;
	/**
	 * Temps reel du chronometre auquel on va soustraire le temps d'envoie de
	 * trame.
	 */
	private static long		chronoTimeStop;

	/**
	 * Instantiates a new client tcp.
	 * 
	 * @param host
	 *            the host
	 * @param port
	 *            the port
	 * @param activity
	 *            L'activité.
	 */
	public ClientStopTcp(final String host, final int port,
			final Activity activity)
	{
		this.host = host;
		this.port = port;
		this.activity = activity;
	}

	/* _________________________________________________________ */
	/**
	 * Extracted.
	 * 
	 * @param host
	 *            the host
	 * @param port
	 *            the port
	 * @throws UnknownHostException
	 *             the unknown host exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void extracted(final String host, final int port)
			throws UnknownHostException, IOException
	{
		final InetAddress connectedAddress = InetAddress.getByName(host);
		tcpSocket = new Socket(connectedAddress, port);
		inBuff = new BufferedReader(new InputStreamReader(
				tcpSocket.getInputStream()));
	}

	/* _________________________________________________________ */
	/**
	 * Modifie l'aspect du bouton de connexion.
	 * 
	 * @param connected
	 *            La valeur correspondant au status du bouton de connexion.
	 */
	public void modifyStatus(final boolean connected)
	{
		final Button connection = (Button) activity.findViewById(R.id.connect2);
		final ProgressBar wait = (ProgressBar) activity
				.findViewById(R.id.progressBar1);
		if (connected == true)
		{
			connection.setText(Constantes.DISCONNECTEDSTOP);
		}
		else
		{
			connection.setText(Constantes.CONNECTEDSTOP);
		}
		wait.setVisibility(View.GONE);
	}

	/* _________________________________________________________ */
	/**
	 * Run.
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		try
		{
			extracted(host, port);
			run = true;
		}
		catch (final Exception e1)
		{
			// Erreur lancée sur le thread principal d'activity
			activity.runOnUiThread(new Runnable()
			{
				@Override
				public void run()
				{
					modifyStatus(false);
					final TextView log = (TextView) activity
							.findViewById(R.id.textlog);
					log.append(Constantes.ERRORSOCKET);
				}
			});
			run = false;
		}
		if (run)
		{
			activity.runOnUiThread(new Runnable()
			{
				@Override
				public void run()
				{
					modifyStatus(true);
					final TextView log = (TextView) activity
							.findViewById(R.id.textlog);
					log.append(Constantes.CONNECTEDFROMSTOP);
				}
			});
		}
		final business.Chronometer chronos = (business.Chronometer) activity
				.findViewById(R.id.chronometer);
		while (run)
		{
			String newLine = Constantes.EMPTY;
			try
			{
				newLine = inBuff.readLine();
				if ((newLine != null) && newLine.contains(Constantes.STOP))
				{
					/** Traitement synchronisation */
					chronoTimeStop = System.currentTimeMillis();
					final long timeToPing = getTimePing();
					chronoTimeStop = chronoTimeStop - timeToPing;
					/*******************************/
					activity.runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
							// System.out.println(Formatter
							// .miseEnForme(chronoTimeStop
							// - ClientStartTcp.chronoTimeStart));
							chronos.stop();
							final Intent intent = new Intent(activity,
									ActivityListAthlete.class);
							final Bundle objetbunble = new Bundle();
							objetbunble.putString(Constantes.BUNDLETIME, String
									.valueOf(SystemClock.elapsedRealtime()
											- chronos.getBase()));
							intent.putExtras(objetbunble);
							activity.startActivity(intent);
						}
					});
					run = false;
					activity.runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
							modifyStatus(false);
						}
					});
					closeConnection();
					activity.runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
							final TextView log = (TextView) activity
									.findViewById(R.id.textlog);
							log.append(Constantes.DISCONNECTEDFROMSTOP);
						}
					});
				}
			}
			catch (final Exception e)
			{
				activity.runOnUiThread(new Runnable()
				{
					@Override
					public void run()
					{
						modifyStatus(false);
						closeConnection();
					}
				});
			}
		}
	}

	/* _________________________________________________________ */
	/**
	 * Methode qui ping l'arduino et qui retourne le temps d'envoie de la trame.
	 * 
	 * @return Le temps d'envoie de la requete ping.
	 */
	protected long getTimePing()
	{
		final InetAddress target = tcpSocket.getInetAddress();
		final long start = System.currentTimeMillis();
		try
		{
			target.isReachable(1000);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		final long end = System.currentTimeMillis();
		long timePing = end - start;
		timePing = timePing / 2;
		System.out.println("ClientStopTcp.getTimePing()" + timePing);
		return timePing;
	}

	/* _________________________________________________________ */
	/**
	 * Permet de fermer la connexion de la socket.
	 */
	public static void closeConnection()
	{
		if (tcpSocket != null)
		{
			try
			{
				tcpSocket.close();
			}
			catch (final IOException e)
			{
				e.printStackTrace();
			}
		}
		run = false;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier ClientStopTcp.java.
 * /*_________________________________________________________
 */
