//package declaration
package ch.nolix.core.programcontrol.sequencer;

import java.util.function.BooleanSupplier;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.logger.GlobalLogger;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//class
/**
 * @author Silvan Wyss
 * @date 2017-06-04
 */
final class JobRunner extends Thread {

  // constant
  private static final JobMerger JOB_MERGER = new JobMerger();

  // static method
  public static JobRunner forJobs(final IContainer<Runnable> jobs) {
    return new JobRunner(JOB_MERGER.createMergedJobForJobs(jobs), 1);
  }

  // attribute
  private final Runnable job;

  // attribute
  private int finishedJobCount;

  // attribute
  private boolean running = true;

  // optional attribute
  private final Integer maxRunCount;

  // optional attribute
  private final BooleanSupplier condition;

  // optional attribute
  private final Integer timeIntervalInMilliseconds;

  // optional attribute
  private Throwable error;

  // constructor
  /**
   * Creates a new {@link JobRunner} with the given job. The {@link JobRunner}
   * will start automatically.
   * 
   * @param job
   * @throws ArgumentIsNullException if the given job is null.
   */
  public JobRunner(final Runnable job) {

    // Asserts that the given job is not null.
    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseCatalogue.JOB).isNotNull();

    this.job = job;
    maxRunCount = null;
    condition = null;
    timeIntervalInMilliseconds = null;

