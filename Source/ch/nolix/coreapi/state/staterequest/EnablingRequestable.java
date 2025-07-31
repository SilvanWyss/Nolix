package ch.nolix.coreapi.state.staterequest;

/**
 * A {@link EnablingRequestable} can be asked if it is enabled or disabled.
 * 
 * @author Silvan Wyss
 * @version 2020-10-02
 */
public interface EnablingRequestable {

  /**
   * @return true if the current {@link EnablingRequestable} is disabled.
   */
  default boolean isDisabled() {
    return !isEnabled();
  }

  /**
   * @return true if the current {@link EnablingRequestable} is enabled.
   */
  boolean isEnabled();
}
