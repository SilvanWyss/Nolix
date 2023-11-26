//package declaration
package ch.nolix.coreapi.methodapi.requestapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link VoidnessRequestable} can be asked if it is void.
 * 
 * @author Silvan Wyss
 * @date 2022-03-10
 */
@AllowDefaultMethodsAsDesignPattern
public interface VoidnessRequestable {

  //method
  /**
   * @return true if the current {@link VoidnessRequestable} is not void.
   */
  default boolean isEffectual() {
    return !isVoid();
  }

  //method declaration
  /**
   * @return true if the current {@link VoidnessRequestable} is void.
   */
  boolean isVoid();
}
