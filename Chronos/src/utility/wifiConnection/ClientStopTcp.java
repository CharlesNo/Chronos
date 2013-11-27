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
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import utility.Constantes;
import view.ActivityListAthlete;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.TextView;
import com.chronos.R;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientStartTcp.
 */
public class ClientStopTcp implements Runnable
{
	/** The tcp socket. */
	private static Socket	tcpSocket;
	/** The in buff. */
	private BufferedReader	inBuff;
	/** The out print. */
	private PrintWriter		outPrint;
	/** The host. */
	private String			host	= "";
	/** The port. */
	private int				port	= 80;
	/** L'activité */
	private final Activity	activity;
	/** Boolean run */
	static boolean			run		= true;

	/**
	 * Instantiates a new client tcp.
	 * 
	 * @param host
	 *            the host
	 * @param port
	 *            the port
	 * @param activity
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
		outPrint = new PrintWriter(tcpSocket.getOutputStream());
	}

	public void modifyStatus(final boolean connected)
	{
		final Button connection = (Button) activity.findViewById(R.id.connect2);
		if (connected == true)
		{
			connection.setText(Constantes.DISCONNECTEDSTOP);
		}
		else
		{
			connection.setText(Constantes.CONNECTEDSTOP);
		}
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
		while (run)
		{
			String newLine = "";
			try
			{
				newLine = inBuff.readLine();
				System.out.println("newLine: " + newLine);
				if ((newLine != null) && newLine.contains("Stop"))
				{
					System.out.println(newLine);
					final business.Chronometer chronos = (business.Chronometer) activity
							.findViewById(R.id.chronometer);
					activity.runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
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
