//package declaration
package ch.nolix.techtest.projecttest;

//class
/**
 * Of the {@link Launcher} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 20
 */
public final class Launcher {
	
	//main method
	/**
	 * Creates a new {@link ProjectTestPool} and runs it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new ProjectTestPool().run();
	}
	
	//visibility-reduced constructor
	/**
	 * Avoids that an instance of the {@link Launcher} can be created.
	 */
	private Launcher() {}
}
