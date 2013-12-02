/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : Formatter.java
 * 
 * Créé le 2 déc. 2013 à 12:57:58
 * 
 * Auteur : Jerome POINAS
 */
package utility;

import java.text.DecimalFormat;

/* _________________________________________________________ */
/**
 * @author Jerome POINAS
 * 
 */
public class Formatter
{
	/**
	 * Methode de mise en forme du rendu chronometre.
	 * 
	 * @param timeElapsed
	 *            the time elapsed
	 * @return the string
	 */
	public static String miseEnForme(final long timeElapsed)
	{
		final DecimalFormat df = new DecimalFormat("00");
		int remaining = (int) (timeElapsed % (3600 * 1000));
		final int minutes = remaining / (60 * 1000);
		remaining = remaining % (60 * 1000);
		final int seconds = remaining / 1000;
		remaining = remaining % (1000);
		final int milliseconds = ((((int) timeElapsed % 1000) / 10));
		final StringBuilder builder = new StringBuilder();
		builder.append(df.format(minutes)).append("'");
		builder.append(df.format(seconds)).append("\"");
		builder.append(df.format(milliseconds));
		return builder.toString();
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier Formatter.java.
 * /*_________________________________________________________
 */
