/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.application.main;

import ch.nolix.base.datastructure.extendediterableview.ExtendedIterableView;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.net.level3server.AbstractEndPoint;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.chainednode.IChainedNode;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.net.level3server.IDataProviderController;

/**
 * A {@link ClientDataProviderController} is a {@link IDataProviderController}
 * for the {@link AbstractEndPoint} of a {@link AbstractClient}.
 * 
 * @author Silvan Wyss
 */
final class ClientDataProviderController implements IDataProviderController {
  private final AbstractClient<?> parentClient;

  /**
   * Creates a new {@link ClientDataProviderController} with the given
   * parentClient.
   * 
   * @param parentClient
   * @throws RuntimeException if the given parentClient is null
   */
  private ClientDataProviderController(final AbstractClient<?> parentClient) {
    Validator.assertThat(parentClient).thatIsNamed("parent client").isNotNull();

    this.parentClient = parentClient;
  }

  /**
   * @param client
   * @return a new {@link ClientDataProviderController} for the given client
   * @throws RuntimeException if the given client is null
   */
  public static ClientDataProviderController forClient(final AbstractClient<?> client) {
    return new ClientDataProviderController(client);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Node<?> getDataForRequest(final IChainedNode request) {
    return parentClient.getDataFromHere(request);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Node<?>> getDataForRequests(final IChainedNode... requests) {
    // Concatenates the given requests.
    final var concatenatedRequests = ImmutableList.withElements(requests);

    // Calls other method.
    return getDataForRequests(concatenatedRequests);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Node<?>> getDataForRequests(final Iterable<? extends IChainedNode> requests) {
    return ExtendedIterableView.forIterable(requests).to(parentClient::getDataFromHere);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void runCommand(final IChainedNode command) {
    parentClient.runHere(command);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void runCommands(final IChainedNode... commands) {
    for (final var c : commands) {
      parentClient.runHere(c);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void runCommands(final Iterable<? extends IChainedNode> commands) {
    for (final var c : commands) {
      parentClient.runHere(c);
    }
  }
}
