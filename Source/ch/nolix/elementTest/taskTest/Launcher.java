//package declaration
package ch.nolix.elementTest.taskTest;

//class
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 20
 */
public final class Launcher {

	//main method
	/**
	 * Creates a new task test pool and runs it.
	 * 
	 * @param args
	 */
	public static final void main(String[] args) {			
		new TaskTestPool().run();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
