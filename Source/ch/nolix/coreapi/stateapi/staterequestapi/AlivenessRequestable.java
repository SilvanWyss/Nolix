package ch.nolix.coreapi.stateapi.staterequestapi;

import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link AlivenessRequestable} can be asked if it is alive or outdated.
 * 
 * @author Silvan Wyss
 * @version 2023-08-02
 */
@AllowDefaultMethodsAsDesignPattern
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
