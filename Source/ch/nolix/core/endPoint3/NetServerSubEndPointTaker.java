//package declaration
package ch.nolix.core.endPoint3;

//own imports
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
* @author Silvan Wyss
* @month 2016-05
* @lines 50
*/
final class NetServerSubEndPointTaker
implements ch.nolix.core.endPoint2.IEndPointTaker {

	//name
	private static final String NAME = "InternalEndPointTaker";
	
	//attribute
	private final NetServer netServer;
	
	//constructor
	/**
	 * Creates a new net server sub end point taker with the given net server.
	 * 
	 * @param netServer
	 * @throws NullArgumentException if the given net server is null.
	 */
	public NetServerSubEndPointTaker(final NetServer netServer) {
		
		//Checks if the given net server is not null.
		Validator.suppose(netServer).isInstanceOf(NetServer.class);
		
		//Sets the net server of this end point taker.
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
	 */
	@Override
	public void takeEndPoint(
		final ch.nolix.core.endPoint2.EndPoint endPoint
	) {
		netServer.takeEndPoint(new NetEndPoint(endPoint));
	}
}
