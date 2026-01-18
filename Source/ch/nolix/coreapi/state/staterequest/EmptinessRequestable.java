/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.state.staterequest;

/**
 * A {@link EmptinessRequestable} can be asked if it is empty or contains
 * elements.
 * 
 * @author Silvan Wyss
 */
public interface EmptinessRequestable {
  /**
   * @return true if the current {@link EmptinessRequestable} contains one or
   *         several elements, false otherwise.
   */
  default boolean containsAny() {
    return !isEmpty();
  }

  /**
   * @return true if {@link EmptinessRequestable} does not contain an element,
   *         false otherwise.
   */
  boolean isEmpty();
}
