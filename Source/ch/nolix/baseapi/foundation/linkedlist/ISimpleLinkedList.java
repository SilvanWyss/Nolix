/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.foundation.linkedlist;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link ISimpleLinkedList}
 */
public interface ISimpleLinkedList<E> extends Iterable<E> {
  void addAtBegin(E element);

  void addAtEnd(E element);

  void clear();

  int getElementCount();

  E getStoredFirst();

  boolean isEmpty();

  void removeFirst();
}
