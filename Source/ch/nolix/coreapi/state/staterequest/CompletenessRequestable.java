package ch.nolix.coreapi.state.staterequest;

/**
 * A {@link CompletenessRequestable} can be asked if it is complete.
 * 
 * @author Silvan Wyss
 * @version 2023-02-17
 */
public interface CompletenessRequestable {
  /**
   * @return true if the current {@link CompletenessRequestable} is complete,
   *         false otherwise.
   */
  boolean isComplete();

  /**
   * @return true if the current {@link CompletenessRequestable} is not complete,
   *         false otherwise.
   */
  default boolean isIncomplete() {
    return !isComplete();
  }
}
