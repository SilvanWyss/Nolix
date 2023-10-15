//package declaration
package ch.nolix.coreapi.functionapi.requestapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link EmptinessRequestable} can be asked if it is empty or contains
 * elements.
 * 
 * @author Silvan Wyss
 * @date 2020-06-11
 */
@AllowDefaultMethodsAsDesignPattern
public interface EmptinessRequestable {

  // method
  /**
   * @return true if the current {@link EmptinessRequestable} contains one or
   *         several elements.
   */
  default boolean containsAny() {
    return !isEmpty();
  }

  // method declaration
  /**
   * @return true if {@link EmptinessRequestable} does not contain an element.
   */
  boolean isEmpty();
}
