package ch.nolix.coreapi.state.staterequest;

/**
 * A {@link AlivenessRequestable} can be asked if it is alive or outdated.
 * 
 * @author Silvan Wyss
 * @version 2023-08-02
 */
public interface AlivenessRequestable {

  /**
   * @return true if the current {@link AlivenessRequestable} is alive.
   */
  boolean isAlive();

  /**
   * @return true if the current {@link AlivenessRequestable} is outdated.
   */
  default boolean isOutdated() {
    return !isAlive();
  }
}
