/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontype.iterableexaminer;

/**
 * @author Silvan Wyss
 */
public interface IIterableContainAnyExaminer {
  /**
   * The time complexity of this method is O(1).
   * 
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @return true if the given iterable contains an element, false otherwise
   */
  boolean containsAny(Iterable<?> iterable);

  /**
   * The time complexity of this method is O(n) if the given iterable contains n
   * elements.
   * 
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @return true if the given iterable contains a non-null element, false
   *         otherwise
   */
  boolean containsNonNull(Iterable<?> iterable);

  /**
   * The time complexity of this method is O(1).
   * 
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @return true if the given iterable contains exactly 1 element, false
   *         otherwise
   */
  boolean containsOne(Iterable<?> iterable);

  /**
   * The time complexity of this method is O(n) if the given iterable contains n
   * elements.
   * 
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @return true if the given iterable contains exactly 1 non-null element, false
   *         otherwise
   */
  boolean containsOneNoneNull(Iterable<?> iterable);

  /**
   * The time complexity of this method is O(1).
   * 
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @return true if the given iterable is empty, false otherwise
   */
  boolean isEmpty(Iterable<?> iterable);
}
