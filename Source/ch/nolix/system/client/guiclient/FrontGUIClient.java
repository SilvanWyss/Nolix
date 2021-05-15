//package declaration
package ch.nolix.system.client.guiclient;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.net.endpoint.NetServer;
import ch.nolix.element.gui.base.GUI;
import ch.nolix.system.client.base.Application;
import ch.nolix.system.client.base.Server;
import ch.nolix.system.client.baseguiclient.BaseFrontGUIClient;
import ch.nolix.system.client.baseguiclient.BaseFrontGUIClientGUIType;

//class
/**
 * A {@link FrontGUIClient} provides a {@link GUI} and is controlled by a {@link BackGUIClient}.
 * 
 * @author Silvan Wyss
 * @date 2016-12-01
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
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
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
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
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
	 * @param server
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
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
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
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public FrontGUIClient(final String ip, final int port, final String name) {
		
		//Calls constructor of the base class.
		super(BaseFrontGUIClientGUIType.WIDGET_GUI);
		
		internalConnectTo(ip, port, name);
	}
}
