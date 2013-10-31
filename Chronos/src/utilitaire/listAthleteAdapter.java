/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : listAthleteAdapter.java
 * 
 * Créé le 31 oct. 2013 à 14:34:55
 * 
 * Auteur : Jerome POINAS
 */
package utilitaire;

import java.util.List;
import metier.Athlete;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.chronos.R;

/* _________________________________________________________ */
/**
 * @author Jerome POINAS
 * 
 */
public class listAthleteAdapter extends BaseAdapter
{
	/**
	 * La liste des Athletes.
	 */
	List<Athlete>	mesAthletes;
	/**
	 * Permet de charger le fichier xml.
	 */
	LayoutInflater	inflater;

	/**
	 * Constructeur complet.
	 * 
	 * @param context
	 *            Le context.
	 * @param listAthlete
	 *            La liste des athletes.
	 */
	public listAthleteAdapter(final Context context,
			final List<Athlete> listAthlete)
	{
		inflater = LayoutInflater.from(context);
		mesAthletes = listAthlete;
	}

	/* _________________________________________________________ */
	/**
	 * @return La taille de la liste des athletes.
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return mesAthletes.size();
	}

	/* _________________________________________________________ */
	/**
	 * @param position
	 *            La position de l'item a retourner.
	 * @return L'item à retourner.
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(final int position)
	{
		return mesAthletes.get(position);
	}

	/* _________________________________________________________ */
	/**
	 * @param position
	 *            La position de l'item.
	 * @return La position de l'item.
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(final int position)
	{
		return position;
	}

	/**
	 * Mémorise les éléments de la liste en mémoire.
	 */
	private class ViewHolder
	{
		TextView	tvNom;
		TextView	tvPrenom;
	}

	@Override
	public View getView(final int position, View convertView,
			final ViewGroup parent)
	{
		ViewHolder holder;
		if (convertView == null)
		{
			holder = new ViewHolder();
			convertView = inflater.inflate(
					R.layout.activity_main_activity_chronos, null);
			holder.tvNom = (TextView) convertView.findViewById(R.id.tvNom);
			holder.tvPrenom = (TextView) convertView
					.findViewById(R.id.tvPrenom);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvNom.setText(mesAthletes.get(position).getNom());
		holder.tvPrenom.setText(mesAthletes.get(position).getPrenom());
		return convertView;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier listAthleteAdapter.java.
 * /*_________________________________________________________
 */
