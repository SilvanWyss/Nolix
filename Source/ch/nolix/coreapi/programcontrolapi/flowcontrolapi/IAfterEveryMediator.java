package ch.nolix.coreapi.programcontrolapi.flowcontrolapi;

import ch.nolix.coreapi.programcontrolapi.futureapi.IFuture;

/**
 * @author Silvan Wyss
 * @version 2025-02-10
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
