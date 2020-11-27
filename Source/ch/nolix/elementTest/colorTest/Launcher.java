//package declaration
package ch.nolix.elementTest.colorTest;

//class
/**
 * Of the {@link Launcher} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 20
 */
public final class Launcher {
	
	//main method
	/**
	 * Creates a new {@link ColorTestPool} and runs it.
	 * 
	 * @param args
	 */
	public static final void main(String[] args) {
		new ColorTestPool().run();
	}
	
	//visibility-reduced constructor
	/**
	 * Avoids that an instance of the {@link Launcher} can be created.
	 */
	private Launcher() {}
}
