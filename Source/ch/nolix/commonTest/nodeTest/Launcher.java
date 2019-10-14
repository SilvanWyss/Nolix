//package declaration
package ch.nolix.commonTest.nodeTest;

//class
/**
 * Of the {@link Launcher} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 20
 */
public final class Launcher {
	
	//main method
	/**
	 * Creates a new {@link NodeTestPool} and runs it.
	 * 
	 * @param arguments
	 */
	public static void main(final String[] arguments) {
		new NodeTestPool().run();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link Launcher} can be created.
	 */
	private Launcher() {}
}
