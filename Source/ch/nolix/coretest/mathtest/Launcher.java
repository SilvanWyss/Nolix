//package declaration
package ch.nolix.coretest.mathtest;

//class
/**
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public final class Launcher {

	//main method
	/**
	 * Creates a new mathematics test pool and executes it.
	 * 
	 * @param arguments
	 */
	public static void main(final String[] arguments) {
		new MathTestPool().run();
	}
	
	//constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
