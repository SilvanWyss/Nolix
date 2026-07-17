/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.baseextendediterable;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link StoringRequestable}.
 */
public interface StoringRequestable<E> {
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
   * @return true if the current {@link StoringRequestable} contains exactly 1
   *         element, false otherwise
   */
  boolean containsOne();
}
