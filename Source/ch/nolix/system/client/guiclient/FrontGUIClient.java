//package declaration
package ch.nolix.system.client.guiclient;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.client.base.Application;
import ch.nolix.system.client.base.Server;
import ch.nolix.system.client.baseguiclient.BaseFrontGUIClient;

//class
/**
 * @author Silvan Wyss
 * @date 2018-09-05
 */
public final class FrontGUIClient extends BaseFrontGUIClient<FrontGUIClient> {
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect to the default application
	 * on the default port on the local machine.
	 */
	public FrontGUIClient() {
		connectTo(Server.DEFAULT_PORT);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect to the given application.
	 * 
	 * @param application
	 * @throws ArgumentIsNullException if the given application is null.
	 */
	public <AC> FrontGUIClient(final Application<BackGUIClient<AC>, AC> application) {
		connectTo(application);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect to the main application
	 * on the given port on the local machine.
	 * 
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public FrontGUIClient(final int port) {
		connectTo(port);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect
	 * to the given application on the given port on the local machine.
	 * 
	 * @param port
	 * @param application
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given application is null.
	 * @throws InvalidArgumentException if the given application is blank.
	 */
	public FrontGUIClient(final int port, final String application) {
		connectTo(port, application);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect to
	 * the default {@link Application} on the {@link Server#DEFAULT_PORT} on the machine with the given ip.
	 * 
	 * @param ip
	 */
	public FrontGUIClient(final String ip) {
		connectTo(ip);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect
	 * to the main application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 */
	public FrontGUIClient(final String ip, final int port) {
		connectTo(ip, port);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontGUIClient} that will connect
	 * to the given application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param application
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given application is null.
	 * @throws InvalidArgumentException if the given application is blank.
	 */
	public FrontGUIClient(final String ip, final int port, final String application) {
		connectTo(ip, port, application);
	}
}
