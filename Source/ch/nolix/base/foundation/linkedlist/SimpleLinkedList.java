/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.foundation.linkedlist;

import java.util.Iterator;

import ch.nolix.baseapi.foundation.linkedlist.ISimpleLinkedList;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link SimpleLinkedList}
 */
public final class SimpleLinkedList<E> implements ISimpleLinkedList<E> {
  private int elementCount;

  private SimpleLinkedListNode<E> beginNode;

  private SimpleLinkedListNode<E> endNode;

  private SimpleLinkedList() {
  }

  public static String[] createArrayFromList(final SimpleLinkedList<String> list) {
    final var array = new String[list.getElementCount()];

    var index = 0;
    for (final var e : list) {
      array[index] = e;
      index++;
    }

    return array;
  }

  public static <T> SimpleLinkedList<T> createEmpty() {
    return new SimpleLinkedList<>();
  }

  public static <T> SimpleLinkedList<T> fromArray(final T[] array) {
    final var list = new SimpleLinkedList<T>();

    for (final var e : array) {
      list.addAtEnd(e);
    }

    return list;
  }

  public static <T> SimpleLinkedList<T> withElements(final Iterable<T> elements) {
    final var list = new SimpleLinkedList<T>();

    for (final var e : elements) {
      list.addAtEnd(e);
    }

    return list;
  }

  public static <T> SimpleLinkedList<T> withElements(@SuppressWarnings("unchecked") final T... elements) {
    final var list = new SimpleLinkedList<T>();

    for (final var e : elements) {
      list.addAtEnd(e);
    }

    return list;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addAtEnd(final E element) {
    final var node = SimpleLinkedListNode.withElement(element);

    if (isEmpty()) {
      beginNode = node;
      endNode = node;
    } else {
      endNode.setNextNode(node);
      endNode = node;
    }

    elementCount++;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    beginNode = null;
    endNode = null;
    elementCount = 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getElementCount() {
    return elementCount;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredFirst() {
    assertIsNotEmpty();

    return beginNode.getStoredElement();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return (beginNode == null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterator<E> iterator() {
    if (isEmpty()) {
      return SimpleLinkedListIterator.forEmptyList();
    }

    return SimpleLinkedListIterator.forStartNode(beginNode);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void removeFirst() {
    assertIsNotEmpty();

    if (!beginNode.hasNextNode()) {
      beginNode = null;
      endNode = null;
    } else {
      beginNode = beginNode.getStoredNextNode();
    }

    elementCount--;
  }

  private void assertIsNotEmpty() {
    if (isEmpty()) {
      throw new IllegalStateException("The current List is empty.");
    }
  }
}
