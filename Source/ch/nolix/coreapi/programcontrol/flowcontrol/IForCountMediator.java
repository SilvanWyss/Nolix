package ch.nolix.coreapi.programcontrol.flowcontrol;

import java.util.function.IntConsumer;

import ch.nolix.coreapi.programcontrol.future.IFuture;

/**
 * @author Silvan Wyss
 * @version 2025-07-27
 */
public interface IForCountMediator {
  /**
   * Lets the current {@link IForCountMediator} run the given job.
   * 
   * @param job
   * @throws RuntimeException if the given job is null.
   */
  void run(Runnable job);

  /**
   * Lets the current {@link IForCountMediator} run the given job.
   * 
   * @param job
   * @throws RuntimeException if the given job is null.
   */
  void run(IntConsumer job);

  /**
   * Lets the current {@link IForCountMediator} run the given job in background.
   * 
   * @param job
   * @return a new {@link IFuture} for the job execution.
   * @throws RuntimeException if the given job is null.
   */
  IFuture runInBackground(Runnable job);
}
