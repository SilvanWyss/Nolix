/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.flowcontrol;

import java.util.function.BooleanSupplier;

import ch.nolix.base.errorcontrol.logging.Logger;
import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.base.programcontrol.job.JobTool;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.baseapi.programcontrol.job.IJobTool;

/**
 * @author Silvan Wyss
 */
public final class JobExecutor extends Thread {
  private static final IJobTool JOB_TOOL = new JobTool();

  private final Runnable step;

  private final Integer optionalMaxStepRunCount;

  private final BooleanSupplier optionalNextStepRunCondition;

  private final Integer optionalDelayBetweenStepRunsInMilliseconds;

  private boolean started;

  private boolean running;

  private int finishedStepCount;

  private Throwable optionalCaughtError;

  /**
   * Creates a new {@link JobExecutor} with the given step.
   * 
   * @param step
   * @throws RuntimeException if the given step is null.
   */
  private JobExecutor(final Runnable step) {
    Validator.assertThat(step).thatIsNamed(LowerCaseVariableCatalog.STEP).isNotNull();

    this.step = step;
    optionalMaxStepRunCount = 1;
    optionalNextStepRunCondition = null;
    optionalDelayBetweenStepRunsInMilliseconds = null;
  }

  /**
   * Creates a new {@link JobExecutor} with the given job and condition.
   * 
   * @param job
   * @param condition
   * @throws RuntimeException if the given job is null.
   * @throws RuntimeException if the given condition is null.
   */
  public JobExecutor(final Runnable job, final BooleanSupplier condition) {
    //Asserts that the given job is not null.
    Validator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    //Asserts that the given condition is not null.
    Validator.assertThat(condition).thatIsNamed("condition").isNotNull();

    this.step = job;
    optionalMaxStepRunCount = null;
    this.optionalNextStepRunCondition = condition;
    optionalDelayBetweenStepRunsInMilliseconds = null;
  }

  /**
   * Creates a new {@link JobExecutor} with the given job, condition and
   * timeIntervalInMilliseconds.
   * 
   * @param job
   * @param condition
   * @param timeIntervalInMilliseconds
   * @throws RuntimeException if the given job is null.
   * @throws RuntimeException if the given condition is null.
   * @throws RuntimeException if the given timeIntervalInMilliseconds is negative.
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

    this.step = job;
    this.optionalMaxStepRunCount = null;
    this.optionalNextStepRunCondition = condition;
    this.optionalDelayBetweenStepRunsInMilliseconds = timeIntervalInMilliseconds;
  }

  /**
   * Creates a new {@link JobExecutor} with the given job and maxRunCount.
   * 
   * @param job
   * @param maxRunCount
   * @throws RuntimeException if the given job is null.
   * @throws RuntimeException if the given maxRunCount is negative.
   */
  public JobExecutor(
    final Runnable job,
    final int maxRunCount) {
    //Asserts that the given job is not null.
    Validator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    //Asserts that the given maxRunCount is not negative.
    Validator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();

    this.step = job;
    this.optionalMaxStepRunCount = maxRunCount;
    optionalNextStepRunCondition = null;
    optionalDelayBetweenStepRunsInMilliseconds = null;
  }

