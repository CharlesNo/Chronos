/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : DialogFragmentSettings.java
 * 
 * Créé le 15 nov. 2013 à 08:23:54
 * 
 * Auteur : Jerome POINAS
 */
package business.dialog;

import utility.Constantes;
import view.controler.ControlerListAthlete;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import com.chronos.R;

/* _________________________________________________________ */
/**
 * @author Jerome POINAS
 * 
 */
public class DialogFragmentSettings extends DialogFragment implements
		Constantes
{
	/** L'activité utilisé pour lancer la boite de dialogue */
	private Activity					activity	= null;
	/** Le controleur du numberPicker */
	private final ControlerListAthlete	controler;

	/**
	 * Constructeur complet d'un DialogFragmentSettings.
	 * 
	 * @param activity
	 *            L'activité utilisé.
	 * @param controler
	 *            Le controleur.
	 */
	public DialogFragmentSettings(final Activity activity,
			final ControlerListAthlete controler)
	{
		this.activity = activity;
		this.controler = controler;
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
	public Dialog onCreateDialog(final Bundle savedInstanceState)
	{
		final LayoutInflater li = LayoutInflater
				.from(activity.getBaseContext());
		final View promptsView = li
				.inflate(R.layout.distance_picker_view, null);
		// **********Parametrage du numberPicker****************//
		final NumberPicker picker = (NumberPicker) promptsView
				.findViewById(R.id.pickerDistance);
		final String[] tabValeurs = new String[4];
		tabValeurs[0] = Integer.toString(50);
		tabValeurs[1] = Integer.toString(100);
		tabValeurs[2] = Integer.toString(200);
		tabValeurs[3] = Integer.toString(500);
		picker.setMinValue(0);
		picker.setMaxValue(tabValeurs.length - 1);
		picker.setDisplayedValues(tabValeurs);
		picker.setDisplayedValues(tabValeurs);
		picker.setWrapSelectorWheel(true);
		picker.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
		picker.setOnValueChangedListener(controler);
		// ******************************************************//
		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(Constantes.REGLAGE_DISTANCE);
		builder.setView(promptsView);
		builder.setPositiveButton(Constantes.VALIDATE,
				new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(final DialogInterface dialog,
							final int id)
					{
					}
				});
		builder.setNegativeButton(Constantes.CANCEL,
				new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(final DialogInterface dialog,
							final int id)
					{
					}
				});
		return builder.create();
	}
}
/* _________________________________________________________ */
/*
 * Fin du fichier DialogFragmentSettings.java.
 * /*_________________________________________________________
 */
