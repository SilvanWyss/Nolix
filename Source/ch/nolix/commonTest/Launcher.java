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
	 * Creates a new core test pool and runs it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new CommonTestPool().run();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
