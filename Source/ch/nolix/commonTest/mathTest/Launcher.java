//package declaration
package ch.nolix.commonTest.mathTest;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 20
 */
public final class Launcher {

	//main method
	/**
	 * Creates a new mathematics test pool and executes it.
	 * 
	 * @param arguments
	 */
	public static final void main(final String[] arguments) {
		new MathematicsTestPool().run();
	}
	
	//access-reducing constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
