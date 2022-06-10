//package declaration
package ch.nolix.core.net.endpoint3;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
/**
 * @author Silvan Wyss
 * @date 2021-06-28
 */
final class ServerEndPointTaker implements ch.nolix.core.net.endpoint2.IEndPointTaker {
	
	//attributes
	private final String name;
	private final Server parentServer;
	
	//constructor
	/**
	 * Creates a new {@ServerEndPointTaker} that will belong to the given parentServer.
	 * 
	 * @param name
	 * @param parentServer
	 * @throws ArgumentIsNullException if given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given parentServer is null.
	 */
	public ServerEndPointTaker(final String name, final Server parentServer) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		GlobalValidator.assertThat(parentServer).thatIsNamed("parent server").isNotNull();
		
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
	public void takeEndPoint(final ch.nolix.core.net.endpoint2.EndPoint endPoint) {
		parentServer.takeEndPoint(new NetEndPoint(endPoint));
	}
}
