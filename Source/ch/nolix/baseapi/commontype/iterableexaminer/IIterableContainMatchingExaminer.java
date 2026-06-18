/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontype.iterableexaminer;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 */
public interface IIterableContainMatchingExaminer {
  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param selector can select elements, is considered not to select any element
   *                 when is null
   * @param <T>      the type of the elements of the given iterable
   * @return true if the given iterable contains an element the given selector
   *         selects, false otherwise. Ignores null elements in the given
   *         iterable.
   */
  <T> boolean containsMatching(Iterable<T> iterable, Predicate<T> selector);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param selector can select elements, is considered not to select any element
   *                 when is null
   * @param <T>      the type of the elements of the given iterable
   * @return true if the given iterable contains only elements the given selector
   *         selects, false otherwise. Ignores null elements in the given
   *         iterable.
   */
  <T> boolean containsMatchingOnly(Iterable<T> iterable, Predicate<T> selector);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param selector can select elements, is considered not to select any element
   *                 when is null
   * @param <T>      the type of the elements of the given iterable
   * @return true if the given iterable contains exactly 1 element the given
   *         selector selects, false otherwise. Ignores null elements in the given
   *         iterable.
   */
  <T> boolean containsOneMatching(Iterable<T> iterable, Predicate<T> selector);
}
