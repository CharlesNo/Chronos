/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : DatabaseExport.java
 * 
 * Créé le 10 déc. 2013 à 07:57:19
 * 
 * Auteur : Jerome POINAS
 */
package persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import utility.Constantes;
import android.app.Activity;
import android.os.Environment;
import android.widget.Toast;
import business.Athlete;

/* _________________________________________________________ */
/**
 * @author Jerome POINAS
 *         La classe DatabaseExport qui permet d'exporter la base de données de
 *         l'application directement sur la SDCard du téléphone afin de pouvoir
 *         récupérer le fichier associé et de le synchroniser avec l'application
 *         PC.
 */
public class DatabaseExport
{
	/* _________________________________________________________ */
	/**
	 * @param activity
	 *            L'activité concernée.
	 */
	public static void exportDataBase(final Activity activity)
	{
		final List<Athlete> maListeAthlete = DatabaseHandler.getInstance(
				activity.getApplicationContext()).getAllAthletes();
		final File myFile = new File(Environment.getExternalStorageDirectory()
				+ File.separator + "chronos", "database.txt");
		final File myDir = new File(Environment.getExternalStorageDirectory()
				+ File.separator + "chronos");
		Boolean success = true;
		if (!myDir.exists())
		{
			success = myDir.mkdir();
		}
		if (success)
		{
			FileOutputStream output;
			try
			{
				output = new FileOutputStream(myFile, false);
				try
				{
					final Writer out = new BufferedWriter(
							new OutputStreamWriter(output, "UTF-8"));
					for (final Athlete athlete : maListeAthlete)
					{
						out.write(athlete.toString());
						out.flush();
					}
					out.close();
				}
				catch (final IOException e)
				{
					e.printStackTrace();
				}
				try
				{
					output.close();
				}
				catch (final IOException e)
				{
					e.printStackTrace();
				}
			}
			catch (final FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			Toast.makeText(activity, Constantes.DIRERROR, Toast.LENGTH_SHORT)
					.show();
		}
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier DatabaseExport.java.
 * /*_________________________________________________________
 */
