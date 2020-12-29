//package declaration
package ch.nolix.system.client;

//own imports
import ch.nolix.common.chainednode.ChainedNode;
import ch.nolix.common.controllerapi.IDataProviderController;
import ch.nolix.common.endpoint5.EndPoint;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link ClientReceiverController} is a {@link IDataProviderController}for the {@link EndPoint }of a {@link Client}.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 60
 */
final class ClientReceiverController implements IDataProviderController {

	//attribute
	private final Client<?> client;
	
	//constructor
	/**
	 * Creates a new {@link ClientReceiverController} with the given client.
	 * 
	 * @param client
	 * @throws ArgumentIsNullException if the given client is null.
	 */
	public ClientReceiverController(final Client<?> client) {
		
		//Asserts that the given client is not null.
		Validator.assertThat(client).isOfType(Client.class);
		
		//Sets the client of the current ClientReceiverController.
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
		return client.internalGetData(request);
	}
	
	//method
	/**
	 * Lets this client {@link ClientReceiverController} run the given command.
	 * 
	 * @param command
	 * @throws InvalidArgumentException if the given command is not valid.
	 */
	@Override
	public void run(ChainedNode command) {
		client.internalRun(command);
	}
}
