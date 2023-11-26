//package declaration
package ch.nolix.coreapi.methodapi.requestapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link CloseStateRequestable} can be asked if it is closed or open.
 * 
 * @author Silvan Wyss
 * @date 2020-07-01
 */
@AllowDefaultMethodsAsDesignPattern
public interface CloseStateRequestable {

  //method declaration
  /**
   * @return true if the current {@link CloseStateRequestable} is closed.
   */
  boolean isClosed();

  //method
  /**
   * @return true if the current {@link CloseStateRequestable} is not closed.
   */
  default boolean isOpen() {
    return !isClosed();
  }
}
