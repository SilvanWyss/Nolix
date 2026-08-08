/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.senderandreplierserver;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.resourcecontrol.closecontroller.CloseController;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.net.senderandreplierserver.Server;
import ch.nolix.baseapi.net.senderandreplierserver.Slot;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractServer implements Server {
  private final ICloseController closeController = CloseController.forElement(this);

  private Slot memberDefaultSlot;

  private final LinkedList<Slot> slots = LinkedList.createEmpty();

  /**
   * {@inheritDoc}
   */
  @Override
  public final void addDefaultSlot(final Slot defaultSlot) {
    addSlotToList(defaultSlot);

    memberDefaultSlot = defaultSlot;

    noteAddedDefaultSlot(defaultSlot);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void addSlot(final Slot slot) {
    addSlotToList(slot);

    noteAddedSlot(slot);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void clear() {
    slots.forEach(this::removeSlot);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsDefaultSlot() {
    return (memberDefaultSlot != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsSlotWithName(final String name) {
    return slots.containsMatching(ept -> ept.hasName(name));
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
  public final boolean isEmpty() {
    return slots.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void noteClose() {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeSlotByName(final String name) {
    removeSlot(slots.getStoredFirst(s -> s.hasName(name)));
  }

  /**
   * Lets the current {@link AbstractServer} take the given endPoint.
   * 
   * @param abstractEndPoint
   * @throws RuntimeException                      if the given endPoint is closed
   * @throws ArgumentDoesNotHaveAttributeException if the given endPoint does not
   *                                               have a target and the current
   *                                               {@link AbstractServer} does not
   *                                               contain a default {@link Slot}
   * @throws ArgumentDoesNotHaveAttributeException if the given endPoint has a
   *                                               target and the current
   *                                               {@link AbstractServer} does not
   *                                               contain a {@link Slot} with a
   *                                               name that equals the target of
   *                                               the given endPoint.
   */
  final void internalTakeBackendEndPoint(final AbstractEndPoint abstractEndPoint) {
    abstractEndPoint.assertIsOpen();

    if (!abstractEndPoint.hasCustomTargetSlot()) {
      getStoredDefaultSlot().takeBackendEndPoint(abstractEndPoint);
    } else {
      getStoredSlotByName(abstractEndPoint.getCustomTargetSlot()).takeBackendEndPoint(abstractEndPoint);
    }
  }

  /**
   * Notes that the given defaultSlot has been added to the current
   * {@link AbstractServer}.
   * 
   * @param defaultSlot
   */
  protected abstract void noteAddedDefaultSlot(Slot defaultSlot);

  /**
   * Notes that the given slot has been added to the current
   * {@link AbstractServer}.
   * 
   * @param slot
   */
  protected abstract void noteAddedSlot(Slot slot);

  /**
   * Notes that the given slot has been removed from the current
   * {@link AbstractServer}.
   * 
   * @param slot
   */
  protected abstract void noteRemovedSlot(Slot slot);

  /**
   * Adds the given slot to the list of {@link Slot}s of the current
   * {@link AbstractServer}.
   * 
   * @param slot
   * @throws RuntimeException if the current {@link AbstractServer} contains
   *                          already a {@link Slot} with the same name like the
   *                          given slot.
   */
  private void addSlotToList(Slot slot) {
    assertDoesNotContainSlotWithName(slot.getName());

    this.slots.addAtEnd(slot);
  }

  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractServer} does not
   *                                               contains a default
   *                                               {@link Slot}.
   */
  private void assertContainsDefaultSlot() {
    if (!containsDefaultSlot()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "default end point taker");
    }
  }

  /**
   * @param name
   * @throws RuntimeException if the current {@link AbstractServer} contains
   *                          already a {@link Slot} with the same name as the
   *                          given slot.
   */
  private void assertDoesNotContainSlotWithName(final String name) {
    if (containsSlotWithName(name)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "contains already a Slot with the name '" + name + "'");
    }
  }

  /**
   * @return the default {@link Slot} of the current {@link AbstractServer}
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractServer} does not
   *                                               contain a default
   *                                               {@link Slot}.
   */
  private Slot getStoredDefaultSlot() {
    assertContainsDefaultSlot();

    return memberDefaultSlot;
  }

  /**
   * 
   * @param name
   * @return the {@link Slot} with the given name from the current
   *         {@link AbstractServer}
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractServer} does not
   *                                               contain a {@link Slot} with
   *                                               the given name.
   */
  private Slot getStoredSlotByName(final String name) {
    return slots.getStoredFirst(ept -> ept.hasName(name));
  }

  /**
   * Removes the given slot from the current {@link AbstractServer}.
   * 
   * @param slot
   * @throws ArgumentDoesNotContainElementException if the current
   *                                                {@link AbstractServer} does
   *                                                not contain the given slot.
   */
  private void removeSlot(final Slot slot) {
    slots.removeStrictlyFirstOccurrenceOf(slot);

    if (slot == memberDefaultSlot) {
      memberDefaultSlot = null;
    }

    noteRemovedSlot(slot);
  }
}
