//package declaration
package ch.nolix.elementtest.timetest;

//class
/**
 * The launcher provides a main method to create and run a core test pool.
 * 
 * @author Silvan Wyss
 * @date 2017-11-14
 * @lines 20
 */
public final class Launcher {

	//main method
	/**
	 * Creates a new core test pool and runs it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new TimeTestPool().run();
	}
	
	//constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
