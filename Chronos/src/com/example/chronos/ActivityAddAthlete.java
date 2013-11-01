/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : ActivityAddAthlete.java
 * 
 * Créé le 31 oct. 2013 à 14:45:37
 * 
 * Auteur : Jerome POINAS
 */
package com.example.chronos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/* _________________________________________________________ */
/**
 * @author Jerome POINAS
 * 
 */
public class ActivityAddAthlete extends Activity
{
	
	EditText champNom;
	EditText champPrenom;
	
	
	/* _________________________________________________________ */
	/**
	 * On create.
	 * 
	 * @param savedInstanceState
	 *            the saved instance state
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_athlete);
		
		final Button valider = (Button) findViewById(R.id.buttonValiderForm);
		champNom = (EditText) findViewById(R.id.editTextNom);
		champPrenom = (EditText) findViewById(R.id.editTextPrenom);
		
		valider.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(final View v)
			{
				
				if(!champNom.getText().toString().equals("") && !champPrenom.getText().toString().equals(""))
				{
					
					//On créé un objet Bundle, c'est ce qui va nous permetre d'envoyer des données à l'autre Activity
					Bundle objetbunble = new Bundle();
		 
					//Cela fonctionne plus ou moins comme une HashMap, on entre une clef et sa valeur en face
					objetbunble.putString("Nom",champNom.getText().toString());
					objetbunble.putString("Prenom",champPrenom.getText().toString());
					
					
					final Intent intent = new Intent(ActivityAddAthlete.this,
							ActivityListeAthlete.class);
					intent.putExtras(objetbunble);
					
					startActivity(intent);
				}
				else
				{
					Toast.makeText(ActivityAddAthlete.this, "Veuillez renseigner le nom et le prenom de l'athlete.", Toast.LENGTH_SHORT).show();
				}	
			}
		});
		
	}

	/* _________________________________________________________ */
	/**
	 * On create options menu.
	 * 
	 * @param menu
	 *            the menu
	 * @return true, if successful
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_chronos, menu);
		return true;
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier ActivityAddAthlete.java.
 * /*_________________________________________________________
 */
