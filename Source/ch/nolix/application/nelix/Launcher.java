//package declaration
package ch.nolix.application.nelix;

//class
/**
 * This class provides a main method to launch a nelix.
 * 
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 30
 */
public final class Launcher {

	//main method
	/**
	 * Creates new nelix.
	 * 
	 * @param arguments
	 */
	public static void main(final String[] arguments) {
		new Nelix();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
