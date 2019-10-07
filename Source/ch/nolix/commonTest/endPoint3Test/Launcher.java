//package declaration
package ch.nolix.commonTest.endPoint3Test;

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
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link Launcher} can be created.
	 */
	private Launcher() {}
}
