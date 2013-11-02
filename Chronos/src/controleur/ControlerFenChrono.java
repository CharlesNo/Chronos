package controleur;

import java.text.DecimalFormat;
import vue.ActivityListeAthlete;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Chronometer;
import com.chronos.R;

/* _________________________________________________________ */
/**
 * The Class ControlerListeAthlete.
 * 
 * @author Jerome Poinas
 */
public class ControlerFenChrono implements OnClickListener
{
	/** The activity. */
	private final Activity	activity;

	/**
	 * Instantiates a new controler fen chrono.
	 * 
	 * @param activityChronos
	 *            the activity chronos
	 */
	public ControlerFenChrono(final Activity activityChronos)
	{
		activity = activityChronos;
	}

	/**
	 * Methode de mise en forme du rendu chronometre.
	 * 
	 * @param timeElapsed
	 *            the time elapsed
	 * @return the string
	 */
	public String miseEnForme(final long timeElapsed)
	{
		final DecimalFormat df = new DecimalFormat("00");
		final int hours = (int) (timeElapsed / (3600 * 1000));
		int remaining = (int) (timeElapsed % (3600 * 1000));
		final int minutes = remaining / (60 * 1000);
		remaining = remaining % (60 * 1000);
		final int seconds = remaining / 1000;
		remaining = remaining % (1000);
		final int milliseconds = (((int) timeElapsed % 1000));
		String text = "";
		if (hours > 0)
		{
			text += df.format(hours) + ":";
		}
		text += df.format(minutes) + ":";
		text += df.format(seconds) + ":";
		text += Integer.toString(milliseconds);
		return text;
	}

	/* _________________________________________________________ */
	/**
	 * On click.
	 * 
	 * @param source
	 *            the source
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(final View source)
	{
		final Chronometer chrono = (Chronometer) activity
				.findViewById(R.id.chronometer);
		if (source == activity.findViewById(R.id.btStart))
		{
			chrono.start();
		}
		if (source == activity.findViewById(R.id.btStop))
		{
			chrono.stop();
			final Intent intent = new Intent(activity,
					ActivityListeAthlete.class);
			final Bundle objetbunble = new Bundle();
			objetbunble.putString(
					"temps",
					miseEnForme(SystemClock.elapsedRealtime()
							- chrono.getBase()));
			intent.putExtras(objetbunble);
			activity.startActivity(intent);
		}
	}
}
