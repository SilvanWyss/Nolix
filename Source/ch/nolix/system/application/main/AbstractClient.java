package ch.nolix.system.application.main;

import java.util.HashMap;

import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnconnectedArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.net.endpoint3.AbstractEndPoint;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.netapi.endpoint3api.IEndPoint;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.systemapi.applicationapi.mainapi.IClient;

/**
 * A {@link AbstractClient} is an end point with comfortable functionalities.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <C> is the type of a {@link AbstractClient}.
 */
public abstract class AbstractClient<C extends AbstractClient<C>> implements IClient {

  private final ICloseController closeController = CloseController.forElement(this);

  private IEndPoint endPoint;

  private final HashMap<String, String> sessionVariables = new HashMap<>();

  /**
   * @param key
   * @return true if the current {@link AbstractClient} contains a session
   *         variable with the given key, false otherwise.
   */
  public final boolean containsSessionVariableWithKey(final String key) {
    return sessionVariables.containsKey(key);
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
  public final SecurityMode getSecurityMode() {
    return endPoint.getSecurityMode();
  }

  /**
   * @param key
   * @return the value of the session variable with the given key from the current
   *         {@link AbstractClient}.
   * @throws ArgumentDoesNotContainElementException if the current
   *                                                {@link AbstractClient} does
   *                                                not contain a session variable
   *                                                with the given key.
   */
  public final String getSessionVariableValueByKey(final String key) {

    final var value = sessionVariables.get(key);

    if (value == null) {
      throw ArgumentDoesNotContainElementException.forArgumentAndElement(
        this,
        "session variable with the key + '" + key + "'");
    }

    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getUrlInstanceNameOfTargetApplication() {
    return getStoredEndPoint().getCustomTargetSlot();
  }

  /**
   * @return true if the current {@link AbstractClient} has requested the
   *         connection.
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

    /*
     * The end point of the current Client can be requested only when the current
     * Client is connected.
     */
    return (isConnected() && getStoredEndPoint().isClosed());
  }

  /**
   * @return true if the current {@link AbstractClient} is a local
   *         {@link AbstractClient}.
   */
  public final boolean isLocalClient() {
    return getStoredEndPoint().isLocalEndPoint();
  }

  /**
   * @return true if the current {@link AbstractClient} is a net
   *         {@link AbstractClient}.
   */
  public final boolean isNetClient() {
    return getStoredEndPoint().isSocketEndPoint();
  }

  /**
   * @return true if the current {@link AbstractClient} is a web
   *         {@link AbstractClient}.
   * @throws UnconnectedArgumentException if the current {@link AbstractClient} is
   *                                      not connected.
   */
  public final boolean isWebClient() {
    return getStoredEndPoint().isWebSocketEndPoint();
  }

  /**
   * Sets a session variable with the given key and value to the current
   * {@link AbstractClient}.
   * 
   * Will overwrite a previous session variable if the current
   * {@link AbstractClient} contains already a session variable with the given
   * key.
   * 
   * @param key
   * @param value
   */
  public final void setSessionVariableWithKeyAndValue(final String key, final String value) {
    sessionVariables.put(key, value);
  }

  /**
   * @return the current {@link AbstractClient} as concrete
   *         {@link AbstractClient}.
   */
  @SuppressWarnings("unchecked")
  protected final C asConcrete() {
    return (C) this;
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
   * @return true if the current {@link AbstractClient} is connected.
   */
  protected final boolean isConnected() {
    return (endPoint != null);
  }

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
   * @throws ClosedArgumentException if the current {@link AbstractClient} is
   *                                 closed.
   */
  void internalAssertIsOpen() {
    if (isClosed()) {
      throw ClosedArgumentException.forArgument(this);
    }
  }

  /**
   * Sets the {@link AbstractEndPoint} of the current {@link AbstractClient}.
   * 
   * @param endPoint
   * @throws ArgumentIsNullException  if the given endPoint is null.
   * @throws InvalidArgumentException if the current {@link AbstractClient} is
   *                                  already connected.
   */
  final void internalSetEndPoint(final IEndPoint endPoint) {

    //Asserts that the given endPoint is not null.
    Validator.assertThat(endPoint).thatIsNamed(AbstractEndPoint.class).isNotNull();

    //Asserts that the current Client is not already connected.
    assertIsNotConnected();

    //Sets the EndPoint of the current Client.
    this.endPoint = endPoint;

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

    return endPoint;
  }
}
