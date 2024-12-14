package ch.nolix.coreapi.stateapi.staterequestapi;

import ch.nolix.coreapi.structureapi.typemarkerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link ActivenessRequestable} can be asked if it is active.
 * 
 * @author Silvan Wyss
 * @version 2023-07-08
 */
@AllowDefaultMethodsAsDesignPattern
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
