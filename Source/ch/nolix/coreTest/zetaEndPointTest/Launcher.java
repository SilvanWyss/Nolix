//package declaration
package ch.nolix.coreTest.zetaEndPointTest;

//class
/**
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 20
 */
public final class Launcher {
	
	//main method
	/**
	 * Creates new alpha end point test pool and executes it.
	 * 
	 * @param arguments
	 */
	public static void main(final String[] arguments) {
		new ZetaEndPointTestPool().execute();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}