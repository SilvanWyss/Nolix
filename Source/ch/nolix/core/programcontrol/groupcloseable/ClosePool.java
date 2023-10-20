//package declaration
package ch.nolix.core.programcontrol.groupcloseable;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentContainsElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.logger.GlobalLogger;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.processproperty.CloseState;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.IClosePool;

//class
/**
 * @author Silvan Wyss
 * @date 2020-07-06
 */
final class ClosePool implements IClosePool {

  //attribute
  private CloseState state = CloseState.OPEN;

  //multi-attribute
  private final LinkedList<GroupCloseable> elements = new LinkedList<>();

  //constructor
  /**
   * Creates a new {@link ClosePool} with the given element.
   * 
   * @param element
   * @throws ArgumentIsNullException if the given element is null.
   */
  public ClosePool(final GroupCloseable element) {
    addElement(element);
  }

  //static method
  /**
   * @param element
   * @return a new {@link ClosePool} with the given element.
   * @throws ArgumentIsNullException if the given element is null.
   */
  public static ClosePool forElement(final GroupCloseable element) {
    return new ClosePool(element);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public void addElements(final IContainer<GroupCloseable> elements) {
    elements.forEach(this::addElement);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public void closeElementsIfStateIsOpen() {
    if (getState() == CloseState.OPEN) {
      closeElementsWhenStateIsOpen();
    }
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<GroupCloseable> getStoredElements() {
    return elements;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public CloseState getState() {
    return state;
  }

  //method
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

  //method
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

  //method
  /**
   * Closes the elements of the current {@link IClosePool} for the case that the
   * state of the current {@link IClosePool} is {@link CloseState#OPEN}.
   */
  private void closeElementsWhenStateIsOpen() {

    state = CloseState.ON_CLOSING;

    elements.forEach(this::letNoteClose);

    state = CloseState.CLOSED;
  }

  //method
  /**
   * @param element
   * @return true if the current {@link ClosePool} contains the given element.
   */
  private boolean containsElement(final GroupCloseable element) {
    return elements.contains(element);
  }

  //method
  /**
   * Lets the given element note a close.
   * 
   * @param element
   */
  private void letNoteClose(final GroupCloseable element) {
    try {
      element.noteClose();
    } catch (final Throwable exception) { //NOSONAR: All Throwables must be caught here.
      GlobalLogger.logError(exception);
    }
  }
}
