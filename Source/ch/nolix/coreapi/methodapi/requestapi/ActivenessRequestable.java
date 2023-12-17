//package declaration
package ch.nolix.coreapi.methodapi.requestapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link ActivenessRequestable} can be asked if it is active.
 * 
 * @author Silvan Wyss
 * @date 2023-07-08
 */
@AllowDefaultMethodsAsDesignPattern
public interface ActivenessRequestable {

  //method declaration
  /**
   * @return true if the current {@link ActivenessRequestable} is active.
   */
  boolean isActive();

  //method declaration
  /**
   * @return true if the current {@link ActivenessRequestable} is not active.
   */
  boolean isInactive();
}