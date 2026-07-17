/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.iterablecontainrequest;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link IterableContainMatchingRequestable}
 */
public interface IterableContainMatchingRequestable<E> {
  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableContainMatchingRequestable} contains n elements.
   * 
   * @param selector can select elements, is considered not to select any element
   *                 when is null
   * @return true if the current {@link IterableContainMatchingRequestable} contains an
   *         element the given selector selects, false otherwise, ignoring null
   *         elements
   */
  boolean containsMatching(Predicate<E> selector);

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link IterableContainMatchingRequestable} contains n elements.
   * 
   * @param selector can select elements, is considered not to select any element
   *                 when is null
   * @return true if the current {@link IterableContainMatchingRequestable} contains only
   *         elements the given selector selects, but at least 1 time, false
   *         otherwise, ignoring null elements
   */
  boolean containsMatchingOnly(Predicate<E> selector);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableContainMatchingRequestable} contains n elements.
   * 
   * @param selector can select elements, is considered not to select any element
   *                 when is null
   * @return true if the current {@link IterableContainMatchingRequestable} does not
   *         contain an element the given selector selects, false otherwise,
   *         ignoring null elements
   * @throws RuntimeException if the given selector is null.
   */
  boolean containsNoMatching(Predicate<E> selector);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link IterableContainMatchingRequestable} contains n elements.
   * 
   * @param selector can select elements, is considered not to select any element
   *                 when is null
   * @return true if the current {@link IterableContainMatchingRequestable} contains
   *         exactly 1 element the given selector selects, false otherwise,
   *         ignoring null elements
   * @throws RuntimeException if the given selector is null.
   */
  boolean containsOneMatching(Predicate<E> selector);
}
