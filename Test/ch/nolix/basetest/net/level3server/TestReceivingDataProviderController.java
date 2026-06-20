/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.level3server;

import ch.nolix.base.container.containerview.ContainerView;
import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.document.chainednode.IChainedNode;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.net.level3server.IDataProviderController;

/**
 * @author Silvan Wyss
 */
public final class TestReceivingDataProviderController implements IDataProviderController {
  private IChainedNode latestReceivedCommand;

  private IChainedNode latestReceivedRequest;

  @Override
  public INode<?> getDataForRequest(final IChainedNode request) {
    latestReceivedRequest = request;

    return ImmutableNode.withHeader("test_data");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IWellOrderContainer<? extends INode<?>> getDataForRequests(final IChainedNode... requests) {
    return getDataForRequests(ImmutableList.fromArray(requests));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IWellOrderContainer<? extends INode<?>> getDataForRequests(final Iterable<? extends IChainedNode> requests) {
    return ContainerView.forIterable(requests).to(this::getDataForRequest);
  }

  public IChainedNode getLatestReceivedCommand() {
    return latestReceivedCommand;
  }

  public IChainedNode getLatestReceivedRequest() {
    return latestReceivedRequest;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void runCommand(final IChainedNode command) {
    latestReceivedCommand = command;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void runCommands(final IChainedNode... commands) {
    final var commandsList = ImmutableList.fromArray(commands);

    runCommands(commandsList);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void runCommands(Iterable<? extends IChainedNode> commands) {
    commands.forEach(this::runCommand);
  }
}
