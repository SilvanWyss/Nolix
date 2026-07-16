/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontype.iterableexaminer;

/**
 * @author Silvan Wyss
 */
public interface IIterableContainAnyExaminer {
  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @return true if the given iterable contains an element, false otherwise
   */
  boolean containsAny(Iterable<?> iterable);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @return true if the given iterable contains a non-null element, false
   *         otherwise
   */
  boolean containsNonNull(Iterable<?> iterable);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @return true if the given iterable contains exactly 1 element, false
   *         otherwise
   */
  boolean containsOne(Iterable<?> iterable);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @return true if the given iterable is empty, false otherwise
   */
  boolean isEmpty(Iterable<?> iterable);
}
