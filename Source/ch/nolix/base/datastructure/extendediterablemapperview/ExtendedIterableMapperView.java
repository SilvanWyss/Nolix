/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.extendediterablemapperview;

import java.util.function.Function;

import ch.nolix.base.commontype.iteratortool.IterableTool;
import ch.nolix.base.datastructure.arraylist.AbstractExtendedContainer;
import ch.nolix.base.datastructure.extendediterable.AbstractExtendedIterable;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.iterator.CopyableIterator;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a {@link ExtendedIterableMapperView}.
 * @param <T> is the type of the elements a {@link ExtendedIterableMapperView} maps
 *            from its elements.
 */
public final class ExtendedIterableMapperView<E, T> extends AbstractExtendedContainer<T> {
  private final ExtendedIterable<E> wellOrderContainer;

  private final Function<E, T> mapper;

  /**
   * Creates a new {@link ExtendedIterableMapperView} for the given container and
   * mapper.
   * 
   * @param container
   * @param mapper
   * @throws RuntimeException if the given container is null.
   * @throws RuntimeException if the given mapper is null.
   */
  private ExtendedIterableMapperView(final ExtendedIterable<E> container, final Function<E, T> mapper) {
    Validator.assertThat(container).thatIsNamed(LowerCaseVariableCatalog.CONTAINER).isNotNull();

    this.wellOrderContainer = container;
    this.mapper = mapper;
  }

  /**
   * @param container
   * @param mapper
   * @param <T>       is the type of the elements of the created
   *                  {@link ExtendedIterableMapperView}.
   * @param <T2>      is the type of the elements the created
   *                  {@link ExtendedIterableMapperView} maps from its elements.
   * @return a new {@link ExtendedIterableMapperView} with the given container and
   *         mapper.
   * @throws RuntimeException if the given container is null.
   * @throws RuntimeException if the given mapper is null.
   */
  public static <T, T2> ExtendedIterableMapperView<T, T2> forContainerAndMapper(
    final AbstractExtendedIterable<T> container,
    final Function<T, T2> mapper) {
    return new ExtendedIterableMapperView<>(container, mapper);
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
  public T getStoredAtOneBasedIndex(final int oneBasedIndex) {
    final var element = wellOrderContainer.getStoredAtOneBasedIndex(oneBasedIndex);

    return mapper.apply(element);
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
  public CopyableIterator<T> iterator() {
    return ExtendedIterableMapperViewIterator.forIteratorAndMapper(wellOrderContainer.iterator(), mapper);
  }
}
