/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.foundation.immutablelist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of the parent {@link SimpleImmutableList}
 *            of a {@link SimpleImmutableListIterator}.
 */
public final class SimpleImmutableListIterator<E> implements Iterator<E> {
  private final E[] parentArray;

  private int nextIndex;

  private SimpleImmutableListIterator(final E[] parrentArray) {
    if (parrentArray == null) {
      throw new IllegalArgumentException("The given parent array is null.");
    }

    this.parentArray = parrentArray; // NOSONAR: An ArrayIterator operates on the original instance.
    nextIndex = 0;
  }

  public static <T> SimpleImmutableListIterator<T> forArray(final T[] array) {
    return new SimpleImmutableListIterator<>(array);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return (nextIndex < parentArray.length);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E next() {
    assertHasNext();

    return nextWhenHasNext();
  }

  private void assertHasNext() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException("The current ArrayIterator does not have a next element.");
    }
  }

  private E nextWhenHasNext() {
    final var element = parentArray[nextIndex];

    nextIndex++;

    return element;
  }
}
