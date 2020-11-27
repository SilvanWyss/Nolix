//package declaration
package ch.nolix.commonTest.containerTest;

//class
/**
 * Of this class an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 20
 */
public final class Launcher {

	//main method
	/**
	 * Creates a new container test pool and runs it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new ContainerTestPool().run();
	}
	
	//visibility-reduced constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
