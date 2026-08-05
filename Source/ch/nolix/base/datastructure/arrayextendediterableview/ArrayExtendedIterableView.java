/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.arrayextendediterableview;

import ch.nolix.base.commontype.arraytool.ArrayIterator;
import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.base.datastructure.extendediterable.AbstractExtendedIterable;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.baseapi.foundation.marker.Marker;
import ch.nolix.baseapi.generalcatalog.textcatalog.CharacterCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link ArrayExtendedIterableView}
 */
public final class ArrayExtendedIterableView<E> extends AbstractExtendedIterable<E> {
  private static final ArrayExtendedIterableView<Object> EMPTY_ARRAY_CONTAINER_VIEW = new ArrayExtendedIterableView<>(
    new Object[0]);

  private final E[] array;

  /**
   * Creates a new {@link ArrayExtendedIterableView} for the given array.
   * 
   * @param array
   * @throws RuntimeException if the given array is null
   */
  private ArrayExtendedIterableView(final E[] array) {
    Validator.assertThat(array).thatIsNamed(LowerCaseVariableNameCatalog.ARRAY).isNotNull();

    this.array = array; // NOSONAR: An ArrayContainerView operates on the original instance.
  }

  /**
   * @return an empty {@link ArrayExtendedIterableView}
   * @param <T> the type the elements the {@link ArrayExtendedIterableView} would
   *            have.
   */
  @SuppressWarnings("unchecked")
  public static <T> ArrayExtendedIterableView<T> createEmpty() {
    return (ArrayExtendedIterableView<T>) EMPTY_ARRAY_CONTAINER_VIEW;
  }

  /**
   * @param array
   * @param <T>   the type of the elements of the given array
   * @return a new {@link ArrayExtendedIterableView} for the given array
   * @throws RuntimeException if the given array is null
   */
  public static <T> ArrayExtendedIterableView<T> forArray(final T[] array) {
    return new ArrayExtendedIterableView<>(array);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return array.length;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    Validator.assertThat(oneBasedIndex).thatIsNamed("1-based index").isBetween(0, getCount());

    final var zeroBasedIndex = oneBasedIndex - 1;

    return array[zeroBasedIndex];
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return ArrayIterator.forArray(array);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithDelimiter(CharacterCatalog.COMMA);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <T> IArrayList<T> createEmptyArrayListFromMarkerWithInitialCapacity(
    final Marker<T> marker,
    final int initialCapacity) {
    return ArrayList.withInitialCapacity(initialCapacity);
  }
}
