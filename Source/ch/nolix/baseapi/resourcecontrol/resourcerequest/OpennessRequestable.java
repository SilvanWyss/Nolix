/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.resourcecontrol.resourcerequest;

/**
 * A {@link OpennessRequestable} can be asked if it is closed or open.
 * 
 * @author Silvan Wyss
 */
public interface OpennessRequestable {
  /**
   * @return true if the current {@link OpennessRequestable} is closed, false
   *         otherwise
   */
  boolean isClosed();

  /**
   * @return true if the current {@link OpennessRequestable} is open, false
   *         otherwise
   */
  default boolean isOpen() {
    return !isClosed();
  }
}