    start();
  }

  // constructor
  /**
   * Creates a new {@link JobRunner} with the given job and condition. The
   * {@link JobRunner} will start automatically.
   * 
   * @param job
   * @param condition
   * @throws ArgumentIsNullException if the given job is null.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public JobRunner(final Runnable job, final BooleanSupplier condition) {

    // Asserts that the given job is not null.
    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseCatalogue.JOB).isNotNull();

    // Asserts that the given condition is not null.
    GlobalValidator.assertThat(condition).thatIsNamed("condition").isNotNull();

    this.job = job;
    maxRunCount = null;
    this.condition = condition;
    timeIntervalInMilliseconds = null;

    start();
  }

  // constructor
  /**
   * Creates a new {@link JobRunner} with the given job, condition and
   * timeIntervalInMilliseconds. The {@link JobRunner} will start automatically.
   * 
   * @param job
   * @param condition
   * @param timeIntervalInMilliseconds
   * @throws ArgumentIsNullException   if the given job is null.
   * @throws ArgumentIsNullException   if the given condition is null.
   * @throws NegativeArgumentException if the given timeIntervalInMilliseconds is
   *                                   negative.
   */
  public JobRunner(
      final Runnable job,
      final BooleanSupplier condition,
      final int timeIntervalInMilliseconds) {
    // Asserts that the given job is not null.
    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseCatalogue.JOB).isNotNull();

    // Asserts that the given condition is not null.
    GlobalValidator.assertThat(condition).thatIsNamed(LowerCaseCatalogue.CONDITION).isNotNull();

    // Asserts that the given timeIntervalInMilliseconds is not negative.
    GlobalValidator.assertThat(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseoconds")
        .isNotNegative();

    this.job = job;
    this.maxRunCount = null;
    this.condition = condition;
    this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;

    start();
  }

  // constructor
  /**
   * Creates a new {@link JobRunner} with the given job and maxRunCount. The
   * {@link JobRunner} will start automatically.
   * 
   * @param job
   * @param maxRunCount
   * @throws ArgumentIsNullException   if the given job is null.
   * @throws NegativeArgumentException if the given maxRunCount is negative.
   */
  public JobRunner(
      final Runnable job,
      final int maxRunCount) {

    // Asserts that the given job is not null.
    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseCatalogue.JOB).isNotNull();

    // Asserts that the given maxRunCount is not negative.
    GlobalValidator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();

    this.job = job;
    this.maxRunCount = maxRunCount;
    condition = null;
    timeIntervalInMilliseconds = null;

    start();
  }

  // constructor
  /**
   * Creates a new {@link JobRunner} with the given job, maxRunCount and
   * condition. The {@link JobRunner} will start automatically.
   * 
   * @param job
   * @param maxRunCount
   * @param condition
   * @throws ArgumentIsNullException   if the given job is null.
   * @throws NegativeArgumentException if the given maxRunCount is negative.
   * @throws ArgumentIsNullException   if the given condition is null.
   */
  public JobRunner(
      final Runnable job,
      final int maxRunCount,
      final BooleanSupplier condition) {

    // Asserts that the given job is not null.
    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseCatalogue.JOB).isNotNull();

    // Asserts that the given maxRunCount is not negative.
    GlobalValidator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();

    // Asserts that the given condition is not null.
    GlobalValidator.assertThat(condition).thatIsNamed(LowerCaseCatalogue.CONDITION).isNotNull();

    this.job = job;
    this.maxRunCount = maxRunCount;
    this.condition = condition;
    timeIntervalInMilliseconds = null;

    start();
  }

  // constructor
  /**
   * Creates a new {@link JobRunner} with the given job, maxRunCount, condition
   * and timeIntervalInMilliseconds. The {@link JobRunner} will start
   * automatically.
   * 
   * @param job
   * @param maxRunCount
   * @param condition
   * @param timeIntervalInMilliseconds
   * @throws ArgumentIsNullException   if the given job is null.
   * @throws NegativeArgumentException if the given maxRunCount is negative.
   * @throws ArgumentIsNullException   if the given condition is null.
   * @throws NegativeArgumentException if the given timeIntervalInMilliseconds is
   *                                   negative.
   */
  public JobRunner(
      final Runnable job,
      final int maxRunCount,
      final BooleanSupplier condition,
      final int timeIntervalInMilliseconds) {
    // Asserts that the given job is not null.
    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseCatalogue.JOB).isNotNull();

    // Asserts that the given max run count is not negative.
    GlobalValidator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();

    // Asserts that the given condition is not null.
    GlobalValidator.assertThat(condition).thatIsNamed(LowerCaseCatalogue.CONDITION).isNotNull();

    // Asserts that the given timeIntervalInMilliseconds is not negative.
    GlobalValidator.assertThat(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseconds").isNotNegative();

    this.job = job;
    this.maxRunCount = maxRunCount;
    this.condition = condition;
    this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;

    start();
  }

  // constructor
  /**
   * Creates a new {@link JobRunner} with the given job, maxRunCount and
   * timeIntervalInMilliseconds. The {@link JobRunner} will start automatically.
   * 
   * @param job
   * @param maxRunCount
   * @param timeIntervalInMilliseconds
   * @throws ArgumentIsNullException   if the given job is null.
   * @throws NegativeArgumentException if the given maxRunCount is negative.
   * @throws NegativeArgumentException if the given timeIntervalInMilliseconds is
   *                                   negative.
   */
  public JobRunner(
      final Runnable job,
      final int maxRunCount,
      final int timeIntervalInMilliseconds) {

    // Asserts that the given job is not null.
    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseCatalogue.JOB).isNotNull();

    // Asserts that the given maxRunCount is not negative.
    GlobalValidator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();

    // Asserts that the given timeIntervalInMilliseconds is not negative.
    GlobalValidator.assertThat(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseconds").isNotNegative();

    this.job = job;
    this.maxRunCount = maxRunCount;
    condition = null;
    this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;

    start();
  }

  // method
  /**
   * @return true if the current {@link JobRunner} has caught an error.
   */
  public boolean caughtError() {
    return (error != null);
  }

  // method
  /**
   * @return the error of the current {@link JobRunner}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link JobRunner} does not have
   *                                               an error.
   */
  public Throwable getError() {

    // Asserts that the current JobRunner has an error.
    // For a better performance, this implementation does not use all comfortable
    // methods.
    if (error == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.ERROR);
    }

    return error;
  }

  // method
  /**
   * @return the number of finished jobs of the current {@link JobRunner}.
   */
  public int getFinishedJobCount() {
    return finishedJobCount;
  }

  // method
  /**
   * @return true if the current {@link JobRunner} has a condition.
   */
  public boolean hasCondition() {
    return (condition != null);
  }

  // method
  /**
   * @return true if the current {@link JobRunner} has a max run count.
   */
  public boolean hasMaxRunCount() {
    return (maxRunCount != null);
  }

  // method
  /**
   * @return true if the current {@link JobRunner} has a time interval.
   */
  public boolean hasTimeInterval() {
    return (timeIntervalInMilliseconds != null);
  }

  // method
  /**
   * @return true if the current {@link JobRunner} is finished.
   */
  public boolean isFinished() {
    return !isRunning();
  }

  // method
  /**
   * @return true if the current {@link JobRunner} is finished successfully.
   */
  public boolean isFinishedSuccessfully() {
    return (isFinished() && !caughtError());
  }

  // method
  /**
   * @return true if the current {@link JobRunner} is running.
   */
  public boolean isRunning() {
    return running;
  }

  // method
  /**
   * Lets the current {@link JobRunner} run.
   */
  @Override
  public void run() {

    // main loop
    while (!reachedProbableMaxRunCount()) {
      try {

        // Handles the case that the current JobRunner has a time interval.
        if (hasTimeInterval()) {
          Waiter.waitForMilliseconds(timeIntervalInMilliseconds);
        }

        if (violatesProbableCondition()) {
          break;
        }

        finishedJobCount++;
        job.run();
      } catch (final Throwable lError) { // NOSONAR: All Throwables must be caught here.
        error = lError;
        GlobalLogger.logError(lError);
        break;
      }
    }

    running = false;
  }

  // method
  /**
   * @return true if the current {@link JobRunner} has a condition and violates
   *         it.
   */
  private boolean violatesProbableCondition() {
    return (hasCondition() && !condition.getAsBoolean());
  }

  // method
  /**
   * @return true if the current {@link JobRunner} has a max run count and has
   *         reached it.
   */
  private boolean reachedProbableMaxRunCount() {
    return (hasMaxRunCount() && finishedJobCount >= maxRunCount);
  }
}
