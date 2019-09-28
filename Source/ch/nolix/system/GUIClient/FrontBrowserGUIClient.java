//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.system.baseGUIClient.BaseFrontGUIClient;
import ch.nolix.system.baseGUIClient.BaseFrontGUIClientGUIType;
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
 * @lines 130
 */
public final class FrontBrowserGUIClient extends BaseFrontGUIClient<FrontBrowserGUIClient> {
	
	//constructor
	/**
	 * Creates a new {@link FrontBrowserGUIClient} that will connect to the given application.
	 * 
	 * @throws ArgumentIsNullException if the given application is null.
	 */
	public FrontBrowserGUIClient(final Application<BackGUIClient> application) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.CanvasGUI);
		
		internal_connectTo(application);
		
		//TODO: The connection seems to need a delay, otherwise the GUI is probably frozen from beginning.
		Sequencer.waitForMilliseconds(200);
		
		noteResizeOnCounterpart();
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
		super(BaseFrontGUIClientGUIType.CanvasGUI);
		
		internal_connectTo(port);
		
		//TODO: The connection seems to need a delay, otherwise the GUI is probably frozen from beginning.
		Sequencer.waitForMilliseconds(200);
		
		noteResizeOnCounterpart();
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontBrowserGUIClient} that will connect
	 * to the given application on the given port on the local machine.
	 * 
	 * @param port
	 * @param application
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws ArgumentIsNullException if the given application is null.
	 * @throws InvalidArgumentException if the given application is blank.
	 */
	public FrontBrowserGUIClient(final int port, final String application) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.CanvasGUI);
		
		internal_connectTo(port, application);
		
		//TODO: The connection seems to need a delay, otherwise the GUI is probably frozen from beginning.
		Sequencer.waitForMilliseconds(200);
		
		noteResizeOnCounterpart();
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
		super(BaseFrontGUIClientGUIType.CanvasGUI);
		
		internal_connectTo(ip, port);
		
		//TODO: The connection seems to need a delay, otherwise the GUI is probably frozen from beginning.
		Sequencer.waitForMilliseconds(200);
		
		noteResizeOnCounterpart();
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
	 * @throws ArgumentIsNullException if the given application is null.
	 * @throws InvalidArgumentException if the given application is blank.
	 */
	public FrontBrowserGUIClient(final String ip, final int port, final String application) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.CanvasGUI);
		
		internal_connectTo(ip, port, application);
		
		//TODO: The connection seems to need a delay, otherwise the GUI is probably frozen from beginning.
		Sequencer.waitForMilliseconds(200);
		
		noteResizeOnCounterpart();
	}
}
