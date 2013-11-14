/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : DataBaseHandler.java
 * 
 * Créé le 5 nov. 2013 à 08:46:00
 * 
 * Auteur : Jerome POINAS
 */
package persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import business.Athlete;
import business.Constantes;
import business.Performance;
import business.exceptions.InvalidFirstNameException;
import business.exceptions.InvalidNameException;

/* _________________________________________________________ */
/**
 * @author Jerome POINAS
 * 
 */
public class DatabaseHandler extends SQLiteOpenHelper implements Constantes
{
	/** Version de la base de données */
	private static final int		DATABASE_VERSION	= 6;
	/** Instance de la base de données */
	private static DatabaseHandler	instance;

	/* _________________________________________________________ */
	/**
	 * Methode qui retourne l'instance unique de la base de données.
	 * 
	 * @param context
	 *            Le contexte.
	 * @return Instance unique de la base de données.
	 */
	public static DatabaseHandler getInstance(final Context context)
	{
		if (instance == null)
		{
			instance = new DatabaseHandler(context);
		}
		return instance;
	}

	/**
	 * Constructeur complet de la classe DatabaseHandler
	 * 
	 * @param context
	 *            Le contexte.
	 */
	private DatabaseHandler(final Context context)
	{
		super(context, Constantes.DATABASE_NAME, null, DATABASE_VERSION);
	}

