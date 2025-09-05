package ch.nolix.coreapi.programcontrol.flowcontrol;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import ch.nolix.coreapi.programcontrol.future.IFuture;
import ch.nolix.coreapi.programcontrol.future.IResultFuture;

/**
 * @author Silvan Wyss
 * @version 2025-07-28
 */
public interface IFlowControllerMediator {
  /**
   * @param condition
   * @return a new {@link IAsLongAsMediator} with the given condition.
   * @throws RuntimeException if the given condition is null.
   */
  IAsLongAsMediator asLongAs(BooleanSupplier condition);

  /**
   * @param condition
   * @return a new {@link IAsSoonAsMediator} with the given condition.
   * @throws RuntimeException if the given condition is null.
   */
  IAsSoonAsMediator asSoonAs(BooleanSupplier condition);

  /**
   * @param condition
   * @return a new {@link IAsSoonAsMediator} with the negation of the given
   *         condition.
   * @throws RuntimeException if the given condition is null.
   */
  IAsSoonAsMediator asSoonAsNoMore(BooleanSupplier condition);

  /**
   * Enqueues the given job.
   * 
   * @param job
   * @return a {@link IFuture} for the given job.
   * @throws RuntimeException if the given job is null.
   */
  IFuture enqueue(Runnable job);

  /**
   * @param maxRunCount
   * @return a new {@link IForCountMediator} with the given maxRunCount.
   * @throws RuntimeException if the given maxRunCount is negative.
   */
  IForCountMediator forCount(int maxRunCount);

  /**
   * @param maxDurationInMilliseconds
   * @return a new {@link IForMaxMillisecondsMediator} for the given
   *         maxDurationInMilliseconds.
   * @throws RuntimeException if the given maxDurationInMilliseconds is negative.
   */
  IForMaxMillisecondsMediator forMaxMilliseconds(int maxDurationInMilliseconds);

  /**
   * @param maxDurationInSeconds
   * @return a new {@link IForMaxMillisecondsMediator} for the given
   *         maxDurationInSeconds.
   * @throws RuntimeException if the given maxDurationInSeconds is negative.
   */
  IForMaxMillisecondsMediator forMaxSeconds(int maxDurationInSeconds);

  /**
   * Runs the given job in background.
   * 
   * @param job
   * @return a new {@link IFuture} for the execution of the given job.
   * @throws RuntimeException if the given job is null.
   */
  IFuture runInBackground(Runnable job);

  /**
   * Runs the given resultJob in background. A result job is a job that returns a
   * result.
   * 
   * @param resultJob
   * @param <R>       is the type of the result the given resultJob returns.
   * @return a new {@link IResultFuture} for the execution of the given resultJob.
   * @throws RuntimeException if the given resultJob is null.
   */
  <R> IResultFuture<R> runInBackground(Supplier<R> resultJob);

  /**
   * @param condition
   * @return a new {@link IAsLongAsMediator} for the negation of the given
   *         condition.
   * @throws RuntimeException if the given condition is null.
   */
  IAsLongAsMediator until(BooleanSupplier condition);

  /**
   * Waits as long as the given condition is fulfilled.
   * 
   * @param condition
   * @throws RuntimeException if the given condition is null.
   */
  void waitAsLongAs(BooleanSupplier condition);

  /**
   * Waits for the given durationInMilliseconds.
   * 
   * @param durationInMilliseconds
   * @throws RuntimeException if the given durationInMilliseconds is negative.
   */
  void waitForMilliseconds(int durationInMilliseconds);

  /**
   * Waits for the given durationInSeconds.
   * 
   * @param durationInSeconds
   * @throws RuntimeException if the given durationInSeconds is negative.
   */
  void waitForSeconds(int durationInSeconds);

  /**
   * Waits until the given condition is fulfilled.
   * 
   * @param condition
   * @throws RuntimeException if the given condition is null.
   */
  void waitUntil(BooleanSupplier condition);
}
