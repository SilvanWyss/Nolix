//package declaration
package ch.nolix.system.client;

import ch.nolix.core.endPoint5.EndPoint;
import ch.nolix.core.endPoint5.IEndPointTaker;
import ch.nolix.primitive.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 50
 */
final class NetServerSubDuplexControllerTaker implements IEndPointTaker {
	
	//name
	private static final String NAME = "InternalDuplexControllerTaker";
	
	//attribute
	private final NetServer netServer;
	
	//constructor
	/**
	 * Creates a new net server sub duplex controller taker with the given net server.
	 * 
	 * @param netServer
	 * @throws NullArgumentException if the given net server is not an instance.
	 */
	public NetServerSubDuplexControllerTaker(final NetServer netServer) {
		
		//Checks if the given server is an instance.
		Validator.suppose(netServer).isInstanceOf(NetServer.class);
		
		//Sets the net server of this snet server sub duplex controller taker.
		this.netServer = netServer;
	}

	//method
	/**
	 * @return the name of this net server sub duplex controller taker.
	 */
	public String getName() {
		return NAME;
	}
	
	//method
	/**
	 * Lets this net server sub duplex controller taker take the given duplex controller.
	 * 
	 * @param endPoint
	 */
	public void takeDuplexController(final EndPoint endPoint) {	
		netServer.takeDuplexController(endPoint);
	}
}
