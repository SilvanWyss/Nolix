package ch.nolix.baseapi.commontype.iterabletool;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 */
public interface IIterableFirstElementSearcher {
  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param <E>      the type of the elements of the given iterable
   * @return the first non-null element of the given iterable
   * @throws RuntimeException if the given iterable does not contain a non-null
   *                          element
   */
  <E> E getStoredFirstNonNull(Iterable<E> iterable);

  /**
   * Null elements in the given iterable will be ignored.
   * 
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param selector the selector that selects elements
   * @param <E>      the type of the elements of the given iterable
   * @return the first element the given selector selects from the given
   *         {@link Iterable}
   * @throws RuntimeException if the given iterable does not contain an element
   *                          the given selector selects
   * @throws RuntimeException if the given selector is null.
   */
  <E> E getStoredFirst(Iterable<E> iterable, Predicate<? super E> selector);
}
