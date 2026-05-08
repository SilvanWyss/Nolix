/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontypetool.iterableexaminer;

/**
 * @author Silvan Wyss
 */
public interface IIterableContainMultipleObjectExaminer {
  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched for {@link Object}s, is considered to be empty
   *                 when is null
   * @return true if the given iterable contains all of the given objects, false
   *         otherwise
   */
  boolean containsAll(Iterable<Object> iterable, Iterable<Object> objects);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched for {@link Object}s, is considered to be empty
   *                 when is null
   * @return true if the given iterable contains all of the given objects, false
   *         otherwise
   */
  boolean containsAll(Iterable<Object> iterable, Object... objects);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched for {@link Object}s, is considered to be empty
   *                 when is null
   * @return true if the given iterable contains any of the given objects, false
   *         otherwise
   */
  boolean containsAny(Iterable<Object> iterable, Iterable<Object> objects);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched for {@link Object}s, is considered to be empty
   *                 when is null
   * @return true if the given iterable contains any of the given objects, false
   *         otherwise
   */
  boolean containsAny(Iterable<Object> iterable, Object... objects);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched for {@link Object}s, is considered to be empty
   *                 when is null
   * @return true if the given iterable contains exactly all the given objects,
   *         false otherwise
   */
  boolean containsExactlyAll(Iterable<Object> iterable, Iterable<Object> objects);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched for {@link Object}s, is considered to be empty
   *                 when is null
   * @return true if the given iterable contains exactly all the given objects,
   *         false otherwise
   */
  boolean containsExactlyAll(Iterable<Object> iterable, Object... objects);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched for {@link Object}s, is considered to be empty
   *                 when is null
   * @return true if the given iterable contains exactly all the given objects in
   *         the same order, false otherwise
   */
  boolean containsExactlyAllInSameOrder(Iterable<Object> iterable, Iterable<Object> objects);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched for {@link Object}s, is considered to be empty
   *                 when is null
   * @return true if the given iterable contains exactly all the given objects in
   *         the same order, false otherwise
   */
  boolean containsExactlyAllInSameOrder(Iterable<Object> iterable, Object... objects);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched for {@link Object}s, is considered to be empty
   *                 when is null
   * @return true if the given iterable does not contain any of the given objects,
   *         false otherwise
   */
  boolean containsNone(Iterable<Object> iterable, Iterable<Object> objects);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param objects  the searched for {@link Object}s, is considered to be empty
   *                 when is null
   * @return true if the given iterable does not contain any of the given objects,
   *         false otherwise
   */
  boolean containsNone(Iterable<Object> iterable, Object... objects);
}
