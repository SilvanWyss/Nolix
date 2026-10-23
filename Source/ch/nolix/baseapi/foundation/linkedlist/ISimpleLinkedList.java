/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.foundation.linkedlist;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link ISimpleLinkedList}
 */
public interface ISimpleLinkedList<E> extends Iterable<E> {
  /**
   * Adds the given element at the end of the current {@link ISimpleLinkedList}.
   * 
   * @param element
   */
  void addAtEnd(E element);

  /**
   * Removes all elements of the current {@link ISimpleLinkedList}.
   */
  void clear();

  /**
   * @return the number of elements of the current {@link ISimpleLinkedList}
   */
  int getElementCount();

  /**
   * @return the first element of the current {@link ISimpleLinkedList}
   * @throws RuntimeException if the current {@link ISimpleLinkedList} is empty
   */
  E getStoredFirst();

  /**
   * @return true if the current {@link ISimpleLinkedList} is empty, false
   *         otherwise
   */
  boolean isEmpty();

  /**
   * Removes the first element of the current {@link ISimpleLinkedList}.
   * 
   * @throws RuntimeException if the current {@link ISimpleLinkedList} is empty
   */
  void removeFirst();
}
