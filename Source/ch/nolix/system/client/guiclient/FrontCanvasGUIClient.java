//package declaration
package ch.nolix.system.client.guiclient;

import ch.nolix.common.endpoint.NetServer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.client.base.Application;
import ch.nolix.system.client.baseguiclient.BaseFrontGUIClient;
import ch.nolix.system.client.baseguiclient.BaseFrontGUIClientGUIType;

//class
/**
 * A {@link FrontCanvasGUIClient} does the same as a browser-analogue of a {@link FrontGUIClient}.
 * 
 * The requirements for a browser-analogue of a {@link FrontGUIClient} allow
 * that a browser-analogue of a {@link FrontGUIClient} can be implemented in JavaScript.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 120
 */
public final class FrontCanvasGUIClient extends BaseFrontGUIClient<FrontCanvasGUIClient> {
	
	//constructor
	/**
	 * Creates a new {@link FrontCanvasGUIClient} that will connect to the default application
	 * on the default port on the local machine.
	 */
	public FrontCanvasGUIClient() {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.CANVAS_GUI);
		
		internalConnectTo(NetServer.DEFAULT_PORT);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontCanvasGUIClient} that will connect to the given application.
	 * 
	 * @param application
	 * @throws ArgumentIsNullException if the given application is null.
	 */
	public FrontCanvasGUIClient(final Application<BackGUIClient> application) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.CANVAS_GUI);
		
		internalConnectTo(application);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontCanvasGUIClient} that will connect to the main application
	 * on the given port on the local machine.
	 * 
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public FrontCanvasGUIClient(final int port) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.CANVAS_GUI);
		
		internalConnectTo(port);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontCanvasGUIClient} that will connect
	 * to the given application on the given port on the local machine.
	 * 
	 * @param port
	 * @param application
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given application is null.
	 * @throws InvalidArgumentException if the given application is blank.
	 */
	public FrontCanvasGUIClient(final int port, final String application) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.CANVAS_GUI);
		
		internalConnectTo(port, application);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontCanvasGUIClient} that will connect
	 * to the main application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public FrontCanvasGUIClient(final String ip, final int port) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.CANVAS_GUI);
		
		internalConnectTo(ip, port);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontCanvasGUIClient} that will connect
	 * to the given application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param application
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given application is null.
	 * @throws InvalidArgumentException if the given application is blank.
	 */
	public FrontCanvasGUIClient(final String ip, final int port, final String application) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.CANVAS_GUI);
		
		internalConnectTo(ip, port, application);
	}
}
