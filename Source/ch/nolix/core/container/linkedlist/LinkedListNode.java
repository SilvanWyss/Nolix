package ch.nolix.core.container.linkedlist;

import java.util.function.Predicate;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * A {@link LinkedListNode} contains an element. A {@link LinkedListNode} can
 * have a next node.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <E> is the type of the element of a {@link LinkedListNode}.
 */
final class LinkedListNode<E> {

  private E element;

  private LinkedListNode<E> nextNode;

  /**
   * Creates a new {@link LinkedListNode} with the given element.
   * 
   * @param element
   * @throws ArgumentIsNullException if the given element is null.
   */
  public LinkedListNode(final E element) {
    setElement(element);
  }

  /**
   * @param selector
   * @return true if the current {@link LinkedListNode} contains an element the
   *         given selector selects.
   */
  public boolean contains(final Predicate<E> selector) {
    return selector.test(getElement());
  }

  /**
   * @param element
   * @return true if the current {@link LinkedListNode} contains the given
   *         element.
   */
  public boolean contains(final Object element) {
    return (getElement() == element);
  }

  /**
   * @return the element of the current {@link LinkedListNode}.
   */
  public E getElement() {
    return element;
  }

  /**
   * @return the next node of the current {@link LinkedListNode}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link LinkedListNode} does not
   *                                               have a next node.
   */
  public LinkedListNode<E> getNextNode() {

    //Asserts that the current list node has a next node.
    if (!hasNextNode()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "next node");
    }

    return nextNode;
  }

  /**
   * @return true if the current {@link LinkedListNode} has a next node.
   */
  public boolean hasNextNode() {
    return (nextNode != null);
  }

  /**
   * Removes the next node of the current {@link LinkedListNode}.
   */
  public void removeNextNode() {
    nextNode = null;
  }

  /**
   * Sets the element of the current {@link LinkedListNode}.
   * 
   * @param element
   * @throws ArgumentIsNullException if the given element is null.
   */
  public void setElement(final E element) {

    //Asserts that the given element is not null.
    GlobalValidator
      .assertThat(element)
      .thatIsNamed(LowerCaseVariableCatalog.ELEMENT)
      .isNotNull();

    //Sets the element of the current list node.
    this.element = element;
  }

  /**
   * Sets the next node of the current {@link LinkedListNode}.
   * 
   * @param nextNode
   * @throws ArgumentIsNullException if the given next node is null.
   */
  public void setNextNode(final LinkedListNode<E> nextNode) {

    //Asserts that the given next node is not null.
    GlobalValidator.assertThat(nextNode).thatIsNamed("next node").isNotNull();

    //Sets the next node of the current list node.
    this.nextNode = nextNode;
  }

  /**
   * Swaps the element of the current {@link LinkedListNode} with the element of
   * the next node of the current {@link LinkedListNode}.
   * 
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link LinkedListNode} does not
   *                                               have a next node.
   */
  public void swapElementWithNextNode() {

    //Asserts that the current list node has a next node.
    if (!hasNextNode()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "next node");
    }

    final var lElement = nextNode.getElement();
    nextNode.setElement(getElement());
    setElement(lElement);
  }
}
