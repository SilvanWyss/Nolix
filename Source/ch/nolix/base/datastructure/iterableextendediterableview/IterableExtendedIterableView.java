/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.iterableextendediterableview;

import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.base.datastructure.extendediterable.AbstractExtendedIterable;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.baseapi.foundation.marker.Marker;
import ch.nolix.baseapi.generalcatalog.textcatalog.CharacterCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a
 *            {@link IterableExtendedIterableView}.
 */
public final class IterableExtendedIterableView<E> extends AbstractExtendedIterable<E> {
  private final Iterable<E> iterable;

  /**
   * Creates a new {@link IterableExtendedIterableView} for a new empty container.
   */
  public IterableExtendedIterableView() {
    // Calls other constructor.
    this(ArrayList.createEmpty());
  }

  /**
   * Creates a new {@link IterableExtendedIterableView} for the given container.
   * 
   * @param container
   * @param <T>       the type of the elements of the given container
   * @throws RuntimeException if the given container is null
   */
  @SuppressWarnings("unchecked")
  private <T extends E> IterableExtendedIterableView(final Iterable<T> container) {
    // Asserts that the given container is not null.
    Validator
      .assertThat(container)
      .thatIsNamed(LowerCaseVariableNameCatalog.CONTAINER)
      .isNotNull();

    // Sets the container of the current IterableView.
    this.iterable = (Iterable<E>) container;
  }

  /**
   * @param iterable
   * @param <T>      the type of the elements of the given iterable
   * @return a new {@link IterableExtendedIterableView} for the given iterable
   * @throws RuntimeException if the given iterable is null
   */
  public static <T> IterableExtendedIterableView<T> forIterable(final Iterable<? extends T> iterable) {
    return new IterableExtendedIterableView<>(iterable);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    var size = 0;

    // Iterates the current IterableView.
    final var iterator = iterable.iterator();
    while (iterator.hasNext()) {
      size++;
      iterator.next();
    }

    return size;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    // Iterates the current IterableView.
    var i = 1;
    for (final var e : this) {
      // Asserts that the current index is the given index.
      if (i == oneBasedIndex) {
        return e;
      }

      i++;
    }

    throw ArgumentIsOutOfRangeException.forArgumentAndArgumentNameAndRangeWithMinAndMax(
      oneBasedIndex,
      "1-based index",
      1,
      getCount());
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
    return IterableExtendedIterableViewIterator.forIterable(iterable);
  }

  /**
   * The time complexity of this implementation is O(n). if the current
   * {@link IterableExtendedIterableView} contains n elements.
   * 
   * @return a {@link String} representation of the current
   *         {@link IterableExtendedIterableView}.
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
