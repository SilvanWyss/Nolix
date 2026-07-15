/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontype.iterabletool;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 */
public interface IIterableCountSearcher {
  /**
   * @param iterable the examined {@link Iterable}, is considered to be empty when
   *                 is null
   * @return the number of elements of the given iterable
   */
  int getCount(Iterable<?> iterable);

  /**
   * @param iterable the examined {@link Iterable}, is considered to be empty when
   *                 is null
   * @param selector can select elements, ignores null elements, is considered not
   *                 to select any element when is null
   * @param <E>      the type of the elements of the given iterable
   * @return the number of elements the given selector selects from the given
   *         iterable
   */
  <E> int getCount(Iterable<E> iterable, Predicate<E> selector);

  /**
   * @param iterable the examined {@link Iterable}, is considered to be empty when
   *                 is null
   * @param object
   * @return the number of occurrences of the given object in the given iterable
   */
  int getCountOf(Iterable<?> iterable, Object object);
}
