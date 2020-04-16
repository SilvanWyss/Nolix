//package declaration
package ch.nolix.elementTest.timeTest;

//class
/**
 * The launcher provides a main method to create and run a core test pool.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 20
 */
public final class Launcher {

	//main method
	/**
	 * Creates a new core test pool and runs it.
	 * 
	 * @param args
	 */
	public static final void main(String[] args) {
		new TimeTestPool().run();
	}
	
	//visibility-reducing constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
