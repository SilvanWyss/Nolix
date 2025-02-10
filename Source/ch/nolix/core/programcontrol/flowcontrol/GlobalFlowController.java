package ch.nolix.core.programcontrol.flowcontrol;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.jobpool.JobPool;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programcontrolapi.futureapi.IFuture;

/**
 * The {@link GlobalFlowController} provides methods for flow control. Of the
 * {@link GlobalFlowController} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2016-06-01
 */
public final class GlobalFlowController {

  private static final JobPool JOB_POOL = new JobPool();

  private static final ActionMediator ACTION_MEDIATOR = new ActionMediator();

  /**
   * Prevents that an instance of the {@link GlobalFlowController} can be created.
   */
  private GlobalFlowController() {
  }

  /**
   * @param condition
   * @return a new {@link AsLongAsMediator} with the given condition.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static AsLongAsMediator asLongAs(final BooleanSupplier condition) {
    return new AsLongAsMediator(condition);
  }

  /**
   * @param condition
   * @return a new {@link AsSoonAsMediator} with the given condition.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static AsSoonAsMediator asSoonAs(final BooleanSupplier condition) {
    return new AsSoonAsMediator(condition);
  }

  /**
   * @param condition
   * @return a new {@link AsSoonAsMediator} with the negation of the given
   *         condition.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static AsSoonAsMediator asSoonAsNoMore(final BooleanSupplier condition) {
    return new AsSoonAsMediator(() -> !condition.getAsBoolean());
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
   * @return a new {@link ForCountMediator} with the given max run count.
   * @throws NegativeArgumentException if the given max run count is negative.
   */
  public static ForCountMediator forCount(final int maxRunCount) {
    return ForCountMediator.forMaxRunCount(maxRunCount);
  }

  /**
   * @param maxDurationInMilliseconds
   * @return a new {@link ForMaxMillisecondsMediator} for the given
   *         maxDurationInMilliseconds.
   * @throws NegativeArgumentException if the given maxDurationInMilliseconds is
   *                                   negative.
   */
  public static ForMaxMillisecondsMediator forMaxMilliseconds(final int maxDurationInMilliseconds) {
    return ForMaxMillisecondsMediator.forMaxMilliseconds(maxDurationInMilliseconds);
  }

  /**
   * @param maxDurationInSeconds
   * @return a new {@link ForMaxMillisecondsMediator} for the given
   *         maxDurationInSeconds.
   * @throws NegativeArgumentException if the given maxDurationInSeconds is
   *                                   negative.
   */
  public static ForMaxMillisecondsMediator forMaxSeconds(final int maxDurationInSeconds) {
    return ForMaxMillisecondsMediator.forMaxSeconds(maxDurationInSeconds);
  }

  /**
   * Runs the given job in background.
   * 
   * @param job
   * @return a new {@link Future}.
   * @throws ArgumentIsNullException if the given job is null.
   */
  public static Future runInBackground(final Runnable job) {
    return new Future(new JobRunner(job, 1));
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

    return new Future(JobRunner.forJobs(allJobes));
  }

  /**
   * Runs the given result job in background. A result job is a job that returns a
   * result.
   * 
   * @param resultJob
   * @param <R>       is the type of the result the given resultJob returns.
   * @return a new {@link ResultFuture}.
   * @throws ArgumentIsNullException if the given result job is null.
   */
  public static <R> ResultFuture<R> runInBackground(final Supplier<R> resultJob) {
    return new ResultFuture<>(new ResultJobRunner<>(resultJob));
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
   * @return true if the given job run successfully, false otherwise.
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
   * @return a new {@link AsLongAsMediator} for the negation of the given
   *         condition.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static AsLongAsMediator until(final BooleanSupplier condition) {
    return new AsLongAsMediator(() -> !condition.getAsBoolean());
  }

  /**
   * Waits as long as the given condition is fulfilled.
   * 
   * @param condition
   * @return a {@link ActionMediator}.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static ActionMediator waitAsLongAs(final BooleanSupplier condition) {

    //Asserts that the given condition is not null.
    GlobalValidator.assertThat(condition).thatIsNamed(LowerCaseVariableCatalog.CONDITION).isNotNull();

    var i = 1;
    while (condition.getAsBoolean()) {

      if (i < 100) {
        waitForMilliseconds(10);
        i++;
      } else {
        waitForMilliseconds(100);
      }
    }

    return ACTION_MEDIATOR;
  }

  /**
   * Waits for the given durationInMilliseconds.
   * 
   * @param durationInMilliseconds
   * @return a {@link ActionMediator}.
   * @throws NegativeArgumentException if the given durationInMilliseconds is
   *                                   negative.
   */
  public static ActionMediator waitForMilliseconds(final int durationInMilliseconds) {

    Waiter.waitForMilliseconds(durationInMilliseconds);

    return ACTION_MEDIATOR;
  }

  /**
   * Waits for the given durationInSeconds.
   * 
   * @param durationInSeconds
   * @return a {@link ActionMediator}.
   * @throws NegativeArgumentException if the given durationInSeconds is negative.
   */
  public static ActionMediator waitForSeconds(final int durationInSeconds) {

    Waiter.waitForSeconds(durationInSeconds);

    return ACTION_MEDIATOR;
  }

  /**
   * Waits until the given condition is fulfilled.
   * 
   * @param condition
   * @return a {@link ActionMediator}.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static ActionMediator waitUntil(final BooleanSupplier condition) {

    waitAsLongAs(() -> !condition.getAsBoolean());

    return ACTION_MEDIATOR;
  }
}
