//package declaration
package ch.nolix.systemtest.elementenumtest;

//class
/**
 * Of the {@link Launcher} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-09-16
 */
public final class Launcher {
	
	//main method
	/**
	 * Creates a new {@link ElementEnumTestPool} and runs it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new ElementEnumTestPool().run();
	}
	
	//constructor
	/**
	 * Prevents that an instance of the {@link Launcher} can be created.
	 */
	private Launcher() {}
}
