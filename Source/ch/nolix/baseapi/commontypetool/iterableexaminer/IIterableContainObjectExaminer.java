/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontypetool.iterableexaminer;

/**
 * @author Silvan Wyss
 */
public interface IIterableContainObjectExaminer {
  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param object
   * @return true if the given iterable contains the given object, false otherwise
   */
  boolean contains(Iterable<Object> iterable, Object object);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param object
   * @return true if the given iterable contains the given object exactly 1 time,
   *         false otherwise
   */
  boolean containsOnce(Iterable<Object> iterable, Object object);

  /**
   * @param iterable the searched {@link Iterable}, is considered to be empty when
   *                 is null
   * @param object
   * @return true if the given iterable contains only the given object, whether 1
   *         or several times, false otherwise
   */
  boolean containsOnly(Iterable<Object> iterable, Object object);
}
