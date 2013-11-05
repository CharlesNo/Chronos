/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : DataBaseHandler.java
 * 
 * Créé le 5 nov. 2013 à 08:46:00
 * 
 * Auteur : Jerome POINAS
 */
package database;

import java.util.ArrayList;
import java.util.List;
import modele.Athlete;
import modele.Constantes;
import modele.exception.InvalideNomException;
import modele.exception.InvalidePrenomException;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* _________________________________________________________ */
/**
 * @author Jerome POINAS
 * 
 */
public class DatabaseHandler extends SQLiteOpenHelper implements Constantes
{
	/** Version de la base de données */
	private static final int	DATABASE_VERSION	= 1;

	/**
	 * Constructeur complet de la classe DatabaseHandler
	 * 
	 * @param context
	 *            Le contexte.
	 */
	public DatabaseHandler(final Context context)
	{
		super(context, Constantes.DATABASE_NAME, null, DATABASE_VERSION);
	}

	/* _________________________________________________________ */
	/**
	 * @param db
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(final SQLiteDatabase db)
	{
		final String CREATE_CONTACTS_TABLE = "CREATE TABLE "
				+ Constantes.TABLE_ATHLETE + "(" + Constantes.KEY_ID
				+ " INTEGER PRIMARY KEY," + Constantes.KEY_NOM + " TEXT,"
				+ Constantes.KEY_PRENOM + " TEXT" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	/**
	 * Mise à jour de la base de données.
	 * 
	 * @param db
	 *            La base de données à mettre à jour.
	 * @param oldVersion
	 *            Ancienne version.
	 * @param newVersion
	 *            Nouvelle version.
	 */
	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
			final int newVersion)
	{
		// Si la table existe on l'a supprime
		db.execSQL("DROP TABLE IF EXISTS " + Constantes.TABLE_ATHLETE);
		onCreate(db);
	}

	/*********************************************************************/
	// All CRUD(Create, Read, Update, Delete) Operations
	/**
	 * Methode qui permet d'enregister un athlete dans la base de données.
	 * 
	 * @param athlete
	 *            L'athlete à ajouter.
	 */
	public void addAthlete(final Athlete athlete)
	{
		final SQLiteDatabase db = getWritableDatabase();
		final ContentValues values = new ContentValues();
		values.put(Constantes.KEY_NOM, athlete.getNom());
		values.put(Constantes.KEY_PRENOM, athlete.getPrenom());
		// Insertion d'une ligne dans la table
		db.insert(Constantes.TABLE_ATHLETE, null, values);
		db.close(); // On ferme la connexion
	}

	/**
	 * Methode qui permet de retourner une ligne dans la table.
	 * 
	 * @param id
	 *            L'ID de l'athlete à trouver.
	 * @return L'athlete recherché.
	 */
	public Athlete getAthlete(final int id)
	{
		final SQLiteDatabase db = getReadableDatabase();
		final Cursor cursor = db.query(Constantes.TABLE_ATHLETE, new String[] {
				Constantes.KEY_ID, Constantes.KEY_NOM, Constantes.KEY_PRENOM },
				Constantes.KEY_ID + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);
		if (cursor != null)
		{
			cursor.moveToFirst();
		}
		Athlete athlete = null;
		try
		{
			athlete = new Athlete(cursor.getString(1), cursor.getString(2));
			return athlete;
		}
		catch (final InvalideNomException e)
		{
		}
		catch (final InvalidePrenomException e)
		{
		}
		return athlete;
	}

	/**
	 * Methode qui permet de retourner l'ensemble des athletes de la base.
	 * 
	 * @return La liste des athletes.
	 */
	public List<Athlete> getAllAthletes()
	{
		final List<Athlete> mesAthletes = new ArrayList<Athlete>();
		// On selectionne toutes les lignes dans la base de données
		final String selectQuery = "SELECT  * FROM " + Constantes.TABLE_ATHLETE;
		final SQLiteDatabase db = getWritableDatabase();
		final Cursor cursor = db.rawQuery(selectQuery, null);
		// Boucle pour remonter toutes les lignes.
		if (cursor.moveToFirst())
		{
			do
			{
				Athlete athlete;
				try
				{
					athlete = new Athlete(cursor.getString(1),
							cursor.getString(2));
					mesAthletes.add(athlete);
				}
				catch (final InvalideNomException e)
				{
				}
				catch (final InvalidePrenomException e)
				{
				}
			}
			while (cursor.moveToNext());
		}
		return mesAthletes;
	}

	/**
	 * Mise à jour d'un athlete.
	 * 
	 * @param athlete
	 *            L'athlete à modifier.
	 * @return L'ID de l'athlete modifié.
	 * 
	 */
	public int updateContact(final Athlete athlete)
	{
		final SQLiteDatabase db = getWritableDatabase();
		final ContentValues values = new ContentValues();
		values.put(Constantes.KEY_NOM, athlete.getNom());
		values.put(Constantes.KEY_PRENOM, athlete.getPrenom());
		// Mise à jour
		return db.update(Constantes.TABLE_ATHLETE, values, Constantes.KEY_ID
				+ " = ?", new String[] { String.valueOf(athlete.getID()) });
	}

	/**
	 * Methode qui permet de supprimer un athlete de la base de données.
	 * 
	 * @param athlete
	 *            L'athlete à supprimer.
	 * 
	 */
	public void deleteAthlete(final Athlete athlete)
	{
		final SQLiteDatabase db = getWritableDatabase();
		db.delete(Constantes.TABLE_ATHLETE, Constantes.KEY_NOM + " = ?",
				new String[] { String.valueOf(athlete.getNom()) });
		db.close();
	}

	/**
	 * Retourne le nombre d'athlete dans la base de données.
	 * 
	 * @return Le nombre d'athlete dans la base de données.
	 */
	public int getContactsCount()
	{
		final String countQuery = "SELECT  * FROM " + Constantes.TABLE_ATHLETE;
		final SQLiteDatabase db = getReadableDatabase();
		final Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		return cursor.getCount();
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier DataBaseHandler.java.
 * /*_________________________________________________________
 */
