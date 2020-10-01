//package declaration
package ch.nolix.techTest.projectTest;

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
	 * Creates a new {@link TaskTestPool} and runs it.
	 * 
	 * @param args
	 */
	public static final void main(String[] args) {
		new TaskTestPool().run();
	}
	
	//visibility-reducing constructor
	/**
	 * Avoids that an instance of the {@link Launcher} can be created.
	 */
	private Launcher() {}
}
