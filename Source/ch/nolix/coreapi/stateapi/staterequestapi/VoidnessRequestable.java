package ch.nolix.coreapi.stateapi.staterequestapi;

import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link VoidnessRequestable} can be asked if it is void.
 * 
 * @author Silvan Wyss
 * @version 2022-03-10
 */
@AllowDefaultMethodsAsDesignPattern
public interface VoidnessRequestable {

  /**
   * @return true if the current {@link VoidnessRequestable} is not void.
   */
  default boolean isEffectual() {
    return !isVoid();
  }

  /**
   * @return true if the current {@link VoidnessRequestable} is void.
   */
  boolean isVoid();
}
