package ch.nolix.core.net.endpoint3;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.netapi.endpoint3api.ISlot;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;

/**
 * A local duplex controller can interact with another local duplex controller.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public final class LocalEndPoint extends AbstractEndPoint {

  private final PeerType peerType;

  private final LocalEndPoint counterpart;

  private final String target;

  /**
   * Creates a new local duplex controller that will connect to another new local
   * duplex controller.
   */
  public LocalEndPoint() {

    peerType = PeerType.FRONTEND;

    //Creates the counterpart of this local duplex controller.
    this.counterpart = new LocalEndPoint(this);

    //Clears the target of this local duplex controller.
    target = null;
  }

  /**
   * Creates a new local duplex controller that will connect to the given target.
   * 
   * @param target
   */
  public LocalEndPoint(final ISlot target) {

    peerType = PeerType.FRONTEND;

    //Creates the counterpart of this local duplex controller.
    this.counterpart = new LocalEndPoint(this, target.getName());

    //Clears the target of this local duplex controller.
    this.target = null;

    //Lets the given target take the counterpart of this local duplex controller.
    target.takeBackendEndPoint(getStoredCounterpart());
  }

  /**
   * Creates a new local duplex controller with the given counterpart.
   * 
   * @param counterpart
   * @throws ArgumentIsNullException if the given counterpart is null.
   */
  private LocalEndPoint(LocalEndPoint counterpart) {

    peerType = PeerType.BACKEND;

    //Asserts that the given counterpart is not null.
    Validator.assertThat(counterpart).thatIsNamed("counterpart").isNotNull();

    //Sets the counterpart of this local duplex controller.
    this.counterpart = counterpart;

    createCloseDependencyTo(counterpart);

    //Clears the target of this local duplex controller.
    target = null;
  }

  /**
   * Creates a new local duplex controller with the given counterpart and target.
   * 
   * @param counterpart
   * @param target
   * @throws ArgumentIsNullException if the given target is null.
   * @throws EmptyArgumentException  if the given target is empty.
   */
  private LocalEndPoint(
    final LocalEndPoint counterpart,
    final String target) {

    peerType = PeerType.BACKEND;

    //Asserts that the given counterpart is not null.
    Validator.assertThat(counterpart).thatIsNamed("counterpart").isNotNull();

    //Sets the counterpart of this local duplex controller.
    this.counterpart = counterpart;

    //Asserts that the given target is not null or empty.
    Validator.assertThat(target).thatIsNamed("target").isNotEmpty();

    //Sets the target of this local duplex controller.
    this.target = target;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ConnectionType getConnectionType() {
    return ConnectionType.LOCAL;
  }

  /**
   * @return the data the given request requests from this local duplex
   *         controller.
   * @throws ArgumentDoesNotHaveAttributeException if this local duplex controller
   *                                               does not have a receiver
   *                                               controller.
   */
  @Override
  public INode<?> getDataForRequest(final IChainedNode request) {
    return counterpart.getStoredReceiverController().getDataForRequest(request);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends INode<?>> getDataForRequests(final IChainedNode request, final IChainedNode... requests) {
    return counterpart.getStoredReceiverController().getDataForRequests(request, requests);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends INode<?>> getDataForRequests(final Iterable<? extends IChainedNode> requests) {
    return counterpart.getStoredReceiverController().getDataForRequests(requests);
  }

  /**
   * @return the counterpart of this local duplex controller.
   */
  public LocalEndPoint getStoredCounterpart() {
    return counterpart;
  }

  /**
   * @return the target of this local duplex controller.
   */
  @Override
  public String getCustomTargetSlot() {
    return target;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PeerType getPeerType() {
    return peerType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityMode() {
    return SecurityMode.NONE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasCustomTargetSlot() {
    return (target != null);
  }

  /**
   * Lets this local duplex controller run the given command.
   * 
   * @param command
   * @throws ClosedArgumentException               if this local duplex controller
   *                                               is closed.
   * @throws ArgumentDoesNotHaveAttributeException if this local duplex controller
   *                                               does not have a receiver
   *                                               controller.
   */
  @Override
  public void runCommand(final IChainedNode command) {

    //Asserts that this local duplex controller is not aborted.
    assertIsOpen();

    counterpart.getStoredReceiverController().runCommand(command);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void runCommands(final Iterable<? extends IChainedNode> commands) {

    //Asserts that this local duplex controller is open.
    assertIsOpen();

    final var counterpartReceiverController = counterpart.getStoredReceiverController();

    commands.forEach(counterpartReceiverController::runCommand);
  }
}
