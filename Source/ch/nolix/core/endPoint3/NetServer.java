//package declaration
package ch.nolix.core.endPoint3;

//class
/**
* A {@link NetServer} is a {@link Server}
* that listens to {@link NetEndPoint} on a specific port.
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
	 * Creates a new {@link NetServer}
	 * that will listen to {@link NetEndPoint} on the given port.
	 * 
	 * @param port
	 * @throws OutOfRangeArgumentException if the given port is not in [0,65535].
	 */
	public NetServer(final int port) {
		
		//Creates the internal net server of the current net server.
		internalNetServer =	new ch.nolix.core.endPoint2.NetServer(port);
		
		//Creates a close dependency to the internal net server of the current net server.
		createCloseDependency(internalNetServer);
		
		internalNetServer.addArbitraryEndPointTaker(new NetServerSubEndPointTaker(this));
	}
	
	//method
	/**
	 * @return the port of the current {@link NetServer}.
	 */
	public final int getPort() {
		return internalNetServer.getPort();
	}
}
