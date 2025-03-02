package ch.nolix.core.net.endpoint2;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.netapi.endpoint2protocol.MessageRole;
import ch.nolix.coreapi.netapi.endpointapi.IEndPoint;
import ch.nolix.coreapi.netapi.netproperty.ConnectionType;
import ch.nolix.coreapi.netapi.netproperty.PeerType;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * A {@link NetEndPoint} is a {@link AbstractEndPoint} that can send messages to an
 * other {@link NetEndPoint}.
 * 
 * @author Silvan Wyss
 * @version 2017-05-22
 */
public final class NetEndPoint extends AbstractEndPoint {

  private int nextSentPackageIndex = 1;

  private final IEndPoint internalEndPoint;

  private final LinkedList<Package> receivedPackages = LinkedList.createEmpty();

  /**
   * Creates a new {@link NetEndPoint} that will connect to the default target on
   * the given port on the local machine.
   * 
   * @param port
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   */
  public NetEndPoint(final int port) {

    //Calls other constructor.
    this(new ch.nolix.core.net.endpoint.SocketEndPoint(port));
  }

  /**
   * Creates a new {@link NetEndPoint} that will connect to the given target on
   * the given port on the local machine.
   * 
   * @param port
   * @param target
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   * @throws ArgumentIsNullException       if the given target is null.
   * @throws InvalidArgumentException      if the given target is blank.
   */
  public NetEndPoint(final int port, final String target) {

    //Calls other constructor.
    this(new ch.nolix.core.net.endpoint.SocketEndPoint(port, target));
  }

  /**
   * Creates a new {@link NetEndPoint} that will connect to the default target on
   * the HTTP port (80) on the machine with the given ip.
   * 
   * @param ip
   */
  public NetEndPoint(final String ip) {

    //Calls other constructor.
    this(new ch.nolix.core.net.endpoint.SocketEndPoint(ip));
  }

  /**
   * Creates a new {@link NetEndPoint} that will connect to the default target on
   * the given port on the machine with the given ip.
   * 
   * @param ip
   * @param port
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   */
  public NetEndPoint(final String ip, final int port) {

    //Calls other constructor.
    this(new ch.nolix.core.net.endpoint.SocketEndPoint(ip, port));
  }

  /**
   * Creates a new {@link NetEndPoint} that will connect to the given target on
   * the given port on the machine with the given ip.
   * 
   * @param ip
   * @param port
   * @param target
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   * @throws ArgumentIsNullException       if the given target is null.
   * @throws InvalidArgumentException      if the given target is blank.
   */
  public NetEndPoint(final String ip, final int port, final String target) {

    //Calls other constructor.
    this(new ch.nolix.core.net.endpoint.SocketEndPoint(ip, port, target));
  }

