/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.container.arraylist;

import java.util.function.Function;

import ch.nolix.base.commontype.iteratortool.IterableTool;
import ch.nolix.base.container.wellordercontainer.AbstractWellOrderContainer;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.container.iterator.CopyableIterator;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a {@link MappingContainerView}.
 * @param <T> is the type of the elements a {@link MappingContainerView} maps
 *            from its elements.
 */
public final class MappingContainerView<E, T> extends AbstractExtendedContainer<T> {
  private final IWellOrderContainer<E> wellOrderContainer;

  private final Function<E, T> mapper;

  /**
   * Creates a new {@link MappingContainerView} for the given container and
   * mapper.
   * 
   * @param container
   * @param mapper
   * @throws RuntimeException if the given container is null.
   * @throws RuntimeException if the given mapper is null.
   */
  private MappingContainerView(final IWellOrderContainer<E> container, final Function<E, T> mapper) {
    Validator.assertThat(container).thatIsNamed(LowerCaseVariableCatalog.CONTAINER).isNotNull();

    this.wellOrderContainer = container;
    this.mapper = mapper;
  }

  /**
   * @param container
   * @param mapper
   * @param <T>       is the type of the elements of the created
   *                  {@link MappingContainerView}.
   * @param <T2>      is the type of the elements the created
   *                  {@link MappingContainerView} maps from its elements.
   * @return a new {@link MappingContainerView} with the given container and
   *         mapper.
   * @throws RuntimeException if the given container is null.
   * @throws RuntimeException if the given mapper is null.
   */
  public static <T, T2> MappingContainerView<T, T2> forContainerAndMapper(
    final AbstractWellOrderContainer<T> container,
    final Function<T, T2> mapper) {
    return new MappingContainerView<>(container, mapper);
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
    return MappingContainerViewIterator.forIteratorAndMapper(wellOrderContainer.iterator(), mapper);
  }
}
