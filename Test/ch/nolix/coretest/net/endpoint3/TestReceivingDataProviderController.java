package ch.nolix.coretest.net.endpoint3;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.netapi.endpoint3api.IDataProviderController;

public final class TestReceivingDataProviderController implements IDataProviderController {

  private IChainedNode latestReceivedCommand;

  private IChainedNode latestReceivedRequest;

  @Override
  public INode<?> getDataForRequest(final IChainedNode request) {

    latestReceivedRequest = request;

    return Node.withHeader("test_data");
  }

  @Override
  public IContainer<? extends INode<?>> getDataForRequests(
    final IChainedNode request,
    final IChainedNode... requests) {
    return getDataForRequests(ImmutableList.withElement(request, requests));
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
  public void runCommands(final IChainedNode command, final IChainedNode... commands) {
    runCommands(ImmutableList.withElement(command, commands));
  }

  @Override
  public void runCommands(Iterable<? extends IChainedNode> commands) {
    commands.forEach(this::runCommand);
  }
}
