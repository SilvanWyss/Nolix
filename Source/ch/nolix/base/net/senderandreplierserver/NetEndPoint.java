/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.senderandreplierserver;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.errorcontrol.generalexception.GeneralException;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.netproperty.ConnectionType;
import ch.nolix.baseapi.net.netproperty.PeerType;
import ch.nolix.baseapi.net.netproperty.SecurityMode;
import ch.nolix.baseapi.net.senderandreceiverserver.EndPoint;
import ch.nolix.baseapi.net.senderandreplierserverprotocol.MessageRole;

/**
 * A {@link NetEndPoint} is a {@link AbstractEndPoint} that can send messages to
 * an other {@link NetEndPoint}.
 * 
 * @author Silvan Wyss
 */
public final class NetEndPoint extends AbstractEndPoint {
  private int nextSentPackageIndex = 1;

  private final EndPoint memberInternalEndPoint;

  private final LinkedList<Package> receivedPackages = LinkedList.createEmpty();

  /**
   * Creates a new {@link NetEndPoint} that will connect to the default slot on
   * the given port on the local machine.
   * 
   * @param port
   * @throws RuntimeException if the given port is not in [0, 65535]
   */
  private NetEndPoint(final int port) {
    this(ch.nolix.base.net.senderandreceiverserver.SocketEndPoint.toLocaleMachineAndGivenPortAndDefaultSlot(port));
  }

  /**
   * Creates a new {@link NetEndPoint} that will connect to the given slot on the
   * given port on the local machine.
   * 
   * @param slot
   * @param port
   * @throws RuntimeException if the given port is not in [0, 65535]
   * @throws RuntimeException if the given slot is null or blank
   */
  private NetEndPoint(final int port, final String slot) {
    this(ch.nolix.base.net.senderandreceiverserver.SocketEndPoint.toLocalMachineAndGivenPortAndGivenSlot(port, slot));
  }

  /**
   * Creates a new {@link NetEndPoint} that will connect to the default slot on
   * the HTTP port on the given host.
   * 
   * @param host
   */
  private NetEndPoint(final String host) {
    this(ch.nolix.base.net.senderandreceiverserver.SocketEndPoint.toGivenHostAndHttpPortAndDefaultSlot(host));
  }

  /**
   * Creates a new {@link NetEndPoint} that will connect to the default slot on
   * the given port on the given host.
   * 
   * @param host
   * @param port
   * @throws RuntimeException if the given port is not in [0, 65535]
   */
  private NetEndPoint(final String host, final int port) {
    this(ch.nolix.base.net.senderandreceiverserver.SocketEndPoint.toGivenHostAndGivenPortAndDefaultSlot(host, port));
  }

  /**
   * Creates a new {@link NetEndPoint} that will connect to the given slot on the
   * given port on the given host.
   * 
   * @param host
   * @param port
   * @param slot
   * @throws RuntimeException if the given port is not in [0, 65535]
   * @throws RuntimeException if the given slot is null
   * @throws RuntimeException if the given slot is blank
   */
  private NetEndPoint(final String host, final int port, final String slot) {
    this(ch.nolix.base.net.senderandreceiverserver.SocketEndPoint.toGivenHostAndGivenPortAndGivenSlot(host, port, slot));
  }

  /**
   * Creates a new {@link NetEndPoint} with the given internalEndPoint.
   * 
   * @param internalEndPoint
   * @throws RuntimeException if the given internalEndPoint is null
   */
  private NetEndPoint(final EndPoint internalEndPoint) {
    Validator.assertThat(internalEndPoint).thatIsNamed("internal EndPoint").isNotNull();

    this.memberInternalEndPoint = internalEndPoint;
    createCloseDependencyTo(internalEndPoint);
    internalEndPoint.setReceiver(this::receive);
  }

  /**
   * @param host
   * @param port
   * @return a new {@link NetEndPoint} that will connect to the default slot on
   *         the given port on the given host
   * @throws RuntimeException if the given port is not in [0, 65535]
   */
  public static NetEndPoint toGivenHostAndGivenPortAndDefaultSlot(final String host, final int port) {
    return new NetEndPoint(host, port);
  }

  /**
   * @param host
   * @param port
   * @param slot
   * @return a new {@link NetEndPoint} that will connect to the given slot on the
   *         given port on the given host
   * @throws RuntimeException if the given port is not in [0, 65535]
   * @throws RuntimeException if the given slot is null
   * @throws RuntimeException if the given slot is blank
   */
  public static NetEndPoint toGivenHostAndGivenPortAndGivenSlot(final String host, final int port, final String slot) {
    return new NetEndPoint(host, port, slot);
  }

  /**
   * @param host
   * @return a new {@link NetEndPoint} that will connect to the default slot on
   *         the HTTP port on the given host.
   */
  public static NetEndPoint toGivenHostAndHttpPortAndDefaultSlot(final String host) {
    return new NetEndPoint(host);
  }

  /**
   * @param port
   * @return a new {@link NetEndPoint} that will connect to the default slot on
   *         the given port on the local machine
   * @throws RuntimeException if the given port is not in [0, 65535]
   */
  public static NetEndPoint toLocalMachineAndGivenPortAndDefaultSlot(final int port) {
    return new NetEndPoint(port);
  }

