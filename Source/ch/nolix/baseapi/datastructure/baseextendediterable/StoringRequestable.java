/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.baseextendediterable;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link StoringRequestable}.
 */
public interface StoringRequestable<E> {
  /**
   * The time complexity of this method is O(n) if the current
   * {@link StoringRequestable} contains n elements.
   * 
   * @param object
   * @return true if the current {@link StoringRequestable} contains the given
   *         object, false otherwise
   */
  boolean contains(Object object);

  /**
   * The time complexity of this method is O(m*n) if the current
   * {@link StoringRequestable} contains m elements and n objects are given.
   * 
   * @param objects the searched objects, is considered to be empty when is null
   * @return true if the current {@link StoringRequestable} contains all of the
   *         given objects, false otherwise
   */
  boolean containsAll(Iterable<?> objects);

  /**
   * The time complexity of this method is O(m*n) if the current
   * {@link StoringRequestable} contains m elements and n objects are given.
   * 
   * @param objects the searched objects, is considered to be empty when is null
   * @return true if the current {@link StoringRequestable} contains all of the
   *         given objects, false otherwise
   */
  boolean containsAll(Object... objects);

  /**
   * The time complexity of this method is O(m*n) if the current
   * {@link StoringRequestable} contains m elements and n objects are given.
   * 
   * @param objects the searched objects, is considered to be empty when is null
   * @return true if the current {@link StoringRequestable} contains any of the
   *         given objects, false otherwise
   * @throws RuntimeException if the given objects is null
   */
  boolean containsAnyOf(Iterable<?> objects);

  /**
   * The time complexity of this method is O(m*n) if the current
   * {@link StoringRequestable} contains m elements and n objects are given.
   * 
   * @param objects the searched objects, is considered to be empty when is null
   * @return true if the current {@link StoringRequestable} contains at least one
   *         of the given objects, false otherwise
   * @throws RuntimeException if the given objects is null
   */
  boolean containsAnyOf(Object... objects);

  /**
   * The time complexity of this method is O(m+n) if the current
   * {@link StoringRequestable} contains m elements and the given iterable
   * contains n elements.
   * 
   * @param iterable the searched iterable, is considered to be empty when is null
   * @return true if the current {@link StoringRequestable} contains as many
   *         elements as the given container, false otherwise
   */
  boolean containsAsManyAs(Iterable<?> iterable);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link StoringRequestable} contains n elements.
   * 
   * @param iterable
   * @return true if the current {@link StoringRequestable} contains exactly
   *         elements that equal the elements of given iterable in the same order,
   *         false otherwise
   */
  boolean containsExactlyEqualInSameOrder(Iterable<?> iterable);

  /**
   * @param iterable
   * @return true if the current {@link StoringRequestable} contains exactly the
   *         elements of the given iterable in the same order, false otherwise
   */
  boolean containsExactlyInSameOrder(Iterable<?> iterable);

  /**
   * @param iterable
   * @return true if the current {@link StoringRequestable} contains less elements
   *         than the given container, false otherwise
   */
  boolean containsLessThan(Iterable<?> iterable);

  /**
   * @param iterable
   * @return true if the current {@link StoringRequestable} contains more elements
   *         than the given container, false otherwise
   */
  boolean containsMoreThan(Iterable<?> iterable);

  /**
   * @param objects
   * @return true if the current {@link StoringRequestable} does not contain any
   *         of the given objects, false otherwise
   * @throws RuntimeException if the given objects is null
   */
  boolean containsNoneOf(Iterable<?> objects);

  /**
   * @param objects
   * @return true if the current {@link StoringRequestable} does not contain any
   *         of the given objects, false otherwise
   * @throws RuntimeException if the given objects is null
   */
  boolean containsNoneOf(Object... objects);

  /**
   * @param object
   * @return true if the current {@link StoringRequestable} contains the given
   *         object exactly 1 time, false otherwise
   */
  boolean containsOnce(Object object);

  /**
   * @return true if the current {@link StoringRequestable} contains exactly 1
   *         element, false otherwise
   */
  boolean containsOne();

  /**
   * @param selector
   * @return true if the current {@link StoringRequestable} contains only elements
   *         the given selector selects, false otherwise, ignoring null elements
   * @throws RuntimeException if the given selector is null
   */
  boolean containsOnly(Predicate<E> selector);
}
