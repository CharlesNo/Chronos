/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : ModeleList.java
 * 
 * Créé le 1 nov. 2013 à 23:09:29
 * 
 * Auteur : Charles NEAU
 */
package modele;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/* _________________________________________________________ */
/**
 * The Class ModeleList.
 * 
 * @author Charles NEAU
 */
public class ModeleList extends BaseAdapter
{
	/** The modele. */
	private final Modele	modele;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new modele list.
	 * 
	 * @param modele
	 *            the modele
	 */
	public ModeleList(final Modele modele)
	{
		super();
		this.modele = modele;
	}

	/* _________________________________________________________ */
	/**
	 * Gets the count.
	 * 
	 * @return the count
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return modele.size();
	}

	/* _________________________________________________________ */
	/**
	 * Gets the item.
	 * 
	 * @param arg0
	 *            the arg0
	 * @return the item
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(final int arg0)
	{
		return modele.get(arg0);
	}

	/* _________________________________________________________ */
	/**
	 * Gets the item id.
	 * 
	 * @param arg0
	 *            the arg0
	 * @return the item id
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(final int arg0)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/* _________________________________________________________ */
	/**
	 * Gets the view.
	 * 
	 * @param arg0
	 *            the arg0
	 * @param arg1
	 *            the arg1
	 * @param arg2
	 *            the arg2
	 * @return the view
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 *      android.view.ViewGroup)
	 */
	@Override
	public View getView(final int arg0, final View arg1, final ViewGroup arg2)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier ModeleList.java.
 * /*_________________________________________________________
 */
