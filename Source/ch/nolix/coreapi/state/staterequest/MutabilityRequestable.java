/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.state.staterequest;

/**
 * A {@link MutabilityRequestable} can be asked if it is mutable.
 * 
 * @author Silvan Wyss
 */
public interface MutabilityRequestable {
  /**
   * @return true if the current {@link MutabilityRequestable} is not mutable,
   *         false otherwise.
   */
  default boolean isImmutable() {
    return !isMutable();
  }

  /**
   * @return true if the current {@link MutabilityRequestable} is mutable, false
   *         otherwise.
   */
  boolean isMutable();
}
