package ch.nolix.coreapi.programcontrolapi.processapi;

import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link FinishRequestable} can be asked if it is running or finished.
 * 
 * @author Silvan Wyss
 * @version 2022-06-18
 */
@AllowDefaultMethodsAsDesignPattern
public interface FinishRequestable {

  /**
   * @return true if the current {@link FinishRequestable} is finished.
   */
  boolean isFinished();

  /**
   * @return true if the current {@link FinishRequestable} is still running.
   */
  default boolean isRunning() {
    return !isFinished();
  }
}
