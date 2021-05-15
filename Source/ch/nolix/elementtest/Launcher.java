//package declaration
package ch.nolix.elementtest;

//class
/**
 * @author Silvan Wyss
 * @date 2016-02-01
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

	//constructor
	/**
	 * Avoids that an instance of this class can be instantiated.
	 */
	private Launcher() {}
}
