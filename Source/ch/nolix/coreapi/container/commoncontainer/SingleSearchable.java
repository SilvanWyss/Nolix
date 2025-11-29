package ch.nolix.coreapi.container.commoncontainer;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @version 2024-11-12
 * @param <E> is the type of the elements a {@link SingleSearchable}.
 */
public interface SingleSearchable<E> {
  /**
   * @return a {@link Optional} with the first element of the current
   *         {@link SingleSearchable} if the current {@link SingleSearchable} is
   *         not empty and if the first element of the current
   *         {@link SingleSearchable} is not null, an empty {@link Optional}
   *         otherwise.
   */
  Optional<E> getOptionalStoredFirst();

  /**
   * @param selector
   * @return a new {@link Optional} with the first element the given selector
   *         selects from the current {@link SingleSearchable} if the current
   *         {@link SingleSearchable} contains an element the given selector
   *         selects, an empty {@link Optional} otherwise. Ignores null elements.
   * @throws RuntimeException if the given selector is null.
   */
  Optional<E> getOptionalStoredFirst(Predicate<? super E> selector);

  /**
   * @param oneBasedIndex
   * @return the element at the given oneBasedIndex. The element can be null.
   * @throws RuntimeException if the current {@link SingleSearchable} does not
   *                          contain an element at the given oneBasedIndex.
   */
  E getStoredAtOneBasedIndex(int oneBasedIndex);

  /**
   * @param comparableMapper
   * @param <C>              is the type of the {@link Comparable}s the given
   *                         comparableMapper maps from the elements of the
   *                         current {@link SingleSearchable}.
   * @return the element with the biggest {@link Comparable} the given
   *         comparableMapper maps from the elements of the current
   *         {@link SingleSearchable}. Ignores null elements.
   * @throws RuntimeException if the given comparableMapper is null.
   * @throws RuntimeException if the current {@link SingleSearchable} does not
   *                          contain a non-null element.
   */
  <C extends Comparable<C>> E getStoredByMax(Function<E, C> comparableMapper);

  /**
   * @param comparableMapper
   * @param <C>              is the type of the {@link Comparable}s the given
   *                         comparableMapper maps from the elements of the
   *                         current {@link SingleSearchable}.
   * @return the element with the smallest {@link Comparable} the given
   *         comparableMapper maps from the elements of the current
   *         {@link SingleSearchable}. Ignores null elements.
   * @throws RuntimeException if the given comparableMapper is null.
   * @throws RuntimeException if the current {@link SingleSearchable} does not
   *                          contain a non-null element.
   */
  <C extends Comparable<C>> E getStoredByMin(Function<E, C> comparableMapper);

  /**
   * @return the first element of the current {@link SingleSearchable}. The
   *         element can be null.
   * @throws RuntimeException if the current {@link SingleSearchable} is empty.
   */
  E getStoredFirst();

  /**
   * @param selector
   * @return the first element the given selector selects from the current
   *         {@link SingleSearchable}. Ignores null elements.
   * @throws RuntimeException if the current {@link SingleSearchable} does not
   *                          contain an element the given selector selects.
   */
  E getStoredFirst(Predicate<? super E> selector);

  /**
   * @param type
   * @param <T> is the given type.
   * @return the first element from the current {@link SingleSearchable} that is
   *         of the given type.
   * @throws RuntimeException if the given type is null.
   * @throws RuntimeException if the current {@link SingleSearchable} does not
   *                          contain an element of the given type.
   */
  <T extends E> T getStoredFirstOfType(Class<T> type);

  /**
   * @return the last element of the current {@link SingleSearchable}. The element
   *         can be null.
   * @throws RuntimeException if the current {@link SingleSearchable} is empty.
   */
  E getStoredLast();

  /**
   * @return the one element of the current {@link SingleSearchable}. The element
   *         can be null.
   * @throws RuntimeException if the current {@link SingleSearchable} is empty.
   * @throws RuntimeException if the current {@link SingleSearchable} contains
   *                          several elements.
   */
  E getStoredOne();

  /**
   * @param selector
   * @return the one element the given selector selects from the current
   *         {@link SingleSearchable}. Ignores null elements.
   * @throws RuntimeException if the given selector is null.
   * @throws RuntimeException if the given selector selects none or several
   *                          elements from the current {@link SingleSearchable}.
   */
  E getStoredOne(Predicate<? super E> selector);
}
