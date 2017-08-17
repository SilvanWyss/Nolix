//package declaration
package ch.nolix.application.performanceDetector;

//own import
import ch.nolix.system.GUIClient.GUIFrontClient;

//package-visible class
/**
 * This class provides a main method to launch a performance detector.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2016-07
 * @lines 30
 */
final class Launcher {

	//main method
	/**
	 * Creates a new performance detector
	 * and a new front GUI client that will connect to the performance detector.
	 * 
	 * @param args
	 */
	public static final void main(String[] args) {
		
		//Creates performance detector.
		final PerformanceDetector performanceDetector = new PerformanceDetector();
		
		//Creates front GUI client.
		new GUIFrontClient(performanceDetector);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
