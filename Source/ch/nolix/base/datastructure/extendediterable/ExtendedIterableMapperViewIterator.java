/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.extendediterable;

import java.util.function.Function;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a
 *            {@link ExtendedIterableMapperViewIterator}.
 * @param <T> the type of the elements a
 *            {@link ExtendedIterableMapperViewIterator} maps from its elements.
 */
public final class ExtendedIterableMapperViewIterator<E, T> implements CopyableIterator<T> {
  private final CopyableIterator<E> iterator;

  private final Function<E, T> mapper;

  /**
   * Creates a new {@link ExtendedIterableMapperViewIterator} with the given
   * iterator and mapper.
   * 
   * @param iterator
   * @param mapper
   * @throws RuntimeException if the given container is null
   * @throws RuntimeException if the given mapper is null
   */
  private ExtendedIterableMapperViewIterator(final CopyableIterator<E> iterator, final Function<E, T> mapper) {
    Validator.assertThat(iterator).thatIsNamed(LowerCaseVariableNameCatalog.ITERATOR).isNotNull();
    Validator.assertThat(mapper).thatIsNamed(LowerCaseVariableNameCatalog.MAPPER).isNotNull();

    this.iterator = iterator;
    this.mapper = mapper;
  }

  /**
   * @param iterator
   * @param mapper
   * @param <T>      the type of the elements of the created
   *                 {@link ExtendedIterableMapperViewIterator}
   * @param <T2>     the type of the elements the created
   *                 {@link ExtendedIterableMapperViewIterator} maps from its
   *                 elements
   * @return a new {@link ExtendedIterableMapperViewIterator} with the given
   *         iterator and mapper
   * @throws RuntimeException if the given container is null
   * @throws RuntimeException if the given mapper is null
   */
  public static <T, T2> ExtendedIterableMapperViewIterator<T, T2> forIteratorAndMapper(
    final CopyableIterator<T> iterator,
    final Function<T, T2> mapper) {
    return new ExtendedIterableMapperViewIterator<>(iterator, mapper);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<T> getCopy() {
    return forIteratorAndMapper(iterator.getCopy(), mapper);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return iterator.hasNext();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public T next() {
    return mapper.apply(iterator.next());
  }
}
