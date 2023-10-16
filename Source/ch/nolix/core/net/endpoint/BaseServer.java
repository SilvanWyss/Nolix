//package declaration
package ch.nolix.core.net.endpoint;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.netapi.endpointapi.IEndPoint;
import ch.nolix.coreapi.netapi.endpointapi.IServer;
import ch.nolix.coreapi.netapi.endpointapi.ISlot;

//class
/**
 * @author Silvan Wyss
 * @date 2017-05-06
 */
public abstract class BaseServer implements IServer {

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //optional attribute
  private ISlot defaultSlot;

  //multi-attribute
  private final LinkedList<ISlot> slots = new LinkedList<>();

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final void addDefaultSlot(final ISlot defaultSlot) {

    addSlot(defaultSlot);

    this.defaultSlot = defaultSlot;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final void addSlot(final ISlot slot) {

    assertDoesNotContainSlotWithName(slot.getName());

    this.slots.addAtEnd(slot);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final void clear() {

    slots.clear();

    defaultSlot = null;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsDefaultSlot() {
    return (defaultSlot != null);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsSlotWithName(final String name) {
    return slots.containsAny(s -> s.hasName(name));
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final CloseController getStoredCloseController() {
    return closeController;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isEmpty() {
    return slots.isEmpty();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeSlotByName(final String name) {
    removeSlot(slots.getStoredFirst(s -> s.hasName(name)));
  }

  //method
  /**
   * Lets the current {@link BaseServer} take the given endPoint.
   * 
   * @param endPoint
   * @throws ClosedArgumentException               if the given endPoint is
   *                                               closed.
   * @throws ArgumentDoesNotHaveAttributeException if the given endPoint does not
   *                                               have a target and the current
   *                                               {@link BaseServer} does not
   *                                               contain a default
   *                                               {@link IEndPointTaker}.
   * @throws ArgumentDoesNotHaveAttributeException if the given endPoint has a
   *                                               target and the current
   *                                               {@link BaseServer} does not
   *                                               contain a
   *                                               {@link IEndPointTaker} with a
   *                                               name that equals the target of
   *                                               the given endPoint.
   */
  final void internalTakeBackendEndPoint(final IEndPoint endPoint) {

    //Asserts that the given endPoint is open.
    assertIsOpen(endPoint);

    //Handles the case that the given endPoint does not have a target.
    if (!endPoint.hasCustomTargetSlot()) {
      getStoredDefaultSlot().takeBackendEndPoint(endPoint);

      //Handles the case that the given endPoint has a target.
    } else {
      getStoredSlotName(endPoint.getCustomTargetSlot()).takeBackendEndPoint(endPoint);
    }
  }

  //method
  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link BaseServer} does not
   *                                               contain a default
   *                                               {@link IEndPointTaker}.
   */
  private void assertContainsDefaultSlot() {
    if (!containsDefaultSlot()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "default end point taker");
    }
  }

  //method
  /**
   * @param name
   * @throws InvalidArgumentException if the current {@link BaseServer} contains
   *                                  already a {@link IEndPointTaker} with the
   *                                  same name as the given endPointTaker.
   */
  private void assertDoesNotContainSlotWithName(final String name) {
    if (containsSlotWithName(name)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
          this,
          "contains already an EndPointTaker with the name '" + name + "'");
    }
  }

  //method
  /**
   * @param endPoint
   * @throws ClosedArgumentException if the given endPoint is closed.
   */
  private void assertIsOpen(final IEndPoint endPoint) {
    if (endPoint.isClosed()) {
      throw ClosedArgumentException.forArgument(endPoint);
    }
  }

  //method
  /**
   * @return the default {@link IEndPointTaker} of the current {@link BaseServer}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link BaseServer} does not
   *                                               contain a default
   *                                               {@link IEndPointTaker}.
   */
  private ISlot getStoredDefaultSlot() {

    assertContainsDefaultSlot();

    return defaultSlot;
  }

  //method
  /**
   * 
   * @param name
   * @return the {@link IEndPointTaker} with the given name from the current
   *         {@link BaseServer}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link BaseServer} does not
   *                                               contain a
   *                                               {@link IEndPointTaker} with the
   *                                               given name.
   */
  private ISlot getStoredSlotName(final String name) {
    return slots.getStoredFirst(ept -> ept.hasName(name));
  }

  //method
  /**
   * Removes the given endPointTaker from the current {@link BaseServer}.
   * 
   * @param endPointTaker
   * @throws InvalidArgumentException if the current {@link BaseServer} does not
   *                                  contain the given endPointTaker.
   */
  private void removeSlot(final ISlot endPointTaker) {

    slots.removeFirstOccurrenceOf(endPointTaker);

    if (endPointTaker == defaultSlot) {
      defaultSlot = null;
    }
  }
}
