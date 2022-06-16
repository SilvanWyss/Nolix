//package declaration
package ch.nolix.system.application.main;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.endpoint3.EndPoint;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
/**
 * @author Silvan Wyss
 * @date 2021-06-28
 */
final class ServerEndPointTaker implements ch.nolix.core.net.endpoint3.IEndPointTaker {
	
	//attribute
	private final String name;
	
	//attribute
	private final Server parentServer;
	
	//constructor
	/**
	 * Creates a new {@ServerClientTaker} with the given name that will belong to the given parentServer.
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
	public void takeEndPoint(final EndPoint endPoint) {
		parentServer.internalTakeEndPoint(endPoint);
	}
}
