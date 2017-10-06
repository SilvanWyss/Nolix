//package declaration
package ch.nolix.application.timer;

//own import
import ch.nolix.system.GUIClient.GUIFrontClient;

//package-visible class
/**
 * This class provides a main method to launch a timer.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2016-06
 * @lines 30
 */
final class Launcher {

	//main method
	/**
	 * Creates a new timer and a new front GUI client that will connect to the timer.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static final void main(String[] args) {
		new GUIFrontClient(new Timer());
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
