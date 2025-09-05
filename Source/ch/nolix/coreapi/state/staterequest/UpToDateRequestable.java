package ch.nolix.coreapi.state.staterequest;

/**
 * A {@link UpToDateRequestable} can be asked if it is up-to-date.
 * 
 * @author Silvan Wyss
 * @version 2025-07-07
 */
public interface UpToDateRequestable {
  /**
   * @return true if the current {@link UpToDateRequestable} is not up-to-date,
   *         false otherwise.
   */
  default boolean isOutOfDate() {
    return !isUpToDate();
  }

  /**
   * @return true if the current {@link UpToDateRequestable} is up-to-date, false
   *         otherwise.
   */
  boolean isUpToDate();
}
