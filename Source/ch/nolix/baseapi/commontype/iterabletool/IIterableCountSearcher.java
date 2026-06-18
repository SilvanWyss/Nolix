/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontype.iterabletool;

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
}
