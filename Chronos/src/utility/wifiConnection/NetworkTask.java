package utility.wifiConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import view.ActivityChronometer;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;
import com.chronos.R;

/* _________________________________________________________ */
/**
 * @author Jerome POINAS
 *         La classe de gestion des sockets
 */
public class NetworkTask extends AsyncTask<Void, byte[], Boolean>
{
	/**
	 * 
	 */
	Socket								nsocket;
	/**
	 * 
	 */
	InputStream							nis;
	/**
	 * 
	 */
	BufferedReader						inFromServer;
	/**
	 * 
	 */
	private final ActivityChronometer	activity;
	/** Connexion booleen */
	static Boolean						connected	= false;

	/* _________________________________________________________ */
	/**
	 * @param activityChronometer
	 */
	public NetworkTask(final ActivityChronometer activityChronometer)
	{
		activity = activityChronometer;
	}

	/* _________________________________________________________ */
	/**
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute()
	{
		changeConnectionStatus(true);
	}

	/* _________________________________________________________ */
	/**
	 * @param params
	 * @return
	 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
	 */
	@Override
	protected Boolean doInBackground(final Void... params)
	{ // This runs on a different thread
		boolean result = false;
		try
		{
			final InetSocketAddress sockaddr = new InetSocketAddress(
					"192.168.43.216", 8888);
			nsocket = new Socket();
			nsocket.connect(sockaddr, 10000);
			System.out.println("Connecté");
			nis = nsocket.getInputStream();
			System.out.println("InputStream");
			inFromServer = new BufferedReader(new InputStreamReader(nis));
			while (nsocket.isConnected())
			{
				System.out.println("Buffered");
				final String readLine = inFromServer.readLine();
				System.out.println(readLine);
				/*
				 * if (readLine.contains("start"))
				 * {
				 * final business.Chronometer chronos = (business.Chronometer)
				 * activity
				 * .findViewById(R.id.chronometer);
				 * System.out.println("reception start");
				 * chronos.start();
				 * break;
				 * }
				 */
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
			result = true;
		}
		catch (final Exception e)
		{
			e.printStackTrace();
			result = true;
		}
		finally
		{
			closeSocket();
		}
		return result;
	}

	// Method closes the socket
	public void closeSocket()
	{
		try
		{
			nis.close();
			nsocket.close();
			changeConnectionStatus(false);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}

	// Method is called when task is cancelled
	@Override
	protected void onCancelled()
	{
		changeConnectionStatus(false);
	}

	// Method is called after taskexecution
	@Override
	protected void onPostExecute(final Boolean result)
	{
		if (result)
		{
			outputText("onPostExecute: Terminé avec une erreur.");
		}
		else
		{
			outputText("onPostExecute: Terminé.");
		}
		changeConnectionStatus(false);
	}

	/**
	 * Permet de changer le statu de la connexion.
	 * 
	 * @param isConnected
	 *            boolean
	 */
	public void changeConnectionStatus(final Boolean isConnected)
	{
		connected = isConnected;
		final Button buttonConnect = (Button) activity
				.findViewById(R.id.connect);
		if (isConnected)
		{
			outputText("Connexion établie");
			buttonConnect.setText("Déconnexion");
		}
		else
		{
			outputText("Déconnecté du serveur.");
			buttonConnect.setText("Connexion");
		}
	}

	public static boolean getConnected()
	{
		return connected;
	}

	/**
	 * Methode qui permet d'ecrire les textlog
	 * 
	 * @param msg
	 *            Le message.
	 */
	public void outputText(final String msg)
	{
		final TextView textlog = (TextView) activity.findViewById(R.id.textlog);
		textlog.append(msg + "\n");
	}
}
