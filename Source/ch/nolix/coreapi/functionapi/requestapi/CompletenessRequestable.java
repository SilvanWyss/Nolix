//package declaration
package ch.nolix.coreapi.functionapi.requestapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link CompletenessRequestable} can be asked if it is complete.
 * 
 * @author Silvan Wyss
 * @date 2023-02-17
 */
@AllowDefaultMethodsAsDesignPattern
public interface CompletenessRequestable {

  //method declaration
  /**
   * @return true if the current {@link CompletenessRequestable} is complete,
   *         false otherwise.
   */
  boolean isComplete();

  //method
  /**
   * @return true if the current {@link CompletenessRequestable} is not complete,
   *         false otherwise.
   */
  default boolean isIncomplete() {
    return !isComplete();
  }
}