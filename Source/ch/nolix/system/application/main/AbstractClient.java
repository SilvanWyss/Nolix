package ch.nolix.system.application.main;

import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnconnectedArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.net.endpoint3.AbstractEndPoint;
import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.coreapi.document.chainednode.IChainedNode;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.net.endpoint3.IEndPoint;
import ch.nolix.coreapi.net.securityproperty.SecurityMode;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.systemapi.application.client.IClient;

/**
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <C> is the type of a {@link AbstractClient}.
 */
public abstract class AbstractClient<C extends AbstractClient<C>> implements IClient {
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
   *         current {@link AbstractClient}.
   * @throws UnconnectedArgumentException if the current {@link AbstractClient} is
   *                                      not connected.
   */
  protected final INode<?> getDataFromCounterpart(final IChainedNode request) {
    return getStoredEndPoint().getDataForRequest(request);
  }

  /**
   * @param request
   * @return the data the given request requests from the current
   *         {@link AbstractClient}.
   */
  protected abstract INode<?> getDataFromHere(IChainedNode request);

  /**
   * Lets the current {@link AbstractClient} run the given command.
   * 
   * @param command
   */
  protected abstract void runHere(IChainedNode command);

  /**
   * Runs the given command on the counterpart of the current
   * {@link AbstractClient}.
   * 
   * @param command
   * @throws UnconnectedArgumentException if the current {@link AbstractClient} is
   *                                      not connected.
   */
  protected final void runOnCounterpart(final IChainedNode command) {
    getStoredEndPoint().runCommand(command);
  }

  /**
   * Runs the given commands on the counterpart of the current
   * {@link AbstractClient}.
   * 
   * @param command
   * @param commands
   * @throws UnconnectedArgumentException if the current {@link AbstractClient} is
   *                                      not connected.
   */
  protected final void runOnCounterpart(final ChainedNode command, final ChainedNode... commands) {
    getStoredEndPoint().runCommands(command, commands);
  }

  /**
   * Runs the given commands on the counterpart of the current
   * {@link AbstractClient}.
   * 
   * @param commands
   * @throws UnconnectedArgumentException if the current {@link AbstractClient} is
   *                                      not connected.
   */
  protected final void runOnCounterpart(final Iterable<? extends IChainedNode> commands) {
    getStoredEndPoint().runCommands(commands);
  }

  /**
   * Sets the {@link AbstractEndPoint} of the current {@link AbstractClient}.
   * 
   * @param endPoint
   * @throws ArgumentIsNullException  if the given endPoint is null.
   * @throws InvalidArgumentException if the current {@link AbstractClient} is
   *                                  already connected.
   */
  final void setEndPoint(final IEndPoint endPoint) {
    //Asserts that the given endPoint is not null.
    Validator.assertThat(endPoint).thatIsNamed(AbstractEndPoint.class).isNotNull();

    //Asserts that the current Client is not already connected.
    assertIsNotConnected();

    //Sets the EndPoint of the current Client.
    this.nullableEndPoint = endPoint;

    //Creates a close dependency between the current Client and its EndPoint.
    createCloseDependencyTo(endPoint);

    //Sets the receiver controller of the EndPoint of the current Client.
    endPoint.setReceivingDataProviderController(new ClientDataProviderController(this));
  }

  /**
   * @throws UnconnectedArgumentException if the current {@link AbstractClient} is
   *                                      not connected.
   */
  private void assertIsConnected() {
    if (!isConnected()) {
      throw UnconnectedArgumentException.forArgument(this);
    }
  }

  /**
   * @throws InvalidArgumentException if the current {@link AbstractClient} is
   *                                  already connected.
   */
  private void assertIsNotConnected() {
    if (isConnected()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is already connected");
    }
  }

  /**
   * @return the {@link AbstractEndPoint} of the current {@link AbstractClient}.
   * @throws UnconnectedArgumentException if the current {@link AbstractClient} is
   *                                      not connected.
   */
  private IEndPoint getStoredEndPoint() {
    assertIsConnected();

    return nullableEndPoint;
  }

  /**
   * @return true if the current {@link AbstractClient} is connected, false
   *         otherwise.
   */
  private boolean isConnected() {
    return (nullableEndPoint != null);
  }
}
