package controleur;

import com.chronos.R;

import vue.ActivityListeAthlete;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Chronometer;

/* _________________________________________________________ */
/**
 * The Class ControlerListeAthlete.
 * 
 * @author Jerome Poinas
 */
public class ControlerFenChrono implements OnClickListener
{
	/** The activity. */
	private final Activity				activity;

	public ControlerFenChrono(final Activity activityChronos) {
		this.activity = activityChronos;
	}

	@Override
	public void onClick(View source) {
		Chronometer chrono = (Chronometer) activity.findViewById(R.id.chronometer1);
		if(source == activity.findViewById(R.id.btStart))
		{
			chrono.start();
		}
		if(source == activity.findViewById(R.id.btStop))
		{
			chrono.stop();
			Intent intent = new Intent(activity, ActivityListeAthlete.class);
			Bundle objetbunble = new Bundle(); 
			objetbunble.putString("temps", String.valueOf(SystemClock.elapsedRealtime()-chrono.getBase()));	
			intent.putExtras(objetbunble);
			activity.startActivity(intent);
		}
	}
}
