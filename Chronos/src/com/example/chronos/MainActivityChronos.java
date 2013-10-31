package com.example.chronos;

import java.util.ArrayList;
import java.util.List;
import metier.Athlete;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import exception.InvalideNomException;
import exception.InvalidePrenomException;

/**
 * La classe Main activity
 * _________________________________________________________
 */
/**
 * @author Jerome POINAS
 * 
 */
public class MainActivityChronos extends Activity
{
	List<Athlete>	mesAthletes;
	ListView		lvListe;

	/* _________________________________________________________ */
	/**
	 * @param savedInstanceState
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity_chronos);
		mesAthletes = new ArrayList<Athlete>();
		try
		{
			init();
			lvListe = (ListView) findViewById(R.id.listAthlete);
			lvListe.setAdapter(new ArrayAdapter<Athlete>(this,
					android.R.layout.simple_list_item_1, mesAthletes));
		}
		catch (final InvalideNomException e)
		{
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT)
					.show();
		}
		catch (final InvalidePrenomException e)
		{
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT)
					.show();
		}
	}

	/* _________________________________________________________ */
	/**
	 * @param menu
	 * @return
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_chronos, menu);
		return true;
	}

	/**
	 * Methode qui initialise la liste des Athletes.
	 * 
	 * @throws InvalideNomException
	 *             Nom null.
	 * @throws InvalidePrenomException
	 *             Prenom null.
	 */
	private void init() throws InvalideNomException, InvalidePrenomException
	{
		mesAthletes.clear();
		mesAthletes.add(new Athlete("Imbert", "Jean-louis"));
		mesAthletes.add(new Athlete("Neau", "Charles"));
		mesAthletes.add(new Athlete("Poinas", "Jerome"));
	}
}
