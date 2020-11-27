//package declaration
package ch.nolix.elementTest.financeTest;

//class
/**
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 20
 */
public final class Launcher {

	//main method
	/**
	 * Creates a new finance test pool and executes it.
	 * 
	 * @param arguments
	 */
	public static void main(final String[] arguments) {
		new FinanceTestPool().run();
	}
	
	//visibility-reduced constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
