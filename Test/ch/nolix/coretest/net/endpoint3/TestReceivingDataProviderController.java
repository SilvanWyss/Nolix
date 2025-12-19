package ch.nolix.coretest.net.endpoint3;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.chainednode.IChainedNode;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.net.endpoint3.IDataProviderController;

/**
 * @author Silvan Wyss
 */
public final class TestReceivingDataProviderController implements IDataProviderController {
  private IChainedNode latestReceivedCommand;

  private IChainedNode latestReceivedRequest;

  @Override
  public INode<?> getDataForRequest(final IChainedNode request) {
    latestReceivedRequest = request;

    return Node.withHeader("test_data");
  }

  @Override
  public IContainer<? extends INode<?>> getDataForRequests(final IChainedNode... requests) {
    return getDataForRequests(ImmutableList.fromArray(requests));
  }

  @Override
  public IContainer<? extends INode<?>> getDataForRequests(final Iterable<? extends IChainedNode> requests) {
    return ContainerView.forIterable(requests).to(this::getDataForRequest);
  }

  public IChainedNode getLatestReceivedCommand() {
    return latestReceivedCommand;
  }

  public IChainedNode getLatestReceivedRequest() {
    return latestReceivedRequest;
  }

  @Override
  public void runCommand(final IChainedNode command) {
    latestReceivedCommand = command;
  }

  @Override
  public void runCommands(final IChainedNode... commands) {
    final var commandsList = ImmutableList.fromArray(commands);

    runCommands(commandsList);
  }

  @Override
  public void runCommands(Iterable<? extends IChainedNode> commands) {
    commands.forEach(this::runCommand);
  }
}
