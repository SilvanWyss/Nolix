//package declaration
package ch.nolix.common.endPoint3;

//own imports
import ch.nolix.common.validator.Validator;

//class
/**
* @author Silvan Wyss
* @month 2016-05
* @lines 50
*/
final class NetServerEndPointTaker implements ch.nolix.common.endPoint2.IEndPointTaker {
	
	//constant
	private static final String NAME = "NetServerEndPointTaker";
	
	//attribute
	private final NetServer parentNetServer;
	
	//constructor
	/**
	 * Creates a new {@NetServerEndPointTaker} that will belong to the given parentNetServer.
	 * 
	 * @param parentNetServer
	 * @throws ArgumentIsNullException if the given parentNetServer is null.
	 */
	public NetServerEndPointTaker(final NetServer parentNetServer) {
		
		//Checks if the given parentNetServer is not null.
		Validator.suppose(parentNetServer).isOfType(NetServer.class);
		
		//Sets the parentNetServer of the current {@NetServerEndPointTaker}.
		this.parentNetServer = parentNetServer;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return NAME;
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void takeEndPoint(final ch.nolix.common.endPoint2.EndPoint endPoint) {
		parentNetServer.takeEndPoint(new NetEndPoint(endPoint));
	}
}
