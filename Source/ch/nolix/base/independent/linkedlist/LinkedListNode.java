/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.independent.linkedlist;

import java.util.NoSuchElementException;

final class LinkedListNode<E> {
  private final E memberElement;

  private LinkedListNode<E> nextNode;

  private LinkedListNode(final E element) {
    if (element == null) {
      throw new IllegalArgumentException("The given element is null.");
    }

    memberElement = element;
  }

  public static <T> LinkedListNode<T> withElement(final T element) {
    return new LinkedListNode<>(element);
  }

  public boolean contains(final E element) {
    return (memberElement == element);
  }

  public E getStoredElement() {
    return memberElement;
  }

  public LinkedListNode<E> getStoredNextNode() {
    assertHasNextNode();

    return nextNode;
  }

  public boolean hasNextNode() {
    return (nextNode != null);
  }

  public void removeNextNode() {
    nextNode = null;
  }

  public void setNextNode(final LinkedListNode<E> nextNode) {
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
