//package declaration
package ch.nolix.application.timer;

//own imports
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.client.Application;

//class
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 20
 */
public final class Timer extends Application<BackGUIClient> {

	//name
	public static final String NAME = "Timer";

	//constructor
	/**
	 * Creates a new timer.
	 */
	public Timer() {
		
		//Calls constructor of the base class.
		super(NAME, BackGUIClient.class, MainSession.class);
	}
}
