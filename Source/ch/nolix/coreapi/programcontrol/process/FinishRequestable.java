/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.programcontrol.process;

/**
 * A {@link FinishRequestable} can be asked if it is running or finished.
 * 
 * @author Silvan Wyss
 */
public interface FinishRequestable {
  /**
   * @return true if the current {@link FinishRequestable} is finished, false
   *         otherwise.
   */
  boolean isFinished();

  /**
   * @return true if the current {@link FinishRequestable} is still running, false
   *         otherwise.
   */
  default boolean isRunning() {
    return !isFinished();
  }
}
