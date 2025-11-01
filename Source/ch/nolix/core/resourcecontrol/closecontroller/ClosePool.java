package ch.nolix.core.resourcecontrol.closecontroller;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentContainsElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.programcontrol.processproperty.CloseState;
import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.coreapi.resourcecontrol.closecontroller.IClosePool;

/**
 * @author Silvan Wyss
 * @version 2020-07-06
 */
final class ClosePool implements IClosePool {
  private CloseState state = CloseState.OPEN;

  private final LinkedList<GroupCloseable> elements = LinkedList.createEmpty();

  /**
   * Creates a new {@link ClosePool} with the given element.
   * 
   * @param element
   * @throws ArgumentIsNullException if the given element is null.
   */
  public ClosePool(final GroupCloseable element) {
    addElement(element);
  }

  /**
   * @param element
   * @return a new {@link ClosePool} with the given element.
   * @throws ArgumentIsNullException if the given element is null.
   */
  public static ClosePool forElement(final GroupCloseable element) {
    return new ClosePool(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addElements(final IContainer<GroupCloseable> elements) {
    elements.forEach(this::addElement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void closeElementsIfStateIsOpen() {
    if (getState() == CloseState.OPEN) {
      closeElementsWhenStateIsOpen();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<GroupCloseable> getStoredElements() {
    return elements;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CloseState getState() {
    return state;
  }

  /**
   * Adds the given element to the current {@link ClosePool}.
   * 
   * @param element
   * @throws ArgumentIsNullException          if the given element is null.
   * @throws ArgumentContainsElementException if the current {@link ClosePool}
   *                                          contains already the given element.
   */
  private void addElement(GroupCloseable element) {
    assertDoesNotContainElement(element);

    elements.addAtEnd(element);
  }

  /**
   * @param element
   * @throws ArgumentContainsElementException if the current {@link ClosePool}
   *                                          contains the given element.
   */
  private void assertDoesNotContainElement(final GroupCloseable element) {
    if (containsElement(element)) {
      throw ArgumentContainsElementException.forArgumentAndElement(this, element);
    }
  }

  /**
   * Closes the elements of the current {@link IClosePool} for the case that the
   * state of the current {@link IClosePool} is {@link CloseState#OPEN}.
   */
  private void closeElementsWhenStateIsOpen() {
    state = CloseState.ON_CLOSING;

    elements.forEach(ClosePoolHelper::letNoteClose);

    state = CloseState.CLOSED;
  }

  /**
   * @param element
   * @return true if the current {@link ClosePool} contains the given element.
   */
  private boolean containsElement(final GroupCloseable element) {
    return elements.contains(element);
  }
}
