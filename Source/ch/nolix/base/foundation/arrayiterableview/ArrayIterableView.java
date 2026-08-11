/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.foundation.arrayiterableview;

import java.util.Iterator;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of the array of a
 *            {@link ArrayIterableView}
 */
public final class ArrayIterableView<E> implements Iterable<E> {
  private final E[] array;

  /**
   * Creates a new {@link ArrayIterableView} for the given array.
   * 
   * @param array
   * @throws RuntimeException if the given array is null
   */
  private ArrayIterableView(final E[] array) {
    if (array == null) {
      throw new IllegalArgumentException("The given array is null.");
    }

    this.array = array; // NOSONAR: The current ArrayIterableView operates on the given original array.
  }

  /**
   * @param array
   * @param <T>   the type of the elements of the given array
   * @return a new {@link ArrayIterableView} for the given array
   * @throws RuntimeException if the given array is null
   */
  public static <T> ArrayIterableView<T> forArray(final T[] array) {
    return new ArrayIterableView<>(array);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterator<E> iterator() {
    return ArrayIterableViewIterator.forArray(array);
  }
}
