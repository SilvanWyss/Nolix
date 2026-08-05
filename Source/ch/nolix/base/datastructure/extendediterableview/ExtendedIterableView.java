/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.extendediterableview;

import ch.nolix.base.datastructure.arrayextendediterableview.ArrayExtendedIterableView;
import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.base.datastructure.extendediterable.AbstractExtendedIterable;
import ch.nolix.base.datastructure.extendediterable.Marker;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.datastructure.iterableextendediterableview.IterableExtendedIterableView;
import ch.nolix.base.datastructure.multiextendediterableview.MultiExtendedIterableView;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * A {@link ExtendedIterableView} wraps one or several given {@link Iterable}s
 * or arrays.
 * 
 * A {@link ExtendedIterableView} prevents that its accessed {@link Iterable}s
 * or arrays are mutated. A {@link ExtendedIterableView} does not prevent that
 * the elements of its {@link Iterable} or array are mutated.
 * 
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link ExtendedIterableView}.
 */
public final class ExtendedIterableView<E> extends AbstractExtendedIterable<E> {
  private static final ExtendedIterableView<Object> EMPTY_ARRAY_CONTAINER_VIEW = //
  new ExtendedIterableView<>(ImmutableList.createEmpty());

  private final ExtendedIterable<E> extendedIterable;

  /**
   * Creates a new {@link ExtendedIterableView} for the given container.
   * 
   * @param container
   * @throws RuntimeException if the given container is null
   */
  private ExtendedIterableView(final ExtendedIterable<E> container) {
    Validator.assertThat(container).thatIsNamed(LowerCaseVariableNameCatalog.CONTAINER).isNotNull();

    extendedIterable = container;
  }

  /**
   * @return an empty {@link ExtendedIterableView}
   * @param <T> the types the elements the {@link ExtendedIterableView} would
   *            have.
   */
  @SuppressWarnings("unchecked")
  public static <T> ExtendedIterableView<T> createEmpty() {
    return (ExtendedIterableView<T>) EMPTY_ARRAY_CONTAINER_VIEW;
  }

  /**
   * @param array
   * @param <T>   the type of the elements of the given array
   * @return a new {@link ExtendedIterableView} for the given array
   * @throws RuntimeException if the given array is null
   */
  public static <T> ExtendedIterableView<T> forArray(final T[] array) {
    final var container = ArrayExtendedIterableView.forArray(array);

    return new ExtendedIterableView<>(container);
  }

  /**
   * @param arrays
   * @param <T>    the type of the elements of the given arrays
   * @return a new {@link ExtendedIterableView} for the given arrays
   * @throws RuntimeException if the given arrays is null
   * @throws RuntimeException if one of the given arrays is null
   */
  @SafeVarargs
  public static <T> ExtendedIterableView<T> forArrays(final T[]... arrays) {
    final var container = MultiExtendedIterableView.forArrays(arrays);

    return new ExtendedIterableView<>(container);
  }

  /**
   * @param iterable
   * @param <T>      the type of the elements of the given iterable
   * @return a new {@link ExtendedIterableView} for the given iterable
   * @throws RuntimeException if the given iterable is null
   */
  public static <T> ExtendedIterableView<T> forIterable(final Iterable<T> iterable) {
    final var container = IterableExtendedIterableView.forIterable(iterable);

    return new ExtendedIterableView<>(container);
  }

  /**
   * @param iterable
   * @param element
   * @param <T>      the type of the elements of the given iterable and element
   * @return a new {@link ExtendedIterableView} for the given iterable and element
   * @throws RuntimeException if the given iterable is null
   */
  public static <T> ExtendedIterableView<T> forIterableAndElement(final Iterable<T> iterable, final T element) {
    final var iterableWithElement = ImmutableList.withElement(element);
    final var container = MultiExtendedIterableView.forIterables(iterable, iterableWithElement);

    return new ExtendedIterableView<>(container);
  }

  /**
   * @param iterables
   * @param <T>       the type of the elements of the given iterables
   * @return a new {@link ExtendedIterableView} for the given iterables
   * @throws RuntimeException if the given iterables is null
   * @throws RuntimeException if one of the given iterables is null
   */
  @SafeVarargs
  public static <T> ExtendedIterableView<T> forIterables(final Iterable<? extends T>... iterables) {
    final var container = MultiExtendedIterableView.forIterables(iterables);

    return new ExtendedIterableView<>(container);
  }

  /**
   * A {@link Object} equals a {@link ExtendedIterableView} when the object is a
   * {@link Iterable} that contains exactly the same elements in the same order as
   * the {@link ExtendedIterableView}.
   * 
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object object) {
    if (object instanceof final Iterable<?> iterable) {
      return containsExactlyInSameOrder(iterable);
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return extendedIterable.getCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    return extendedIterable.getStoredAtOneBasedIndex(oneBasedIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return extendedIterable.hashCode();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return extendedIterable.isMaterialized();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return extendedIterable.iterator();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return extendedIterable.toString();
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
