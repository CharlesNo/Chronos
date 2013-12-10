/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : DialogFragmentSettings.java
 * 
 * Créé le 15 nov. 2013 à 08:23:54
 * 
 * Auteur : Jerome POINAS
 */
package view.dialog;

import utility.Constantes;
import view.controler.dialogInterface.ControlerDistanceDialogInterface;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import business.DialogDistance;
import com.chronos.R;

/* _________________________________________________________ */
/**
 * @author Jerome POINAS
 * 
 */
@SuppressLint("ValidFragment")
public class DialogFragmentSettings extends DialogFragment implements
		Constantes
{
	/** L'activité utilisé pour lancer la boite de dialogue */
	private Activity				activity	= null;
	/** The dialog distance. */
	private final DialogDistance	dialogDistance;

	/**
	 * Constructeur complet d'un DialogFragmentSettings.
	 * 
	 * @param activity
	 *            L'activité utilisé.
	 * @param dialogDistance
	 *            the dialog distance
	 */
	public DialogFragmentSettings(final Activity activity,
			final DialogDistance dialogDistance)
	{
		this.activity = activity;
		this.dialogDistance = dialogDistance;
		final Dialog dial = onCreateDialog(getArguments());
		dial.show();
	}

	/* _________________________________________________________ */
	/**
	 * Methode qui permet de creer la boite de dialogue.
	 * 
	 * @param savedInstanceState
	 *            Bundle.
	 * @return Notre Dialog.
	 * @see android.app.DialogFragment#onCreateDialog(android.os.Bundle)
	 */
	@Override
	public Dialog onCreateDialog(
			@SuppressWarnings("unused") final Bundle savedInstanceState)
	{
		final LayoutInflater li = LayoutInflater
				.from(activity.getBaseContext());
		final View promptsView = li
				.inflate(R.layout.distance_picker_view, null);
		// **********Parametrage du numberPicker****************//
		final NumberPicker picker = (NumberPicker) promptsView
				.findViewById(R.id.pickerDistance);
		picker.setMinValue(0);
		picker.setMaxValue(dialogDistance.getValues().length - 1);
		picker.setDisplayedValues(dialogDistance.getValues());
		picker.setWrapSelectorWheel(true);
		picker.setValue(dialogDistance.getIndex());
		picker.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
		// ******************************************************//
		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(Constantes.REGLAGE_DISTANCE);
		builder.setView(promptsView);
		builder.setPositiveButton(Constantes.VALIDATE,
				new ControlerDistanceDialogInterface(picker, activity,
						dialogDistance));
		return builder.create();
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier DialogFragmentSettings.java.
 * /*_________________________________________________________
 */
