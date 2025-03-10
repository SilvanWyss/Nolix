package ch.nolix.coreapi.stateapi.staterequestapi;

import ch.nolix.coreapi.structureapi.typemarkerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link EnablingRequestable} can be asked if it is enabled or disabled.
 * 
 * @author Silvan Wyss
 * @version 2020-10-02
 */
@AllowDefaultMethodsAsDesignPattern
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
