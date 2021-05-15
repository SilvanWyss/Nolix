//package declaration
package ch.nolix.commontest.containertest;

//class
/**
 * Of this class an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2016-09-01
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
	
	//constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
