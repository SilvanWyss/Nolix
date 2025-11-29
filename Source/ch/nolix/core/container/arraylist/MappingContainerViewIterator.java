package ch.nolix.core.container.arraylist;

import java.util.function.Function;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2025-05-14
 * @param <E> is the type of the elements of a
 *            {@link MappingContainerViewIterator}.
 * @param <T> is the type of the elements a {@link MappingContainerViewIterator}
 *            maps from its elements.
 */
public final class MappingContainerViewIterator<E, T> implements CopyableIterator<T> {
  private final CopyableIterator<E> iterator;

  private final Function<E, T> mapper;

  /**
   * Creates a new {@link MappingContainerViewIterator} with the given iterator
   * and mapper.
   * 
   * @param iterator
   * @param mapper
   * @throws ArgumentIsNullException if the given container is null.
   * @throws ArgumentIsNullException if the given mapper is null.
   */
  private MappingContainerViewIterator(final CopyableIterator<E> iterator, final Function<E, T> mapper) {
    Validator.assertThat(iterator).thatIsNamed(LowerCaseVariableCatalog.ITERATOR).isNotNull();
    Validator.assertThat(mapper).thatIsNamed(LowerCaseVariableCatalog.MAPPER).isNotNull();

    this.iterator = iterator;
    this.mapper = mapper;
  }

  /**
   * @param iterator
   * @param mapper
   * @param <T>     is the type of the elements of the created
   *                 {@link MappingContainerViewIterator}.
   * @param <T2>     is the type of the elements the created
   *                 {@link MappingContainerViewIterator} maps from its elements.
   * @return a new {@link MappingContainerViewIterator} with the given iterator
   *         and mapper.
   * @throws ArgumentIsNullException if the given container is null.
   * @throws ArgumentIsNullException if the given mapper is null.
   */
  public static <T, T2> MappingContainerViewIterator<T, T2> forIteratorAndMapper(
    final CopyableIterator<T> iterator,
    final Function<T, T2> mapper) {
    return new MappingContainerViewIterator<>(iterator, mapper);
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
