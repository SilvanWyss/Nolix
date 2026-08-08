/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.executoranddataproviderserver;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.chainednode.IChainedNode;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.net.executoranddataproviderserver.Slot;
import ch.nolix.baseapi.net.netproperty.ConnectionType;
import ch.nolix.baseapi.net.netproperty.PeerType;
import ch.nolix.baseapi.net.netproperty.SecurityMode;

/**
 * A {@link LocalEndPoint} can communicate with another {@link LocalEndPoint}.
 * 
 * @author Silvan Wyss
 */
public final class LocalEndPoint extends AbstractEndPoint {
  private final PeerType peerType;

  private final LocalEndPoint counterpart;

  private final String customTargetSlot;

  /**
   * Creates a new {@link LocalEndPoint} that will connect to another new local
   * duplex controller.
   */
  public LocalEndPoint() {
    peerType = PeerType.FRONTEND;

    // Creates the counterpart of this {@link LocalEndPoint}.
    this.counterpart = new LocalEndPoint(this);

    // Clears the target of this {@link LocalEndPoint}.
    customTargetSlot = null;
  }

  /**
   * Creates a new {@link LocalEndPoint} that will connect to the given slot.
   * 
   * @param slot
   * @throws RuntimeException if the given slot is null
   */
  private LocalEndPoint(final Slot slot) {
    this.peerType = PeerType.FRONTEND;
    this.counterpart = new LocalEndPoint(this, slot.getName());
    this.customTargetSlot = null;

    slot.takeBackendEndPoint(getStoredCounterpart());
  }

  /**
   * Creates a new {@link LocalEndPoint} with the given counterpart.
   * 
   * @param counterpart
   * @throws RuntimeException if the given counterpart is null
   */
  private LocalEndPoint(LocalEndPoint counterpart) {
    peerType = PeerType.BACKEND;

    // Asserts that the given counterpart is not null.
    Validator.assertThat(counterpart).thatIsNamed("counterpart").isNotNull();

    // Sets the counterpart of this {@link LocalEndPoint}.
    this.counterpart = counterpart;

    createCloseDependencyTo(counterpart);

    // Clears the target of this {@link LocalEndPoint}.
    customTargetSlot = null;
  }

  /**
   * Creates a new {@link LocalEndPoint} with the given counterpart and target.
   * 
   * @param counterpart
   * @param target
   * @throws RuntimeException if the given target is null
   * @throws RuntimeException if the given target is empty
   */
  private LocalEndPoint(
    final LocalEndPoint counterpart,
    final String target) {
    peerType = PeerType.BACKEND;

    // Asserts that the given counterpart is not null.
    Validator.assertThat(counterpart).thatIsNamed("counterpart").isNotNull();

    // Sets the counterpart of this {@link LocalEndPoint}.
    this.counterpart = counterpart;

    // Asserts that the given target is not null or empty.
    Validator.assertThat(target).thatIsNamed("target").isNotEmpty();

    // Sets the target of this {@link LocalEndPoint}.
    this.customTargetSlot = target;
  }

  /**
   * @param slot
   * @return a new {@link LocalEndPoint} that will connect to the given slot
   * @throws RuntimeException if the given slot is null
   */
  public static LocalEndPoint toSlot(final Slot slot) {
    return new LocalEndPoint(slot);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ConnectionType getConnectionType() {
    return ConnectionType.LOCAL;
  }

  /**
   * @return the data the given request requests from this local duplex controller
   * @throws ArgumentDoesNotHaveAttributeException if this {@link LocalEndPoint}
   *                                               does not have a receiver
   *                                               controller.
   */
  @Override
  public Node<?> getDataForRequest(final IChainedNode request) {
    return counterpart.getStoredReceiverController().getDataForRequest(request);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends Node<?>> getDataForRequests(final IChainedNode... requests) {
    return counterpart.getStoredReceiverController().getDataForRequests(requests);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<? extends Node<?>> getDataForRequests(final Iterable<? extends IChainedNode> requests) {
    return counterpart.getStoredReceiverController().getDataForRequests(requests);
  }

  /**
   * @return the counterpart of this {@link LocalEndPoint}.
   */
  public LocalEndPoint getStoredCounterpart() {
    return counterpart;
  }

  /**
   * @return the target of this {@link LocalEndPoint}.
   */
  @Override
  public String getCustomTargetSlot() {
    return customTargetSlot;
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
    return (customTargetSlot != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void runCommand(final IChainedNode command) {
    assertIsOpen();

    counterpart.getStoredReceiverController().runCommand(command);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void runCommands(final Iterable<? extends IChainedNode> commands) {
    assertIsOpen();

    commands.forEach(counterpart.getStoredReceiverController()::runCommand);
  }
}
