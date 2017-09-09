//package declaration
package ch.nolix.core.duplexController;

//class
/**
 * A net server is a server that listens to net duplex controllers on a specific port.
 * 
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 80
 */
public final class NetServer extends Server {
	
	//attribute
	private ch.nolix.core.endPoint3.NetServer internalNetServer;
	
	//constructor
	/**
	 * Creates new net server with the given port.
	 * 
	 * @param port
	 */
	public NetServer(final int port) {
		
		//Creates the internal server of this net server.
		internalNetServer = new ch.nolix.core.endPoint3.NetServer(port);
		
		//Creates an abort dependency between this net server and its internal net server.
		createAbortDependency(internalNetServer);
		
		internalNetServer.addDefaultEndPointTaker(new EndPointTaker(this));
	}
	
	public int getPort() {
		return internalNetServer.getPort();
	}
}
