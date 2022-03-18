//package declaration
package ch.nolix.system.client.consoleclient;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.net.endpoint.Server;
import ch.nolix.system.client.base.Application;
import ch.nolix.system.client.base.BaseServer;
import ch.nolix.system.client.baseguiclient.BaseFrontGUIClient;

//class
/**
 * @author Silvan Wyss
 * @date 2017-03-02
 */
public final class FrontConsoleClient extends BaseFrontGUIClient<FrontConsoleClient> {
	
	//constructor
	/**
	 * Creates a new {@link FrontConsoleClient} that will connect to the default application on the default port.
	 */
	public FrontConsoleClient() {
		connectTo(Server.DEFAULT_PORT);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontConsoleClient} that will connect to the given application.
	 * 
	 * @param application
	 * @throws ArgumentIsNullException if the given target application is null.
	 */
	public <AC> FrontConsoleClient(final Application<BackConsoleClient<AC>, AC> application) {
		connectTo(application);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontConsoleClient} that will connect to the default application on the given port.
	 * 
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public FrontConsoleClient(final int port) {
		connectTo(port);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontConsoleClient} that will connect to the given application on the server.
	 * 
	 * @param baseServer
	 * @param application
	 * @throws ArgumentIsNullException if the given application is null.
	 * @throws InvalidArgumentException if the given application is blank.
	 */
	public FrontConsoleClient(final BaseServer baseServer, final String application) {
		connectTo(baseServer, application);
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
		connectTo(ip, port, application);
	}
}
