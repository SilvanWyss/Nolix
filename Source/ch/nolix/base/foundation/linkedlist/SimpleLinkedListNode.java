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

  public static <T> SimpleLinkedListNode<T> withElement(final T element) {
    return new SimpleLinkedListNode<>(element);
  }

  public boolean contains(final E element) {
    return (memberElement == element);
  }

  public E getStoredElement() {
    return memberElement;
  }

  public SimpleLinkedListNode<E> getStoredNextNode() {
    assertHasNextNode();

    return nextNode;
  }

  public boolean hasNextNode() {
    return (nextNode != null);
  }

  public void removeNextNode() {
    nextNode = null;
  }

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
