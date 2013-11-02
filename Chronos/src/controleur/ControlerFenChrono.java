package controleur;

import java.text.DecimalFormat;

import com.chronos.R;

import vue.ActivityListeAthlete;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;

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
		Chronometer chrono = (Chronometer) activity.findViewById(R.id.chronometer);	
		
		if(source == activity.findViewById(R.id.btStart))
		{
			chrono.start();
		}
		if(source == activity.findViewById(R.id.btStop))
		{
			chrono.stop();
			Intent intent = new Intent(activity, ActivityListeAthlete.class);
			Bundle objetbunble = new Bundle(); 
			objetbunble.putString("temps", miseEnForme(SystemClock.elapsedRealtime()-chrono.getBase()));	
			intent.putExtras(objetbunble);
			activity.startActivity(intent);
		}
	}
	
	/**
	 * Methode de mise en forme du rendu chronometre
	 * @param timeElapsed
	 * @return
	 */
	public String miseEnForme(long timeElapsed)
	{
		 DecimalFormat df = new DecimalFormat("00");
		
		int hours = (int)(timeElapsed / (3600 * 1000));
        int remaining = (int)(timeElapsed % (3600 * 1000));
        
        int minutes = (int)(remaining / (60 * 1000));
        remaining = (int)(remaining % (60 * 1000));
        
        int seconds = (int)(remaining / 1000);
        remaining = (int)(remaining % (1000));
        
        int milliseconds = (int)(((int)timeElapsed % 1000));
        
        String text = "";
        
        if (hours > 0) {
                text += df.format(hours) + ":";
        }
        
               text += df.format(minutes) + ":";
               text += df.format(seconds) + ":";
               text += Integer.toString(milliseconds);
               
        return text;
	}
}
