//package declaration
package ch.nolix.core.programcontrol.sequencer;

//Java imports
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

//own imports
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.function.GlobalFunctionHelper;
import ch.nolix.core.programcontrol.jobpool.JobPool;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.coreapi.programcontrolapi.futureapi.IFuture;

//class
/**
 * The {@link GlobalSequencer} provides methods for flow control. Of the
 * {@link GlobalSequencer} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2016-06-01
 */
public final class GlobalSequencer {

  //constant
  private static final JobPool JOB_POOL = new JobPool();

  //constant
  private static final ActionMediator ACTION_MEDIATOR = new ActionMediator();

  //constructor
  /**
   * Prevents that an instance of the {@link GlobalSequencer} can be created.
   */
  private GlobalSequencer() {
  }

  //static method
  /**
   * @param condition
   * @return a new {@link AsLongAsMediator} with the given condition.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static AsLongAsMediator asLongAs(final BooleanSupplier condition) {
    return new AsLongAsMediator(condition);
  }

  //static method
  /**
   * @param condition
   * @return a new {@link AsSoonAsMediator} with the given condition.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static AsSoonAsMediator asSoonAs(final BooleanSupplier condition) {
    return new AsSoonAsMediator(condition);
  }

  //static method
  /**
   * @param condition
   * @return a new {@link AsSoonAsMediator} with the negation of the given
   *         condition.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static AsSoonAsMediator asSoonAsNoMore(final BooleanSupplier condition) {
    return new AsSoonAsMediator(GlobalFunctionHelper.createNegatorForBooleanSupplier(condition));
  }

  //static method
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

  //static method
  /**
   * @param maxRunCount
   * @return a new {@link ForCountMediator} with the given max run count.
   * @throws NegativeArgumentException if the given max run count is negative.
   */
  public static ForCountMediator forCount(final int maxRunCount) {
    return new ForCountMediator(maxRunCount);
  }

  //static method
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

  //static method
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

  //static method
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

  //static method
  /**
   * Runs the given jobs in background in the given order.
   * 
   * @param job
   * @param jobs
   * @return a new {@link IFuture} for the running of the given jobs.
   */
  public static IFuture runInBackgroundAndOrder(final Runnable job, final Runnable... jobs) {
    return new Future(JobRunner.forJobs(ReadContainer.forElement(job, jobs)));
  }

  //static method
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

  //static method
  /**
   * @param condition
   * @return a new {@link AsLongAsMediator} for the negation of the given
   *         condition.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static AsLongAsMediator until(final BooleanSupplier condition) {
    return new AsLongAsMediator(GlobalFunctionHelper.createNegatorForBooleanSupplier(condition));
  }

  //static method
  /**
   * Waits as long as the given condition is fulfilled.
   * 
   * @param condition
   * @return a {@link ActionMediator}.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static ActionMediator waitAsLongAs(final BooleanSupplier condition) {

    //Asserts that the given condition is not null.
    GlobalValidator.assertThat(condition).thatIsNamed(LowerCaseCatalogue.CONDITION).isNotNull();

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

  //static method
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

  //static method
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

  //static method
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
