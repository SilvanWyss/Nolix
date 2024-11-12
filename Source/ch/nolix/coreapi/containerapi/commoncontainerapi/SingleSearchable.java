package ch.nolix.coreapi.containerapi.commoncontainerapi;

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
   *         {@link SingleSearchable} if the current {@link SingleSearchable}
   *         contains any, an empty {@link Optional} otherwise. Ignores null
   *         elements.
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
   * @param param1BasedIndex
   * @return the element at the given param1BasedIndex. The element can be null.
   * @throws RuntimeException if the current {@link SingleSearchable} does not
   *                          contain an element at the given param1BasedIndex.
   */
  E getStoredAt1BasedIndex(int param1BasedIndex);

  /**
   * @param norm
   * @param <C>  is the type of the {@link Comparable}s the given norm returns.
   * @return the element with the biggest {@link Comparable} the given norm
   *         returns from the elements of the current {@link SingleSearchable}.
   *         Ignores null elements.
   * @throws RuntimeException if the current {@link SingleSearchable} is empty.
   */
  <C extends Comparable<C>> E getStoredByMax(Function<E, C> norm);

  /**
   * @param norm
   * @param <C>  is the type of the {@link Comparable}s the given norm returns.
   * @return the element with the smallest {@link Comparable} the given norm
   *         returns from the elements of the current {@link SingleSearchable}.
   *         Ignores null elements.
   * @throws RuntimeException if the current {@link SingleSearchable} is empty.
   */
  <C extends Comparable<C>> E getStoredByMin(Function<E, C> norm);

  /**
   * @return the first element of the current {@link SingleSearchable}. Ignores
   *         null elements.
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
   * @return the last element of the current {@link SingleSearchable}. Ignores
   *         null elements.
   * @throws RuntimeException if the current {@link SingleSearchable} is empty.
   */
  E getStoredLast();

  /**
   * @return the one element of the current {@link SingleSearchable}.
   * @throws RuntimeException if the current {@link SingleSearchable} is empty.
   * @throws RuntimeException if the current {@link SingleSearchable} contains
   *                          several elements.
   */
  E getStoredOne();

  /**
   * @param selector
   * @return the one element the given selector selects from the current
   *         {@link SingleSearchable}. Ignores null elements.
   * @throws RuntimeException if the given selector selects none or several
   *                          elements from the current {@link SingleSearchable}.
   */
  E getStoredOne(Predicate<? super E> selector);
}
