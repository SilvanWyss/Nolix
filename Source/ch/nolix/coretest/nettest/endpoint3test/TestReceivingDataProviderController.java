//package declaration
package ch.nolix.coretest.nettest.endpoint3test;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.netapi.endpoint3api.IDataProviderController;

//class
public final class TestReceivingDataProviderController implements IDataProviderController {

  // optional attribute
  private IChainedNode latestReceivedCommand;

  // optional attribute
  private IChainedNode latestReceivedRequest;

  // method
  @Override
  public INode<?> getDataForRequest(final IChainedNode request) {

    latestReceivedRequest = request;

    return Node.withHeader("test_data");
  }

  // method
  @Override
  public IContainer<? extends INode<?>> getDataForRequests(
      final IChainedNode request,
      final IChainedNode... requests) {
    return getDataForRequests(ImmutableList.withElement(request, requests));
  }

  // method
  @Override
  public IContainer<? extends INode<?>> getDataForRequests(final Iterable<? extends IChainedNode> requests) {
    return ReadContainer.forIterable(requests).to(this::getDataForRequest);
  }

  // method
  public IChainedNode getLatestReceivedCommand() {
    return latestReceivedCommand;
  }

  // method
  public IChainedNode getLatestReceivedRequest() {
    return latestReceivedRequest;
  }

  // method
  @Override
  public void runCommand(final IChainedNode command) {
    latestReceivedCommand = command;
  }

  // method
  @Override
  public void runCommands(final IChainedNode command, final IChainedNode... commands) {
    runCommands(ImmutableList.withElement(command, commands));
  }

  // method
  @Override
  public void runCommands(Iterable<? extends IChainedNode> commands) {
    commands.forEach(this::runCommand);
  }
}
