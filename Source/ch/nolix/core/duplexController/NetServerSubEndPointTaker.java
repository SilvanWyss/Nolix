//package declaration
package ch.nolix.core.duplexController;

//own imports
import ch.nolix.core.endPoint3.EndPoint;
import ch.nolix.core.endPoint3.IEndPointTaker;
import ch.nolix.core.endPoint3.NetEndPoint;
import ch.nolix.primitive.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2016-10
 * @lines 60
 */
final class NetServerSubEndPointTaker implements IEndPointTaker {

	//name
	private final static String NAME = "IntenralEndPointTaker";
	
	//attribute
	private final NetServer netServer;
	
	//constructor
	/**
	 * Creates new net server sub end point taker with the given duplex contorller taker.
	 * 
	 * @param duplexControllerTaker
	 * @throws NullArgumentException
	 * if the given net server is null.
	 */
	public NetServerSubEndPointTaker(final NetServer netServer) {
		
		//Checks if the given net server is not null.
		Validator
		.suppose(netServer)
		.thatIsInstanceOf(NetServer.class)
		.isNotNull();
		
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
	 * @throws NullArgumentException if the given end point is null.
	 * @throws InvalidArgumentException if the given end point is no NetEndPoint.
	 */
	public void takeEndPoint(final EndPoint endPoint) {		
		netServer.takeDuplexController(
			new NetDuplexController((NetEndPoint)endPoint)
		);
	}
}
