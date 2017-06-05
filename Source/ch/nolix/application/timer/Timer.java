//package declaration
package ch.nolix.application.timer;

//own imports
import ch.nolix.system.GUIClient.GUIClient;
import ch.nolix.system.client.StandardApplication;

//class
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 20
 */
public final class Timer extends StandardApplication<GUIClient> {

	//name
	public static final String NAME = "Timer";

	//constructor
	/**
	 * Creates new timer.
	 */
	public Timer() {
		
		//Calls constructor of the base class.
		super(NAME, MainSession.class);
	}
}
