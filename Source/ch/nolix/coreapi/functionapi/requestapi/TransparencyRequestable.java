//package declaration
package ch.nolix.coreapi.functionapi.requestapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link TransparencyRequestable} can be asked if it is transparent.
 * 
 * @author Silvan Wyss
 * @date 2022-07-15
 */
@AllowDefaultMethodsAsDesignPattern
public interface TransparencyRequestable {

  //method
  /**
   * @return true if the current {@link TransparencyRequestable} is opaque.
   */
  default boolean isOpaque() {
    return !isTransparent();
  }

  //method
  /**
   * @return true if the current {@link TransparencyRequestable} is transparent.
   */
  boolean isTransparent();
}
