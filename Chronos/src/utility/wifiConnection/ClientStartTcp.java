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
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import utility.Constantes;
import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import com.chronos.R;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientStartTcp.
 */
public class ClientStartTcp implements Runnable
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
	public ClientStartTcp(final String host, final int port, final Activity activity)
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
		final Button connection = (Button) activity.findViewById(R.id.connect);
		if (connected == true)
		{
			connection.setText(Constantes.DISCONNECTED);
		}
		else
		{
			connection.setText(Constantes.CONNECTED);
		}
	}

	/**
	 * Send message.
	 * 
	 * @param message
	 *            the message
	 */
	public void sendMessage(final String message)
	{
		synchronized (this)
		{
			if (tcpSocket.isConnected())
			{
				outPrint.println(message);
				outPrint.flush();
			}
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
					log.append(Constantes.CONNECTEDFROMSTART);
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
				if ((newLine != null) && newLine.contains("Start"))
				{
					System.out.println(newLine);
					final business.Chronometer chronos = (business.Chronometer) activity
							.findViewById(R.id.chronometer);
					activity.runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
							chronos.start();
						}
					});
					run = false;
					modifyStatus(false);
					closeConnection();
					activity.runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
							modifyStatus(true);
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
