/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.container.arraylist;

import java.util.function.Predicate;

import ch.nolix.base.commontypetool.iteratortool.IterableTool;
import ch.nolix.base.container.containerview.ContainerView;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.container.iterator.CopyableIterator;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a {@link FilterContainerView}.
 */
public final class FilterContainerView<E> extends AbstractExtendedContainer<E> {
  private final IContainer<E> container;

  private final Predicate<E> selector;

  /**
   * Creates a new {@link FilterContainerView} for the given container and
   * selector.
   * 
   * @param container
   * @param selector
   * @throws RuntimeException if the given container is null.
   * @throws RuntimeException if the given selector is null.
   */
  private FilterContainerView(final IContainer<E> container, final Predicate<E> selector) {
    Validator.assertThat(container).thatIsNamed(LowerCaseVariableCatalog.CONTAINER).isNotNull();
    Validator.assertThat(selector).thatIsNamed(LowerCaseVariableCatalog.SELECTOR).isNotNull();

    this.container = container;
    this.selector = selector;
  }

  /**
   * @return a new {@link FilterContainerView} for an empty {@link IContainer}.
   * @param <T> is the types of the elements of the created
   *            {@link FilterContainerView}.
   */
  public static <T> FilterContainerView<T> createEmpty() {
    return forContainerAndSelector(ArrayList.createEmpty(), _ -> true);
  }

  /**
   * @param container
   * @param selector
   * @param <T>       is the type of the elements of the given container.
   * @return a new {@link FilterContainerView} for the given container and
   *         selector.
   * @throws RuntimeException if the given container is null.
   * @throws RuntimeException if the given selector is null.
   */
  public static <T> FilterContainerView<T> forContainerAndSelector(
    final IContainer<T> container,
    final Predicate<T> selector) {
    return new FilterContainerView<>(container, selector);
  }

  /**
   * @param array
   * @param selector
   * @param <T>      is the type of the given element and elements of the given
   *                 array.
   * @return a new {@link FilterContainerView} for the given element and array.
   * @throws RuntimeException if the given array is null.
   * @throws RuntimeException if the given selector is null.
   */
  public static <T> FilterContainerView<T> forArrayAndSelector(
    final T[] array,
    final Predicate<T> selector) {
    final var container = ContainerView.forArray(array);

    return forContainerAndSelector(container, selector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return IterableTool.getCount(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    return IterableTool.getStoredAtOneBasedIndex(this, oneBasedIndex);
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
    return FilterContainerViewIterator.forIteratorAndSelector(container.iterator(), selector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalog.COMMA);
  }
}
