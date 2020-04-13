//package declaration
package ch.nolix.system.consoleClient;

//own imports
import ch.nolix.system.baseGUIClient.BaseFrontGUIClient;
import ch.nolix.system.baseGUIClient.BaseFrontGUIClientGUIType;
import ch.nolix.system.client.Application;
import ch.nolix.system.client.Server;

//class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 70
 */
public final class FrontConsoleClient extends BaseFrontGUIClient<FrontConsoleClient> {
	
	//constructor
	/**
	 * Creates a new {@link FrontConsoleClient} that will connect to the given application.
	 * 
	 * @param application
	 * @throws ArgumentIsNullException if the given target application is null.
	 */
	public FrontConsoleClient(final Application<BackConsoleClient> application) {
		
		super(BaseFrontGUIClientGUIType.LayerGUI);
		
		internal_connectTo(application);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontConsoleClient} that will connect to the main application on the given port.
	 * 
	 * @param port
	 * @throws ArgumentIsOutOfRangeException if the given port is not in [0,65535].
	 */
	public FrontConsoleClient(final int port) {
		
		super(BaseFrontGUIClientGUIType.LayerGUI);
		
		internal_connectTo(port);
	}
	
	//constructor
	/**
	 * Creates a new {@link FrontConsoleClient} that will connect to the given application on the server.
	 * 
	 * @param server
	 * @param application
	 * @throws ArgumentIsNullException if the given application is null.
	 * @throws BlankArgumentException if the given application is blank.
	 */
	public FrontConsoleClient(final Server server, final String application) {
		
		super(BaseFrontGUIClientGUIType.LayerGUI);
		
		internal_connectTo(server, application);
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
	 * @throws BlankArgumentException if the given application is blank.
	 */
	public FrontConsoleClient(final String ip, final int port, final String application) {
		
		super(BaseFrontGUIClientGUIType.LayerGUI);
		
		internal_connectTo(ip, port, application);
	}
}
