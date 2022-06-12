//package declaration
package ch.nolix.systemtest.timetest;

//class
/**
 * The launcher provides a main method to create and run a core test pool.
 * 
 * @author Silvan Wyss
 * @date 2017-11-14
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
	 * Prevents that an instance of the {@link Launcher} can be created.
	 */
	private Launcher() {}
}
