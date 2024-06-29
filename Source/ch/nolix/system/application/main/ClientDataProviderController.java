//package declaration
package ch.nolix.system.application.main;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.endpoint3.EndPoint;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.netapi.endpoint3api.IDataProviderController;

//class
/**
 * A {@link ClientDataProviderController} is a {@link IDataProviderController}
 * for the {@link EndPoint} of a {@link Client}.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 */
final class ClientDataProviderController implements IDataProviderController {

  //attribute
  private final Client<?> parentClient;

  //constructor
  /**
   * Creates a new {@link ClientDataProviderController} with the given
   * parentClient.
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
  public IContainer<INode<?>> getDataForRequests(final IChainedNode request, final IChainedNode... requests) {

    //Concatenates the given requests.
    final var concatenatedRequests = ImmutableList.withElement(request, requests);

    //Calls other method.
    return getDataForRequests(concatenatedRequests);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<INode<?>> getDataForRequests(final Iterable<? extends IChainedNode> requests) {
    return ReadContainer.forIterable(requests).to(parentClient::getDataFromHere);
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

    //Concatenates the given commands.
    final var concatenatedCommands = ImmutableList.withElement(command, commands);

    //Calls other method.
    runCommands(concatenatedCommands);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public void runCommands(final Iterable<? extends IChainedNode> commands) {

    //Iterates the given commands.
    for (final var c : commands) {
      parentClient.runHere(c);
    }
  }
}
