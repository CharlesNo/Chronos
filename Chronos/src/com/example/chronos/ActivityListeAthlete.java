package com.example.chronos;

import java.util.ArrayList;
import java.util.List;

import metier.Athlete;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import exception.InvalideNomException;
import exception.InvalidePrenomException;

/**
 * La classe Main activity
 * 
 * @author Jerome POINAS
 *         Charles NEAU
 * 
 */
public class ActivityListeAthlete extends Activity
{
	/** The mes athletes. */
	List<Athlete>	mesAthletes = new ArrayList<Athlete>();
	/** The lv liste. */
	ListView		lvListe;

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
		setContentView(R.layout.activity_main_activity_chronos);
		
		
		lvListe = (ListView) findViewById(R.id.listAthlete);
		final Button addAthlete = (Button) findViewById(R.id.bouttonAddAthlete);
		/***********************************************************/
	
		final ArrayAdapter<Athlete> adapter = new ArrayAdapter<Athlete>(getBaseContext(), android.R.layout.simple_list_item_1,mesAthletes);
		
		lvListe.setOnItemClickListener(new OnItemClickListener() {
				@Override
	         	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
					//On recupere l'athlete selectionné
	        		Athlete athleteSelected = (Athlete) lvListe.getItemAtPosition(position);
	        		//Boite de dialogue
	        		AlertDialog.Builder adb = new AlertDialog.Builder(ActivityListeAthlete.this);
	        		adb.setTitle("Sélection Item");
	        		adb.setMessage("Votre choix : "+athleteSelected.getPrenom()+" "+athleteSelected.getNom());
	        		adb.setPositiveButton("Ok", null);
	        		adb.show();
	        	}
		});
		lvListe.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int position, long id) {
			        AlertDialog.Builder adb=new AlertDialog.Builder(ActivityListeAthlete.this);
			        adb.setTitle("Suppression d'un athlete");
			        adb.setMessage("Etes vous sure de vouloir le supprimer ? ");
			        final int positionToRemove = position;
			        adb.setNegativeButton("Cancel", null);
			        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
			            public void onClick(DialogInterface dialog, int which) {
			            	adapter.remove((Athlete) lvListe.getItemAtPosition(positionToRemove));
			            }});
			        adb.show();
			        return true;
			}});
		

		//On recupere les données provenant de ActivityAddAthlete si il y en a
		 Bundle objetbunble  = this.getIntent().getExtras();
		 
	        //On récupère les données du Bundle
	    if (objetbunble != null && objetbunble.containsKey("Nom") && objetbunble.containsKey("Prenom")) {
	        	String nomAthlete = this.getIntent().getStringExtra("Nom");
	            String prenomAthlete = this.getIntent().getStringExtra("Prenom");
	            try {
	            	Athlete athlete = new Athlete(nomAthlete,prenomAthlete);
	            	mesAthletes.add(athlete);
					
				} catch (InvalideNomException e) {
					Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT)
					.show();
				} catch (InvalidePrenomException e) {
					Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT)
					.show();
				}
	            
	    }
	    lvListe.setAdapter(adapter);

		
		
		addAthlete.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(final View v)
			{
				final Intent intent = new Intent(ActivityListeAthlete.this,
						ActivityAddAthlete.class);
				startActivity(intent);
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
