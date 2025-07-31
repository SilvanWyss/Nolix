package ch.nolix.coreapi.net.staterequest;

/**
 * A {@link ConnectionSideRequestable} can be requested if it is on back-end or
 * front-end.
 * 
 * @author Silvan Wyss
 * @version 2025-07-11
 */
public interface ConnectionSideRequestable {

  /**
   * @return true if the current {@link ConnectionSideRequestable} is on the
   *         back-end, false otherwise.
   */
  boolean isOnBackend();

  /**
   * @return true if the current {@link ConnectionSideRequestable} is on the
   *         front-end, false otherwise.
   */
  default boolean isOnFrontend() {
    return !isOnBackend();
  }
}
