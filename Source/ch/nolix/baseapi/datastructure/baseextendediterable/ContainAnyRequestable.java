/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.baseextendediterable;

import ch.nolix.baseapi.state.staterequest.EmptinessRequestable;

/**
 * @author Silvan Wyss
 */
public interface ContainAnyRequestable extends EmptinessRequestable {
  /**
   * The time complexity of this method is O(1).
   */
  @Override
  boolean containsAny();

  /**
   * The time complexity of this method is O(n) if the current
   * {@link ContainAnyRequestable} contains n elements.
   * 
   * @return true if the current {@link ContainAnyRequestable} contains a non-null
   *         element, false otherwise
   */
  boolean containsNonNull();

  /**
   * The time complexity of this method is O(1).
   * 
   * @return true if the current {@link ContainAnyRequestable} contains exactly 1
   *         element, false otherwise
   */
  boolean containsOne();

  /**
   * The time complexity of this method is O(n) if the current current
   * {@link ContainAnyRequestable} contains n elements.
   * 
   * @return true if the current {@link ContainAnyRequestable} contains exactly 1
   *         non-null element, false otherwise
   */
  boolean containsOneNoneNull();

  /**
   * The time complexity of this method is O(1).
   */
  @Override
  boolean isEmpty();
}
