/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : Athlete.java
 * 
 * Créé le 31 oct. 2013 à 10:54:05
 * 
 * Auteur : Jerome POINAS
 */
package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import business.exceptions.InvalidFirstNameException;
import business.exceptions.InvalidNameException;

/* _________________________________________________________ */
/**
 * La classe Athlete.
 * Cette classe représente un Athlete.
 * Un Athlete est une personne, il a un name et un prénom.
 * 
 * Il a une liste de performances/performances.
 * 
 * @author Jerome POINAS
 *         Charles NEAU
 */
public class Athlete implements Comparable<Athlete>, Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/**
	 * Le name de famille de l'athlete.
	 */
	private String				name;
	/**
	 * Le firstName de l'athlete.
	 */
	private String				firstName;
	/**
	 * Les performances de cet athlete.
	 */
	private List<Performance>	performances;

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ name.
	 * 
	 * @return la valeur du champ name.
	 */
	public String getName()
	{
		return name;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ firstName.
	 * 
	 * @return la valeur du champ firstName.
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ performances.
	 * 
	 * @return la valeur du champ performances.
	 */
	public List<Performance> getPerformances()
	{
		return performances;
	}

	/* _________________________________________________________ */
	/**
	 * Constructeur d'un athlete.
	 * 
	 * @param nom
	 *            the nom
	 * @param prenom
	 *            the prenom
	 * @throws InvalidNameException
	 *             Exception le name est null.
	 * @throws InvalidFirstNameException
	 *             Exception le firstName est null.
	 */
	public Athlete(final String nom, final String prenom)
			throws InvalidNameException, InvalidFirstNameException
	{
		performances = new ArrayList<Performance>();
		setName(nom);
		setFirstName(prenom);
	}

	/* _________________________________________________________ */
	/**
	 * Constructeur d'un athlete.
	 * 
	 * @param nom
	 *            the nom
	 * @param prenom
	 *            the prenom
	 * @param list
	 * @throws InvalidNameException
	 *             Exception le name est null.
	 * @throws InvalidFirstNameException
	 *             Exception le firstName est null.
	 */
	@SuppressWarnings("javadoc")
	public Athlete(final String nom, final String prenom,
			final List<Performance> list) throws InvalidNameException,
			InvalidFirstNameException
	{
		setName(nom);
		setFirstName(prenom);
		setListPerf(list);
	}

	/**
	 * Methode qui permet de modifier le name de l'athlete.
	 * 
	 * @param nom
	 *            the new le name de famille de l'athlete
	 * @throws InvalidNameException
	 *             to
	 */
	public void setName(final String nom) throws InvalidNameException
	{
		if (nom == null)
		{
			throw new InvalidNameException("Le name est invalide");
		}
		name = nom;
	}

	/**
	 * Methode qui permet de modifier le firstName de l'athlete.
	 * 
	 * @param prenom
	 *            the new le firstName de l'athlete
	 * @throws InvalidFirstNameException
	 *             to
	 */
	public void setFirstName(final String prenom)
			throws InvalidFirstNameException
	{
		if (prenom == null)
		{
			throw new InvalidFirstNameException("Le firstName est invalide");
		}
		firstName = prenom;
	}

	/**
	 * Methode qui permet de modifier la liste des performances de l'athlete.
	 * 
	 * @param mesPerformances
	 *            La liste des performances.
	 */
	public void setListPerf(final List<Performance> mesPerformances)
	{
		performances = mesPerformances;
	}

	/* _________________________________________________________ */
	/**
	 * To string.
	 * 
	 * @return the string
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		builder.append(firstName).append(" ").append(name);
		builder.append("[");
		for (int i = 0; i < performances.size(); i++)
		{
			final Performance perf = performances.get(i);
			if (i == (performances.size() - 1))
			{
				builder.append(perf);
			}
			else
			{
				builder.append(perf).append(";");
			}
		}
		builder.append("]");
		builder.append("\n");
		return builder.toString();
	}

	/* _________________________________________________________ */
	/**
	 * Methode permettant de comparer deux athletes.
	 * 
	 * @param athlete
	 *            L'athlete a comparer.
	 * @return int représentant l'égalité ou pas des deux athletes.
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(final Athlete athlete)
	{
		int resultat = 0;
		if (getName().compareTo(athlete.getName()) > 0)
		{
			resultat = 1;
		}
		else if (getName().compareTo(athlete.getName()) < 0)
		{
			resultat = -1;
		}
		else if (getName().equals(athlete.getName()))
		{
			if (getFirstName().compareTo(athlete.getFirstName()) > 0)
			{
				resultat = 1;
			}
			else if (getFirstName().compareTo(athlete.getFirstName()) < 0)
			{
				resultat = -1;
			}
			else
			{
				resultat = 0;
			}
		}
		return resultat;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier Athlete.java.
 * /*_________________________________________________________
 */
