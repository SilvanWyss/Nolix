//package declaration
package ch.nolix.core.endPoint3;

//class
/**
* A net server is a server that listens to net end points on a specific port.
* 
* @author Silvan Wyss
* @month 2016-05
* @lines 30
*/
public final class NetServer extends Server {
	
	//constructor
	/**
	 * Creates new net server that will listen to net end points on the given port.
	 * 
	 * @param port
	 * @throws OutOfRangeArgumentException if the given port is not in [0, 65535].
	 */
	public NetServer(final int port) {
		
		//Calls constructor of the base class.
		super(new ch.nolix.core.endPoint2.NetServer(port));	
	}
	
	//method
	/**
	 * @return the port of this net server.
	 */
	public final int getPort() {
		return ((ch.nolix.core.endPoint2.NetServer)getRefInternalServer()).getPort();
	}
}
