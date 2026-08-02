/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.generalstate.statemutation;

import ch.nolix.baseapi.generalstate.staterequest.EmptinessRequestable;

/**
 * A {@link Clearable} can contain elements that can be removed.
 * 
 * @author Silvan Wyss
 */
public interface Clearable extends EmptinessRequestable {
  /**
   * Removes the elements of the current {@link Clearable}.
   */
  void clear();
}
