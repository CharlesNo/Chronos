/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : SyncStartTcp.java
 * 
 * Créé le 20 déc. 2013 à 14:54:28
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
import android.widget.TextView;
import com.chronos.R;

/* _________________________________________________________ */
/**
 * @author Jerome POINAS
 * 
 */
public class SyncStopTcp implements Runnable
{
	/** The tcp socket. */
	private static Socket	tcpSocket;
	/** The in buff. */
	private BufferedReader	inBuff;
	/** The host. */
	private String			host				= Constantes.EMPTY;
	/** The port. */
	private int				port				= 80;
	/** L'activité */
	private final Activity	activity;
	/** Temps de l'horloge arduino */
	public static String	tempsArduinoStop	= "0";
	/** Boolean run */
	static boolean			run					= true;
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
	public SyncStopTcp(final String host, final int port,
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
			activity.runOnUiThread(new Runnable()
			{
				@Override
				public void run()
				{
					final TextView log = (TextView) activity
							.findViewById(R.id.textlog);
					log.append("Arrivée prêt à être synchronisé\n");
				}
			});
		}
		catch (final Exception e1)
		{
			run = false;
		}
		while (run)
		{
			String newLine = Constantes.EMPTY;
			try
			{
				newLine = inBuff.readLine();
				if ((newLine != null) && newLine.contains(Constantes.STOP))
				{
					tempsArduinoStop = newLine.substring(5, newLine.length());
					run = false;
					closeConnection();
				}
			}
			catch (final Exception e)
			{
				activity.runOnUiThread(new Runnable()
				{
					@Override
					public void run()
					{
						closeConnection();
					}
				});
			}
		}
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
 * Fin du fichier SyncStartTcp.java.
 * /*_________________________________________________________
 */
