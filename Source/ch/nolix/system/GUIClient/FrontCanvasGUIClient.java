//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.common.endPoint2.NetServer;
import ch.nolix.system.baseGUIClient.BaseFrontGUIClient;
import ch.nolix.system.baseGUIClient.BaseFrontGUIClientGUIType;
import ch.nolix.system.client.Application;

//class
/**
 * A {@link FrontCanvasGUIClient} does the same as a browser-analogue of a {@link FrontGUIClient}.
 * 
 * The requirements for a browser-analogue of a {@link FrontGUIClient} allow
 * that a browser-analogue of a {@link FrontGUIClient} can be implemented in JavaScript.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 130
 */
public final class FrontCanvasGUIClient extends BaseFrontGUIClient<FrontCanvasGUIClient> {
	
	//constructor
	/**
	 * Creates a new {@link FrontCanvasGUIClient} that will connect to the default application
	 * on the default port on the local machine.
	 */
	public FrontCanvasGUIClient() {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.CanvasGUI);
		
		internalConnectTo(NetServer.DEFAULT_PORT);
		
		noteResizeOnCounterpart();
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontCanvasGUIClient} that will connect to the given application.
	 * 
	 * @throws ArgumentIsNullException if the given application is null.
	 */
	public FrontCanvasGUIClient(final Application<BackGUIClient> application) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.CanvasGUI);
		
		internalConnectTo(application);
				
		noteResizeOnCounterpart();
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontCanvasGUIClient} that will connect to the main application
	 * on the given port on the local machine.
	 * 
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 */
	public FrontCanvasGUIClient(final int port) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.CanvasGUI);
		
		internalConnectTo(port);
				
		noteResizeOnCounterpart();
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontCanvasGUIClient} that will connect
	 * to the given application on the given port on the local machine.
	 * 
	 * @param port
	 * @param application
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws ArgumentIsNullException if the given application is null.
	 * @throws InvalidArgumentException if the given application is blank.
	 */
	public FrontCanvasGUIClient(final int port, final String application) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.CanvasGUI);
		
		internalConnectTo(port, application);
		
		noteResizeOnCounterpart();
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontCanvasGUIClient} that will connect
	 * to the main application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 */
	public FrontCanvasGUIClient(final String ip, final int port) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.CanvasGUI);
		
		internalConnectTo(ip, port);
				
		noteResizeOnCounterpart();
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontCanvasGUIClient} that will connect
	 * to the given application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param application
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws ArgumentIsNullException if the given application is null.
	 * @throws InvalidArgumentException if the given application is blank.
	 */
	public FrontCanvasGUIClient(final String ip, final int port, final String application) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.CanvasGUI);
		
		internalConnectTo(ip, port, application);
		
		noteResizeOnCounterpart();
	}
}
