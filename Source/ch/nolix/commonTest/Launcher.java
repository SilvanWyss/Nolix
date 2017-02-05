//package declaration
package ch.nolix.commonTest;

//class
/**
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 20
 */
public final class Launcher {
	
	//main method
	/**
	 * Creates new common test pool and executes it.
	 * 
	 * @param arguments
	 */
	public static void main(final String[] arguments) {
		new CommonTestPool().execute();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
