//package declaration
package ch.nolix.core.net.endpoint;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.endpointapi.ISlot;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.programcontrolapi.processproperty.SecurityLevel;

//class
/**
 * A local end point can send messages to an other local end point.
 * 
 * @author Silvan Wyss
 * @date 2017-05-06
 */
public final class LocalEndPoint extends EndPoint {

  // attribute
  private final PeerType peerType;

  // attribute
  private final LocalEndPoint counterpart;

  // constructor
  /**
   * Creates a new local end point that will connect to an other new local end
   * point.
   */
  public LocalEndPoint() {

    peerType = PeerType.FRONTEND;

    // Creates the counterpart of this local end point.
    counterpart = new LocalEndPoint(this);
  }

  // constructor
  /**
   * Creates a new local end point that will connect to the given target.
   * 
   * @param target
   * @throws ArgumentIsNullException if the given target is null.
   */
  public LocalEndPoint(final ISlot target) {

    peerType = PeerType.FRONTEND;

    // Creates the counterpart of this local end point.
    counterpart = new LocalEndPoint(this);

    // Sets the target of the counterpart of htis local end point.
    getStoredCounterPart().setTarget(target.getName());

    // Lets the given target take the counterpart of this local end point.
    target.takeBackendEndPoint(getStoredCounterPart());
  }

  // constructor
  /**
   * Creates a new local end point that will connect to the given target on the
   * given server.
   * 
   * @param baseServer
   * @param target
   * @throws ArgumentIsNullException if the given target is null.
   * @throws EmptyArgumentException  if the given target is empty.
   */
  public LocalEndPoint(final BaseServer baseServer, final String target) {

    peerType = PeerType.FRONTEND;

    // Creates the counterpart of this local end point.
    counterpart = new LocalEndPoint(this);

    // Sets the target of the counterpart of this local end point.
    getStoredCounterPart().setTarget(target);

    // Lets the given server take the counterpart of this local end point.
    baseServer.internalTakeBackendEndPoint(getStoredCounterPart());
  }

  // constructor
  /**
   * Creates a new local end point with the given counterpart.
   * 
   * @param counterpart
   * @throws ArgumentIsNullException if the given counterpart is null.
   */
  private LocalEndPoint(final LocalEndPoint counterpart) {

    peerType = PeerType.BACKEND;

    // Asserts that the given counterpart is not null.
    GlobalValidator.assertThat(counterpart).thatIsNamed("counterpart").isNotNull();

    // Creates a close dependency from the current LocalEndPoint to the given
    // counterpart.
    createCloseDependencyTo(counterpart);

    // Sets the counterpart of this local end point.
    this.counterpart = counterpart;
  }

  // method
  @Override
  public PeerType getPeerType() {
    return peerType;
  }

  // method
  /**
   * @return the counterpart of this local end point.
   */
  public LocalEndPoint getStoredCounterPart() {
    return counterpart;
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public ConnectionType getConnectionType() {
    return ConnectionType.LOCAL;
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityLevel getConnectionSecurityLevel() {
    return SecurityLevel.UNSECURE;
  }

  // method
  /**
   * Lets this local send the given message.
   * 
   * @param message
   * @throws ArgumentIsNullException if the given message is null.
   * @throws ClosedArgumentException if this local end point is closed.
   */
  @Override
  public void sendMessage(final String message) {

    // Asserts that the given message is not null.
    GlobalValidator.assertThat(message).thatIsNamed("message").isNotNull();

    // Asserts that this local end point is open.
    assertIsOpen();

    counterpart.receiveRawMessage(message);
  }

  // method
  /**
   * {@inheritDoc}
   */
  @Override
  public void noteClose() {
    // Does nothing.
  }

  // method
  /**
   * Lets the current {@link EndPoint} receive the given message.
   * 
   * @param message
   */
  private void receiveRawMessage(final String message) {
    getStoredReceiver().run(message);
  }
}
