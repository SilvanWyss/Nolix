/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.arraytool;

import java.util.NoSuchElementException;

import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of the array of a {@link ArrayIterator}
 */
public final class ArrayIterator<E> implements CopyableIterator<E> {
  private final E[] array;

  private int nextIndex;

  private ArrayIterator(final E[] array) {
    if (array == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.ARRAY);
    }

    this.array = array; // NOSONAR: The current ArrayIterator operates on the given original array.
    this.nextIndex = 0;
  }

  private ArrayIterator(final E[] array, final int startIndex) {
    if (array == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.ARRAY);
    }

    if (startIndex < 0) {
      throw NegativeArgumentException.forArgumentAndArgumentName(startIndex, LowerCaseVariableNameCatalog.START_INDEX);
    }

    this.array = array; // NOSONAR: The current ArrayIterator operates on the given original array.
    this.nextIndex = startIndex;
  }

  public static <T> ArrayIterator<T> forArray(final T[] array) {
    return new ArrayIterator<>(array);
  }

  public static <T> ArrayIterator<T> forArrayAndStartIndex(final T[] array, final int startIndex) {
    return new ArrayIterator<>(array, startIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> getCopy() {
    return forArrayAndStartIndex(array, nextIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return (nextIndex < array.length);
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public E next() {
    if (nextIndex >= array.length) {
      throw new NoSuchElementException("The current ArrayIterator does not have a next element.");
    }

    final var element = array[nextIndex];

    nextIndex++;

    return element;
  }
}
