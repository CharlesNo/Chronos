/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : ChronometerHandler.java
 * 
 * Créé le 2 nov. 2013 à 22:53:53
 * 
 * Auteur : Charles NEAU
 */
package business;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/* _________________________________________________________ */
/**
 * @author Charles NEAU
 * 
 */
public class ChronometerHandler extends Handler
{
	/** The chronometer. */
	private final Chronometer	chronometer;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new chronometer handler.
	 * 
	 * @param chronometer
	 *            the chronometer
	 */
	public ChronometerHandler(final Chronometer chronometer)
	{
		this.chronometer = chronometer;
	}

	/* _________________________________________________________ */
	/**
	 * Handle message.
	 * 
	 * @param msg
	 *            the msg
	 * @see android.os.Handler#handleMessage(android.os.Message)
	 */
	@Override
	public void handleMessage(@SuppressWarnings("unused") final Message msg)
	{
		if (chronometer.getmRunning())
		{
			chronometer.updateText(SystemClock.elapsedRealtime());
			chronometer.dispatchChronometerTick();
			sendMessageDelayed(Message.obtain(this, Chronometer.TICK_WHAT), 1);
		}
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier ChronometerHandler.java.
 * /*_________________________________________________________
 */
