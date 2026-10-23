/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.foundation.linkedlist;

import java.util.NoSuchElementException;

final class SimpleLinkedListNode<E> {
  private final E memberElement;

  private SimpleLinkedListNode<E> nextNode;

  private SimpleLinkedListNode(final E element) {
    memberElement = element;
  }

  /**
   * @param element
   * @param <T>     the type of the given element
   * @return a new {@link SimpleLinkedListNode} with the given element
   */
  public static <T> SimpleLinkedListNode<T> withElement(final T element) {
    return new SimpleLinkedListNode<>(element);
  }

  /**
   * @param element
   * @return true if the current {@link SimpleLinkedListNode} contains the given
   *         element, false otherwise
   */
  public boolean contains(final E element) {
    return memberElement == element;
  }

  /**
   * @return the element of the current {@link SimpleLinkedListNode}
   */
  public E getStoredElement() {
    return memberElement;
  }

  /**
   * @return the next node of the current {@link SimpleLinkedListNode}
   * @throws RuntimeException if the current {@link SimpleLinkedListNode} does not
   *                          have a next node
   */
  public SimpleLinkedListNode<E> getStoredNextNode() {
    assertHasNextNode();

    return nextNode;
  }

  /**
   * @return true if the current {@link SimpleLinkedListNode} has a next node,
   *         false otherwise
   */
  public boolean hasNextNode() {
    return nextNode != null;
  }

  /**
   * Removes the next node of the current {@link SimpleLinkedListNode}.
   */
  public void removeNextNode() {
    nextNode = null;
  }

  /**
   * Sets the given nextNode to the current {@link SimpleLinkedListNode}.
   * 
   * @param nextNode
   */
  public void setNextNode(final SimpleLinkedListNode<E> nextNode) {
    if (nextNode == null) {
      throw new IllegalArgumentException("The given next node is null.");
    }

    this.nextNode = nextNode;
  }

  private void assertHasNextNode() {
    if (!hasNextNode()) {
      throw new NoSuchElementException("The current ListIterator does not have a next node.");
    }
  }
}
