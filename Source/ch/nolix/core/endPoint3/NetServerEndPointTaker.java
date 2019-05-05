//package declaration
package ch.nolix.core.endPoint3;

//own import
import ch.nolix.core.validator.Validator;

//package-visible class
/**
* @author Silvan Wyss
* @month 2016-05
* @lines 50
*/
final class NetServerEndPointTaker implements ch.nolix.core.endPoint2.IEndPointTaker {
	
	//constant
	private static final String NAME = "NetServerEndPointTaker";
	
	//attribute
	private final NetServer parentNetServer;
	
	//constructor
	/**
	 * Creates a new {@NetServerEndPointTaker} that will belong to the given parentNetServer.
	 * 
	 * @param parentNetServer
	 * @throws NullArgumentException if the given parentNetServer is null.
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
	public void takeEndPoint(final ch.nolix.core.endPoint2.EndPoint endPoint) {
		parentNetServer.takeEndPoint(new NetEndPoint(endPoint));
	}
}
