package ch.nolix.coreapi.programcontrol.flowcontrol;

import ch.nolix.coreapi.programcontrol.future.IFuture;

/**
 * @author Silvan Wyss
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
   * Lets the current {@link IAsLongAsMediator} run the given job asynchronously
   * in background.
   * 
   * @param job
   * @return a new {@link IFuture}.
   * @throws RuntimeException if the given job is null.
   */
  IFuture runInBackground(Runnable job);
}
