/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontype.iterablesearcher;

/**
 * @author Silvan Wyss
 */
public interface IIterableSearcher extends IIterableFirstElementSearcher, IIterableCountSearcher {
  /**
   * @param iterable      the searched {@link Iterable}, is considered to be empty
   *                      when is null
   * @param oneBasedIndex the one-based index at which an element is requested
   * @param <E>           the type of the elements of the given iterable
   * @return the element at the given oneBasedIndexed from the given iterable
   * @throws RuntimeException if the given iterable does not contain an element at
   *                          the given oneBasedIndex
   */
  <E> E getStoredAtOneBasedIndex(Iterable<E> iterable, int oneBasedIndex);
}
