//package declaration
package ch.nolix.coreTest.containerTest;

//class
/**
 * Of this class no instance can be created.
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
	public static final void main(String[] args) {
		new ContainerTestPool().run();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
