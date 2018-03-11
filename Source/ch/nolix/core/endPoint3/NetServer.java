//package declaration
package ch.nolix.core.endPoint3;

//class
/**
* A net server is a server that listens to net end points on a specific port.
* 
* @author Silvan Wyss
* @month 2016-05
* @lines 40
*/
public final class NetServer extends Server {
	
	//attribute
	private final ch.nolix.core.endPoint2.NetServer internalNetServer;
	
	//constructor
	/**
	 * Creates a new net server that will listen to net end points on the given port.
	 * 
	 * @param port
	 * @throws OutOfRangeArgumentException if the given port is not in [0,65535].
	 */
	public NetServer(final int port) {
		
		//Creates the internal net server of this net server.
		internalNetServer =	new ch.nolix.core.endPoint2.NetServer(port);
		
		createCloseDependency(internalNetServer);
		
		internalNetServer.addArbitraryEndPointTaker(new NetServerSubEndPointTaker(this));
	}
	
	//method
	/**
	 * @return the port of this net server.
	 */
	public final int getPort() {
		return internalNetServer.getPort();
	}
	
	public void takeEndPoint(final ch.nolix.core.endPoint2.EndPoint endPoint) {
		
	}
}
