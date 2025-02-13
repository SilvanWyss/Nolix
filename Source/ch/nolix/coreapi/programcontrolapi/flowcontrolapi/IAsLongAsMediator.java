package ch.nolix.coreapi.programcontrolapi.flowcontrolapi;

import ch.nolix.coreapi.programcontrolapi.futureapi.IFuture;

/**
 * @author Silvan Wyss
 * @version 2025-02-13
 */
public interface IAsLongAsMediator {

  /**
   * @param timeIntervalInMilliseconds
   * @return a new {@link IAfterEveryMediator} with the given
   *         timeIntervalInMilliseconds.
   * @throws RuntimeException if the given time timeIntervalInMilliseconds is
   *                          negative.
   */
  IAfterEveryMediator afterEveryMilliseconds(int timeIntervalInMilliseconds);

  /**
   * @return a new {@link IAfterEveryMediator} with a time interval of 1 second.
   */
  IAfterEveryMediator afterEverySecond();

  /**
   * Lets the current {@link IAsLongAsMediator} run the given job.
   * 
   * @param job
   */
  void run(Runnable job);

  /**
   * Lets the current {@link IAsLongAsMediator} the given job asynchronously in
   * background.
   * 
   * @param job
   * @return a new {@link IFuture}.
   * @throws RuntimeException if the given job is null.
   */
  IFuture runInBackground(Runnable job);
}
