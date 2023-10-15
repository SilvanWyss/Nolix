//package declaration
package ch.nolix.coreapi.functionapi.requestapi;

import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link FinishRequestable} can be asked if it is running or finished.
 * 
 * @author Silvan Wyss
 * @date 2022-06-18
 */
@AllowDefaultMethodsAsDesignPattern
public interface FinishRequestable {

  // method declaration
  /**
   * @return true if the current {@link FinishRequestable} is finished.
   */
  boolean isFinished();

  // method
  /**
   * @return true if the current {@link FinishRequestable} is still running.
   */
  default boolean isRunning() {
    return !isFinished();
  }
}
