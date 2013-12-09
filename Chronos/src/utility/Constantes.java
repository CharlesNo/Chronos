/* ________________________________________________________ */
/**
 * Fichier : Constantes.java
 * 
 * cree le 11 oct. 2012 a 09:12:20
 * 
 * Auteur : Jean-Louis IMBERT
 */
package utility;

import java.io.File;
import android.os.Environment;

/* ________________________________________________________ */
/**
 * The Interface Constantes.
 */
@SuppressWarnings("javadoc")
public interface Constantes
{
	/** Le nom de la base de données */
	public static final String	DATABASE_NAME			= "ManagerAthlete";
	/** Colonne ID de la table Athlete */
	public static final String	KEY_ID					= "ID";
	/** Colonne NOM de la table Athlete */
	public static final String	KEY_NOM					= "NOM";
	/** Colonne PRENOM de la table Athlete */
	public static final String	KEY_PRENOM				= "PRENOM";
	/** Nom de la table qui conservera les athletes */
	public static final String	TABLE_ATHLETE			= "Athlete";
	/** Nom de la table qui conservera les performances */
	public static final String	TABLE_PERFORMANCE		= "Performance";
	/** Colonne TEMPS de la table performance */
	public static final String	KEY_TEMPS				= "TEMPS";
	/** Colonne DISTANCE de la table performance */
	public static final String	KEY_DISTANCE			= "DISTANCE";
	/** Colonne DATE de la table performance */
	public static final String	KEY_DATE				= "DATE";
	// ** String de l'application *****************************************//
	public static final String	REGLAGE_DISTANCE		= "Réglage de la distance";
	public static final String	VALIDATE				= "Valider";
	public static final String	CANCEL					= "Annuler";
	public static final String	ADD						= "Ajouter";
	public static final String	EMPTY					= "";
	public static final String	EMPTYNAME				= "Le nom est vide";
	public static final String	EMPTYFIRSTNAME			= "Le prenom est vide";
	public static final String	REQUESTINFORM			= "Veuillez renseigner le nom et le prenom de l'athlete.";
	public static final String	BUNDLETIME				= "temps";
	public static final String	MANAGETITLE				= "Gestion";
	public static final String	MODIFY					= "Modifier";
	public static final String	ADDPERF					= "Lier temps";
	public static final String	DELETE					= "Supprimer";
	public static final String	ASSOCIATEDPERF			= "Performance associée";
	public static final String	DELETETITLE				= "Suppression d'un athlète";
	public static final String	VALIDATEDELETE			= "Êtes vous sûr de vouloir supprimer cet athlète? ";
	public static final String	BUNDLEDISTANCE			= "distance";
	public static final String	ERRORSOCKET				= "Impossible de contacter le serveur\n";
	public static final String	CONNECTED				= "Connexion départ";
	public static final String	DISCONNECTED			= "Déconnexion départ";
	public static final String	CONNECTEDFROMSTART		= "Connection établie avec le capteur de départ\n";
	public static final String	DISCONNECTEDFROMSTART	= "Déconnexion avec le capteur de départ\n";
	public static final String	CONNECTEDSTOP			= "Connexion arrivée";
	public static final String	DISCONNECTEDSTOP		= "Déconnexion arrivée";
	public static final String	CONNECTEDFROMSTOP		= "Connection établie avec le capteur d'arrivée\n";
	public static final String	DISCONNECTEDFROMSTOP	= "Déconnexion avec le capteur d'arrivée\n";
	public static final String	WAITINGFORCONNECTION	= "En attente de connexion...\n";
	public static final String	INITCONNECTION			= "Initialisation de la connexion...\n";
	public static final String	DATABASEEXPORTED		= "Les données ont été exportées dans "
																+ Environment
																		.getExternalStorageDirectory()
																+ File.separator
																+ "chronos";
	public static final String	INTERRUPTEDSTART		= "Connexion interrompue avec le capteur de départ\n";
	public static final String	INTERRUPTEDSTOP			= "Connexion interrompue avec le capteur d'arrivée\n";		;
}
/* ________________________________________________________ */
/*
 * Fin du fichier Constantes.java
 * /*________________________________________________________
 */
