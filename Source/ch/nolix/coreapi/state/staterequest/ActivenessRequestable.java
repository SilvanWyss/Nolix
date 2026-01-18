/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.state.staterequest;

/**
 * A {@link ActivenessRequestable} can be asked if it is active.
 * 
 * @author Silvan Wyss
 */
public interface ActivenessRequestable {
  /**
   * @return true if the current {@link ActivenessRequestable} is active, false
   *         otherwise.
   */
  boolean isActive();

  /**
   * @return true if the current {@link ActivenessRequestable} is not active,
   *         false otherwise.
   */
  boolean isInactive();
}
