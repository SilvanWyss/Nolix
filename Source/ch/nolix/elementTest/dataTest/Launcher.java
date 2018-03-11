//package declaration
package ch.nolix.elementTest.dataTest;

//package-visible class
/**
 * This class provides a main method to create and execute a data test pool.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 20
 */
final class Launcher {

	//main method
	/**
	 * Creates a new data test pool and executes it.
	 * 
	 * @param arguments
	 */
	public static final void main(final String[] arguments) {
		new DataTestPool().run();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
