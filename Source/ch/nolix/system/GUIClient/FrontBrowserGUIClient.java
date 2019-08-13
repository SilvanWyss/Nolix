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
 * @lines 100
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
	
	//constructor
	/**
	 * Creates a new {@link FrontBrowserGUIClient} that will connect to the main application
	 * on the given port on the local machine.
	 * 
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 */
	public FrontBrowserGUIClient(final int port) {
		
		//Calls constructor of the base class.
		super(FrontGUIClientoidGUIType.CanvasGUI);
		
		internal_connectTo(port);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontBrowserGUIClient} that will connect
	 * to the given application on the given port on the local machine.
	 * 
	 * @param port
	 * @param application
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws NullArgumentException if the given application is null.
	 * @throws InvalidArgumentException if the given application is blank.
	 */
	public FrontBrowserGUIClient(final int port, final String application) {
		
		//Calls constructor of the base class.
		super(FrontGUIClientoidGUIType.CanvasGUI);
		
		internal_connectTo(port, application);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontBrowserGUIClient} that will connect
	 * to the main application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 */
	public FrontBrowserGUIClient(final String ip, final int port) {
		
		//Calls constructor of the base class.
		super(FrontGUIClientoidGUIType.CanvasGUI);
		
		internal_connectTo(ip, port);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontBrowserGUIClient} that will connect
	 * to the given application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param application
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws NullArgumentException if the given application is null.
	 * @throws InvalidArgumentException if the given application is blank.
	 */
	public FrontBrowserGUIClient(final String ip, final int port, final String application) {
		
		//Calls constructor of the base class.
		super(FrontGUIClientoidGUIType.CanvasGUI);
		
		internal_connectTo(ip, port, application);
	}
}
