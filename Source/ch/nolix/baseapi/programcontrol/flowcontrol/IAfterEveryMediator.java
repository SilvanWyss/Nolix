/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.programcontrol.flowcontrol;

import ch.nolix.baseapi.programcontrol.future.IFuture;

/**
 * @author Silvan Wyss
 */
public interface IAfterEveryMediator {
  /**
   * Lets the current {@link IAfterEveryMediator} run the given job.
   * 
   * @param job
   * @throws RuntimeException if the given job is null.
   */
  void run(Runnable job);

  /**
   * Lets the current {@link IAfterEveryMediator} run the given job asynchronously
   * in the background.
   * 
   * @param job
   * @return a new {@link IFuture}.
   * @throws RuntimeException if the given job is null.
   */
  IFuture runInBackground(Runnable job);
}
