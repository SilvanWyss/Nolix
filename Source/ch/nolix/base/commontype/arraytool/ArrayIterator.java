/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.arraytool;

import java.util.NoSuchElementException;

import ch.nolix.base.commontype.iteratorvalidator.IteratorValidator;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of the parent array of a
 *            {@link ArrayIterator}
 */
public final class ArrayIterator<E> implements CopyableIterator<E> {
  private static final IteratorValidator ITERATOR_VALIDATOR = new IteratorValidator();

  private final E[] parentArray;

  private int nextIndex;

  private ArrayIterator(final E[] parrentArray) {
    if (parrentArray == null) {
      throw ArgumentIsNullException.forArgumentName("parent array");
    }

    this.parentArray = parrentArray; // NOSONAR: An ArrayIterator operates on the original instance.
    nextIndex = 0;
  }

  private ArrayIterator(final E[] parrentArray, final int startIndex) {
    if (parrentArray == null) {
      throw ArgumentIsNullException.forArgumentName("parent array");
    }
    if (startIndex < 0) {
      throw NegativeArgumentException.forArgumentAndArgumentName(startIndex, LowerCaseVariableNameCatalog.START_INDEX);
    }

    this.parentArray = parrentArray; // NOSONAR: An ArrayIterator operates on the original instance.
    nextIndex = startIndex;
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
    return forArrayAndStartIndex(parentArray, nextIndex);
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
    ITERATOR_VALIDATOR.assertHasNext(this);
  }

  private E nextWhenHasNext() {
    final var element = parentArray[nextIndex];

    nextIndex++;

    return element;
  }
}
