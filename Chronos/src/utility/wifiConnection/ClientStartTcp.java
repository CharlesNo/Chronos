/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : ClientTCP.java
 * 
 * Créé le 25 nov. 2013 à 13:47:51
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
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.chronos.R;

/**
 * The Class ClientStartTcp.
 */
public class ClientStartTcp implements Runnable
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
	static long				chronoTimeStart;

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
	public ClientStartTcp(final String host, final int port,
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
		final ProgressBar wait = (ProgressBar) activity
				.findViewById(R.id.progressBar1);
		final Button connection = (Button) activity.findViewById(R.id.connect);
		if (connected == true)
		{
			connection.setText(Constantes.DISCONNECTED);
		}
		else
		{
			connection.setText(Constantes.CONNECTED);
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
					log.append(Constantes.CONNECTEDFROMSTART);
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
				if ((newLine != null) && newLine.contains(Constantes.START))
				{
					/** Traitement synchronisation */
					chronoTimeStart = System.currentTimeMillis();
					final long timeToPing = getTimePing();
					chronoTimeStart = chronoTimeStart - timeToPing;
					/*******************************/
					activity.runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
							chronos.start();
							final Button btStop = (Button) activity
									.findViewById(R.id.btStop);
							btStop.setEnabled(true);
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
							log.append(Constantes.DISCONNECTEDFROMSTART);
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
 * Fin du fichier ClientTCP.java.
 * /*_________________________________________________________
 */
