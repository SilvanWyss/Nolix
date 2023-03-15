//package declaration
package ch.nolix.system.application.main;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.controlleruniversalapi.IDataProviderController;
import ch.nolix.core.net.endpoint3.EndPoint;

//class
/**
 * A {@link ClientDataProviderController} is
 * a {@link IDataProviderController} for the {@link EndPoint} of a {@link Client}.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
final class ClientDataProviderController implements IDataProviderController {
	
	//attribute
	private final Client<?> parentClient;
	
	//constructor
	/**
	 * Creates a new {@link ClientDataProviderController} with the given parentClient.
	 * 
	 * @param parentClient
	 * @throws ArgumentIsNullException if the given parentClient is null.
	 */
	public ClientDataProviderController(final Client<?> parentClient) {
		
		GlobalValidator.assertThat(parentClient).thatIsNamed("parent client").isNotNull();
		
		this.parentClient = parentClient;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Node getData(final ChainedNode request) {
		return parentClient.getDataFromHere(request);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(final ChainedNode command) {
		parentClient.runHere(command);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(final ChainedNode command, final ChainedNode... commands) {
		
		parentClient.runHere(command);
		
		//Iterates the given commands.
		for (final var c : commands) {
			parentClient.runHere(c);
		}
	}
}
