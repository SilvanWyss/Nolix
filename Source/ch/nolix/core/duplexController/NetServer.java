//package declaration
package ch.nolix.core.duplexController;

//own import
import ch.nolix.core.endPoint3.NetEndPoint;

//class
/**
 * A net server is a server that listens to net duplex controllers on a specific port.
 * 
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 10
 */
public final class NetServer extends Server {
	
	//attribute
	private ch.nolix.core.endPoint3.NetServer internalServer;
	
	//constructor
	public NetServer(final int port) {
		
		//Creates the internal server of this net server.
		internalServer = new ch.nolix.core.endPoint3.NetServer(port);
		
		createAbortDependency(internalServer);
	}

	//package-visible method
	/**
	 * Lets this net duplex controller listener take the given alpha end point
	 * 
	 * @param alphaEndPoint
	 * @throws Exception if the given alpha end point is null
	 */
	void takeNetEndPoint(final NetEndPoint netEndPoint) {
		takeDuplexController(new NetDuplexController(netEndPoint));
	}
}
