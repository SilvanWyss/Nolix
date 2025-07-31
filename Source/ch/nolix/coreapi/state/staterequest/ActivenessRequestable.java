package ch.nolix.coreapi.state.staterequest;

/**
 * A {@link ActivenessRequestable} can be asked if it is active.
 * 
 * @author Silvan Wyss
 * @version 2023-07-08
 */
public interface ActivenessRequestable {

  /**
   * @return true if the current {@link ActivenessRequestable} is active.
   */
  boolean isActive();

  /**
   * @return true if the current {@link ActivenessRequestable} is not active.
   */
  boolean isInactive();
}
