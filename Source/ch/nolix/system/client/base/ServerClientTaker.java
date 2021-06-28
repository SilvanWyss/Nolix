//package declaration
package ch.nolix.system.client.base;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @date 2021-06-28
 * @lines 50
 */
public class ServerClientTaker implements ch.nolix.common.net.endpoint3.IEndPointTaker {
	
	//attributes
	private final String name;
	private final Server parentServer;
	
	//constructor
	/**
	 * Creates a new {@ServerClientTaker} that will belong to the given parentServer.
	 * 
	 * @param name
	 * @param parentServer
	 * @throws ArgumentIsNullException if given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 * @throws ArgumentIsNullException if the given parentServer is null.
	 */
	public ServerClientTaker(final String name, final Server parentServer) {
		
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
	public void takeEndPoint(final ch.nolix.common.net.endpoint3.EndPoint endPoint) {
		parentServer.takeEndPoint(endPoint);
	}
}
