/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.sessionserver;

import ch.nolix.base.document.chainednode.ChainedNode;
import ch.nolix.base.net.executoranddataproviderserver.AbstractEndPoint;
import ch.nolix.base.resourcecontrol.closecontroller.CloseController;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.document.chainednode.IChainedNode;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.UnconnectedArgumentException;
import ch.nolix.baseapi.net.executoranddataproviderserver.IEndPoint;
import ch.nolix.baseapi.net.netproperty.SecurityMode;
import ch.nolix.baseapi.net.sessionserver.Client;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;

/**
 * @author Silvan Wyss
 * @param <C> the type of a {@link AbstractClient}.
 */
public abstract class AbstractClient<C extends AbstractClient<C>> implements Client {
  private final ICloseController closeController = CloseController.forElement(this);

  private IEndPoint nullableEndPoint;

  /**
   * {@inheritDoc}
   */
  @Override
  public final SecurityMode getSecurityMode() {
    return getStoredEndPoint().getSecurityMode();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ICloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getUrlInstanceNameOfTargetApplication() {
    return getStoredEndPoint().getCustomTargetSlot();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasRequestedConnection() {
    return getStoredEndPoint().isFrontendEndPoint();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasUrlInstanceNameOfTargetApplication() {
    return getStoredEndPoint().hasCustomTargetSlot();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isClosed() {
    // The end point of the current Client can be requested only when the current Client is connected.
    return //
    isConnected()
    && getStoredEndPoint().isClosed();
  }

  /**
   * @param request
   * @return the data the given request requests from the counterpart of the
   *         current {@link AbstractClient}
   * @throws RuntimeException if the current {@link AbstractClient} is not
   *                          connected.
   */
  protected final Node<?> getDataFromCounterpart(final IChainedNode request) {
    return getStoredEndPoint().getDataForRequest(request);
  }

  /**
   * @param request
   * @return the data the given request requests
   */
  protected abstract Node<?> provideData(IChainedNode request);

  /**
   * Runs the given command.
   * 
   * @param command
   */
  protected abstract void provideRun(IChainedNode command);

  /**
   * Runs the given command on the counterpart of the current
   * {@link AbstractClient}.
   * 
   * @param command
   * @throws RuntimeException if the current {@link AbstractClient} is not
   *                          connected.
   */
  protected final void runOnCounterpart(final IChainedNode command) {
    getStoredEndPoint().runCommand(command);
  }

  /**
   * Runs the given commands on the counterpart of the current
   * {@link AbstractClient}.
   * 
   * @param commands
   * @throws RuntimeException if the current {@link AbstractClient} is not
   *                          connected.
   */
  protected final void runOnCounterpart(final ChainedNode... commands) {
    getStoredEndPoint().runCommands(commands);
  }

  /**
   * Runs the given commands on the counterpart of the current
   * {@link AbstractClient}.
   * 
   * @param commands
   * @throws RuntimeException if the current {@link AbstractClient} is not
   *                          connected.
   */
  protected final void runOnCounterpart(final Iterable<? extends IChainedNode> commands) {
    getStoredEndPoint().runCommands(commands);
  }

  /**
   * Sets the {@link AbstractEndPoint} of the current {@link AbstractClient}.
   * 
   * @param endPoint
   * @throws RuntimeException if the given endPoint is null
   * @throws RuntimeException if the current {@link AbstractClient} is already
   *                          connected.
   */
  final void setEndPoint(final IEndPoint endPoint) {
    Validator.assertThat(endPoint).thatIsNamed(AbstractEndPoint.class).isNotNull();

    assertIsNotConnected();

    this.nullableEndPoint = endPoint;

    createCloseDependencyTo(endPoint);

    final var clientDataProviderController = ClientDataProviderController.forClient(this);

    endPoint.setExecutorAndDataProvider(clientDataProviderController);
  }

  /**
   * @throws RuntimeException if the current {@link AbstractClient} is not
   *                          connected.
   */
  private void assertIsConnected() {
    if (!isConnected()) {
      throw UnconnectedArgumentException.forArgument(this);
    }
  }

  /**
   * @throws RuntimeException if the current {@link AbstractClient} is already
   *                          connected.
   */
  private void assertIsNotConnected() {
    if (isConnected()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is already connected");
    }
  }

  /**
   * @return the {@link AbstractEndPoint} of the current {@link AbstractClient}
   * @throws RuntimeException if the current {@link AbstractClient} is not
   *                          connected.
   */
  private IEndPoint getStoredEndPoint() {
    assertIsConnected();

    return nullableEndPoint;
  }

  /**
   * @return true if the current {@link AbstractClient} is connected, false
   *         otherwise
   */
  private boolean isConnected() {
    return nullableEndPoint != null;
  }
}
