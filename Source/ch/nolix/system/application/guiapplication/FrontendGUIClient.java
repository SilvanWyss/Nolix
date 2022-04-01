//package declaration
package ch.nolix.system.application.guiapplication;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.application.baseguiapplication.BaseFrontendGUIClient;
import ch.nolix.system.application.main.Application;
import ch.nolix.system.application.main.Server;

//class
/**
 * @author Silvan Wyss
 * @date 2018-09-05
 */
public final class FrontendGUIClient extends BaseFrontendGUIClient<FrontendGUIClient> {
	
	//constructor
	/**
	 * Creates a new {@link FrontendGUIClient} that will connect to
	 * the default application on the default port on the local computer.
	 */
	public FrontendGUIClient() {
		connectTo(Server.DEFAULT_PORT);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontendGUIClient} that will connect to the given application.
	 * 
	 * @param application
	 * @throws ArgumentIsNullException if the given application is null.
	 */
	public <AC> FrontendGUIClient(final Application<BackendGUIClient<AC>, AC> application) {
		connectTo(application);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontendGUIClient} that will connect to
	 * the default application on the given port on the local computer.
	 * 
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public FrontendGUIClient(final int port) {
		connectTo(port);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontendGUIClient} that will connect to
	 * the given application on the given port on the local computer.
	 * 
	 * @param port
	 * @param application
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given application is null.
	 * @throws InvalidArgumentException if the given application is blank.
	 */
	public FrontendGUIClient(final int port, final String application) {
		connectTo(port, application);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontendGUIClient} that will connect to the default application of the given server.
	 * 
	 * @param server
	 */
	public FrontendGUIClient(final Server server) {
		connectTo(server);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontendGUIClient} that will connect to
	 * the default application on the default port on the computer with the given ip.
	 * 
	 * @param ip
	 */
	public FrontendGUIClient(final String ip) {
		connectTo(ip);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontendGUIClient} that will connect to
	 * the main application on the given port on the computer with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public FrontendGUIClient(final String ip, final int port) {
		connectTo(ip, port);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontendGUIClient} that will connect to
	 * the given application on the given port on the computer with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param application
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given application is null.
	 * @throws InvalidArgumentException if the given application is blank.
	 */
	public FrontendGUIClient(final String ip, final int port, final String application) {
		connectTo(ip, port, application);
	}
}
