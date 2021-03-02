//package declaration
package ch.nolix.system.client.consoleclient;

import ch.nolix.common.endpoint.NetServer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.client.base.Application;
import ch.nolix.system.client.base.Server;
import ch.nolix.system.client.baseguiclient.BaseFrontGUIClient;
import ch.nolix.system.client.baseguiclient.BaseFrontGUIClientGUIType;

//class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 90
 */
public final class FrontConsoleClient extends BaseFrontGUIClient<FrontConsoleClient> {
	
	//constructor
	/**
	 * Creates a new {@link FrontConsoleClient} that will connect to the default application on the default port.
	 */
	public FrontConsoleClient() {
		
		super(BaseFrontGUIClientGUIType.CANVAS_GUI);
		
		internalConnectTo(NetServer.DEFAULT_PORT);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontConsoleClient} that will connect to the given application.
	 * 
	 * @param application
	 * @throws ArgumentIsNullException if the given target application is null.
	 */
	public FrontConsoleClient(final Application<BackConsoleClient> application) {
		
		super(BaseFrontGUIClientGUIType.CANVAS_GUI);
		
		internalConnectTo(application);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontConsoleClient} that will connect to the default application on the given port.
	 * 
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public FrontConsoleClient(final int port) {
		
		super(BaseFrontGUIClientGUIType.CANVAS_GUI);
		
		internalConnectTo(port);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontConsoleClient} that will connect to the given application on the server.
	 * 
	 * @param server
	 * @param application
	 * @throws ArgumentIsNullException if the given application is null.
	 * @throws InvalidArgumentException if the given application is blank.
	 */
	public FrontConsoleClient(final Server server, final String application) {
		
		super(BaseFrontGUIClientGUIType.CANVAS_GUI);
		
		internalConnectTo(server, application);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontConsoleClient}
	 * that will connect to the given application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param application
	 * @throws ArgumentIsNullException if the given application is null.
	 * @throws InvalidArgumentException if the given application is blank.
	 */
	public FrontConsoleClient(final String ip, final int port, final String application) {
		
		super(BaseFrontGUIClientGUIType.CANVAS_GUI);
		
		internalConnectTo(ip, port, application);
	}
}
