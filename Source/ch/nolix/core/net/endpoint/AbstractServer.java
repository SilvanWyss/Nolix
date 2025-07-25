package ch.nolix.core.net.endpoint;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.net.endpoint.IEndPoint;
import ch.nolix.coreapi.net.endpoint.IServer;
import ch.nolix.coreapi.net.endpoint.ISlot;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;

/**
 * @author Silvan Wyss
 * @version 2017-05-06
 */
public abstract class AbstractServer implements IServer {

  private final ICloseController closeController = CloseController.forElement(this);

  private ISlot defaultSlot;

  private final LinkedList<ISlot> slots = LinkedList.createEmpty();

  /**
   * {@inheritDoc}
   */
  @Override
  public final void addDefaultSlot(final ISlot defaultSlot) {

    addSlot(defaultSlot);

    this.defaultSlot = defaultSlot;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void addSlot(final ISlot slot) {

    assertDoesNotContainSlotWithName(slot.getName());

    this.slots.addAtEnd(slot);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void clear() {

    slots.clear();

    defaultSlot = null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsDefaultSlot() {
    return (defaultSlot != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsSlotWithName(final String name) {
    return slots.containsAny(s -> s.hasName(name));
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
   * @throws ClosedArgumentException               if the given endPoint is
   *                                               closed.
   * @throws ArgumentDoesNotHaveAttributeException if the given endPoint does not
   *                                               have a target and the current
   *                                               {@link AbstractServer} does not
   *                                               contain a default
   *                                               {@link ISlot}.
   * @throws ArgumentDoesNotHaveAttributeException if the given endPoint has a
   *                                               target and the current
   *                                               {@link AbstractServer} does not
   *                                               contain a {@link ISlot} with a
   *                                               name that equals the target of
   *                                               the given endPoint.
   */
  final void internalTakeBackendEndPoint(final IEndPoint endPoint) {

    //Asserts that the given endPoint is open.
    ResourceValidator.assertIsOpen(endPoint);

    //Handles the case that the given endPoint does not have a target.
    if (!endPoint.hasCustomTargetSlot()) {
      getStoredDefaultSlot().takeBackendEndPoint(endPoint);

      //Handles the case that the given endPoint has a target.
    } else {
      getStoredSlotName(endPoint.getCustomTargetSlot()).takeBackendEndPoint(endPoint);
    }
  }

  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractServer} does not
   *                                               contain a default
   *                                               {@link ISlot}.
   */
  private void assertContainsDefaultSlot() {
    if (!containsDefaultSlot()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "default end point taker");
    }
  }

  /**
   * @param name
   * @throws InvalidArgumentException if the current {@link AbstractServer}
   *                                  contains already a {@link ISlot} with the
   *                                  given name.
   */
  private void assertDoesNotContainSlotWithName(final String name) {
    if (containsSlotWithName(name)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "contains already a slot with the name '" + name + "'");
    }
  }

  /**
   * @return the default {@link ISlot} of the current {@link AbstractServer}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractServer} does not
   *                                               contain a default
   *                                               {@link ISlot}.
   */
  private ISlot getStoredDefaultSlot() {

    assertContainsDefaultSlot();

    return defaultSlot;
  }

  /**
   * 
   * @param name
   * @return the {@link ISlot} with the given name from the current
   *         {@link AbstractServer}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractServer} does not
   *                                               contain a {@link ISlot} with
   *                                               the given name.
   */
  private ISlot getStoredSlotName(final String name) {
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
  private void removeSlot(final ISlot slot) {

    slots.removeStrictlyFirstOccurrenceOf(slot);

    if (slot == defaultSlot) {
      defaultSlot = null;
    }
  }
}
