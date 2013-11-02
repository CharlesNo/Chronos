package modele;

import java.text.DecimalFormat;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Chronometre qui prend en compte les millisecondes
 * 
 * @author Jérôme
 * 
 */
public class Chronometer extends TextView
{
	/** The Constant TAG. */
	@SuppressWarnings("unused")
	private static final String			TAG			= "Chronometer";
	/** The m base. */
	private long						mBase;
	/** The m visible. */
	private boolean						mVisible;
	/** The m started. */
	private boolean						mStarted;
	/** The m running. */
	private boolean						mRunning;
	/** The m on chronometer tick listener. */
	private OnChronometerTickListener	mOnChronometerTickListener;
	/** The Constant TICK_WHAT. */
	static final int					TICK_WHAT	= 2;
	/** The time elapsed. */
	private long						timeElapsed;

	/**
	 * Instantiates a new chronometer.
	 * 
	 * @param context
	 *            the context
	 */
	public Chronometer(final Context context)
	{
		this(context, null, 0);
	}

	/**
	 * Instantiates a new chronometer.
	 * 
	 * @param context
	 *            the context
	 * @param attrs
	 *            the attrs
	 */
	public Chronometer(final Context context, final AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	/**
	 * Instantiates a new chronometer.
	 * 
	 * @param context
	 *            the context
	 * @param attrs
	 *            the attrs
	 * @param defStyle
	 *            the def style
	 */
	public Chronometer(final Context context, final AttributeSet attrs,
			final int defStyle)
	{
		super(context, attrs, defStyle);
		init();
	}

	/**
	 * Inits the.
	 */
	private void init()
	{
		mBase = SystemClock.elapsedRealtime();
		updateText(mBase);
	}

	/**
	 * Sets the base.
	 * 
	 * @param base
	 *            the new base
	 */
	public void setBase(final long base)
	{
		mBase = base;
		dispatchChronometerTick();
		updateText(SystemClock.elapsedRealtime());
	}

	/**
	 * Gets the base.
	 * 
	 * @return the base
	 */
	public long getBase()
	{
		return mBase;
	}

	/**
	 * Sets the on chronometer tick listener.
	 * 
	 * @param listener
	 *            the new on chronometer tick listener
	 */
	public void setOnChronometerTickListener(
			final OnChronometerTickListener listener)
	{
		mOnChronometerTickListener = listener;
	}

	/**
	 * Gets the on chronometer tick listener.
	 * 
	 * @return the on chronometer tick listener
	 */
	public OnChronometerTickListener getOnChronometerTickListener()
	{
		return mOnChronometerTickListener;
	}

	/**
	 * Start.
	 */
	public void start()
	{
		mBase = SystemClock.elapsedRealtime();
		mStarted = true;
		updateRunning();
	}

	/**
	 * Stop.
	 */
	public void stop()
	{
		mStarted = false;
		updateRunning();
	}

	/**
	 * Sets the started.
	 * 
	 * @param started
	 *            the new started
	 */
	public void setStarted(final boolean started)
	{
		mStarted = started;
		updateRunning();
	}

	/* _________________________________________________________ */
	/**
	 * @see android.widget.TextView#onDetachedFromWindow()
	 */
	@Override
	protected void onDetachedFromWindow()
	{
		super.onDetachedFromWindow();
		mVisible = false;
		updateRunning();
	}

	/* _________________________________________________________ */
	/**
	 * On window visibility changed.
	 * 
	 * @param visibility
	 *            the visibility
	 * @see android.view.View#onWindowVisibilityChanged(int)
	 */
	@Override
	protected void onWindowVisibilityChanged(final int visibility)
	{
		super.onWindowVisibilityChanged(visibility);
		mVisible = visibility == VISIBLE;
		updateRunning();
	}

	/**
	 * Update text.
	 * 
	 * @param now
	 *            the now
	 */
	synchronized void updateText(final long now)
	{
		timeElapsed = now - mBase;
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
		setText(text);
	}

	/**
	 * Update running.
	 */
	private void updateRunning()
	{
		final boolean running = mVisible && mStarted;
		if (running != getmRunning())
		{
			if (running)
			{
				updateText(SystemClock.elapsedRealtime());
				dispatchChronometerTick();
				mHandler.sendMessageDelayed(
						Message.obtain(mHandler, TICK_WHAT), 1);
			}
			else
			{
				mHandler.removeMessages(TICK_WHAT);
			}
			setmRunning(running);
		}
	}

	/** The m handler. */
	private final Handler	mHandler	= new ChronometerHandler(this);

	/**
	 * Dispatch chronometer tick.
	 */
	void dispatchChronometerTick()
	{
		if (mOnChronometerTickListener != null)
		{
			mOnChronometerTickListener.onChronometerTick(this);
		}
	}

	/**
	 * Gets the time elapsed.
	 * 
	 * @return the time elapsed
	 */
	public long getTimeElapsed()
	{
		return timeElapsed;
	}

	/* _________________________________________________________ */
	/**
	 * Retourne la valeur du champ mRunning.
	 * 
	 * @return la valeur du champ mRunning.
	 */
	public boolean getmRunning()
	{
		return mRunning;
	}

	/* _________________________________________________________ */
	/**
	 * Modifie la valeur du cmap mRunning.
	 * 
	 * @param mRunning
	 *            la valeur à placer dans le champ mRunning.
	 */
	public void setmRunning(final boolean mRunning)
	{
		this.mRunning = mRunning;
	}
}
