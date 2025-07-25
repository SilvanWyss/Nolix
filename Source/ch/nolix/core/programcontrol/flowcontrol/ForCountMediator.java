package ch.nolix.core.programcontrol.flowcontrol;

import java.util.function.IntConsumer;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.programatom.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programcontrolapi.futureapi.IFuture;

/**
 * A {@link ForCountMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2016-06-01
 */
public final class ForCountMediator {

  private final int maxRunCount;

  /**
   * Creates a new {@link ForCountMediator} with the given maxRunCount.
   * 
   * @param maxRunCount
   * @throws NegativeArgumentException if the given maxRunCount is negative.
   */
  private ForCountMediator(final int maxRunCount) {

    Validator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();

    this.maxRunCount = maxRunCount;
  }

  /**
   * @param maxRunCount
   * @return a new {@link ForCountMediator} with the given maxRunCount.
   * @throws NegativeArgumentException if the given maxRunCount is negative.
   */
  public static ForCountMediator forMaxRunCount(final int maxRunCount) {
    return new ForCountMediator(maxRunCount);
  }

  /**
   * Lets the current {@link ForCountMediator} run the given job.
   * 
   * @param job
   * @throws ArgumentIsNullException if the given job is null.
   */
  public void run(final Runnable job) {

    Validator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    for (var i = 1; i <= maxRunCount; i++) {
      job.run();
    }
  }

  /**
   * Lets the current {@link ForCountMediator} run the given job.
   * 
   * @param job
   * @throws ArgumentIsNullException if the given job is null.
   */
  public void run(final IntConsumer job) {

    Validator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    for (var i = 1; i <= maxRunCount; i++) {
      job.accept(i);
    }
  }

  /**
   * Lets the current {@link ForCountMediator} run the given job in background.
   * 
   * @param job
   * @return a new {@link IFuture} for the job running.
   * @throws ArgumentIsNullException if the given job is null.
   */
  public IFuture runInBackground(final Runnable job) {
    return new Future(new JobRunner(job, maxRunCount));
  }
}
