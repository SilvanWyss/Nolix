//package declaration
package ch.nolix.core.net.endpoint2;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.endpoint2api.ISlot;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;

//class
/**
 * A local end point can send messages to an other local end point.
 * 
 * @author Silvan Wyss
 * @version 2017-05-22
 */
public final class LocalEndPoint extends EndPoint {

  //attribute
  private final PeerType peerType;

  //attribute
  private final LocalEndPoint counterpart;

  //optional attribute
  private final String target;

  //constructor
  /**
   * Creates a new local end point that will connect to an other new local end
   * point.
   */
  public LocalEndPoint() {

    peerType = PeerType.FRONTEND;

    //Creates the counterpart of this local end point.
    counterpart = new LocalEndPoint(this);

    //Clears the target of this local end point.
    target = null;
  }

  //constructor
  /**
   * Creates a new local end point that will connect to the given target
   * 
   * @param target
   * @throws ArgumentIsNullException if the given target is null.
   */
  public LocalEndPoint(final ISlot target) {

    peerType = PeerType.FRONTEND;

    //Creates the counterpart of this local end point.
    counterpart = new LocalEndPoint(this, target.getName());

    //Clears the target of this local end point.
    this.target = null;

    target.takeBackendEndPoint(getStoredCounterpart());
  }

  //constructor
  /**
   * Creates a new local end point that will connect to the given target on the
   * given server.
   * 
   * @param baseServer
   * @param target
   */
  public LocalEndPoint(final BaseServer baseServer, final String target) {

    peerType = PeerType.FRONTEND;

    //Creates the counterpart of this local end point.
    counterpart = new LocalEndPoint(this, target);

    this.target = target;

    //Lets the given server take the counterpart of this lcoal end point.
    baseServer.internalTakeBackendEndPoint(getStoredCounterpart());
  }

  //constructor
  /**
   * Creates a new local end point with the given counterpart.
   * 
   * @param counterPart
   * @throws ArgumentIsNullException if the given counterpart is null.
   */
  private LocalEndPoint(final LocalEndPoint counterPart) {

    peerType = PeerType.BACKEND;

    //Asserts that the given counter part is not null.
    GlobalValidator.assertThat(counterPart).thatIsNamed("counterpart").isNotNull();

    //Sets the counter part of this local end point.
    this.counterpart = counterPart;

    //Clears the target of this local end point.
    target = null;
  }

  //constructor
  /**
   * Creates a new local end point with the given counterpart and target.
   * 
   * @param counterpart
   * @param target
   * @throws ArgumentIsNullException if the given counterpart is null.
   * @throws ArgumentIsNullException if the given target is null.
   * @throws EmptyArgumentException  if the given target is empty.
   */
  private LocalEndPoint(final LocalEndPoint counterpart, final String target) {

    peerType = PeerType.BACKEND;

    //Asserts that the given counter part is not null.
    GlobalValidator.assertThat(counterpart).thatIsNamed("counterpart").isNotNull();

    //Sets the counter part of this local end point.
    this.counterpart = counterpart;

    //Asserts that the given target is not null or empty.
    GlobalValidator.assertThat(target).thatIsNamed("target").isNotEmpty();

    //Sets the target of this local end point.
    this.target = target;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public ConnectionType getConnectionType() {
    return ConnectionType.LOCAL;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public PeerType getPeerType() {
    return peerType;
  }

  //method
  /**
   * Lets this local end point send the given message.
   * 
   * @param message
   * @return the reply to the given message.
   * @throws ClosedArgumentException if this local end point is closed.
   */
  public String sendAndWaitToReply(final String message) {

    //Asserts that this local end point is open.
    assertIsOpen();

    return getStoredCounterpart().receiveAndGetReply(message);
  }

  //method
  /**
   * @return the counterpart of this local end point.
   */
  public LocalEndPoint getStoredCounterpart() {
    return counterpart;
  }

  //method
  /**
   * @return the target of this local end point.
   * @throws ArgumentDoesNotHaveAttributeException if this local end point does
   *                                               not have a target.
   */
  @Override
  public String getCustomTargetSlot() {

    //Asserts that this local end point has a target.
    if (!hasCustomTargetSlot()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "target");
    }

    return target;
  }

  //method
  /**
   * Lets this local end point send the given message.
   * 
   * @param message
   * @return the reply to the given message from this local end point.
   */
  @Override
  public String getReplyForRequest(final String message) {
    return getStoredCounterpart().receiveAndGetReply(message);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityMode() {
    return SecurityMode.NONE;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasCustomTargetSlot() {
    return (target != null);
  }

  //method
  /**
   * Lets this local end point receive the given message.
   * 
   * @param message
   * @return the reply to the given message.
   */
  private String receiveAndGetReply(final String message) {
    return getStoredReplier().apply(message);
  }
}
