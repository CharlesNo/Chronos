package view.controler.dialogInterface;

import view.activity.ActivityListAthlete;
import android.app.Activity;
import android.content.DialogInterface;
import android.widget.NumberPicker;
import android.widget.TextView;
import business.DialogDistance;
import com.chronos.R;

/* _________________________________________________________ */
/**
 * @author Jerome POINAS
 * 
 *         La classe ControlerDistanceDialogInterface permettant de gérer les
 *         évenements sur le numberpicker des distances.
 * 
 */
public final class ControlerDistanceDialogInterface implements
		DialogInterface.OnClickListener
{
	/** The picker. */
	private final NumberPicker		picker;
	/** The activity. */
	private final Activity			activity;
	/** The dialog distance. */
	private final DialogDistance	dialogDistance;

	/* _________________________________________________________ */
	/**
	 * Instantiates a new controler distance dialog interface.
	 * 
	 * @param picker
	 *            the picker
	 * @param activity
	 *            the activity
	 * @param dialogDistance
	 *            the dialog distance
	 */
	public ControlerDistanceDialogInterface(final NumberPicker picker,
			final Activity activity, final DialogDistance dialogDistance)
	{
		this.picker = picker;
		this.activity = activity;
		this.dialogDistance = dialogDistance;
	}

	/* _________________________________________________________ */
	/**
	 * On click.
	 * 
	 * @param dialog
	 *            the dialog
	 * @param id
	 *            the id
	 * @see android.content.DialogInterface.OnClickListener#onClick(android.content.DialogInterface,
	 *      int)
	 */
	@Override
	public void onClick(
			@SuppressWarnings("unused") final DialogInterface dialog,
			@SuppressWarnings("unused") final int id)
	{
		final TextView champsDistance = (TextView) activity
				.findViewById(R.id.textView1);
		final String[] values = picker.getDisplayedValues();
		champsDistance.setText("DialogDistance d'enregistrement : ("
				+ values[picker.getValue()] + "m)");
		ActivityListAthlete.setRecordDistance(Integer.parseInt(values[picker
				.getValue()]));
		dialogDistance.setIndex(picker.getValue());
	}
}
