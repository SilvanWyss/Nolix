/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.iterablecontainrequest;

/**
 * @author Silvan Wyss
 */
public interface IterableContainMultipleRequestable {
  /**
   * The time complexity of this method is O(m*n) if the current
   * {@link IterableContainMultipleRequestable} contains m elements and n objects
   * are given.
   * 
   * @param objects the searched objects, is considered to be empty when is null
   * @return true if the current {@link IterableContainMultipleRequestable}
   *         contains all of the given objects, false otherwise
   */
  boolean containsAll(Iterable<?> objects);

  /**
   * The time complexity of this method is O(m*n) if the current
   * {@link IterableContainMultipleRequestable} contains m elements and n objects
   * are given.
   * 
   * @param objects the searched objects, is considered to be empty when is null
   * @return true if the current {@link IterableContainMultipleRequestable}
   *         contains all of the given objects, false otherwise
   */
  boolean containsAll(Object... objects);

  /**
   * The time complexity of this method is O(m*n) if the current
   * {@link IterableContainMultipleRequestable} contains m elements and n objects
   * are given.
   * 
   * @param objects the searched objects, is considered to be empty when is null
   * @return true if the current {@link IterableContainMultipleRequestable}
   *         contains any of the given objects, false otherwise
   */
  boolean containsAny(Iterable<?> objects);

  /**
   * The time complexity of this method is O(m*n) if the current
   * {@link IterableContainMultipleRequestable} contains m elements and n objects
   * are given.
   * 
   * @param objects the searched objects, is considered to be empty when is null
   * @return true if the current {@link IterableContainMultipleRequestable}
   *         contains at least one of the given objects, false otherwise
   */
  boolean containsAny(Object... objects);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableContainMultipleRequestable} contains n elements.
   * 
   * @param objects the searched objects, is considered to be empty when is null
   * @return true if the current {@link IterableContainMultipleRequestable}
   *         contains exactly such elements that equal the elements of given
   *         iterable in the same order, false otherwise
   */
  boolean containsExactlyAllEqualInSameOrder(Iterable<?> objects);

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link IterableContainMultipleRequestable} contains n elements.
   * 
   * 
   * @param iterable the searched iterable, is considered to be empty when is null
   * @return true if the current {@link IterableContainMultipleRequestable}
   *         contains exactly the elements of the given iterable in the same
   *         order, false otherwise
   */
  boolean containsExactlyInSameOrder(Iterable<?> iterable);

  /**
   * The time complexity of this method is O(m*n) if the current
   * {@link IterableContainMultipleRequestable} contains m elements and n objects
   * are given.
   * 
   * @param objects the searched objects, is considered to be empty when is null
   * @return true if the current {@link IterableContainMultipleRequestable} does
   *         not contain any of the given objects, false otherwise
   */
  boolean containsNone(Iterable<?> objects);

  /**
   * The time complexity of this method is O(m*n) if the current
   * {@link IterableContainMultipleRequestable} contains m elements and n objects
   * are given.
   * 
   * @param objects the searched objects, is considered to be empty when is null
   * @return true if the current {@link IterableContainMultipleRequestable} does
   *         not contain any of the given objects, false otherwise
   */
  boolean containsNone(Object... objects);
}
