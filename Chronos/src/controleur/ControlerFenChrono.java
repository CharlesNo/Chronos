package controleur;

import vue.ActivityListeAthlete;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
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
	private final Activity				activity;
	/** Le chronometre */
	private final modele.Chronometer	chronos;

	/**
	 * Instantiates a new controler fen chrono.
	 * 
	 * @param activityChronos
	 *            the activity chronos
	 */
	public ControlerFenChrono(final Activity activityChronos)
	{
		activity = activityChronos;
		chronos = (modele.Chronometer) activity.findViewById(R.id.chronometer);
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
		if (source == activity.findViewById(R.id.btStart))
		{
			chronos.start();
		}
		if (source == activity.findViewById(R.id.btStop))
		{
			chronos.stop();
			final Intent intent = new Intent(activity,
					ActivityListeAthlete.class);
			final Bundle objetbunble = new Bundle();
			objetbunble.putString(
					"temps",
					String.valueOf(SystemClock.elapsedRealtime()
							- chronos.getBase()));
			intent.putExtras(objetbunble);
			activity.startActivity(intent);
		}
	}
}