  /**
   * @param port
   * @param slot
   * @return a new {@link NetEndPoint} that will connect to the given slot on the
   *         given port on the local machine
   * @throws RuntimeException if the given port is not in [0, 65535]
   * @throws RuntimeException if the given slot is null or blank
   */
  public static NetEndPoint toLocalMachineAndGivenPortAndGivenSlot(int port, String slot) {
    return new NetEndPoint(port, slot);
  }

  /**
   * @param internalEndPoint
   * @return a new {@link NetEndPoint} with the given internalEndPoint
   * @throws RuntimeException if the given internalEndPoint is null
   */
  static NetEndPoint withInternalEndPoint(final EndPoint internalEndPoint) {
    return new NetEndPoint(internalEndPoint);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ConnectionType getConnectionType() {
    return memberInternalEndPoint.getConnectionType();
  }

  /**
   * @return the target of the current {@link NetEndPoint}
   * @throws ArgumentDoesNotHaveAttributeException if this net end point does not
   *                                               have a target.
   */
  @Override
  public String getCustomTargetSlot() {
    return memberInternalEndPoint.getCustomTargetSlot();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PeerType getPeerType() {
    return memberInternalEndPoint.getPeerType();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityMode() {
    return memberInternalEndPoint.getSecurityMode();
  }

  /**
   * Sends the given message and returns the reply.
   * 
   * @param message
   * @return the reply to the given message if the current {@link NetEndPoint}
   *         stays connected, null otherwise.
   */
  @Override
  public String getReplyForMessage(final String message) {
    return sendAndWaitToReply(message);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasCustomTargetSlot() {
    return memberInternalEndPoint.hasCustomTargetSlot();
  }

  /**
   * @return the internal end point of the current {@link NetEndPoint}.
   */
  EndPoint getStoredInternalEndPoint() {
    return memberInternalEndPoint;
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
    // Resets the index of the text sent package if it has reached the maximum
    // value.
    if (nextSentPackageIndex == Integer.MAX_VALUE) {
      nextSentPackageIndex = 0;
    }

    // Returns and increments the next sent package index.
    return nextSentPackageIndex++;
  }

  /**
   * Lets the current {@link NetEndPoint} receive the given package.
   * 
   * @param paramPackage
   */
  void receive(final Package paramPackage) {
    // Enumerates the message role of the given package.
    switch (paramPackage.getMessageRole()) { // NOSONAR: A switch-statement allows to add probable additional cases.
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
   * @throws RuntimeException if the current {@link NetEndPoint} has not received
   *                          a package with the given index.
   */
  private Package getAndRemoveReceivedPackage(final int index) {
    return getStoredReceivedPackages().removeAndGetStoredFirst(rp -> rp.hasIndex(index));
  }

  /**
   * @param index
   * @return true if the current {@link NetEndPoint} has received a package with
   *         the given index, false otherwise
   */
  private boolean receivedPackage(final int index) {
    return getStoredReceivedPackages().containsMatching(rp -> rp.hasIndex(index));
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
        final var successResponsePackage = //
        Package.withIndexAndMessageRoleAndMessage(paramPackage.getIndex(), MessageRole.SUCCESS_RESPONSE, reply);

        send(successResponsePackage);
      }
    } catch (final Throwable error) { // NOSONAR: All errors must be caught.
      String responseMessage = error.getMessage();
      final var errorResponsePackage = //
      Package.withIndexAndMessageRoleAndMessage(paramPackage.getIndex(), MessageRole.ERROR_RESPONSE, responseMessage);

      send(errorResponsePackage);
    }
  }

  /**
   * Lets the current {@link NetEndPoint} send the given package.
   * 
   * @param paramPackage
   */
  private void send(final Package paramPackage) {
    memberInternalEndPoint.sendMessage(paramPackage.toString());
  }

  /**
   * Sends the given message and waits to the reply.
   * 
   * @param message
   * @return the reply to the given message if the current {@link NetEndPoint}
   *         stays connected, null otherwise.
   */
  private String sendAndWaitToReply(final String message) {
    final var index = getNextSentPackageIndex();
    final var messagePackage = //
    Package.withIndexAndMessageRoleAndMessage(index, MessageRole.RESPONSE_EXPECTING_MESSAGE, message);

    send(messagePackage);
    final var response = waitToAndGetAndRemoveReceivedPackage(index);

    if (response == null) {
      return null;
    }

    return switch (response.getMessageRole()) {
      case SUCCESS_RESPONSE ->
        response.getStoredContent();
      case ERROR_RESPONSE ->
        throw GeneralException.withErrorMessage(response.getStoredContent());
      default ->
        throw InvalidArgumentException.forArgumentAndArgumentName(response, LowerCaseVariableNameCatalog.REPLY);
    };
  }

  /**
   * Lets the current {@link NetEndPoint} wait to and return and remove the
   * received package with the given index.
   * 
   * @param index
   * @return the received package with the given index
   * @throws RuntimeException if the current {@link NetEndPoint} reaches its
   *                          timeout before it receives a package with the given
   *                          index.
   */
  private Package waitToAndGetAndRemoveReceivedPackage(final int index) {
    // This loop suffers from being optimized away by the compiler or the JVM.
    while (!receivedPackage(index)) {
      // Handles the case that the current NetEndPoint is closed.
      if (isClosed()) {
        return null;
      }

      // This statement, which is theoretically unnecessary, makes that the current
      // loop is not optimized away.
      System.err.flush(); // NOSONAR: This statement is used to keep the loop.
    }

    return getAndRemoveReceivedPackage(index);
  }
}
