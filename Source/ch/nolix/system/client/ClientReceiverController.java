//package declaration
package ch.nolix.system.client;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.controllerAPI.IDataProviderController;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//class
/**
 * A client receiver controller is a receiver controller of the duplex controller of a client.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 50
 */
final class ClientReceiverController implements IDataProviderController {

	//attribute
	private final Client<?> client;
	
	//constructor
	/**
	 * Creates a new receiver controller with the given client.
	 * 
	 * @param client
	 * @throws ArgumentIsNullException if the given client is null.
	 */
	public ClientReceiverController(final Client<?> client) {
		
		//Asserts that the given client is not null.
		Validator.assertThat(client).isOfType(Client.class);
		
		//Sets the client of this client receiver controller.
		this.client = client;
	}
	
	//method
	/**
	 * @param request
	 * @return the data the given request requests.
	 * @throws InvalidArgumentException if the given request is not valid.
	 */
	@Override
	public Node getData(final ChainedNode request) {
		return client.internal_getData(request);
	}
	
	//method
	/**
	 * Lets this client receiver controller run the given command.
	 * 
	 * @param command
	 * @throws InvalidArgumentException if the given command is not valid.
	 */
	@Override
	public void run(ChainedNode command) {
		client.internal_run(command);
	}
}
