/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.state.staterequest;

/**
 * A {@link AlivenessRequestable} can be asked if it is alive or outdated.
 * 
 * @author Silvan Wyss
 */
public interface AlivenessRequestable {
  /**
   * @return true if the current {@link AlivenessRequestable} is alive, false
   *         otherwise.
   */
  boolean isAlive();

  /**
   * @return true if the current {@link AlivenessRequestable} is outdated, false
   *         otherwise.
   */
  default boolean isOutdated() {
    return !isAlive();
  }
}
