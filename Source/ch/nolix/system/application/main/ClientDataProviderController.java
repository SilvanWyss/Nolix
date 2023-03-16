//package declaration
package ch.nolix.system.application.main;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.controlleruniversalapi.IDataProviderController;
import ch.nolix.core.net.endpoint3.EndPoint;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

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
	public INode<?> getDataForRequest(final IChainedNode request) {
		return parentClient.getDataFromHere(request);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void runCommand(final IChainedNode command) {
		parentClient.runHere(command);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void runCommands(final IChainedNode command, final IChainedNode... commands) {
		
		parentClient.runHere(command);
		
		//Iterates the given commands.
		for (final var c : commands) {
			parentClient.runHere(c);
		}
	}
}
