//package declaration
package ch.nolix.coreTest.helperTest;

//class
/**
 * Of the {@link Launcher} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 20
 */
public final class Launcher {

	//main method
	/**
	 * Creates a new {@link HelperTestPool} and runs it.
	 * 
	 * @param args
	 */
	public static final void main(String[] args) {
		new HelperTestPool().run();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link Launcher} can be created.
	 */
	private Launcher() {}
}
