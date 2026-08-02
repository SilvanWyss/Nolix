/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.independent.linkedlist;

import java.util.Iterator;
import java.util.function.Function;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link LinkedList}.
 */
public final class LinkedList<E> implements Iterable<E> {
  private int elementCount;

  private LinkedListNode<E> beginNode;

  private LinkedListNode<E> endNode;

  private LinkedList() {
  }

  public static String[] createArrayFromList(final LinkedList<String> list) {
    final var array = new String[list.getElementCount()];

    var index = 0;
    for (final var e : list) {
      array[index] = e;
      index++;
    }

    return array;
  }

  public static <T> LinkedList<T> createEmpty() {
    return new LinkedList<>();
  }

  public static <T> LinkedList<T> fromArray(final T[] array) {
    final var list = new LinkedList<T>();

    for (final var e : array) {
      list.addAtBegin(e);
    }

    return list;
  }

  public static <T> LinkedList<T> withElements(final Iterable<T> elements) {
    final var list = new LinkedList<T>();

    for (final var e : elements) {
      list.addAtEnd(e);
    }

    return list;
  }

  public void addAtBegin(final E element) {
    final LinkedListNode<E> node = LinkedListNode.withElement(element);

    if (isEmpty()) {
      beginNode = node;
      endNode = node;
    } else {
      node.setNextNode(beginNode);
      beginNode = node;
    }

    elementCount++;
  }

  public void addAtEnd(final E element) {
    final var node = LinkedListNode.withElement(element);

    if (isEmpty()) {
      beginNode = node;
      endNode = node;
    } else {
      endNode.setNextNode(node);
      endNode = node;
    }

    elementCount++;
  }

  public void clear() {
    beginNode = null;
    endNode = null;
    elementCount = 0;
  }

  public LinkedList<E> getCopy() {
    final var list = new LinkedList<E>();

    for (final var e : this) {
      list.addAtEnd(e);
    }

    return list;
  }

  public int getElementCount() {
    return elementCount;
  }

  public E getStoredFirst() {
    assertIsNotEmpty();

    return beginNode.getStoredElement();
  }

  public boolean isEmpty() {
    return (beginNode == null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterator<E> iterator() {
    if (isEmpty()) {
      return LinkedListIterator.forEmptyList();
    }

    return LinkedListIterator.forStartNode(beginNode);
  }

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

  public void removeFirstOccurrenceOf(final E element) {
    if (!isEmpty()) {
      removeFirstOccuranceOfWhenContainsAny(element);
    }
  }

  public byte[] toByteArray(Function<E, Byte> byteMapper) {
    if (byteMapper == null) {
      throw new IllegalArgumentException("The given byteMapper is null.");
    }

    final var array = new byte[getElementCount()];
    var index = 0;

    for (final var e : this) {
      if (e == null) {
        array[index] = 0;
      } else {
        array[index] = byteMapper.apply(e);
      }

      index++;
    }

    return array;
  }

  private void assertIsNotEmpty() {
    if (isEmpty()) {
      throw new IllegalStateException("The current List is empty.");
    }
  }

  private void removeFirstOccuranceOfWhenContainsAny(final E element) {
    if (beginNode.contains(element)) {
      removeFirst();
    } else {
      removeFirstOccuranceOfWhenIsNotFirst(element);
    }
  }

  private void removeFirstOccuranceOfWhenIsNotFirst(final E element) {
    var iteratorNode = beginNode;
    while (iteratorNode.hasNextNode()) {
      final var nextNode = iteratorNode.getStoredNextNode();

      if (nextNode.contains(element)) {
        if (!nextNode.hasNextNode()) {
          iteratorNode.removeNextNode();
          endNode = iteratorNode;
        } else {
          iteratorNode.setNextNode(nextNode.getStoredNextNode());
        }

        elementCount--;
        return;
      }

      iteratorNode = nextNode;
    }
  }
}
