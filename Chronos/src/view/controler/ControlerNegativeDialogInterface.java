package view.controler;

import android.content.DialogInterface;

/* _________________________________________________________ */
/**
 * @author Jerome POINAS
 * 
 */
public final class ControlerNegativeDialogInterface implements
		DialogInterface.OnClickListener
{
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
	public void onClick(final DialogInterface dialog,
			@SuppressWarnings("unused") final int id)
	{
		dialog.cancel();
	}
}
