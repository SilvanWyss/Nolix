//package declaration
package ch.nolix.commonTest.commonTypeHelpersTest;

//class
/**
 * Of the {@link Launcher} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 20
 */
public final class Launcher {

	//main method
	/**
	 * Creates a new {@link CommonTypeHelpersTestPool} and runs it.
	 * 
	 * @param args
	 */
	public static final void main(String[] args) {
		new CommonTypeHelpersTestPool().run();
	}
	
	//visibility-reducing constructor
	/**
	 * Avoids that an instance of the {@link Launcher} can be created.
	 */
	private Launcher() {}
}
