/* ________________________________________________________ */
/**
 * Fichier : Constantes.java
 * 
 * Créé le 13 nov. 2013 à 08:41:36
 * 
 * Auteur : Jerome POINAS
 */
package utility;

import java.io.File;
import android.os.Environment;

/* ________________________________________________________ */
/**
 * The Interface Constantes.
 */
public interface Constantes
{
	// ** SQLite **********************************************************//
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
	/** Chaine de caractère : réglage de la distance. */
	public static final String	REGLAGE_DISTANCE		= "Réglage de la distance";
	/** Chaine de caractère : valider */
	public static final String	VALIDATE				= "Valider";
	/** Chaine de caractère : annuler */
	public static final String	CANCEL					= "Annuler";
	/** Chaine de caractère : ajouter. */
	public static final String	ADD						= "Ajouter";
	/** Chaine de caractère : (vide). */
	public static final String	EMPTY					= "";
	/** Chaine de caractère : nom vide. */
	public static final String	EMPTYNAME				= "Le nom est vide";
	/** Chaine de caractère : prénom vide. */
	public static final String	EMPTYFIRSTNAME			= "Le prénom est vide";
	/** Chaine de caractère : renseigner nom et prénom. */
	public static final String	REQUESTINFORM			= "Veuillez renseigner le nom et le prénom de l'athlète.";
	/** Chaine de caractère : identifier bundle temps. */
	public static final String	BUNDLETIME				= "temps";
	/** Chaine de caractère : gestion. */
	public static final String	MANAGETITLE				= "Gestion";
	/** Chaine de caractère : modifier. */
	public static final String	MODIFY					= "Modifier";
	/** Chaine de caractère : lier temps. */
	public static final String	ADDPERF					= "Lier temps";
	/** Chaine de caractère : supprimer. */
	public static final String	DELETE					= "Supprimer";
	/** Chaine de caractère : performance associée. */
	public static final String	ASSOCIATEDPERF			= "Performance associée";
	/** Chaine de caractère : suppression athlète. */
	public static final String	DELETETITLE				= "Suppression d'un athlète";
	/** Chaine de caractère : suppression athlète validation. */
	public static final String	VALIDATEDELETE			= "Êtes vous sûr de vouloir supprimer cet athlète? ";
	/** Chaine de caractère : distance. */
	public static final String	BUNDLEDISTANCE			= "distance";
	/** Chaine de caractère : erreur contact serveur. */
	public static final String	ERRORSOCKET				= "Impossible de contacter le serveur\n";
	/** Chaine de caractère : connexion départ. */
	public static final String	CONNECTED				= "Connexion départ";
	/** Chaine de caractère : déconnexion départ. */
	public static final String	DISCONNECTED			= "Déconnexion départ";
	/** Chaine de caractère : connexion établie départ. */
	public static final String	CONNECTEDFROMSTART		= "Connection établie avec le capteur de départ\n";
	/** Chaine de caractère : déconnexion capteur départ. */
	public static final String	DISCONNECTEDFROMSTART	= "Déconnexion avec le capteur de départ\n";
	/** Chaine de caractère : connexion arrivée. */
	public static final String	CONNECTEDSTOP			= "Connexion arrivée";
	/** Chaine de caractère : déconnexion arrivée. */
	public static final String	DISCONNECTEDSTOP		= "Déconnexion arrivée";
	/** Chaine de caractère : connexion établie arrivée. */
	public static final String	CONNECTEDFROMSTOP		= "Connection établie avec le capteur d'arrivée\n";
	/** Chaine de caractère : déconnexion capteur arrivée. */
	public static final String	DISCONNECTEDFROMSTOP	= "Déconnexion avec le capteur d'arrivée\n";
	/** Chaine de caractère : en attente connexion. */
	public static final String	WAITINGFORCONNECTION	= "En attente de connexion...\n";
	/** Chaine de caractère : initialisation connexion. */
	public static final String	INITCONNECTION			= "Initialisation de la connexion...\n";
	/** Chaine de caractère : base de données exportée. */
	public static final String	DATABASEEXPORTED		= "Les données ont été exportées dans "
																+ Environment
																		.getExternalStorageDirectory()
																+ File.separator
																+ "chronos";
	/** Chaine de caractère : interruption connexion départ. */
	public static final String	INTERRUPTEDSTART		= "Connexion interrompue avec le capteur de départ\n";
	/** Chaine de caractère : interruption connexion arrivée. */
	public static final String	INTERRUPTEDSTOP			= "Connexion interrompue avec le capteur d'arrivée\n";
}
/* ________________________________________________________ */
/*
 * Fin du fichier Constantes.java
 * /*________________________________________________________
 */
