//package declaration
package ch.nolix.system.client;

//own import
import ch.nolix.core.duplexController.DuplexController;

//class
/**
 * A net server is a server that listens to net clients on a specific port.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 40
 */
public final class NetServer extends Server {
	
	//attribute
	private ch.nolix.core.duplexController.NetServer internalNetServer;
	
	//constructor
	/**
	 * Creates a new net server with the given port.
	 * 
	 * @param port
	 * @throws OutOfRangeArgumentException if the given port is not in [0,65535].
	 */
	public NetServer(final int port) {
		
		//Creates the internal net server of this net server.
		internalNetServer = new ch.nolix.core.duplexController.NetServer(port);
		
		//Creates a close dependency between this net server and its internal net server.
		createCloseDependency(internalNetServer);
		
		internalNetServer
		.addArbitraryDuplexControllerTaker(new NetServerSubDuplexControllerTaker(this));
	}
	
	//constructor
	/**
	 * Creates a new net server with the given port and applications.
	 * 
	 * @param port
	 * @param applications
	 * @throws OutOfRangeArgumentException if the given port is not in [0,65535].
	 */
	public NetServer(final int port, final Application<?> applications) {
		
		//Calls other constructor.
		this(port);
		
		addApplication(applications);
	}
	
	//method
	/**
	 * @return the port of this net server.
	 */
	public int getPort() {
		return internalNetServer.getPort();
	}
	
	void takeDuplexController(final DuplexController duplexController) {
		
		if (!hasArbitraryApplication()) {
			getRefApplicationByName(duplexController.getTarget())
			.takeDuplexController(duplexController);
		}
		
		else {
			getRefArbitraryApplication().takeDuplexController(duplexController);
		}
	}
}
