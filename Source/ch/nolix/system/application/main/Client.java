package ch.nolix.system.application.main;

import java.util.HashMap;

import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnconnectedArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.endpoint3.EndPoint;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.netapi.endpoint3api.IEndPoint;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;

/**
 * A {@link Client} is an end point with comfortable functionalities.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <C> is the type of a {@link Client}.
 */
public abstract class Client<C extends Client<C>> implements GroupCloseable {

  private final CloseController closeController = CloseController.forElement(this);

  private IEndPoint endPoint;

  private final HashMap<String, String> sessionVariables = new HashMap<>();

  /**
   * @param key
   * @return true if the current {@link Client} contains a session variable with
   *         the given key, false otherwise.
   */
  public final boolean containsSessionVariableWithKey(final String key) {
    return sessionVariables.containsKey(key);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final CloseController getStoredCloseController() {
    return closeController;
  }

  /**
   * @return the {@link SecurityMode} of the current {@link Client}.
   */
  public SecurityMode getSecurityMode() {
    return endPoint.getSecurityMode();
  }

  /**
   * @param key
   * @return the value of the session variable with the given key from the current
   *         {@link Client}.
   * @throws ArgumentDoesNotContainElementException if the current {@link Client}
   *                                                does not contain a session
   *                                                variable with the given key.
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
   * @return the name of the target {@link Application} of the current
   *         {@link Client}.
   */
  public final String getTarget() {
    return getStoredEndPoint().getCustomTargetSlot();
  }

  /**
   * @return true if the current {@link Client} has requested the connection.
   */
  public final boolean hasRequestedConnection() {
    return getStoredEndPoint().isFrontendEndPoint();
  }

  /**
   * @return true if the current {@link Client} has a target.
   */
  public final boolean hasTarget() {
    return getStoredEndPoint().hasCustomTargetSlot();
  }

  /**
   * @return true if the current {@link Client} is a backend {@link Client}.
   */
  public abstract boolean isBackendClient();

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
   * @return true if the current {@link Client} is a frontend {@link Client}.
   */
  public abstract boolean isFrontendClient();

  /**
   * @return true if the current {@link Client} is a local {@link Client}.
   */
  public final boolean isLocalClient() {
    return getStoredEndPoint().isLocalEndPoint();
  }

  /**
   * @return true if the current {@link Client} is a net {@link Client}.
   */
  public final boolean isNetClient() {
    return getStoredEndPoint().isSocketEndPoint();
  }

  /**
   * @return true if the current {@link Client} is a web {@link Client}.
   * @throws UnconnectedArgumentException if the current {@link Client} is not
   *                                      connected.
   */
  public final boolean isWebClient() {
    return getStoredEndPoint().isWebSocketEndPoint();
  }

  /**
   * Sets a session variable with the given key and value to the current
   * {@link Client}.
   * 
   * Will overwrite a previous session variable if the current {@link Client}
   * contains already a session variable with the given key.
   * 
   * @param key
   * @param value
   */
  public final void setSessionVariableWithKeyAndValue(final String key, final String value) {
    sessionVariables.put(key, value);
  }

  /**
   * @return the current {@link Client} as concrete {@link Client}.
   */
  @SuppressWarnings("unchecked")
  protected final C asConcrete() {
    return (C) this;
  }

  /**
   * @param request
   * @return the data the given request requests from the counterpart of the
   *         current {@link Client}.
   * @throws UnconnectedArgumentException if the current {@link Client} is not
   *                                      connected.
   */
  protected final INode<?> getDataFromCounterpart(final IChainedNode request) {
    return getStoredEndPoint().getDataForRequest(request);
  }

  /**
   * @param request
   * @return the data the given request requests from the current {@link Client}.
   */
  protected abstract INode<?> getDataFromHere(IChainedNode request);

  /**
   * @return true if the current {@link Client} is connected.
   */
  protected final boolean isConnected() {
    return (endPoint != null);
  }

  /**
   * Lets the current {@link Client} run the given command.
   * 
   * @param command
   */
  protected abstract void runHere(IChainedNode command);

  /**
   * Runs the given command on the counterpart of the current {@link Client}.
   * 
   * @param command
   * @throws UnconnectedArgumentException if the current {@link Client} is not
   *                                      connected.
   */
  protected final void runOnCounterpart(final IChainedNode command) {
    getStoredEndPoint().runCommand(command);
  }

  /**
   * Runs the given commands on the counterpart of the current {@link Client}.
   * 
   * @param command
   * @param commands
   * @throws UnconnectedArgumentException if the current {@link Client} is not
   *                                      connected.
   */
  protected final void runOnCounterpart(final ChainedNode command, final ChainedNode... commands) {
    getStoredEndPoint().runCommands(command, commands);
  }

  /**
   * Runs the given commands on the counterpart of the current {@link Client}.
   * 
   * @param commands
   * @throws UnconnectedArgumentException if the current {@link Client} is not
   *                                      connected.
   */
  protected final void runOnCounterpart(final Iterable<? extends IChainedNode> commands) {
    getStoredEndPoint().runCommands(commands);
  }

  /**
   * @throws ClosedArgumentException if the current {@link Client} is closed.
   */
  void internalAssertIsOpen() {
    if (isClosed()) {
      throw ClosedArgumentException.forArgument(this);
    }
  }

  /**
   * Sets the {@link EndPoint} of the current {@link Client}.
   * 
   * @param endPoint
   * @throws ArgumentIsNullException  if the given endPoint is null.
   * @throws InvalidArgumentException if the current {@link Client} is already
   *                                  connected.
   */
  final void internalSetEndPoint(final IEndPoint endPoint) {

    //Asserts that the given endPoint is not null.
    GlobalValidator.assertThat(endPoint).thatIsNamed(EndPoint.class).isNotNull();

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
   * @throws UnconnectedArgumentException if the current {@link Client} is not
   *                                      connected.
   */
  private void assertIsConnected() {
    if (!isConnected()) {
      throw UnconnectedArgumentException.forArgument(this);
    }
  }

  /**
   * @throws InvalidArgumentException if the current {@link Client} is already
   *                                  connected.
   */
  private void assertIsNotConnected() {
    if (isConnected()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is already connected");
    }
  }

  /**
   * @return the {@link EndPoint} of the current {@link Client}.
   * @throws UnconnectedArgumentException if the current {@link Client} is not
   *                                      connected.
   */
  private IEndPoint getStoredEndPoint() {

    assertIsConnected();

    return endPoint;
  }
}
