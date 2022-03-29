//package declaration
package ch.nolix.system.application.consoleapplication;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.net.endpoint.Server;
import ch.nolix.system.application.baseguiapplication.BaseFrontendGUIClient;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.BaseServer;

//class
/**
 * @author Silvan Wyss
 * @date 2017-03-02
 */
public final class FrontendConsoleClient extends BaseFrontendGUIClient<FrontendConsoleClient> {
	
	//constructor
	/**
	 * Creates a new {@link FrontendConsoleClient} that will connect to the default application on the default port.
	 */
	public FrontendConsoleClient() {
		connectTo(Server.DEFAULT_PORT);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontendConsoleClient} that will connect to the given application.
	 * 
	 * @param application
	 * @throws ArgumentIsNullException if the given target application is null.
	 */
	public <AC> FrontendConsoleClient(final Application<BackendConsoleClient<AC>, AC> application) {
		connectTo(application);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontendConsoleClient} that will connect to the default application on the given port.
	 * 
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public FrontendConsoleClient(final int port) {
		connectTo(port);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontendConsoleClient} that will connect to the given application on the server.
	 * 
	 * @param baseServer
	 * @param application
	 * @throws ArgumentIsNullException if the given application is null.
	 * @throws InvalidArgumentException if the given application is blank.
	 */
	public FrontendConsoleClient(final BaseServer baseServer, final String application) {
		connectTo(baseServer, application);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontendConsoleClient}
	 * that will connect to the given application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param application
	 * @throws ArgumentIsNullException if the given application is null.
	 * @throws InvalidArgumentException if the given application is blank.
	 */
	public FrontendConsoleClient(final String ip, final int port, final String application) {
		connectTo(ip, port, application);
	}
}
