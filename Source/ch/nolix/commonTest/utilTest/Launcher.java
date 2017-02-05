//package declaration
package ch.nolix.commonTest.utilTest;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 20
 */
public final class Launcher {

	//main method
	/**
	 * Creates new util test pool and executes it.
	 * 
	 * @param arguments
	 */
	public static final void main(final String[] aruments) {
		new UtilTestPool().execute();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
