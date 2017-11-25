//package declaration
package ch.nolixTest;

//class
/**
 * This class provides a main method to run a Nolix test pool.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 30
 */
public final class Launcher {
	
	//main method
	/**
	 * Creates a new Nolix test pool and runs it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new NolixTestPool().run();
	}

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
