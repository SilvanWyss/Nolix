//package declaration
package ch.nolix.elementTest.elementEnumsTest;

//class
/**
 * Of this class an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 20
 */
public final class Launcher {

	//main method
	/**
	 * Creates a new enum test pool and runs it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new ElementEnumTestPool().run();
	}
	
	//access-reducing constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
