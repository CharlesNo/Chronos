/* _________________________________________________________ */
/* _________________________________________________________ */
/**
 * Fichier : OnChronometerTickListener.java
 * 
 * Créé le 2 nov. 2013 à 23:05:50
 * 
 * Auteur : Charles NEAU
 */
package business;

/**
 * The listener interface for receiving onChronometerTick events.
 * The class that is interested in processing a onChronometerTick
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addOnChronometerTickListener<code> method. When
 * the onChronometerTick event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see OnChronometerTickListener
 */
public interface OnChronometerTickListener
{
	/**
	 * On chronometer tick.
	 * 
	 * @param chronometer
	 *            the chronometer
	 */
	void onChronometerTick(Chronometer chronometer);
}
/* _________________________________________________________ */
/*
 * Fin du fichier OnChronometerTickListener.java.
 * /*_________________________________________________________
 */
