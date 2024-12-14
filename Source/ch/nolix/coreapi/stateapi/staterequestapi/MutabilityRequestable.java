package ch.nolix.coreapi.stateapi.staterequestapi;

import ch.nolix.coreapi.structureapi.typemarkerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link MutabilityRequestable} can be asked if it is mutable.
 * 
 * @author Silvan Wyss
 * @version 2021-03-19
 */
@AllowDefaultMethodsAsDesignPattern
public interface MutabilityRequestable {

  /**
   * @return true if the current {@link MutabilityRequestable} is not mutable.
   */
  default boolean isImmutable() {
    return !isMutable();
  }

  /**
   * @return true if the current {@link MutabilityRequestable} is mutable.
   */
  boolean isMutable();
}
