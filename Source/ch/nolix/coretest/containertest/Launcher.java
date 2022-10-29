//package declaration
package ch.nolix.coretest.containertest;

//class
/**
 * Of this class an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2016-09-01
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
	 * Prevents that an instance of the {@link Launcher} can be created.
	 */
	private Launcher() {}
}
