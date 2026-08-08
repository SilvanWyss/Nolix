/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.senderandreceiverserver;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.resourcecontrol.closecontroller.CloseController;
import ch.nolix.base.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.net.senderandreceiverserver.EndPoint;
import ch.nolix.baseapi.net.senderandreceiverserver.Server;
import ch.nolix.baseapi.net.senderandreceiverserver.Slot;
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
    addSlot(defaultSlot);

    memberDefaultSlot = defaultSlot;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void addSlot(final Slot slot) {
    assertDoesNotContainSlotWithName(slot.getName());

    this.slots.addAtEnd(slot);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void clear() {
    slots.clear();

    memberDefaultSlot = null;
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
    return slots.containsMatching(s -> s.hasName(name));
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
  public final void removeSlotByName(final String name) {
    removeSlot(slots.getStoredFirst(s -> s.hasName(name)));
  }

  /**
   * Lets the current {@link AbstractServer} take the given endPoint.
   * 
   * @param endPoint
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
  final void internalTakeBackendEndPoint(final EndPoint endPoint) {
    // Asserts that the given endPoint is open.
    ResourceValidator.assertIsOpen(endPoint);

    // Handles the case that the given endPoint does not have a target.
    if (!endPoint.hasCustomTargetSlot()) {
      getStoredDefaultSlot().takeBackendEndPoint(endPoint);

      // Handles the case that the given endPoint has a target.
    } else {
      getStoredSlotName(endPoint.getCustomTargetSlot()).takeBackendEndPoint(endPoint);
    }
  }

  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractServer} does not
   *                                               contain a default
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
   *                          already a {@link Slot} with the given name.
   */
  private void assertDoesNotContainSlotWithName(final String name) {
    if (containsSlotWithName(name)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "contains already a slot with the name '" + name + "'");
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
  private Slot getStoredSlotName(final String name) {
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
  }
}
