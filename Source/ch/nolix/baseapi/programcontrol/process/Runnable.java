/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.programcontrol.process;

/**
 * A {@link Runnable} can be run.
 * 
 * @author Silvan Wyss
 */
public interface Runnable {
  /**
   * Lets the current {@link Runnable} run.
   */
  void run();
}
