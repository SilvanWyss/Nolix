//package declaration
package ch.nolix.coreTest.endPoint3Test;

//class
/**
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 20
 */
public final class Launcher {
	
	//main method
	/**
	 * Creates new end point test pool and runs it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new EndPointTestPool().run();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}