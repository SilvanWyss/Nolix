//package declaration
package ch.nolix.commontest.nettest.endpoint2test;

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
	 * Creates a new {@link EndPointTestPool} and runs it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new EndPointTestPool().run();
	}
	
	//visibility-reduced constructor
	/**
	 * Avoids that an instance of the {@link Launcher} can be created.
	 */
	private Launcher() {}
}
