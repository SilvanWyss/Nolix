//package declaration
package ch.nolix.coreTest.nodeTest;

//class
/**
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 20
 */
public final class Launcher {

	//main method
	/**
	 * Creates a new specification test pool and executes it.
	 * 
	 * @param arguments
	 */
	public static void main(final String[] arguments) {
		new NodeTestPool().run();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
