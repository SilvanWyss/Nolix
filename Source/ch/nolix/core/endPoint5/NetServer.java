//package declaration
package ch.nolix.core.endPoint5;

//class
/**
 * A {@link NetServer} is a {@link Server}
 * that listens to {@link NetEndPoint} on a specific port.
 * 
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 60
 */
public final class NetServer extends Server {
	
	//attribute
	private ch.nolix.core.endPoint3.NetServer internalNetServer;
	
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
		internalNetServer = new ch.nolix.core.endPoint3.NetServer(port);
		
		//Creates a close dependency to the internal net server of the current net server.
		createCloseDependency(internalNetServer);
		
		internalNetServer.addMainEndPointTaker(new NetServerListener(this));
	}
	
	//constructor
	/**
	 * Creates a new {@link NetServer}
	 * that will listen to {@link NetEndPoint} on the given port.
	 * 
	 * When a web browser connects to the {@link NetServer},
	 * the {@link NetServer} will send the given HTTP message and close the connection.
	 * 
	 * @param port
	 * @param HTTPMessage
	 * @throws OutOfRangeArgumentException if the given port is not in [0,65535].
	 * @throws NullArgumentException if the given HTTP message is null.
	 * @throws EmptyArgumentException if the given HTTP message is empty.
	 */
	public NetServer(final int port, final String HTTPMessage) {
		
		//Creates the internal net server of the current net server.
		internalNetServer = new ch.nolix.core.endPoint3.NetServer(port, HTTPMessage);
		
		//Creates a close dependency to the internal net server of the current net server.
		createCloseDependency(internalNetServer);
		
		internalNetServer.addMainEndPointTaker(new NetServerListener(this));
	}
	
	//method
	/**
	 * @return the port of the current {@link NetServer}.
	 */
	public int getPort() {
		return internalNetServer.getPort();
	}
}