  /**
   * Creates a new {@link JobExecutor} with the given job, maxRunCount and
   * condition.
   * 
   * @param job
   * @param maxRunCount
   * @param condition
   * @throws RuntimeException if the given job is null.
   * @throws RuntimeException if the given maxRunCount is negative.
   * @throws RuntimeException if the given condition is null.
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

    this.step = job;
    this.optionalMaxStepRunCount = maxRunCount;
    this.optionalNextStepRunCondition = condition;
    optionalDelayBetweenStepRunsInMilliseconds = null;
  }

  /**
   * Creates a new {@link JobExecutor} with the given job, maxRunCount, condition
   * and timeIntervalInMilliseconds.
   * 
   * @param job
   * @param maxRunCount
   * @param condition
   * @param timeIntervalInMilliseconds
   * @throws RuntimeException if the given job is null.
   * @throws RuntimeException if the given maxRunCount is negative.
   * @throws RuntimeException if the given condition is null.
   * @throws RuntimeException if the given timeIntervalInMilliseconds is negative.
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

    this.step = job;
    this.optionalMaxStepRunCount = maxRunCount;
    this.optionalNextStepRunCondition = condition;
    this.optionalDelayBetweenStepRunsInMilliseconds = timeIntervalInMilliseconds;
  }

  /**
   * Creates a new {@link JobExecutor} with the given job, maxRunCount and
   * timeIntervalInMilliseconds.
   * 
   * @param job
   * @param maxRunCount
   * @param timeIntervalInMilliseconds
   * @throws RuntimeException if the given job is null.
   * @throws RuntimeException if the given maxRunCount is negative.
   * @throws RuntimeException if the given timeIntervalInMilliseconds is negative.
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

    this.step = job;
    this.optionalMaxStepRunCount = maxRunCount;
    optionalNextStepRunCondition = null;
    this.optionalDelayBetweenStepRunsInMilliseconds = timeIntervalInMilliseconds;
  }

  /**
   * @param step
   * @return a new {@link JobExecutor} with the given step.
   * @throws RuntimeException if the given step is null.
   */
  public static JobExecutor forStep(final Runnable step) {
    return new JobExecutor(step);
  }

  public static JobExecutor forJobs(final IContainer<Runnable> jobs) {
    final var concatenatedJob = JOB_TOOL.createConcatenatedJobFromJobs(jobs);
    final var jobExecutor = new JobExecutor(concatenatedJob, 1);

    jobExecutor.start();

    return jobExecutor;
  }

  /**
   * @return true if the current {@link JobExecutor} has caught an error, false
   *         otherwise.
   */
  public boolean caughtError() {
    return (optionalCaughtError != null);
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
    if (optionalCaughtError == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.ERROR);
    }

    return optionalCaughtError;
  }

  /**
   * @return the number of finished jobs of the current {@link JobExecutor}.
   */
  public int getFinishedJobCount() {
    return finishedStepCount;
  }

  /**
   * @return true if the current {@link JobExecutor} has a condition, false
   *         otherwise.
   */
  public boolean hasCondition() {
    return (optionalNextStepRunCondition != null);
  }

  /**
   * @return true if the current {@link JobExecutor} has a max run count, false
   *         otherwise.
   */
  public boolean hasMaxRunCount() {
    return (optionalMaxStepRunCount != null);
  }

  /**
   * @return true if the current {@link JobExecutor} has a time interval, false
   *         otherwise.
   */
  public boolean hasTimeInterval() {
    return (optionalDelayBetweenStepRunsInMilliseconds != null);
  }

  /**
   * @return true if the current {@link JobExecutor} is finished, false otherwise.
   */
  public boolean isFinished() {
    return hasStarted() && !isRunning();
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
    started = true;
    running = true;

    while (true) {
      if (!runProbableNextStepAndSayIfRunningMustContinue()) {
        break;
      }
    }

    running = false;
  }

  private boolean hasStarted() {
    return started;
  }

  /**
   * @return true if the current {@link JobExecutor} has a max run count and has
   *         reached it, false otherwise.
   */
  private boolean reachedProbableMaxRunCount() {
    return (hasMaxRunCount() && finishedStepCount >= optionalMaxStepRunCount);
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

      step.run();
      finishedStepCount++;

      return true;
    } catch (final Throwable paramError) { //NOSONAR: All Throwables must be caught.

      optionalCaughtError = paramError;
      Logger.logError(paramError);

      return false;
    }
  }

  /**
   * @return true if the current {@link JobExecutor} has a condition and violates
   *         it, false otherwise.
   */
  private boolean violatesProbableCondition() {
    return (hasCondition() && !optionalNextStepRunCondition.getAsBoolean());
  }

  /**
   * Waits for the time interval of the current {@link JobExecutor} if the current
   * {@link JobExecutor} has a time interval.
   */
  private void waitForTimeIntervalIfHasTimeInterval() {
    if (hasTimeInterval()) {
      Waiter.waitForMilliseconds(optionalDelayBetweenStepRunsInMilliseconds);
    }
  }
}
