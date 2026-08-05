/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.foundation.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of the parent {@link SimpleLinkedList} of
 *            a {@link SimpleLinkedListIterator}.
 */
public final class SimpleLinkedListIterator<E> implements Iterator<E> {
  private SimpleLinkedListNode<E> nextNode;

  private SimpleLinkedListIterator() {
  }

  private SimpleLinkedListIterator(final SimpleLinkedListNode<E> startNode) {
    this.nextNode = startNode;
  }

  public static <T> SimpleLinkedListIterator<T> forEmptyList() {
    return new SimpleLinkedListIterator<>();
  }

  public static <T> SimpleLinkedListIterator<T> forStartNode(final SimpleLinkedListNode<T> startNode) {
    return new SimpleLinkedListIterator<>(startNode);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return (nextNode != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E next() throws NoSuchElementException {
    assertHasNext();

    final var element = nextNode.getStoredElement();

    if (nextNode.hasNextNode()) {
      nextNode = nextNode.getStoredNextNode();
    } else {
      nextNode = null;
    }

    return element;
  }

  private void assertHasNext() throws NoSuchElementException {
    if (nextNode == null) {
      throw new NoSuchElementException("The current ListIterator does not have a next element.");
    }
  }
}
