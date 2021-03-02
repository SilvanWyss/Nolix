//package declaration
package ch.nolix.common.net.endpoint3;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 60
 */
final class NetServerListener implements ch.nolix.common.net.endpoint2.IEndPointTaker {

	//name
	private static final String NAME = "IntenralEndPointTaker";
	
	//attribute
	private final NetServer netServer;
	
	//constructor
	/**
	 * Creates a new net server sub end point taker with the given duplex contorller taker.
	 * 
	 * @param netServer 
	 * @throws ArgumentIsNullException if the given netServer is null.
	 */
	public NetServerListener(final NetServer netServer) {
		
		//Asserts that the given net server is not null.
		Validator.assertThat(netServer).isOfType(NetServer.class);
		
		//Sets the net server of htis net server sub end point taker.
		this.netServer = netServer;
	}
	
	//method
	/**
	 * @return the name of this net server sub end point taker.
	 */
	@Override
	public String getName() {
		return NAME;
	}
	
	//method
	/**
	 * Lets this net server sub end point taker take the given end point.
	 * 
	 * @param endPoint
	 * @throws ArgumentIsNullException if the given end point is null.
	 * @throws InvalidArgumentException if the given end point is not a NetEndPoint.
	 */
	@Override
	public void takeEndPoint(final ch.nolix.common.net.endpoint2.EndPoint endPoint) {
		netServer.takeEndPoint(
			new NetEndPoint((ch.nolix.common.net.endpoint2.NetEndPoint)endPoint)
		);
	}
}
