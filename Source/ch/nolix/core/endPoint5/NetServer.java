//package declaration
package ch.nolix.core.endPoint5;

//class
/**
 * A net server is a server that listens to net duplex controllers on a specific port.
 * 
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 40
 */
public final class NetServer extends Server {
	
	//attribute
	private ch.nolix.core.endPoint3.NetServer internalNetServer;
	
	//constructor
	/**
	 * Creates a new net server with the given port.
	 * 
	 * @param port
	 * @throws OutOfRangeArgumentException if the given port is not in [0,65535].
	 */
	public NetServer(final int port) {
		
		//Creates the internal net server of this net server.
		internalNetServer = new ch.nolix.core.endPoint3.NetServer(port);
		
		//Creates a close dependency between this net server and its internal net server.
		createCloseDependency(internalNetServer);
		
		internalNetServer.addArbitraryEndPointTaker(new NetServerListener(this));
	}
	
	//method
	/**
	 * @return the port of this net server.
	 */
	public int getPort() {
		return internalNetServer.getPort();
	}
}
