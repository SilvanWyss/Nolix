//package declaration
package ch.nolix.businesstest.tradingtest;

//class
/**
 * @author Silvan Wyss
 * @date 2016-10-01
 */
public final class Launcher {

	//main method
	/**
	 * Creates a new finance test pool and executes it.
	 * 
	 * @param arguments
	 */
	public static void main(final String[] arguments) {
		new TradingTestPool().run();
	}
	
	//constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
