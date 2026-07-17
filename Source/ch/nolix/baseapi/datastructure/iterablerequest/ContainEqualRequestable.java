/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.iterablerequest;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link ContainEqualRequestable}
 */
public interface ContainEqualRequestable<E> {
  /**
   * The time complexity of this method is O(n) if the current
   * {@link ContainEqualRequestable} contains n elements.
   * 
   * @param object
   * @return true if the current {@link ContainEqualRequestable} contains an
   *         element that equals the given object, false otherwise
   */
  boolean containsEqual(Object object);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link ContainEqualRequestable} contains n elements.
   * 
   * @param object
   * @return true if the current {@link ContainEqualRequestable} does not contain
   *         an element that equals the given object, false otherwise
   */
  boolean containsNoEqual(E object);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link ContainEqualRequestable} contains n elements.
   * 
   * @param object
   * @return true if the current {@link ContainEqualRequestable} contains exactly
   *         1 element that equals the given object, false otherwise
   */
  boolean containsOneEqual(E object);
}
