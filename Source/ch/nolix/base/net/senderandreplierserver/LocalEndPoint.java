/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.senderandreplierserver;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.net.netproperty.ConnectionType;
import ch.nolix.baseapi.net.netproperty.PeerType;
import ch.nolix.baseapi.net.netproperty.SecurityMode;
import ch.nolix.baseapi.net.senderandreplierserver.Slot;

/**
 * A local end point can send messages to an other local end point.
 * 
 * @author Silvan Wyss
 */
public final class LocalEndPoint extends AbstractEndPoint {
  private final PeerType peerType;

  private final LocalEndPoint counterpart;

  private final String target;

  /**
   * Creates a new {@link LocalEndPoint} that will connect to the given slot.
   * 
   * @param slot
   * @throws NullPointerException if the given slot is null
   */
  private LocalEndPoint(final Slot slot) {
    this.peerType = PeerType.FRONTEND;
    this.counterpart = new LocalEndPoint(this, slot.getName());
    this.target = null;

    slot.takeBackendEndPoint(getStoredCounterpart());
  }

  /**
   * Creates a new {@link LocalEndPoint} that will connect to the given slot on
   * the given server.
   * 
   * @param slot
   * @param server
   * @throws NullPointerException if the given server is null
   * @throws RuntimeException     if the given slot is null
   * @throws RuntimeException     if the given slot is null
   */
  private LocalEndPoint(final AbstractServer server, final String slot) {
    this.peerType = PeerType.FRONTEND;
    this.counterpart = new LocalEndPoint(this, slot);
    this.target = slot;

    server.internalTakeBackendEndPoint(getStoredCounterpart());
  }

  /**
   * Creates a new local end point with the given counterpart and target.
   * 
   * @param counterpart
   * @param target
   * @throws RuntimeException if the given counterpart is null
   * @throws RuntimeException if the given target is null
   * @throws RuntimeException if the given target is empty
   */
  private LocalEndPoint(final LocalEndPoint counterpart, final String target) {
    peerType = PeerType.BACKEND;

    // Asserts that the given counter part is not null.
    Validator.assertThat(counterpart).thatIsNamed("counterpart").isNotNull();

    // Sets the counter part of this local end point.
    this.counterpart = counterpart;

    // Asserts that the given target is not null or empty.
    Validator.assertThat(target).thatIsNamed("target").isNotEmpty();

    // Sets the target of this local end point.
    this.target = target;
  }

  /**
   * @param slot
   * @return a new {@link LocalEndPoint} that will connect to the given slot
   * @throws NullPointerException if the given slot is null
   */
  public static LocalEndPoint toSlot(final Slot slot) {
    return new LocalEndPoint(slot);
  }

  /**
   * @param server
   * @param slot
   * @return a new {@link LocalEndPoint} that will connect to the given slot on
   *         the given server
   * @throws NullPointerException if the given server is null
   * @throws RuntimeException     if the given slot is null
   * @throws RuntimeException     if the given slot is null
   */
  public static LocalEndPoint toServerAndSlot(final AbstractServer server, final String slot) {
    return new LocalEndPoint(server, slot);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ConnectionType getConnectionType() {
    return ConnectionType.LOCAL;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public PeerType getPeerType() {
    return peerType;
  }

  /**
   * Lets this local end point send the given message.
   * 
   * @param message
   * @return the reply to the given message
   * @throws RuntimeException if this local end point is closed
   */
  public String sendAndWaitToReply(final String message) {
    // Asserts that this local end point is open.
    assertIsOpen();

    return getStoredCounterpart().receiveAndGetReply(message);
  }

  /**
   * @return the counterpart of this local end point.
   */
  public LocalEndPoint getStoredCounterpart() {
    return counterpart;
  }

  /**
   * @return the target of this local end point
   * @throws ArgumentDoesNotHaveAttributeException if this local end point does
   *                                               not have a target.
   */
  @Override
  public String getCustomTargetSlot() {
    // Asserts that this local end point has a target.
    if (!hasCustomTargetSlot()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "target");
    }

    return target;
  }

  /**
   * Lets this local end point send the given message.
   * 
   * @param message
   * @return the reply to the given message from this local end point.
   */
  @Override
  public String getReplyForMessage(final String message) {
    return getStoredCounterpart().receiveAndGetReply(message);
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
   * Lets this local end point receive the given message.
   * 
   * @param message
   * @return the reply to the given message.
   */
  private String receiveAndGetReply(final String message) {
    return getStoredReplier().apply(message);
  }
}
