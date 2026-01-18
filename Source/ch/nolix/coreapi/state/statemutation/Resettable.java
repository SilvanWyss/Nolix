/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.state.statemutation;

/**
 * A {@link Resettable} can be reset.
 * 
 * @author Silvan Wyss
 */
public interface Resettable {
  /**
   * Resets the current {@link Resettable}.
   */
  void reset();
}
