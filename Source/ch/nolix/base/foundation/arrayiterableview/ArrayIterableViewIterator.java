/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.foundation.arrayiterableview;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of the array of a
 *            {@link ArrayIterableViewIterator}
 */
public final class ArrayIterableViewIterator<E> implements Iterator<E> {
  private final E[] array;

  private int nextIndex;

  /**
   * Creates a new {@link ArrayIterableViewIterator} for the given array. The
   * invoker is responsible that the given array is not null.
   * 
   * @param array
   */
  private ArrayIterableViewIterator(final E[] array) {
    this.array = array; // NOSONAR: The current ArrayIterableViewIterator operates on the given original array.
  }

  /**
   * The invoker is responsible that the given array is not null.
   * 
   * @param array
   * @param <T>   the type of the elements of the given array
   * @return a new {@link ArrayIterableViewIterator} for the given array
   */
  public static <T> Iterator<T> forArray(T[] array) {
    return new ArrayIterableViewIterator<>(array);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return nextIndex < array.length;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public E next() throws NoSuchElementException {
    if (nextIndex >= array.length) {
      throw new NoSuchElementException("The current ArrayIterableViewIterator does not have a next element.");
    }

    final var element = array[nextIndex];

    nextIndex++;

    return element;
  }
}
