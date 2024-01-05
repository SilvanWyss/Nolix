//package declaration
package ch.nolix.core.net.endpoint;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.endpointapi.ISlot;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.netapi.securityapi.SecurityMode;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;

//class
/**
 * A {@link LocalEndPoint} is an {@link EndPoint} that can send messages to
 * another {@link LocalEndPoint}.
 * 
 * @author Silvan Wyss
 * @date 2017-05-06
 */
public final class LocalEndPoint extends EndPoint {

  //attribute
  private final PeerType peerType;

  //attribute
  private final LocalEndPoint counterpart;

  //constructor
  /**
   * Creates a new {@link LocalEndPoint} that will connect to the given slot on
   * the given server.
   * 
   * @param server
   * @param slot
   * @throws NullPointerException     if the given server is null.
   * @throws ArgumentIsNullException  if the given slot is null.
   * @throws InvalidArgumentException if the given slot is blank.
   */
  private LocalEndPoint(final BaseServer server, final String slot) {

    //Sets the peerType of the current LocalEndPoint.
    peerType = PeerType.FRONTEND;

    //Sets the slot of the current LocalEndPoint.
    setCustomTargetSlot(slot);

    //Creates the counterpart of the current LocalEndPoint.
    counterpart = new LocalEndPoint(this);

    //Sets the slot of the counterpart of the current LocalEndPoint.
    getStoredCounterPart().setCustomTargetSlot(slot);

    //Lets the given server take the counterpart of the current LocalEndPoint.
    server.internalTakeBackendEndPoint(getStoredCounterPart());
  }

  //constructor
  /**
   * Creates a new {@link LocalEndPoint} that will connect to the given slot.
   * 
   * @param slot
   * @throws ArgumentIsNullException if the given target is null.
   */
  private LocalEndPoint(final ISlot slot) {

    //Asserts that the given slot is not null.
    GlobalValidator.assertThat(slot).thatIsNamed(ISlot.class).isNotNull();

    peerType = PeerType.FRONTEND;

    //Creates the counterpart of the current LocalEndPoint.
    counterpart = new LocalEndPoint(this);

    //Sets the target of the counterpart of the current LocalEndPoint.
    getStoredCounterPart().setCustomTargetSlot(slot.getName());

    //Lets the given slot take the counterpart of the current LocalEndPoint.
    slot.takeBackendEndPoint(getStoredCounterPart());
  }

  //constructor
  /**
   * Creates a new {@link LocalEndPoint} with the given counterpart.
   * 
   * @param counterpart
   * @throws ArgumentIsNullException if the given counterpart is null.
   */
  private LocalEndPoint(final LocalEndPoint counterpart) {

    peerType = PeerType.BACKEND;

    //Asserts that the given counterpart is not null.
    GlobalValidator.assertThat(counterpart).thatIsNamed("counterpart").isNotNull();

    //Creates a close dependency from the current LocalEndPoint to the given
    //counterpart.
    createCloseDependencyTo(counterpart);

    //Sets the counterpart of the current LocalEndPoint.
    this.counterpart = counterpart;
  }

  //static method
  /**
   * @param server
   * @param targetSlot
   * @return a new {@link LocalEndPoint} that will connect to the given slot on
   *         the given server.
   * @throws NullPointerException     if the given server is null.
   * @throws ArgumentIsNullException  if the given targetSlot is null.
   * @throws InvalidArgumentException if the given targetSlot is blank.
   */
  public static LocalEndPoint toTargetSlotOnServer(final BaseServer server, final String targetSlot) {
    return new LocalEndPoint(server, targetSlot);
  }

  //static method
  /**
   * @param slot
   * @return a new {@link LocalEndPoint} that will connect to the given slot.
   * @throws ArgumentIsNullException if the given target is null.
   */
  public static LocalEndPoint toSlot(final ISlot slot) {
    return new LocalEndPoint(slot);
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
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityLevel() {
    return SecurityMode.NONE;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public void noteClose() {
    //Does nothing.
  }

  //method
  /**
   * Lets the current {@link LocalEndPoint} send the given message.
   * 
   * @param message
   * @throws ArgumentIsNullException if the given message is null.
   * @throws ClosedArgumentException if the current {@link LocalEndPoint} is
   *                                 closed.
   */
  @Override
  public void sendMessage(final String message) {

    //Asserts that the given message is not null.
    GlobalValidator.assertThat(message).thatIsNamed(LowerCaseCatalogue.MESSAGE).isNotNull();

    //Asserts that the current LocalEndPoint is open.
    assertIsOpen();

    //Lets the counterpart of the current LocalEndPoint receive the given message.
    counterpart.receiveMessage(message);
  }

  //method
  /**
   * @return the counterpart of the current {@link LocalEndPoint}.
   */
  private LocalEndPoint getStoredCounterPart() {
    return counterpart;
  }

  //method
  /**
   * Lets the current {@link LocalEndPoint} receive the given message.
   * 
   * @param message
   */
  private void receiveMessage(final String message) {
    getStoredReceiver().accept(message);
  }
}
