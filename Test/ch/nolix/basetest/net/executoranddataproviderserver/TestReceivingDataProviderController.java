/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.executoranddataproviderserver;

import ch.nolix.base.datastructure.extendediterableview.ExtendedIterableView;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.chainednode.ChainedNode;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.net.executoranddataproviderserver.ExecutorAndDataProvider;

/**
 * @author Silvan Wyss
 */
public final class TestReceivingDataProviderController implements ExecutorAndDataProvider {
  private ChainedNode latestReceivedCommand;

  private ChainedNode latestReceivedRequest;

  @Override
  public Node<?> getDataForRequest(final ChainedNode request) {
    latestReceivedRequest = request;

    return ImmutableNode.withHeader("test_data");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends Node<?>> getDataForRequests(final ChainedNode... requests) {
    return getDataForRequests(ImmutableList.fromArray(requests));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends Node<?>> getDataForRequests(final Iterable<? extends ChainedNode> requests) {
    return ExtendedIterableView.forIterable(requests).to(this::getDataForRequest);
  }

  public ChainedNode getLatestReceivedCommand() {
    return latestReceivedCommand;
  }

  public ChainedNode getLatestReceivedRequest() {
    return latestReceivedRequest;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void runCommand(final ChainedNode command) {
    latestReceivedCommand = command;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void runCommands(final ChainedNode... commands) {
    final var commandsList = ImmutableList.fromArray(commands);

    runCommands(commandsList);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void runCommands(Iterable<? extends ChainedNode> commands) {
    commands.forEach(this::runCommand);
  }
}
