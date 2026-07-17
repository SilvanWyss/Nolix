/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.iterablerequest;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link ContainMatchingRequestable}
 */
public interface ContainMatchingRequestable<E> {
  /**
   * The time complexity of this method is O(n) if the current
   * {@link ContainMatchingRequestable} contains n elements.
   * 
   * @param selector can select elements, is considered not to select any element
   *                 when is null
   * @return true if the current {@link ContainMatchingRequestable} contains an
   *         element the given selector selects, false otherwise, ignoring null
   *         elements
   */
  boolean containsMatching(Predicate<E> selector);

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link ContainMatchingRequestable} contains n elements.
   * 
   * @param selector can select elements, is considered not to select any element
   *                 when is null
   * @return true if the current {@link ContainMatchingRequestable} contains only
   *         elements the given selector selects, but at least 1 time, false
   *         otherwise, ignoring null elements
   */
  boolean containsMatchingOnly(Predicate<E> selector);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link ContainMatchingRequestable} contains n elements.
   * 
   * @param selector can select elements, is considered not to select any element
   *                 when is null
   * @return true if the current {@link ContainMatchingRequestable} does not
   *         contain an element the given selector selects, false otherwise,
   *         ignoring null elements
   * @throws RuntimeException if the given selector is null.
   */
  boolean containsNoMatching(Predicate<E> selector);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link ContainMatchingRequestable} contains n elements.
   * 
   * @param selector can select elements, is considered not to select any element
   *                 when is null
   * @return true if the current {@link ContainMatchingRequestable} contains
   *         exactly 1 element the given selector selects, false otherwise,
   *         ignoring null elements
   * @throws RuntimeException if the given selector is null.
   */
  boolean containsOneMatching(Predicate<E> selector);
}
