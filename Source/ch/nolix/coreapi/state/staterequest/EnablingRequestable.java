package ch.nolix.coreapi.state.staterequest;

/**
 * A {@link EnablingRequestable} can be asked if it is enabled or disabled.
 * 
 * @author Silvan Wyss
 */
public interface EnablingRequestable {
  /**
   * @return true if the current {@link EnablingRequestable} is disabled, false
   *         otherwise.
   */
  default boolean isDisabled() {
    return !isEnabled();
  }

  /**
   * @return true if the current {@link EnablingRequestable} is enabled, false
   *         otherwise.
   */
  boolean isEnabled();
}
