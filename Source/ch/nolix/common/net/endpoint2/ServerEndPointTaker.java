//package declaration
package ch.nolix.common.net.endpoint2;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
/**
* @author Silvan Wyss
* @date 2016-06-01
* @lines 50
*/
final class ServerEndPointTaker implements ch.nolix.common.net.endpoint.IEndPointTaker {
	
	//attributes
	private final String name;
	private final Server parentServer;
	
	//constructor
	/**
	 * Creates a new {@NetServerEndPointTaker} that will belong to the given parentServer.
	 * 
	 * @param name
	 * @param parentServer
	 * @throws ArgumentIsNullException if given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given parentServer is null.
	 */
	public ServerEndPointTaker(final String name, final Server parentServer) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		Validator.assertThat(parentServer).thatIsNamed("parent server").isNotNull();
		
		this.parentServer = parentServer;
		this.name = name;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void takeEndPoint(final ch.nolix.common.net.endpoint.EndPoint endPoint) {
		parentServer.takeEndPoint(new NetEndPoint(endPoint));
	}
}
