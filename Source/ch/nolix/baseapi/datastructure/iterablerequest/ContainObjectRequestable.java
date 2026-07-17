/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.iterablerequest;

/**
 * @author Silvan Wyss
 */
public interface ContainObjectRequestable {
  /**
   * The time complexity of this method is O(n) if the current
   * {@link ContainObjectRequestable} contains n elements.
   * 
   * @param object
   * @return true if the current {@link ContainObjectRequestable} contains the
   *         given object, false otherwise
   */
  boolean contains(Object object);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link ContainObjectRequestable} contains n elements.
   * 
   * @param object
   * @return true if the current {@link ContainObjectRequestable} contains the
   *         given object exactly 1 time, false otherwise
   */
  boolean containsOnce(Object object);

  /**
   * The time complexity of this method is O(n) if the current
   * {@link ContainObjectRequestable} contains n elements.
   * 
   * @param object
   * @return true if the current {@link ContainObjectRequestable} contains only
   *         the given object, but at least 1 time, false otherwise
   */
  boolean containsOnly(Object object);
}
