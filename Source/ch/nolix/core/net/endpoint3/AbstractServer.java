package ch.nolix.core.net.endpoint3;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.resourcecontrol.closecontroller.CloseController;
import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.coreapi.net.endpoint3.IEndPoint;
import ch.nolix.coreapi.net.endpoint3.IServer;
import ch.nolix.coreapi.net.endpoint3.ISlot;
import ch.nolix.coreapi.resourcecontrol.closecontroller.ICloseController;

/**
 * @author Silvan Wyss
 * @version 2017-06-16
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

    addSlotToList(defaultSlot);

    this.defaultSlot = defaultSlot;

    noteAddedDefaultSlot(defaultSlot);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void addSlot(final ISlot slot) {

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
    return (defaultSlot != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsSlotWithName(final String name) {
    return slots.containsAny(ept -> ept.hasName(name));
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
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeSlotByName(final String name) {
    removeSlot(slots.getStoredFirst(s -> s.hasName(name)));
  }

  /**
   * Notes that the given defaultSlot has been added to the current
   * {@link AbstractServer}.
   * 
   * @param defaultSlot
   */
  protected abstract void noteAddedDefaultSlot(ISlot defaultSlot);

  /**
   * Notes that the given slot has been added to the current
   * {@link AbstractServer}.
   * 
   * @param slot
   */
  protected abstract void noteAddedSlot(ISlot slot);

  /**
   * Notes that the given slot has been removed from the current
   * {@link AbstractServer}.
   * 
   * @param slot
   */
  protected abstract void noteRemovedSlot(ISlot slot);

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

    ResourceValidator.assertIsOpen(endPoint);

    if (!endPoint.hasCustomTargetSlot()) {
      getStoredDefaultSlot().takeBackendEndPoint(endPoint);
    } else {
      getStoredSlotByName(endPoint.getCustomTargetSlot()).takeBackendEndPoint(endPoint);
    }
  }

  /**
   * Adds the given slot to the list of {@link ISlot}s of the current
   * {@link AbstractServer}.
   * 
   * @param slot
   * @throws InvalidArgumentException if the current {@link AbstractServer}
   *                                  contains already a {@link ISlot} with the
   *                                  same name like the given slot.
   */
  private void addSlotToList(final ISlot slot) {

    assertDoesNotContainSlotWithName(slot.getName());

    this.slots.addAtEnd(slot);
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
   *                                  same name like the given slot.
   */
  private void assertDoesNotContainSlotWithName(final String name) {
    if (containsSlotWithName(name)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this, "contains already a slot with the name '" + name + "'");
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
  private ISlot getStoredSlotByName(final String name) {
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

    noteRemovedSlot(slot);
  }
}
