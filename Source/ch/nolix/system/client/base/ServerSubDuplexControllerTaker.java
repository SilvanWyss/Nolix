//package declaration
package ch.nolix.system.client.base;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.net.endpoint3.EndPoint;
import ch.nolix.common.net.endpoint3.IEndPointTaker;

//class
/**
 * @author Silvan Wyss
 * @date 2016-11-01
 * @lines 50
 */
final class ServerSubDuplexControllerTaker implements IEndPointTaker {
	
	//name
	private static final String NAME = "InternalDuplexControllerTaker";
	
	//attribute
	private final Server server;
	
	//constructor
	/**
	 * Creates a new net server sub duplex controller taker with the given net server.
	 * 
	 * @param server
	 * @throws ArgumentIsNullException if the given net server is null.
	 */
	public ServerSubDuplexControllerTaker(final Server server) {
		
		//Asserts that the given server is not null.
		Validator.assertThat(server).isOfType(Server.class);
		
		//Sets the net server of this snet server sub duplex controller taker.
		this.server = server;
	}

	//method
	/**
	 * @return the name of this net server sub duplex controller taker.
	 */
	@Override
	public String getName() {
		return NAME;
	}
	
	//method
	/**
	 * Lets this net server sub duplex controller taker take the given duplex controller.
	 * 
	 * @param endPoint
	 */
	@Override
	public void takeEndPoint(final EndPoint endPoint) {
		server.takeEndPoint(endPoint);
	}
}
