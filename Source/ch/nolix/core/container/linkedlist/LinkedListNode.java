/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.container.linkedlist;

import java.util.function.Predicate;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * A {@link LinkedListNode} contains an element and can have a next node.
 * 
 * @author Silvan Wyss
 * @param <E> is the type of the element of a {@link LinkedListNode}.
 */
public final class LinkedListNode<E> {
  private E memberElement;

  private LinkedListNode<E> nullableNextNode;

  /**
   * Creates a new {@link LinkedListNode} with the given element.
   * 
   * @param element
   * @throws RuntimeException if the given element is null.
   */
  private LinkedListNode(final E element) {
    setElement(element);
  }

  /**
   * @param element
   * @param <T>     is the type of the given element.
   * @return a new {@link LinkedListNode} with the given element.
   * @throws RuntimeException if the given element is null.
   */
  public static <T> LinkedListNode<T> withElement(final T element) {
    return new LinkedListNode<>(element);
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * @param selector
   * @return true if the current {@link LinkedListNode} contains an element the
   *         given selector selects, false otherwise.
   */
  public boolean contains(final Predicate<E> selector) {
    return selector.test(memberElement);
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * @param element
   * @return true if the current {@link LinkedListNode} contains the given
   *         element, false otherwise.
   */
  public boolean contains(final Object element) {
    return (memberElement == element);
  }

  /**
   * @return the element of the current {@link LinkedListNode}.
   */
  public E getElement() {
    return memberElement;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * @return the next node of the current {@link LinkedListNode}.
   * @throws RuntimeException if the current {@link LinkedListNode} does not have
   *                          a next node.
   */
  public LinkedListNode<E> getNextNode() {
    if (nullableNextNode == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "next node");
    }

    return nullableNextNode;
  }

  /**
   * @return true if the current {@link LinkedListNode} has a next node, false
   *         otherwise.
   */
  public boolean hasNextNode() {
    return (nullableNextNode != null);
  }

  /**
   * Removes the next node of the current {@link LinkedListNode}.
   */
  public void removeNextNode() {
    nullableNextNode = null;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * Sets the element of the current {@link LinkedListNode}.
   * 
   * @param element
   * @throws RuntimeException if the given element is null.
   */
  public void setElement(final E element) {
    if (element == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.ELEMENT);
    }

    memberElement = element;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * Sets the next node of the current {@link LinkedListNode}.
   * 
   * @param nextNode
   * @throws RuntimeException if the given next node is null.
   */
  public void setNextNode(final LinkedListNode<E> nextNode) {
    if (nextNode == null) {
      throw ArgumentIsNullException.forArgumentName("next node");
    }

    nullableNextNode = nextNode;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * Swaps the element of the current {@link LinkedListNode} with the element of
   * the next node of the current {@link LinkedListNode}.
   * 
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link LinkedListNode} does not
   *                                               have a next node.
   */
  public void swapElementWithNextNode() {
    if (nullableNextNode == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "next node");
    }

    final var element = nullableNextNode.getElement();
    nullableNextNode.setElement(memberElement);
    setElement(element);
  }
}
