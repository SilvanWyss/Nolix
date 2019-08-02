//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.system.GUIClientoid.FrontGUIClientoid;
import ch.nolix.system.GUIClientoid.FrontGUIClientoidGUIType;
import ch.nolix.system.client.Application;

//class
/**
 * A {@link FrontBrowserGUIClient} does the same as a browser-analogue of a {@link FrontGUIClient}.
 * 
 * The requirements for a browser-analogue of a {@link FrontGUIClient} allow
 * that a browser-analogue of a {@link FrontGUIClient} can be implemented in JavaScript.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 50
 */
public final class FrontBrowserGUIClient extends FrontGUIClientoid<FrontBrowserGUIClient> {
	
	//constructor
	/**
	 * Creates a new {@link FrontBrowserGUIClient} that will connect to the given application.
	 * 
	 * @throws NullArgumentException if the given application is null.
	 */
	public FrontBrowserGUIClient(final Application<BackGUIClient> application) {
		
		//Calls constructor of the base class.
		super(FrontGUIClientoidGUIType.CanvasGUI);
		
		internal_connectTo(application);
	}
}
