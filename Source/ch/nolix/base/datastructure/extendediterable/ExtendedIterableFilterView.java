/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.extendediterable;

import java.util.function.Predicate;

import ch.nolix.base.commontype.iterablesearcher.IterableSearcher;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ArrayListCreator;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.baseapi.foundation.marker.Marker;
import ch.nolix.baseapi.generalcatalog.textcatalog.CharacterCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link ExtendedIterableFilterView}
 */
public final class ExtendedIterableFilterView<E> extends AbstractExtendedIterable<E> {
  private static final IterableSearcher ITERABLE_SEARCHER = new IterableSearcher();

  private final ExtendedIterable<E> wellOrderContainer;

  private final Predicate<E> selector;

  private final ArrayListCreator arrayListCreator;

  /**
   * Creates a new {@link ExtendedIterableFilterView} for the given container and
   * with the given selector and arrayListCreator.
   * 
   * @param container
   * @param selector
   * @param arrayListCreator
   * @throws RuntimeException if the given container is null
   * @throws RuntimeException if the given selector is null
   * @throws RuntimeException if the given arrayListCreator is null
   */
  private ExtendedIterableFilterView(
    final ExtendedIterable<E> container,
    final Predicate<E> selector,
    final ArrayListCreator arrayListCreator) {
    Validator.assertThat(container).thatIsNamed(LowerCaseVariableNameCatalog.CONTAINER).isNotNull();
    Validator.assertThat(selector).thatIsNamed(LowerCaseVariableNameCatalog.SELECTOR).isNotNull();
    Validator.assertThat(arrayListCreator).thatIsNamed(ArrayListCreator.class).isNotNull();

    this.wellOrderContainer = container;
    this.selector = selector;
    this.arrayListCreator = arrayListCreator;
  }

  /**
   * @param container
   * @param selector
   * @param arrayListCreator
   * @param <T>              the type of the elements of the given container
   * @return a new {@link ExtendedIterableFilterView} for the given container and
   *         with the given selector and arrayListCreator
   * @throws RuntimeException if the given container is null
   * @throws RuntimeException if the given selector is null
   * @throws RuntimeException if the given arrayListCreator is null
   */
  public static <T> ExtendedIterableFilterView<T> forContainerAndSelectorAndArrayListCreator(
    final ExtendedIterable<T> container,
    final Predicate<T> selector,
    final ArrayListCreator arrayListCreator) {
    return new ExtendedIterableFilterView<>(container, selector, arrayListCreator);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return ITERABLE_SEARCHER.getCount(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    return ITERABLE_SEARCHER.getStoredAtOneBasedIndex(this, oneBasedIndex);
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
    return ExtendedIterableFilterViewIterator.forIteratorAndSelector(wellOrderContainer.iterator(), selector);
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
    return arrayListCreator.createEmptyArrayListFromMarkerWithInitialCapacity(marker, initialCapacity);
  }
}
