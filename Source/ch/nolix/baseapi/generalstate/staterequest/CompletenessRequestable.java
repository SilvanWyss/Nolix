/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.generalstate.staterequest;

/**
 * A {@link CompletenessRequestable} can be asked if it is complete.
 * 
 * @author Silvan Wyss
 */
public interface CompletenessRequestable {
  /**
   * @return true if the current {@link CompletenessRequestable} is complete,
   *         false otherwise
   */
  boolean isComplete();

  /**
   * @return true if the current {@link CompletenessRequestable} is not complete,
   *         false otherwise
   */
  default boolean isIncomplete() {
    return !isComplete();
  }
}
