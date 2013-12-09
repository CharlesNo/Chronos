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
import java.util.Locale;

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

	/* _________________________________________________________ */
	/**
	 * Méthode qui permet de mettre la premiere lettre de la chaine de
	 * charactere
	 * passée en paramètre en majuscule.
	 * 
	 * @param nom
	 *            La chaine de character a mettre en forme.
	 * @return La chaine de character modifiée.
	 */
	public static String firstLetterUpperCase(final String nom)
	{
		final char first = nom.charAt(0);
		String firstLetter = Character.toString(first);
		firstLetter = firstLetter.toUpperCase(Locale.getDefault());
		// Substring pour enlever la premiere lettre
		final String resteChaine = nom.substring(1);
		return firstLetter.concat(resteChaine);
	}

	/* _________________________________________________________ */
	/**
	 * Méthode qui permet de mettre la chaine de charactere
	 * passée en paramètre en majuscule.
	 * 
	 * @param nom
	 *            La chaine de character a mettre en forme.
	 * @return La chaine de character modifiée.
	 */
	public static String ToUpperCase(final String nom)
	{
		final String upperChain = nom.toUpperCase(Locale.getDefault());
		return upperChain;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier Formatter.java.
 * /*_________________________________________________________
 */
