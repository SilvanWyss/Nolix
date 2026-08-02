/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.resourcecontrol.closecontroller;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentContainsElementException;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.IClosePool;
import ch.nolix.baseapi.state.stateproperty.Openness;

/**
 * @author Silvan Wyss
 */
final class ClosePool implements IClosePool {
  private Openness state = Openness.OPEN;

  private final LinkedList<GroupCloseable> memberElements = LinkedList.createEmpty();

  /**
   * Creates a new {@link ClosePool} with the given element.
   * 
   * @param element
   * @throws RuntimeException if the given element is null
   */
  private ClosePool(final GroupCloseable element) {
    addElement(element);
  }

  /**
   * @param element
   * @return a new {@link ClosePool} with the given element
   * @throws RuntimeException if the given element is null
   */
  public static ClosePool forElement(final GroupCloseable element) {
    return new ClosePool(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addElements(final ExtendedIterable<GroupCloseable> elements) {
    elements.forEach(this::addElement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void closeElementsIfStateIsOpen() {
    if (getState() == Openness.OPEN) {
      closeElementsWhenStateIsOpen();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<GroupCloseable> getStoredElements() {
    return memberElements;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Openness getState() {
    return state;
  }

  /**
   * Adds the given element to the current {@link ClosePool}.
   * 
   * @param element
   * @throws RuntimeException if the given element is null
   * @throws RuntimeException if the current {@link ClosePool} contains already
   *                          the given element.
   */
  private void addElement(GroupCloseable element) {
    assertDoesNotContainElement(element);

    memberElements.addAtEnd(element);
  }

  /**
   * @param element
   * @throws RuntimeException if the current {@link ClosePool} contains the given
   *                          element.
   */
  private void assertDoesNotContainElement(final GroupCloseable element) {
    if (containsElement(element)) {
      throw ArgumentContainsElementException.forArgumentAndElement(this, element);
    }
  }

  /**
   * Closes the elements of the current {@link IClosePool} for the case that the
   * state of the current {@link IClosePool} is {@link Openness#OPEN}.
   */
  private void closeElementsWhenStateIsOpen() {
    state = Openness.ON_CLOSING;

    memberElements.forEach(ClosePoolHelper::letNoteClose);

    state = Openness.CLOSED;
  }

  /**
   * @param element
   * @return true if the current {@link ClosePool} contains the given element,
   *         false otherwise
   */
  private boolean containsElement(final GroupCloseable element) {
    return memberElements.contains(element);
  }
}