  /**
   * Creates a new {@link NetEndPoint} with the given internalEndPoint.
   * 
   * @param internalEndPoint
   * @throws ArgumentIsNullException if the given internalEndPoint is null.
   */
  NetEndPoint(final IEndPoint internalEndPoint) {

    Validator.assertThat(internalEndPoint).thatIsNamed("internal EndPoint").isNotNull();

    this.internalEndPoint = internalEndPoint;
    createCloseDependencyTo(internalEndPoint);
    internalEndPoint.setReceiver(this::receive);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ConnectionType getConnectionType() {
    return internalEndPoint.getConnectionType();
  }

  /**
   * @return the target of the current {@link NetEndPoint}.
   * @throws ArgumentDoesNotHaveAttributeException if this net end point does not
   *                                               have a target.
   */
  @Override
  public String getCustomTargetSlot() {
    return internalEndPoint.getCustomTargetSlot();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PeerType getPeerType() {
    return internalEndPoint.getPeerType();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityMode() {
    return internalEndPoint.getSecurityMode();
  }

  /**
   * @return true if the current {@link NetEndPoint} is a net end point.
   */
  public boolean isNetEndPoint() {
    return internalEndPoint.isSocketEndPoint();
  }

  /**
   * Sends the given message and returns the reply.
   * 
   * @param message
   * @return the reply to the given message if the current {@link NetEndPoint}
   *         stays connected, null otherwise.
   */
  @Override
  public String getReplyForRequest(final String message) {
    return sendAndWaitToReply(message);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasCustomTargetSlot() {
    return internalEndPoint.hasCustomTargetSlot();
  }

  /**
   * @return the internal end point of the current {@link NetEndPoint}.
   */
  IEndPoint getStoredInternalEndPoint() {
    return internalEndPoint;
  }

  /**
   * Lets the current {@link NetEndPoint} receive the given message.
   * 
   * @param message
   */
  void receive(final String message) {
    receive(Package.createPackageFromString(message));
  }

  LinkedList<Package> getStoredReceivedPackages() {
    return receivedPackages;
  }

  /**
   * @return the index of the next sent package. of the current
   *         {@link NetEndPoint}
   */
  int getNextSentPackageIndex() {

    //Resets the index of the text sent package if it has reached the maximum
    //value.
    if (nextSentPackageIndex == Integer.MAX_VALUE) {
      nextSentPackageIndex = 0;
    }

    //Returns and increments the next sent package index.
    return nextSentPackageIndex++;
  }

  /**
   * Lets the current {@link NetEndPoint} receive the given package.
   * 
   * @param paramPackage
   */
  void receive(final Package paramPackage) {

    //Enumerates the message role of the given package.
    switch (paramPackage.getMessageRole()) { //NOSONAR: A switch-statement allows to add probable additional cases.
      case RESPONSE_EXPECTING_MESSAGE:
        receiveResponseExpectingMessage(paramPackage);
        break;
      default:
        getStoredReceivedPackages().addAtEnd(paramPackage);
    }
  }

  /**
   * Lets the current {@link NetEndPoint} return and remove the received package
   * with the given index.
   * 
   * @param index
   * @return the reply with the given index
   * @throws InvalidArgumentException if the current {@link NetEndPoint} has not
   *                                  received a package with the given index.
   */
  private Package getAndRemoveReceivedPackage(final int index) {
    return getStoredReceivedPackages().removeAndGetStoredFirst(rp -> rp.hasIndex(index));
  }

  /**
   * @param index
   * @return true if the current {@link NetEndPoint} has received a package with
   *         the given index.
   */
  private boolean receivedPackage(final int index) {
    return getStoredReceivedPackages().containsAny(rp -> rp.hasIndex(index));
  }

  /**
   * Lets the current {@link NetEndPoint} receive a response expecting message
   * which is in the given package.
   * 
   * @param paramPackage
   */
  private void receiveResponseExpectingMessage(final Package paramPackage) {
    try {
      final String reply = getStoredReplier().apply(paramPackage.getStoredContent());
      if (isOpen()) {
        send(new Package(paramPackage.getIndex(), MessageRole.SUCCESS_RESPONSE, reply));
      }
    } catch (final Throwable error) { //NOSONAR: All Throwables must be caught.
      String responseMessage = error.getMessage();
      send(new Package(paramPackage.getIndex(), MessageRole.ERROR_RESPONSE, responseMessage));
    }
  }

  /**
   * Lets the current {@link NetEndPoint} send the given package.
   * 
   * @param paramPackage
   */
  private void send(final Package paramPackage) {
    internalEndPoint.sendMessage(paramPackage.toString());
  }

  /**
   * Sends the given message and waits to the reply.
   * 
   * @param message
   * @return the reply to the given message if the current {@link NetEndPoint}
   *         stays connected, null otherwise.
   */
  private String sendAndWaitToReply(final String message) {

    //Sends message and receives reply.
    final var index = getNextSentPackageIndex();
    send(new Package(index, MessageRole.RESPONSE_EXPECTING_MESSAGE, message));
    final var response = waitToAndGetAndRemoveReceivedPackage(index);

    if (response == null) {
      return null;
    }

    //Enumerates the response.
    return switch (response.getMessageRole()) {
      case SUCCESS_RESPONSE ->
        response.getStoredContent();
      case ERROR_RESPONSE ->
        throw GeneralException.withErrorMessage(response.getStoredContent());
      default ->
        throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalog.REPLY, response);
    };
  }

  /**
   * Lets the current {@link NetEndPoint} wait to and return and remove the
   * received package with the given index.
   * 
   * @param index
   * @return the received package with the given index.
   * @throws RuntimeException if the current {@link NetEndPoint} reaches its
   *                          timeout before it receives a package with the given
   *                          index.
   */
  private Package waitToAndGetAndRemoveReceivedPackage(final int index) {

    //This loop suffers from being optimized away by the compiler or the JVM.
    while (!receivedPackage(index)) {

      //Handles the case that the current NetEndPoint is closed.
      if (isClosed()) {
        return null;
      }

      //This statement, which is theoretically unnecessary, makes that the current
      //loop is not optimized away.
      System.err.flush(); //NOSONAR: This statement is used to keep the loop.
    }

    return getAndRemoveReceivedPackage(index);
  }
}
