package ch.nolix.coreapi.programcontrolapi.savecontrolapi;

import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link ChangeRequestable} can be asked if it has uncommitted changes.
 * 
 * @author Silvan Wyss
 * @version 2021-02-26
 */
@AllowDefaultMethodsAsDesignPattern
public interface ChangeRequestable {

  /**
   * @return true if the current {@link ChangeRequestable} has uncomitted changes.
   */
  boolean hasChanges();

  /**
   * @return true if the current {@link ChangeRequestable} does not have
   *         uncommitted changes.
   */
  default boolean isChangeFree() {
    return !hasChanges();
  }
}
