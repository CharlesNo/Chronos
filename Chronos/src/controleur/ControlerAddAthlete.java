/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : Controleur.java
 * 
 * Créé le 1 nov. 2013 à 18:54:23
 * 
 * Auteur : Charles NEAU
 */
package controleur;

import vue.ActivityListeAthlete;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import com.example.chronos.R;

/* _________________________________________________________ */
/**
 * @author Charles NEAU
 * 
 */
public class ControlerAddAthlete implements OnClickListener
{
	/** The activity. */
	private final Activity	activity;
	/** The champ nom. */
	private final EditText	champNom;
	/** The champ prenom. */
	private final EditText	champPrenom;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new controleur.
	 * Il recoit l'activité qu'il controle.
	 * 
	 * @param activity
	 *            the activity
	 */
	public ControlerAddAthlete(final Activity activity)
	{
		this.activity = activity;
		champNom = (EditText) activity.findViewById(R.id.editTextNom);
		champPrenom = (EditText) activity.findViewById(R.id.editTextPrenom);
	}

	/* _________________________________________________________ */
	/**
	 * On click.
	 * 
	 * @param view
	 *            the view
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(@SuppressWarnings("unused") final View view)
	{
		if (!champNom.getText().toString().equals("")
				&& !champPrenom.getText().toString().equals(""))
		{
			// On créé un objet Bundle, c'est ce qui va nous permetre
			// d'envoyer des données à l'autre Activity
			final Bundle objetbunble = new Bundle();
			// Cela fonctionne plus ou moins comme une HashMap, on entre
			// une clef et sa valeur en face
			objetbunble.putString("Nom", champNom.getText().toString());
			objetbunble.putString("Prenom", champPrenom.getText().toString());
			final Intent intent = new Intent(activity,
					ActivityListeAthlete.class);
			intent.putExtras(objetbunble);
			activity.startActivity(intent);
		}
		else
		{
			Toast.makeText(activity,
					"Veuillez renseigner le nom et le prenom de l'athlete.",
					Toast.LENGTH_SHORT).show();
		}
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier Controleur.java.
 * /*_________________________________________________________
 */
