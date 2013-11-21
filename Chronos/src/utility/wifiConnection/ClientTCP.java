/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : ClientTCP.java
 * 
 * Créé le 21 nov. 2013 à 15:58:47
 * 
 * Auteur : Jerome POINAS
 */
package utility.wifiConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import view.ActivityChronometer;

/* _________________________________________________________ */
/**
 * @author Jerome POINAS
 * 
 */
public class ClientTCP implements Runnable
{
	/** L'instance unique du client */
	private static ClientTCP			instance;
	/** Socket de liaison avec le serveur */
	private Socket						socketClient;
	/** Flux de sortie vers le serveur */
	private ObjectOutputStream			toServer;
	/** Flux d'entrée en provenance du serveur */
	private ObjectInputStream			fromServer;
	/** Adresse du serveur */
	private final InetSocketAddress		addrSvr;
	/** The timeout socket. */
	private final int					timeoutSocket	= 3000;			// 3s
	/** Indique quand arreter l'écoute (du serveur). */
	private boolean						wantToDisconnect;
	/** indique si le client est connecté au serveur */
	private boolean						connected		= false;
	/** Message de la derniére erreur qui s'est produite */
	private String						lastError		= "Aucune erreur";
	private final ActivityChronometer	activity;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new client tcp.
	 * 
	 * @param p_addrIPv4
	 *            the p_addr i pv4
	 * @param p_port
	 *            the p_port
	 * @param activity
	 */
	public ClientTCP(final String p_addrIPv4, final int p_port,
			final ActivityChronometer activity)
	{
		super();
		this.activity = activity;
		addrSvr = new InetSocketAddress(p_addrIPv4, p_port);
	}

	/* __________________________________________________________ */
	/**
	 * Permet au client de se connecter au serveur
	 * 
	 * @return Vrai si la connexion est établie, faux si ce n'est pas le cas.
	 */
	public boolean connexion()
	{
		System.out.println("ClientTCP.connexion()");
		if (!connected)
		{
			System.out.println("si pas connected");
			System.out.println("try");
			socketClient = new Socket();
			try
			{
				socketClient.connect(addrSvr, timeoutSocket);
			}
			catch (final IOException e)
			{
				e.printStackTrace();
			}
			System.out.println("connect");
			try
			{
				toServer = new ObjectOutputStream(
						socketClient.getOutputStream());
			}
			catch (final IOException e)
			{
				e.printStackTrace();
			}
			try
			{
				fromServer = new ObjectInputStream(
						socketClient.getInputStream());
			}
			catch (final IOException e)
			{
				e.printStackTrace();
			}
			wantToDisconnect = false;
			return true;
		}
		return true;
	}

	/* __________________________________________________________ */
	/**
	 * Méthode définissant tout ce que doit exécuter le thread
	 * On aura ici une boucle de lecture du flux en provenance du serveur
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		System.out.println("ClientTCP.run()");
		connected = connexion();
		if (connected)
		{
			ecouterServeur();
		}
		else
		{
			System.err.println("Non connecté");
		}
	}

	/* __________________________________________________________ */
	/**
	 * Permet de lancer l'ecoute de ce qu'envoie le serveur.
	 * L'ecoute se termine quand le client se déconnecte avec la méthode
	 * deconnexion()
	 */
	private void ecouterServeur()
	{
		System.out.println("ClientTCP.ecouterServeur()");
		while (!wantToDisconnect)
		{
			// Récupération des données qu'envoie le serveur
			try
			{
				// final Object obj = fromServer.readObject();
				System.out.println(fromServer.readObject());
				// if (obj != null)
				// {
				// if (obj.toString().contains("start"))
				// {
				// final business.Chronometer chronos = (business.Chronometer)
				// activity
				// .findViewById(R.id.chronometer);
				// chronos.start();
				// }
				// }
			}
			catch (final ClassNotFoundException e)
			{
				lastError = "Données envoyées par le serveur inconnues";
			}
			// Exception attendue lorsqu'on veut se déconnecter. la fermeture du
			// flux fromServer
			// va déclencher cette exception, ou bien perte du réseau
			catch (final IOException e)
			{
				// Cas oé la deconnexion est anormale (perte de réseau)
				if (!wantToDisconnect)
				{
					lastError = "Perte de connexion avec le serveur";
					// traitement
					connected = false;
					deconnexion();
				}
			}
		}
	}

	/* __________________________________________________________ */
	/**
	 * Permet de se déconnecter
	 */
	public void deconnexion()
	{
		// on change le flag gérant la boucle d'ecoute en provenance du serveur
		wantToDisconnect = true;
		// On ferme tous les flux et la socket
		try
		{
			toServer.close();
			fromServer.close();
			socketClient.close();
			connected = false;
		}
		catch (final IOException e)
		{
			lastError = e.getMessage();
		}
	}

	/* __________________________________________________________ */
	/**
	 * Permet d'envoyer des données au serveur.
	 * 
	 * @param p_donnee
	 *            the p_donnee
	 * @return Vrai si l'envoi é réussi, sinon faux ( faux égalemen si le
	 *         parametre est null ==> on n'effectue pas l'envoi)
	 */
	public boolean sendToServer(final Object p_donnee)
	{
		// On ne tente l'envoie que si l'on est connecté
		if (connected)
		{
			// On envoie les données uniquement s'il y en a
			if (p_donnee != null)
			{
				try
				{
					// Envoie des données
					toServer.writeObject(p_donnee);
					toServer.flush(); // "Nettoyage" du flux
					return true;
				}
				catch (final IOException e)
				{
					lastError = e.getMessage();
					return false;
				}
			}
			return false;
		}
		return false;
	}

	/* __________________________________________________________ */
	/**
	 * Permet d'obtenir le message de la derniére erreur qui s'est produite
	 * 
	 * @return Le message de la derniére erreur qui s'est produite.
	 */
	public String getLastError()
	{
		return lastError;
	}

	/* __________________________________________________________ */
	/**
	 * Permet de savoir si le client est connecté au serveur
	 * 
	 * @return vrai si le client est connecté, sinon, faux
	 */
	public boolean isConnected()
	{
		return connected;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier ClientTCP.java.
 * /*_________________________________________________________
 */
