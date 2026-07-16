/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontype.iterableexaminer;

/**
 * @author Silvan Wyss
 */
public interface IIterableContainEqualExaminer {
  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param object   the object of which an equal element will be searched for
   * @return true if the given iterable contains an element that equals the given
   *         object, false otherwise
   */
  boolean containsEqual(Iterable<?> iterable, Object object);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param object   the object of which equal elements will be searched for
   * @return true if the given iterable does not contain an element that euqls the
   *         given object, false otherwise
   */
  boolean containsNoEqual(Iterable<?> iterable, Object object);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param object   the object of which exactly 1 equal element will be searched
   *                 for
   * @return true if the given iterable contains exactly 1 element that equals the
   *         given object, false otherwise
   */
  boolean containsOneEqual(Iterable<?> iterable, Object object);
}
