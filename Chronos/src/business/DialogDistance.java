/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : DialogDistance.java
 * 
 * Créé le 9 déc. 2013 à 15:32:00
 * 
 * Auteur : Jerome POINAS
 */
package business;

/* _________________________________________________________ */
/**
 * The Class DialogDistance.
 * 
 * @author Jerome POINAS
 */
public class DialogDistance
{
	/** The tab valeurs. */
	private final String[]	values	= new String[16];
	/** The index. */
	private int				index	= 5;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new dialog distance.
	 */
	public DialogDistance()
	{
		int val = 50;
		for (int i = 0; i < 16; i++)
		{
			values[i] = Integer.toString(val);
			val = val + 10;
		}
	}

	/* _________________________________________________________ */
	/**
	 * Gets the.
	 * 
	 * @param index
	 *            the index
	 * @return the string
	 */
	public String get(final int index)
	{
		return values[index];
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ values.
	 * 
	 * @return la valeur du champ values.
	 */
	public String[] getValues()
	{
		return values;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ index.
	 * 
	 * @return la valeur du champ index.
	 */
	public int getIndex()
	{
		return index;
	}

	/* _________________________________________________________ */
	/**
	 * Modifie la valeur du cmap index.
	 * 
	 * @param index
	 *            la valeur à placer dans le champ index.
	 */
	public void setIndex(final int index)
	{
		this.index = index;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier DialogDistance.java.
 * /*_________________________________________________________
 */