	/* _________________________________________________________ */
	/**
	 * On create.
	 * 
	 * @param db
	 *            the db
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(final SQLiteDatabase db)
	{
		final String CREATE_ATHLETE_TABLE = "CREATE TABLE "
				+ Constantes.TABLE_ATHLETE + "(" + Constantes.KEY_ID
				+ " INTEGER PRIMARY KEY," + Constantes.KEY_NOM + " TEXT,"
				+ Constantes.KEY_PRENOM + " TEXT" + ")";
		db.execSQL(CREATE_ATHLETE_TABLE);
		final String CREATE_PERFORMANCE_TABLE = "CREATE TABLE "
				+ Constantes.TABLE_PERFORMANCE + "(" + Constantes.KEY_NOM
				+ " TEXT," + Constantes.KEY_PRENOM + " TEXT,"
				+ Constantes.KEY_TEMPS + " TEXT," + Constantes.KEY_DISTANCE
				+ " TEXT," + Constantes.KEY_DATE + " TEXT" + ")";
		db.execSQL(CREATE_PERFORMANCE_TABLE);
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
	@SuppressWarnings("unused")
	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion,
			final int newVersion)
	{
		// Si la table existe on l'a supprime
		db.execSQL("DROP TABLE IF EXISTS " + Constantes.TABLE_ATHLETE);
		db.execSQL("DROP TABLE IF EXISTS " + Constantes.TABLE_PERFORMANCE);
		onCreate(db);
	}

	/*********************************************************************/
	// All CRUD(Create, Read, Update, Delete) Operations
	/**
	 * Methode qui permet d'enregister une performance dans la base de données.
	 * 
	 * @param athlete
	 *            L'athlete concerné.
	 * 
	 * @param performance
	 *            La performance à ajouter.
	 */
	public void addPerformance(final Athlete athlete,
			final Performance performance)
	{
		final SQLiteDatabase db = getWritableDatabase();
		final ContentValues valuesPerf = new ContentValues();
		valuesPerf.put(Constantes.KEY_NOM, athlete.getName());
		valuesPerf.put(Constantes.KEY_PRENOM, athlete.getFirstName());
		valuesPerf.put(Constantes.KEY_TEMPS, performance.getChrono());
		valuesPerf.put(Constantes.KEY_DISTANCE, performance.getDistance());
		valuesPerf.put(Constantes.KEY_DATE, performance.getDate());
		db.insert(Constantes.TABLE_PERFORMANCE, null, valuesPerf);
		db.close(); // On ferme la connexion
	}

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
		values.put(Constantes.KEY_NOM, athlete.getName());
		values.put(Constantes.KEY_PRENOM, athlete.getFirstName());
		// Insertion d'une ligne dans la table
		db.insert(Constantes.TABLE_ATHLETE, null, values);
		final ContentValues valuesPerf = new ContentValues();
		valuesPerf.put(Constantes.KEY_NOM, athlete.getName());
		valuesPerf.put(Constantes.KEY_PRENOM, athlete.getFirstName());
		final List<Performance> listePerf = athlete.getPerformances();
		final int taille = listePerf.size();
		if (taille != 0)
		{
			final Performance perf = listePerf.get(taille - 1);
			valuesPerf.put(Constantes.KEY_TEMPS, perf.getChrono());
			valuesPerf.put(Constantes.KEY_DISTANCE, perf.getDistance());
			valuesPerf.put(Constantes.KEY_DATE, perf.getDate());
			db.insert(Constantes.TABLE_PERFORMANCE, null, valuesPerf);
		}
		db.close(); // On ferme la connexion
	}

	/**
	 * Methode qui permet de retourner une ligne dans la table.
	 * 
	 * @param nom
	 *            Le nom de l'athlete.
	 * @param prenom
	 *            Le prenom de l'athlete.
	 * @return L'athlete recherché.
	 */
	public Athlete getAthlete(final String nom, final String prenom)
	{
		final SQLiteDatabase db = getReadableDatabase();
		final Cursor cursor = db.query(Constantes.TABLE_ATHLETE, new String[] {
				Constantes.KEY_ID, Constantes.KEY_NOM, Constantes.KEY_PRENOM },
				Constantes.KEY_NOM + " = ? AND " + Constantes.KEY_PRENOM
						+ " =?",
				new String[] { String.valueOf(nom), String.valueOf(prenom) },
				null, null, null, null);
		Athlete athlete = null;
		if (cursor != null)
		{
			cursor.moveToFirst();
			try
			{
				athlete = new Athlete(cursor.getString(1), cursor.getString(2));
			}
			catch (final InvalidNameException e)
			{
				e.printStackTrace();
			}
			catch (final InvalidFirstNameException e)
			{
				e.printStackTrace();
			}
			return athlete;
		}
		return athlete;
	}

	/**
	 * Methode qui permet de retourner l'ensemble des athletes de la base.
	 * 
	 * @return La liste des athletes.
	 */
	@SuppressWarnings("unchecked")
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
					final String selectQueryPerf = "SELECT  * FROM "
							+ Constantes.TABLE_PERFORMANCE + " WHERE "
							+ Constantes.KEY_NOM + " = '" + athlete.getName()
							+ "'" + " AND " + Constantes.KEY_PRENOM + " = '"
							+ athlete.getFirstName() + "'";
					final Cursor cursorPerf = db
							.rawQuery(selectQueryPerf, null);
					if (cursorPerf.moveToFirst())
					{
						final List<Performance> mesPerformances = new ArrayList<Performance>();
						do
						{
							Performance perf;
							perf = new Performance(athlete,
									Long.parseLong(cursorPerf.getString(2)),
									Integer.parseInt(cursorPerf.getString(3)),
									cursorPerf.getString(4));
							
							mesPerformances.add(perf);
							athlete.setListPerf(mesPerformances);
						}
						while (cursorPerf.moveToNext());
					}
					mesAthletes.add(athlete);
				}
				catch (final InvalidNameException e)
				{
				}
				catch (final InvalidFirstNameException e)
				{
				}
			}
			while (cursor.moveToNext());
		}
		Collections.sort(mesAthletes);
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
	public int updateAthlete(final Athlete athlete)
	{
		final SQLiteDatabase db = getWritableDatabase();
		final ContentValues values = new ContentValues();
		values.put(Constantes.KEY_NOM, athlete.getName());
		values.put(Constantes.KEY_PRENOM, athlete.getFirstName());
		// Mise à jour
		return db.update(
				Constantes.TABLE_ATHLETE,
				values,
				Constantes.KEY_NOM + " = ? AND " + Constantes.KEY_PRENOM
						+ " =?",
				new String[] { String.valueOf(athlete.getName()),
						String.valueOf(athlete.getFirstName()) });
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
		db.delete(
				Constantes.TABLE_ATHLETE,
				Constantes.KEY_NOM + " = ? AND " + Constantes.KEY_PRENOM
						+ " =?",
				new String[] { String.valueOf(athlete.getName()),
						String.valueOf(athlete.getFirstName()) });
		db.close();
	}

	/**
	 * Retourne le nombre d'athlete dans la base de données.
	 * 
	 * @return Le nombre d'athlete dans la base de données.
	 */
	public int getAthleteCount()
	{
		final String countQuery = "SELECT  * FROM " + Constantes.TABLE_ATHLETE;
		final SQLiteDatabase db = getReadableDatabase();
		final Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		return cursor.getCount();
	}

	/* _________________________________________________________ */
	/**
	 * Methode qui permet de supprimer une performance de la base de données.
	 * 
	 * @param athlete
	 *            L'athlete.
	 * @param perf
	 *            La performance à supprimer.
	 */
	public void removePerformance(final Athlete athlete, final Performance perf)
	{
		final SQLiteDatabase db = getWritableDatabase();
		db.delete(
				Constantes.TABLE_PERFORMANCE,
				Constantes.KEY_NOM + " = ? AND " + Constantes.KEY_PRENOM
						+ " =? AND " + Constantes.KEY_TEMPS
						+ " =? AND " + Constantes.KEY_DISTANCE
						+ " =? AND "+ Constantes.KEY_DATE
						+ " =? ",
				new String[] { String.valueOf(athlete.getName()),
						String.valueOf(athlete.getFirstName()),
						String.valueOf(perf.getChrono()),
						String.valueOf(perf.getDistance()),
						String.valueOf(perf.getDate())});
		db.close();
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier DataBaseHandler.java.
 * /*_________________________________________________________
 */
