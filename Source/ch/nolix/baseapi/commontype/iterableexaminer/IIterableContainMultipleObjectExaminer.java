/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontype.iterableexaminer;

/**
 * @author Silvan Wyss
 */
public interface IIterableContainMultipleObjectExaminer {
  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched objects, is considered to be empty when is null
   * @return true if the given iterable contains all of the given objects, false
   *         otherwise
   */
  boolean containsAll(Iterable<?> iterable, Iterable<?> objects);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched objects, is considered to be empty when is null
   * @return true if the given iterable contains all of the given objects, false
   *         otherwise
   */
  boolean containsAll(Iterable<?> iterable, Object... objects);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched objects, is considered to be empty when is null
   * @return true if the given iterable contains any of the given objects, false
   *         otherwise
   */
  boolean containsAny(Iterable<?> iterable, Iterable<?> objects);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched objects, is considered to be empty when is null
   * @return true if the given iterable contains any of the given objects, false
   *         otherwise
   */
  boolean containsAny(Iterable<?> iterable, Object... objects);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched objects, is considered to be empty when is null
   * @return true if the given iterable contains exactly all the given objects,
   *         false otherwise
   */
  boolean containsExactlyAll(Iterable<?> iterable, Iterable<?> objects);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched objects, is considered to be empty when is null
   * @return true if the given iterable contains exactly all the given objects,
   *         false otherwise
   */
  boolean containsExactlyAll(Iterable<?> iterable, Object... objects);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched for objects, is considered to be empty when is
   *                 null
   * @return true if the given iterable contains exactly all the given objects in
   *         the same order, false otherwise
   */
  boolean containsExactlyAllInSameOrder(Iterable<?> iterable, Iterable<?> objects);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched objects, is considered to be empty when is null
   * @return true if the given iterable contains exactly all the given objects in
   *         the same order, false otherwise
   */
  boolean containsExactlyAllInSameOrder(Iterable<?> iterable, Object... objects);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched objects, is considered to be empty when is null
   * @return true if the given iterable does not contain any of the given objects,
   *         false otherwise
   */
  boolean containsNone(Iterable<?> iterable, Iterable<?> objects);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched objects, is considered to be empty when is null
   * @return true if the given iterable does not contain any of the given objects,
   *         false otherwise
   */
  boolean containsNone(Iterable<?> iterable, Object... objects);
}
