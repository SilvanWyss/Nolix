//package declaration
package ch.nolix.application.performanceDetector;

//own import
import ch.nolix.system.GUIClient.FrontGUIClient;

//class
/**
 * This class provides a main method to launch a performance detector.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 30
 */
public final class Launcher {

	//main method
	/**
	 * Creates a performance detector
	 * and a GUI front client that connects to the performance detector.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static final void main(String[] args) {
		new FrontGUIClient(new PerformanceDetector());
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
