package ch.nolix.coreapi.state.staterequest;

import ch.nolix.coreapi.structureapi.typemarkerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link EmptinessRequestable} can be asked if it is empty or contains
 * elements.
 * 
 * @author Silvan Wyss
 * @version 2020-06-11
 */
@AllowDefaultMethodsAsDesignPattern
public interface EmptinessRequestable {

  /**
   * @return true if the current {@link EmptinessRequestable} contains one or
   *         several elements.
   */
  default boolean containsAny() {
    return !isEmpty();
  }

  /**
   * @return true if {@link EmptinessRequestable} does not contain an element.
   */
  boolean isEmpty();
}
