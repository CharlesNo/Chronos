/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : Controleur.java
 * 
 * Créé le 1 nov. 2013 à 18:54:23
 * 
 * Auteur : Charles NEAU
 */
package com.example.chronos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

/* _________________________________________________________ */
/**
 * @author Charles NEAU
 * 
 */
public class Controleur implements OnClickListener
{
	/** The activity. */
	private final Activity	activity;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new controleur.
	 * Il recoit l'activité qu'il controle.
	 * 
	 * @param activity
	 *            the activity
	 */
	public Controleur(final Activity activity)
	{
		this.activity = activity;
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
		final EditText champNom = (EditText) activity
				.findViewById(R.id.editTextNom);
		final EditText champPrenom = (EditText) activity
				.findViewById(R.id.editTextPrenom);
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
