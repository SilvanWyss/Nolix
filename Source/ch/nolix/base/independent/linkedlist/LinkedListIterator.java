/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.independent.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of the parent {@link LinkedList} of a
 *            {@link LinkedListIterator}.
 */
public final class LinkedListIterator<E> implements Iterator<E> {
  private LinkedListNode<E> nextNode;

  private LinkedListIterator() {
  }

  private LinkedListIterator(final LinkedListNode<E> startNode) {
    this.nextNode = startNode;
  }

  public static <T> LinkedListIterator<T> forEmptyList() {
    return new LinkedListIterator<>();
  }

  public static <T> LinkedListIterator<T> forStartNode(final LinkedListNode<T> startNode) {
    return new LinkedListIterator<>(startNode);
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
