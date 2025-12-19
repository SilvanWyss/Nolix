package ch.nolix.coreapi.state.statemutation;

import ch.nolix.coreapi.state.staterequest.EmptinessRequestable;

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
