/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.foundation.linkedlist;

import java.util.function.Function;

import ch.nolix.baseapi.state.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link ISimpleLinkedList}
 */
public interface ISimpleLinkedList<E> extends Clearable, Iterable<E> {
  void addAtBegin(E element);

  void addAtEnd(E element);

  ISimpleLinkedList<E> getCopy();

  int getElementCount();

  E getStoredFirst();

  void removeFirst();

  void removeFirstOccurrenceOf(E element);

  byte[] toByteArray(Function<E, Byte> byteMapper);
}
