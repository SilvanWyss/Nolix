/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.programcontrol.flowcontrol;

import java.util.function.BooleanSupplier;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.logging.Logger;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 */
final class JobExecutor extends Thread {
  private static final JobMerger JOB_MERGER = new JobMerger();

  private final Runnable job;

  private int finishedJobCount;

  private boolean running = true;

  private final Integer maxRunCount;

  private final BooleanSupplier condition;

  private final Integer timeIntervalInMilliseconds;

  private Throwable error;

  /**
   * Creates a new {@link JobExecutor} with the given job. The {@link JobExecutor}
   * will start automatically.
   * 
   * @param job
   * @throws ArgumentIsNullException if the given job is null.
   */
  public JobExecutor(final Runnable job) {
    //Asserts that the given job is not null.
    Validator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    this.job = job;
    maxRunCount = null;
    condition = null;
    timeIntervalInMilliseconds = null;

    start();
  }

  /**
   * Creates a new {@link JobExecutor} with the given job and condition. The
   * {@link JobExecutor} will start automatically.
   * 
   * @param job
   * @param condition
   * @throws ArgumentIsNullException if the given job is null.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public JobExecutor(final Runnable job, final BooleanSupplier condition) {
    //Asserts that the given job is not null.
    Validator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    //Asserts that the given condition is not null.
    Validator.assertThat(condition).thatIsNamed("condition").isNotNull();

    this.job = job;
    maxRunCount = null;
    this.condition = condition;
    timeIntervalInMilliseconds = null;

    start();
  }

  /**
   * Creates a new {@link JobExecutor} with the given job, condition and
   * timeIntervalInMilliseconds. The {@link JobExecutor} will start automatically.
   * 
   * @param job
   * @param condition
   * @param timeIntervalInMilliseconds
   * @throws ArgumentIsNullException   if the given job is null.
   * @throws ArgumentIsNullException   if the given condition is null.
   * @throws NegativeArgumentException if the given timeIntervalInMilliseconds is
   *                                   negative.
   */
  public JobExecutor(
    final Runnable job,
    final BooleanSupplier condition,
    final int timeIntervalInMilliseconds) {
    //Asserts that the given job is not null.
    Validator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    //Asserts that the given condition is not null.
    Validator.assertThat(condition).thatIsNamed(LowerCaseVariableCatalog.CONDITION).isNotNull();

    //Asserts that the given timeIntervalInMilliseconds is not negative.
    Validator.assertThat(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseoconds")
      .isNotNegative();

    this.job = job;
    this.maxRunCount = null;
    this.condition = condition;
    this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;

    start();
  }

  /**
   * Creates a new {@link JobExecutor} with the given job and maxRunCount. The
   * {@link JobExecutor} will start automatically.
   * 
   * @param job
   * @param maxRunCount
   * @throws ArgumentIsNullException   if the given job is null.
   * @throws NegativeArgumentException if the given maxRunCount is negative.
   */
  public JobExecutor(
    final Runnable job,
    final int maxRunCount) {
    //Asserts that the given job is not null.
    Validator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    //Asserts that the given maxRunCount is not negative.
    Validator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();

    this.job = job;
    this.maxRunCount = maxRunCount;
    condition = null;
    timeIntervalInMilliseconds = null;

    start();
  }

  /**
   * Creates a new {@link JobExecutor} with the given job, maxRunCount and
   * condition. The {@link JobExecutor} will start automatically.
   * 
   * @param job
   * @param maxRunCount
   * @param condition
   * @throws ArgumentIsNullException   if the given job is null.
   * @throws NegativeArgumentException if the given maxRunCount is negative.
   * @throws ArgumentIsNullException   if the given condition is null.
   */
  public JobExecutor(
    final Runnable job,
    final int maxRunCount,
    final BooleanSupplier condition) {
    //Asserts that the given job is not null.
    Validator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    //Asserts that the given maxRunCount is not negative.
    Validator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();

    //Asserts that the given condition is not null.
    Validator.assertThat(condition).thatIsNamed(LowerCaseVariableCatalog.CONDITION).isNotNull();

    this.job = job;
    this.maxRunCount = maxRunCount;
    this.condition = condition;
    timeIntervalInMilliseconds = null;

    start();
  }

  /**
   * Creates a new {@link JobExecutor} with the given job, maxRunCount, condition
   * and timeIntervalInMilliseconds. The {@link JobExecutor} will start
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
  public JobExecutor(
    final Runnable job,
    final int maxRunCount,
    final BooleanSupplier condition,
    final int timeIntervalInMilliseconds) {
    //Asserts that the given job is not null.
    Validator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    //Asserts that the given max run count is not negative.
    Validator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();

    //Asserts that the given condition is not null.
    Validator.assertThat(condition).thatIsNamed(LowerCaseVariableCatalog.CONDITION).isNotNull();

    //Asserts that the given timeIntervalInMilliseconds is not negative.
    Validator.assertThat(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseconds").isNotNegative();

    this.job = job;
    this.maxRunCount = maxRunCount;
    this.condition = condition;
    this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;

    start();
  }

  /**
   * Creates a new {@link JobExecutor} with the given job, maxRunCount and
   * timeIntervalInMilliseconds. The {@link JobExecutor} will start automatically.
   * 
   * @param job
   * @param maxRunCount
   * @param timeIntervalInMilliseconds
   * @throws ArgumentIsNullException   if the given job is null.
   * @throws NegativeArgumentException if the given maxRunCount is negative.
   * @throws NegativeArgumentException if the given timeIntervalInMilliseconds is
   *                                   negative.
   */
  public JobExecutor(
    final Runnable job,
    final int maxRunCount,
    final int timeIntervalInMilliseconds) {
    //Asserts that the given job is not null.
    Validator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    //Asserts that the given maxRunCount is not negative.
    Validator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();

    //Asserts that the given timeIntervalInMilliseconds is not negative.
    Validator.assertThat(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseconds").isNotNegative();

    this.job = job;
    this.maxRunCount = maxRunCount;
    condition = null;
    this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;

    start();
  }

  public static JobExecutor forJobs(final IContainer<Runnable> jobs) {
    return new JobExecutor(JOB_MERGER.createMergedJobForJobs(jobs), 1);
  }

  /**
   * @return true if the current {@link JobExecutor} has caught an error, false
   *         otherwise.
   */
  public boolean caughtError() {
    return (error != null);
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * @return the error of the current {@link JobExecutor}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link JobExecutor} does not
   *                                               have an error.
   */
  public Throwable getError() {
    //Asserts that the current JobRunner has an error.
    if (error == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.ERROR);
    }

    return error;
  }

  /**
   * @return the number of finished jobs of the current {@link JobExecutor}.
   */
  public int getFinishedJobCount() {
    return finishedJobCount;
  }

  /**
   * @return true if the current {@link JobExecutor} has a condition, false
   *         otherwise.
   */
  public boolean hasCondition() {
    return (condition != null);
  }

  /**
   * @return true if the current {@link JobExecutor} has a max run count, false
   *         otherwise.
   */
  public boolean hasMaxRunCount() {
    return (maxRunCount != null);
  }

  /**
   * @return true if the current {@link JobExecutor} has a time interval, false
   *         otherwise.
   */
  public boolean hasTimeInterval() {
    return (timeIntervalInMilliseconds != null);
  }

  /**
   * @return true if the current {@link JobExecutor} is finished, false otherwise.
   */
  public boolean isFinished() {
    return !isRunning();
  }

  /**
   * @return true if the current {@link JobExecutor} is finished successfully,
   *         false otherwise.
   */
  public boolean isFinishedSuccessfully() {
    return (isFinished() && !caughtError());
  }

  /**
   * @return true if the current {@link JobExecutor} is running, false otherwise.
   */
  public boolean isRunning() {
    return running;
  }

  /**
   * Lets the current {@link JobExecutor} run.
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
   * @return true if the current {@link JobExecutor} has a max run count and has
   *         reached it, false otherwise.
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
    } catch (final Throwable paramError) { //NOSONAR: All Throwables must be caught.

      error = paramError;
      Logger.logError(paramError);

      return false;
    }
  }

  /**
   * @return true if the current {@link JobExecutor} has a condition and violates
   *         it, false otherwise.
   */
  private boolean violatesProbableCondition() {
    return (hasCondition() && !condition.getAsBoolean());
  }

  /**
   * Waits for the time interval of the current {@link JobExecutor} if the current
   * {@link JobExecutor} has a time interval.
   */
  private void waitForTimeIntervalIfHasTimeInterval() {
    if (hasTimeInterval()) {
      Waiter.waitForMilliseconds(timeIntervalInMilliseconds);
    }
  }
}
