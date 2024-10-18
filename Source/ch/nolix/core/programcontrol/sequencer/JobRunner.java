package ch.nolix.core.programcontrol.sequencer;

import java.util.function.BooleanSupplier;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.logging.GlobalLogger;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

/**
 * @author Silvan Wyss
 * @version 2017-06-04
 */
final class JobRunner extends Thread {

  private static final JobMerger JOB_MERGER = new JobMerger();

  private final Runnable job;

  private int finishedJobCount;

  private boolean running = true;

  private final Integer maxRunCount;

  private final BooleanSupplier condition;

  private final Integer timeIntervalInMilliseconds;

  private Throwable error;

  /**
   * Creates a new {@link JobRunner} with the given job. The {@link JobRunner}
   * will start automatically.
   * 
   * @param job
   * @throws ArgumentIsNullException if the given job is null.
   */
  public JobRunner(final Runnable job) {

    //Asserts that the given job is not null.
    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseVariableCatalogue.JOB).isNotNull();

    this.job = job;
    maxRunCount = null;
    condition = null;
    timeIntervalInMilliseconds = null;

    start();
  }

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

    //Asserts that the given job is not null.
    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseVariableCatalogue.JOB).isNotNull();

    //Asserts that the given condition is not null.
    GlobalValidator.assertThat(condition).thatIsNamed("condition").isNotNull();

    this.job = job;
    maxRunCount = null;
    this.condition = condition;
    timeIntervalInMilliseconds = null;

    start();
  }

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
    //Asserts that the given job is not null.
    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseVariableCatalogue.JOB).isNotNull();

    //Asserts that the given condition is not null.
    GlobalValidator.assertThat(condition).thatIsNamed(LowerCaseVariableCatalogue.CONDITION).isNotNull();

    //Asserts that the given timeIntervalInMilliseconds is not negative.
    GlobalValidator.assertThat(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseoconds")
      .isNotNegative();

    this.job = job;
    this.maxRunCount = null;
    this.condition = condition;
    this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;

    start();
  }

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

    //Asserts that the given job is not null.
    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseVariableCatalogue.JOB).isNotNull();

    //Asserts that the given maxRunCount is not negative.
    GlobalValidator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();

    this.job = job;
    this.maxRunCount = maxRunCount;
    condition = null;
    timeIntervalInMilliseconds = null;

    start();
  }

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

    //Asserts that the given job is not null.
    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseVariableCatalogue.JOB).isNotNull();

    //Asserts that the given maxRunCount is not negative.
    GlobalValidator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();

    //Asserts that the given condition is not null.
    GlobalValidator.assertThat(condition).thatIsNamed(LowerCaseVariableCatalogue.CONDITION).isNotNull();

    this.job = job;
    this.maxRunCount = maxRunCount;
    this.condition = condition;
    timeIntervalInMilliseconds = null;

    start();
  }

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
    //Asserts that the given job is not null.
    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseVariableCatalogue.JOB).isNotNull();

    //Asserts that the given max run count is not negative.
    GlobalValidator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();

    //Asserts that the given condition is not null.
    GlobalValidator.assertThat(condition).thatIsNamed(LowerCaseVariableCatalogue.CONDITION).isNotNull();

    //Asserts that the given timeIntervalInMilliseconds is not negative.
    GlobalValidator.assertThat(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseconds").isNotNegative();

    this.job = job;
    this.maxRunCount = maxRunCount;
    this.condition = condition;
    this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;

    start();
  }

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

    //Asserts that the given job is not null.
    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseVariableCatalogue.JOB).isNotNull();

    //Asserts that the given maxRunCount is not negative.
    GlobalValidator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();

    //Asserts that the given timeIntervalInMilliseconds is not negative.
    GlobalValidator.assertThat(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseconds").isNotNegative();

    this.job = job;
    this.maxRunCount = maxRunCount;
    condition = null;
    this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;

    start();
  }

  public static JobRunner forJobs(final IContainer<Runnable> jobs) {
    return new JobRunner(JOB_MERGER.createMergedJobForJobs(jobs), 1);
  }

  /**
   * @return true if the current {@link JobRunner} has caught an error.
   */
  public boolean caughtError() {
    return (error != null);
  }

  //For a better performance, this implementation does not use all comfortable methods.
  /**
   * @return the error of the current {@link JobRunner}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link JobRunner} does not have
   *                                               an error.
   */
  public Throwable getError() {

    //Asserts that the current JobRunner has an error.
    if (error == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.ERROR);
    }

    return error;
  }

  /**
   * @return the number of finished jobs of the current {@link JobRunner}.
   */
  public int getFinishedJobCount() {
    return finishedJobCount;
  }

  /**
   * @return true if the current {@link JobRunner} has a condition.
   */
  public boolean hasCondition() {
    return (condition != null);
  }

  /**
   * @return true if the current {@link JobRunner} has a max run count.
   */
  public boolean hasMaxRunCount() {
    return (maxRunCount != null);
  }

  /**
   * @return true if the current {@link JobRunner} has a time interval.
   */
  public boolean hasTimeInterval() {
    return (timeIntervalInMilliseconds != null);
  }

  /**
   * @return true if the current {@link JobRunner} is finished.
   */
  public boolean isFinished() {
    return !isRunning();
  }

  /**
   * @return true if the current {@link JobRunner} is finished successfully.
   */
  public boolean isFinishedSuccessfully() {
    return (isFinished() && !caughtError());
  }

  /**
   * @return true if the current {@link JobRunner} is running.
   */
  public boolean isRunning() {
    return running;
  }

  /**
   * Lets the current {@link JobRunner} run.
   */
  @Override
  public void run() {

    //main loop
    while (true) {
      if (!runProbableNextStepAndSayIfRunningMustContinue()) {
        break;
      }
    }

    running = false;
  }

  /**
   * @return true if the current {@link JobRunner} has a max run count and has
   *         reached it.
   */
  private boolean reachedProbableMaxRunCount() {
    return (hasMaxRunCount() && finishedJobCount >= maxRunCount);
  }

  private boolean runProbableNextStepAndSayIfRunningMustContinue() {
    try {

      if (reachedProbableMaxRunCount()) {
        return false;
      }

      waitForTimeIntervalIfHasTimeInterval();

      if (violatesProbableCondition()) {
        return false;
      }

      job.run();
      finishedJobCount++;

      return true;
    } catch (final Throwable paramError) { //NOSONAR: All Throwables must be caught here.

      error = paramError;
      GlobalLogger.logError(paramError);

      return false;
    }
  }

  /**
   * @return true if the current {@link JobRunner} has a condition and violates
   *         it.
   */
  private boolean violatesProbableCondition() {
    return (hasCondition() && !condition.getAsBoolean());
  }

  /**
   * Waits for the time interval of the current {@link JobRunner} if the current
   * {@link JobRunner} has a time interval.
   */
  private void waitForTimeIntervalIfHasTimeInterval() {
    if (hasTimeInterval()) {
      Waiter.waitForMilliseconds(timeIntervalInMilliseconds);
    }
  }
}
