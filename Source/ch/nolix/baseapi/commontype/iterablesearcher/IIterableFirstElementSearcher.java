package ch.nolix.baseapi.commontype.iterablesearcher;

import java.util.function.Predicate;

import ch.nolix.baseapi.datastructure.iterableextension.SingleSearchable;

/**
 * @author Silvan Wyss
 */
public interface IIterableFirstElementSearcher {
  /**
   * The time complexity of this method is O(n) if the given iterable contains n
   * elements.
   * 
   * @param iterable the searched iterable, is considered to be empty when is null
   * @param <E>      the type of the elements of the given iterable
   * @return the first non-null element of the given iterable
   * @throws RuntimeException if the given iterable does not contain a non-null
   *                          element
   */
  <E> E getStoredFirstNonNull(Iterable<E> iterable);

  /**
   * The time complexity of this method is O(n) if the given iterable contains n
   * elements.
   * 
   * @param iterable the searched iterable, is considered to be empty when is null
   * @param selector can select elements, is considered not to select any element
   *                 when is null
   * @param <E>      the type of the elements of the given iterable
   * @return the first element the given selector selects of the given
   *         {@link Iterable}, ignoring null elements
   * @throws RuntimeException if the given iterable does not contain an element
   *                          the given selector selects
   */
  <E> E getStoredFirst(Iterable<E> iterable, Predicate<? super E> selector);

  /**
   * The time complexity of this method is O(n) if the given iterable contains n
   * elements.
   * 
   * @param iterable the searched iterable, is considered to be empty when is null
   * @param type
   * @param <E>      the type of the elements of the given iterable
   * @param <T>      the modeled type of the given type
   * @return the first element of the current {@link SingleSearchable} that is of
   *         the given type.
   * @throws RuntimeException if the given type is null
   * @throws RuntimeException if the given iterable does not contain an element of
   *                          the given type.
   */
  <E, T extends E> T getStoredFirstOfType(Iterable<E> iterable, Class<T> type);
}
