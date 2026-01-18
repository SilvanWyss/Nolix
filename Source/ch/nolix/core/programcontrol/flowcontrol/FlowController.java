/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.programcontrol.flowcontrol;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.jobpool.JobPool;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programcontrol.flowcontrol.IAsLongAsMediator;
import ch.nolix.coreapi.programcontrol.flowcontrol.IAsSoonAsMediator;
import ch.nolix.coreapi.programcontrol.flowcontrol.IForCountMediator;
import ch.nolix.coreapi.programcontrol.flowcontrol.IForMaxMillisecondsMediator;
import ch.nolix.coreapi.programcontrol.flowcontrol.IWaitMediator;
import ch.nolix.coreapi.programcontrol.future.IFuture;
import ch.nolix.coreapi.programcontrol.future.IResultFuture;

/**
 * The {@link FlowController} provides methods for flow control.
 * 
 * Of the {@link FlowController} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class FlowController {
  private static final JobPool JOB_POOL = new JobPool();

  /**
   * Prevents that an instance of the {@link FlowController} can be created.
   */
  private FlowController() {
  }

  /**
   * @param condition
   * @return a new {@link IAsLongAsMediator} with the given condition.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static IAsLongAsMediator asLongAs(final BooleanSupplier condition) {
    return AsLongAsMediator.withCondition(condition);
  }

  /**
   * @param condition
   * @return a new {@link IAsSoonAsMediator} with the given condition.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static IAsSoonAsMediator asSoonAs(final BooleanSupplier condition) {
    return AsSoonAsMediator.withCondition(condition);
  }

  /**
   * @param condition
   * @return a new {@link IAsSoonAsMediator} with the negation of the given
   *         condition.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static IAsSoonAsMediator asSoonAsNoMore(final BooleanSupplier condition) {
    return AsSoonAsMediator.withCondition(() -> !condition.getAsBoolean());
  }

  /**
   * Enqueues the given job.
   * 
   * @param job
   * @return a {@link IFuture} for the given job.
   * @throws ArgumentIsNullException if the given job is null.
   */
  public static IFuture enqueue(final Runnable job) {
    return JOB_POOL.enqueue(job);
  }

  /**
   * @param maxRunCount
   * @return a new {@link IForCountMediator} with the given maxRunCount.
   * @throws NegativeArgumentException if the given maxRunCount is negative.
   */
  public static IForCountMediator forCount(final int maxRunCount) {
    return ForCountMediator.forMaxRunCount(maxRunCount);
  }

  /**
   * @param maxDurationInMilliseconds
   * @return a new {@link IForMaxMillisecondsMediator} for the given
   *         maxDurationInMilliseconds.
   * @throws NegativeArgumentException if the given maxDurationInMilliseconds is
   *                                   negative.
   */
  public static IForMaxMillisecondsMediator forMaxMilliseconds(final int maxDurationInMilliseconds) {
    return ForMaxMillisecondsMediator.forMaxMilliseconds(maxDurationInMilliseconds);
  }

  /**
   * @param maxDurationInSeconds
   * @return a new {@link IForMaxMillisecondsMediator} for the given
   *         maxDurationInSeconds.
   * @throws NegativeArgumentException if the given maxDurationInSeconds is
   *                                   negative.
   */
  public static IForMaxMillisecondsMediator forMaxSeconds(final int maxDurationInSeconds) {
    return ForMaxMillisecondsMediator.forMaxSeconds(maxDurationInSeconds);
  }

  /**
   * Runs the given job in background.
   * 
   * @param job
   * @return a new {@link IFuture} for the execution of the given job.
   * @throws ArgumentIsNullException if the given job is null.
   */
  public static IFuture runInBackground(final Runnable job) {
    return Future.forJobExecturor(new JobExecutor(job, 1));
  }

  /**
   * Runs the given jobs in background in the given order.
   * 
   * @param job
   * @param jobs
   * @return a new {@link IFuture} for the running of the given jobs.
   */
  public static IFuture runInBackgroundAndOrder(final Runnable job, final Runnable... jobs) {
    final var allJobes = ContainerView.forElementAndArray(job, jobs);

    return Future.forJobExecturor(JobExecutor.forJobs(allJobes));
  }

  /**
   * Runs the given result job in background. A result job is a job that returns a
   * result.
   * 
   * @param resultJob
   * @param <R>       is the type of the result of the given resultJob.
   * @return a new {@link IResultFuture} for the execution of the given resultJob.
   * @throws ArgumentIsNullException if the given resultJob is null.
   */
  public static <R> IResultFuture<R> runInBackground(final Supplier<R> resultJob) {
    return new ResultFuture<>(ResultJobExecutor.forResultJob(resultJob));
  }

  /**
   * Runs the given job in an enclosed mode. Prints out the stack trace of any
   * occurring error. An error will occur it the given job is null or if the given
   * job will not run properly.
   * 
   * @param job
   */
  public static void runInEnclosedMode(final Runnable job) {
    runInEnclosedModeAndGetSuccessFlag(job);
  }

  /**
   * Runs the given job in an enclosed mode. Prints out the stack trace of any
   * occurring error. An error will occur it the given job is null or if the given
   * job will not run properly.
   * 
   * @param job
   * @return true if the given job runs successfully, false otherwise.
   */
  public static boolean runInEnclosedModeAndGetSuccessFlag(final Runnable job) {
    try {
      job.run();
      return true;
    } catch (final Throwable error) { //NOSONAR: All Throwables must be caught.
      error.printStackTrace();
      return false;
    }
  }

  /**
   * @param condition
   * @return a new {@link IAsLongAsMediator} for the negation of the given
   *         condition.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static IAsLongAsMediator until(final BooleanSupplier condition) {
    return AsLongAsMediator.withCondition(() -> !condition.getAsBoolean());
  }

  /**
   * Waits as long as the given condition is fulfilled.
   * 
   * @param condition
   * @return a {@link IWaitMediator}.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static IWaitMediator waitAsLongAs(final BooleanSupplier condition) {
    Validator.assertThat(condition).thatIsNamed(LowerCaseVariableCatalog.CONDITION).isNotNull();

    var i = 1;
    while (condition.getAsBoolean()) {
      if (i < 100) {
        waitForMilliseconds(10);
        i++;
      } else {
        waitForMilliseconds(100);
      }
    }

    return new WaitMediator();
  }

  /**
   * Waits for the given durationInMilliseconds.
   * 
   * @param durationInMilliseconds
   * @return a new {@link IWaitMediator}.
   * @throws NegativeArgumentException if the given durationInMilliseconds is
   *                                   negative.
   */
  public static IWaitMediator waitForMilliseconds(final int durationInMilliseconds) {
    Waiter.waitForMilliseconds(durationInMilliseconds);

    return new WaitMediator();
  }

  /**
   * Waits for the given durationInSeconds.
   * 
   * @param durationInSeconds
   * @return a new {@link IWaitMediator}.
   * @throws NegativeArgumentException if the given durationInSeconds is negative.
   */
  public static IWaitMediator waitForSeconds(final int durationInSeconds) {
    Waiter.waitForSeconds(durationInSeconds);

    return new WaitMediator();
  }

  /**
   * Waits until the given condition is fulfilled.
   * 
   * @param condition
   * @return a new {@link IWaitMediator}.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static IWaitMediator waitUntil(final BooleanSupplier condition) {
    waitAsLongAs(() -> !condition.getAsBoolean());

    return new WaitMediator();
  }
}
