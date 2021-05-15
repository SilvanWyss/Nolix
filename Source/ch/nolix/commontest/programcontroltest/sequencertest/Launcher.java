//package declaration
package ch.nolix.commontest.programcontroltest.sequencertest;

//class
/**
 * The Launcher provides a main method to run a sequencer test pool.
 * Of this class an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-11-18
 * @lines 30
 */
public final class Launcher {
	
	//main method
	/**
	 * Creates a new sequencer test pool and runs it.
	 * li
	 * @param args
	 */
	public static void main(String[] args) {
		new SequencerTestPool().run();
	}

	//constructor
	/**
	 * Avoids that an instance of this class can be instantiated.
	 */
	private Launcher() {}
}
