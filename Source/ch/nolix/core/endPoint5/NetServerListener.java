//package declaration
package ch.nolix.core.endPoint5;

import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 60
 */
final class NetServerListener implements ch.nolix.core.endPoint3.IEndPointTaker {

	//name
	private final static String NAME = "IntenralEndPointTaker";
	
	//attribute
	private final NetServer netServer;
	
	//constructor
	/**
	 * Creates a new net server sub end point taker with the given duplex contorller taker.
	 * 
	 * @param duplexControllerTaker
	 * @throws NullArgumentException
	 * if the given net server is not an instance.
	 */
	public NetServerListener(final NetServer netServer) {
		
		//Checks if the given net server is an instance.
		Validator.suppose(netServer).isInstanceOf(NetServer.class);
		
		//Sets the net server of htis net server sub end point taker.
		this.netServer = netServer;
	}
	
	//method
	/**
	 * @return the name of this net server sub end point taker.
	 */
	public String getName() {
		return NAME;
	}
	
	//method
	/**
	 * Lets this net server sub end point taker take the given end point.
	 * 
	 * @param endPoint
	 * @throws NullArgumentException if the given end point is not an instance.
	 * @throws InvalidArgumentException if the given end point is no NetEndPoint.
	 */
	public void takeEndPoint(final ch.nolix.core.endPoint3.EndPoint endPoint) {		
		netServer.takeDuplexController(
			new NetEndPoint((ch.nolix.core.endPoint3.NetEndPoint)endPoint)
		);
	}
}
