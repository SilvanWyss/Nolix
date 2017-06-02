//package declaration
package ch.nolix.system.client;

//own imports
import ch.nolix.core.controllerInterfaces.ILevel2Controller;
import ch.nolix.core.specification.Statement;
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * A client receiver controller is a receiver controller of the duplex controller of a client.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 50
 */
final class ClientReceiverController implements ILevel2Controller {

	//attribute
	private final Client<?> client;
	
	//constructor
	/**
	 * Creates new receiver controller with the given client.
	 * 
	 * @param client
	 * @throws NullArgumentException if the given client is null.
	 */
	public ClientReceiverController(final Client<?> client) {
		
		//Checks if the given client is not null.
		Validator.supposeThat(client).thatIsInstanceOf(Client.class).isNotNull();
		
		//Sets the client of this client receiver controller.
		this.client = client;
	}
	
	//method
	/**
	 * @param request
	 * @return the data the given request requests.
	 * @throws InvalidArgumentException if the given request is not valid.
	 */
	public Object getRawData(final Statement request) {
		return client.internal_getData(request);
	}
	
	//method
	/**
	 * Lets this client receiver controller run the given command.
	 * 
	 * @param command
	 * @throws InvalidArgumentException if the given command is not valid.
	 */
	public void run(Statement command) {
		client.internal_run(command);
	}
}
