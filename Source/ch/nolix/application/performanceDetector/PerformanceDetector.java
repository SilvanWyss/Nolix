//package declaration
package ch.nolix.application.performanceDetector;

//own imports
import ch.nolix.system.GUIClient.GUIBackClient;
import ch.nolix.system.client.StandardApplication;

//class
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 20
 */
public final class PerformanceDetector extends StandardApplication<GUIBackClient> {

	//name
	public static final String NAME = "Performace Detector";
	
	//constructor
	/**
	 * Creates a new performance detector.
	 */
	public PerformanceDetector() {
		
		//Calls constructor of the base class.
		super(NAME, MainSession.class);
	}
}
