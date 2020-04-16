//package declaration
package ch.nolix.elementTest;

//class
/**
 * @author Silvan Wyss
 * @month 2016-01
 * @lines 20
 */
public final class Launcher {
	
	//main method
	/**
	 * Creates a new element test pool and runs it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new ElementTestPool().run();
	}

	//visibility-reducing constructor
	/**
	 * Avoids that an instance of this class can be instantiated.
	 */
	private Launcher() {}
}
