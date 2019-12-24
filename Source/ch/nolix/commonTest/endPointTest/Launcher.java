//package declaration
package ch.nolix.commonTest.endPointTest;

//class
/**
 * Of this class an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 20
 */
public final class Launcher {

	//main method
	/**
	 * Creates a new end point test pool and runs it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new EndPointTestPool().run();
	}
	
	//access-reducing constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
