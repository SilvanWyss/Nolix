/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.container.containerview;

import ch.nolix.base.container.arraylist.AbstractExtendedContainer;
import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.container.iterator.CopyableIterator;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;

/**
 * A {@link ContainerView} wraps one or several given {@link Iterable}s or
 * arrays.
 * 
 * A {@link ContainerView} prevents that its accessed {@link Iterable}s or
 * arrays are mutated. A {@link ContainerView} does not prevent that the
 * elements of its {@link Iterable} or array are mutated.
 * 
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a {@link ContainerView}.
 */
public final class ContainerView<E> extends AbstractExtendedContainer<E> {
  private static final ContainerView<Object> EMPTY_ARRAY_CONTAINER_VIEW = //
  new ContainerView<>(ImmutableList.createEmpty());

  private final IContainer<E> internalContainer;

  /**
   * Creates a new {@link ContainerView} for the given container.
   * 
   * @param container
   * @throws RuntimeException if the given container is null.
   */
  private ContainerView(final IContainer<E> container) {
    Validator.assertThat(container).thatIsNamed(LowerCaseVariableCatalog.CONTAINER).isNotNull();

    internalContainer = container;
  }

  /**
   * @return an empty {@link ContainerView}.
   * @param <T> is the types the elements the {@link ContainerView} would have.
   */
  @SuppressWarnings("unchecked")
  public static <T> ContainerView<T> createEmpty() {
    return (ContainerView<T>) EMPTY_ARRAY_CONTAINER_VIEW;
  }

  /**
   * @param array
   * @param <T>   is the type of the elements of the given array.
   * @return a new {@link ContainerView} for the given array.
   * @throws RuntimeException if the given array is null.
   */
  public static <T> ContainerView<T> forArray(final T[] array) {
    final var container = ArrayContainerView.forArray(array);

    return new ContainerView<>(container);
  }

  /**
   * @param arrays
   * @param <T>    is the type of the elements of the given arrays.
   * @return a new {@link ContainerView} for the given arrays.
   * @throws RuntimeException if the given arrays is null.
   * @throws RuntimeException if one of the given arrays is null.
   */
  @SafeVarargs
  public static <T> ContainerView<T> forArrays(final T[]... arrays) {
    final var container = MultiContainerView.forArrays(arrays);

    return new ContainerView<>(container);
  }

  /**
   * @param iterable
   * @param <T>      is the type of the elements of the given iterable.
   * @return a new {@link ContainerView} for the given iterable.
   * @throws RuntimeException if the given iterable is null.
   */
  public static <T> ContainerView<T> forIterable(final Iterable<T> iterable) {
    final var container = IterableContainerView.forIterable(iterable);

    return new ContainerView<>(container);
  }

  /**
   * @param iterable
   * @param element
   * @param <T>      is the type of the elements of the given iterable and
   *                 element.
   * @return a new {@link ContainerView} for the given iterable and element.
   * @throws RuntimeException if the given iterable is null.
   */
  public static <T> ContainerView<T> forIterableAndElement(final Iterable<T> iterable, final T element) {
    final var iterableWithElement = ImmutableList.withElement(element);
    final var container = MultiContainerView.forIterables(iterable, iterableWithElement);

    return new ContainerView<>(container);
  }

  /**
   * @param iterables
   * @param <T>       is the type of the elements of the given iterables.
   * @return a new {@link ContainerView} for the given iterables.
   * @throws RuntimeException if the given iterables is null.
   * @throws RuntimeException if one of the given iterables is null.
   */
  @SafeVarargs
  public static <T> ContainerView<T> forIterables(final Iterable<? extends T>... iterables) {
    final var container = MultiContainerView.forIterables(iterables);

    return new ContainerView<>(container);
  }

  /**
   * A {@link Object} equals a {@link ContainerView} when the object is a
   * {@link Iterable} that contains exactly the same elements in the same order as
   * the {@link ContainerView}.
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
    return internalContainer.getCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    return internalContainer.getStoredAtOneBasedIndex(oneBasedIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return internalContainer.hashCode();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return internalContainer.isMaterialized();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return internalContainer.iterator();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return internalContainer.toString();
  }
}
