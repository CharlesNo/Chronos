package com.example.chronos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

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
}
