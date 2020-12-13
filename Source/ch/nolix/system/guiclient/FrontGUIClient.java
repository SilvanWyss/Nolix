//package declaration
package ch.nolix.system.guiclient;

//own imports
import ch.nolix.common.endpoint2.NetServer;
import ch.nolix.element.gui.GUI;
import ch.nolix.system.baseguiclient.BaseFrontGUIClient;
import ch.nolix.system.baseguiclient.BaseFrontGUIClientGUIType;
import ch.nolix.system.client.Application;
import ch.nolix.system.client.Server;

//class
/**
 * A {@link FrontGUIClient} provides a {@link GUI} and is controlled by a {@link BackGUIClient}.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 140
 */
public final class FrontGUIClient extends BaseFrontGUIClient<FrontGUIClient> {
	
	/**
	 * Creates a new {@link FrontGUIClient} that will connect to the main application
	 * on the default port on the local machine.
	 */
	public FrontGUIClient() {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.WIDGET_GUI);
		
		internalConnectTo(NetServer.DEFAULT_PORT);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect to the given application.
	 * 
	 * @param application
	 */
	public FrontGUIClient(Application<BackGUIClient> application) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.WIDGET_GUI);
		
		internalConnectTo(application);
	}
	
	/**
	 * Creates a new {@link FrontGUIClient} that will connect to the main application
	 * on the given port on the local machine.
	 * 
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 */
	public FrontGUIClient(final int port) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.WIDGET_GUI);
		
		internalConnectTo(port);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect
	 * to the application with the given name on given port on the local machine
	 * 
	 * @param port
	 * @param name
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public FrontGUIClient(final int port, final String name) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.WIDGET_GUI);
		
		internalConnectTo(port, name);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect to the main application on the given server.
	 * 
	 * @param application
	 */
	public FrontGUIClient(final Server server) {
		
		super(BaseFrontGUIClientGUIType.WIDGET_GUI);
		
		internalConnectTo(server);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect
	 * to the default application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param name
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public FrontGUIClient(final String ip, final int port) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.WIDGET_GUI);
		
		internalConnectTo(ip, port);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect
	 * to the application with the given name on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param name
	 * @throws OutOfRangeException if the given port is not in [0,65535].
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public FrontGUIClient(final String ip, final int port, final String name) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.WIDGET_GUI);
		
		internalConnectTo(ip, port, name);
	}
}
