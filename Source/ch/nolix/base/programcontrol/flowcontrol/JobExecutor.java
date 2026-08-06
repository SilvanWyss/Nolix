/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.flowcontrol;

import java.util.function.BooleanSupplier;

import ch.nolix.base.datastructure.extendediterableview.ExtendedIterableView;
import ch.nolix.base.errorcontrol.logging.Logger;
import ch.nolix.base.programcontrol.basicflowcontroller.BasicFlowController;
import ch.nolix.base.programcontrol.job.JobTool;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public final class JobExecutor extends Thread {
  private static final JobTool JOB_TOOL = new JobTool();

  private final Runnable step;

  private final Integer optionalMaxStepRunCount;

  private final BooleanSupplier optionalNextStepRunCondition;

  private final Integer optionalDelayBetweenStepRunsInMilliseconds;

  private boolean started;

  private boolean running;

  private int finishedStepRunCount;

  private Throwable optionalCaughtError;

  /**
   * Creates a new {@link JobExecutor} with the given step.
   * 
   * @param step
   * @throws RuntimeException if the given step is null
   */
  private JobExecutor(final Runnable step) {
    Validator.assertThat(step).thatIsNamed(LowerCaseVariableNameCatalog.STEP).isNotNull();

    this.step = step;
    this.optionalMaxStepRunCount = 1;
    this.optionalNextStepRunCondition = null;
    this.optionalDelayBetweenStepRunsInMilliseconds = null;
  }

  /**
   * Creates a new {@link JobExecutor} with the given step and
   * nextStepRunCondition.
   * 
   * @param step
   * @param nextStepRunCondition
   * @throws RuntimeException if the given step is null
   * @throws RuntimeException if the given nextStepRunCondition is null
   */
  private JobExecutor(final Runnable step, final BooleanSupplier nextStepRunCondition) {
    Validator.assertThat(step).thatIsNamed(LowerCaseVariableNameCatalog.STEP).isNotNull();
    Validator.assertThat(nextStepRunCondition).thatIsNamed("next step run condition").isNotNull();

    this.step = step;
    this.optionalMaxStepRunCount = null;
    this.optionalNextStepRunCondition = nextStepRunCondition;
    this.optionalDelayBetweenStepRunsInMilliseconds = null;
  }

  /**
   * Creates a new {@link JobExecutor} with the given step, nextStepRunCondition
   * and delayBetweenStepRunsInMilliseconds.
   * 
   * @param step
   * @param nextStepRunCondition
   * @param delayBetweenStepRunsInMilliseconds
   * @throws RuntimeException if the given step is null
   * @throws RuntimeException if the given nextStepRunCondition is null
   * @throws RuntimeException if the given delayBetweenStepRunsInMilliseconds is
   *                          negative.
   */
  private JobExecutor(
    final Runnable step,
    final BooleanSupplier nextStepRunCondition,
    final int delayBetweenStepRunsInMilliseconds) {
    Validator.assertThat(step).thatIsNamed(LowerCaseVariableNameCatalog.STEP).isNotNull();
    Validator.assertThat(nextStepRunCondition).thatIsNamed("next step run condition").isNotNull();

    Validator
      .assertThat(delayBetweenStepRunsInMilliseconds)
      .thatIsNamed("delay between step runs in milliseconds")
      .isNotNegative();

    this.step = step;
    this.optionalMaxStepRunCount = null;
    this.optionalNextStepRunCondition = nextStepRunCondition;
    this.optionalDelayBetweenStepRunsInMilliseconds = delayBetweenStepRunsInMilliseconds;
  }

  /**
   * Creates a new {@link JobExecutor} with the given step and maxStepRunCount.
   * 
   * @param step
   * @param maxStepRunCount
   * @throws RuntimeException if the given step is null
   * @throws RuntimeException if the given maxStepRunCount is negative
   */
  private JobExecutor(final Runnable step, final int maxStepRunCount) {
    Validator.assertThat(step).thatIsNamed(LowerCaseVariableNameCatalog.STEP).isNotNull();
    Validator.assertThat(maxStepRunCount).thatIsNamed("max step run count").isNotNegative();

    this.step = step;
    this.optionalMaxStepRunCount = maxStepRunCount;
    this.optionalNextStepRunCondition = null;
    this.optionalDelayBetweenStepRunsInMilliseconds = null;
  }

  /**
   * Creates a new {@link JobExecutor} with the given step, maxStepRunCount and
   * nextStepRunCondition.
   * 
   * @param step
   * @param maxStepRunCount
   * @param nextStepRunCondition
   * @throws RuntimeException if the given step is null
   * @throws RuntimeException if the given maxStepRunCount is negative
   * @throws RuntimeException if the given nextStepRunCondition is null
   */
  private JobExecutor(final Runnable step, final int maxStepRunCount, final BooleanSupplier nextStepRunCondition) {
    Validator.assertThat(step).thatIsNamed(LowerCaseVariableNameCatalog.STEP).isNotNull();
    Validator.assertThat(maxStepRunCount).thatIsNamed("max step run count").isNotNegative();
    Validator.assertThat(nextStepRunCondition).thatIsNamed("next step run condition").isNotNull();

    this.step = step;
    this.optionalMaxStepRunCount = maxStepRunCount;
    this.optionalNextStepRunCondition = nextStepRunCondition;
    this.optionalDelayBetweenStepRunsInMilliseconds = null;
  }

  /**
   * Creates a new {@link JobExecutor} with the given step, maxStepRunCount,
   * nextStepRunCondition and delayBetweenStepRunsInMilliseconds.
   * 
   * @param step
   * @param maxStepRunCount
   * @param nextStepRunCondition
   * @param delayBetweenStepRunsInMilliseconds
   * @throws RuntimeException if the given job is null
   * @throws RuntimeException if the given maxRunCount is negative
   * @throws RuntimeException if the given condition is null
   * @throws RuntimeException if the given delayBetweenStepRunsInMilliseconds is
   *                          negative.
   */
  private JobExecutor(
    final Runnable step,
    final int maxStepRunCount,
    final BooleanSupplier nextStepRunCondition,
    final int delayBetweenStepRunsInMilliseconds) {
    Validator.assertThat(step).thatIsNamed(LowerCaseVariableNameCatalog.STEP).isNotNull();
    Validator.assertThat(maxStepRunCount).thatIsNamed("max step run count").isNotNegative();
    Validator.assertThat(nextStepRunCondition).thatIsNamed("next step run condition").isNotNull();

    Validator
      .assertThat(delayBetweenStepRunsInMilliseconds)
      .thatIsNamed("delay between step runs in milliseconds")
      .isNotNegative();

    this.step = step;
    this.optionalMaxStepRunCount = maxStepRunCount;
    this.optionalNextStepRunCondition = nextStepRunCondition;
    this.optionalDelayBetweenStepRunsInMilliseconds = delayBetweenStepRunsInMilliseconds;
  }

  /**
   * Creates a new {@link JobExecutor} with the given step, maxStepRunCount and
   * delayBetweenStepRunsInMilliseconds.
   * 
   * @param step
   * @param maxStepRunCount
   * @param delayBetweenStepRunsInMilliseconds
   * @throws RuntimeException if the given job is null
   * @throws RuntimeException if the given maxRunCount is negative
   * @throws RuntimeException if the given timeIntervalInMilliseconds is negative
   */
  private JobExecutor(
    final Runnable step,
    final int maxStepRunCount,
    final int delayBetweenStepRunsInMilliseconds) {
    Validator.assertThat(step).thatIsNamed(LowerCaseVariableNameCatalog.STEP).isNotNull();
    Validator.assertThat(maxStepRunCount).thatIsNamed("max step run count").isNotNegative();

    Validator
      .assertThat(delayBetweenStepRunsInMilliseconds)
      .thatIsNamed("delay between step runs in milliseconds")
      .isNotNegative();

    this.step = step;
    this.optionalMaxStepRunCount = maxStepRunCount;
    this.optionalNextStepRunCondition = null;
    this.optionalDelayBetweenStepRunsInMilliseconds = delayBetweenStepRunsInMilliseconds;
  }

  /**
   * @param step
   * @return a new {@link JobExecutor} with the given step
   * @throws RuntimeException if the given step is null
   */
  public static JobExecutor forStep(final Runnable step) {
    return new JobExecutor(step);
  }

  /**
   * @param step
   * @param maxStepRunCount
   * @return a new {@link JobExecutor} with the given step and maxStepRunCount
   * @throws RuntimeException if the given step is null
   * @throws RuntimeException if the given maxStepRunCount is negative
   */
  public static JobExecutor forStepAndMaxStepRunCount(final Runnable step, final int maxStepRunCount) {
    return new JobExecutor(step, maxStepRunCount);
  }

  /**
   * @param step
   * @param maxStepRunCount
   * @param delayBetweenStepRunsInMilliseconds
   * @return a new {@link JobExecutor} with the given step, maxStepRunCount and
   *         delayBetweenStepRunsInMilliseconds
   * @throws RuntimeException if the given step is null
   * @throws RuntimeException if the given maxStepRunCount is negative
   * @throws RuntimeException if the given delayBetweenStepRunsInMilliseconds is
   *                          negative.
   */
  public static JobExecutor forStepAndMaxStepRunCountAndDelayBetweenStepRunsInMilliseconds(
    final Runnable step,
    final int maxStepRunCount,
    final int delayBetweenStepRunsInMilliseconds) {
    return new JobExecutor(step, maxStepRunCount, delayBetweenStepRunsInMilliseconds);
  }

  /**
   * @param step
   * @param maxStepRunCount
   * @param nextStepRunCondition
   * @return a new {@link JobExecutor} with the given step, maxStepRunCount and
   *         nextStepRunCondition
   * @throws RuntimeException if the given step is null
   * @throws RuntimeException if the given maxStepRunCount is negative
   * @throws RuntimeException if the given nextStepRunCondition is null
   */
  public static JobExecutor forStepAndMaxStepRunCountAndNextStepRunCondition(
    final Runnable step,
    final int maxStepRunCount,
    final BooleanSupplier nextStepRunCondition) {
    return new JobExecutor(step, maxStepRunCount, nextStepRunCondition);
  }

  /**
   * @param step
   * @param maxStepRunCount
   * @param nextStepRunCondition
   * @param delayBetweenStepRunsInMilliseconds
   * @return a new {@link JobExecutor} with the given step, maxStepRunCount,
   *         nextStepRunCondition and delayBetweenStepRunsInMilliseconds
   * @throws RuntimeException if the given job is null
   * @throws RuntimeException if the given maxRunCount is negative
   * @throws RuntimeException if the given condition is null
   * @throws RuntimeException if the given delayBetweenStepRunsInMilliseconds is
   *                          negative.
   */
  public static JobExecutor forStepAndMaxStepRunCountAndNextStepRunConditionAndDelayBetweenStepRunsInMilliseconds(
    final Runnable step,
    final int maxStepRunCount,
    final BooleanSupplier nextStepRunCondition,
    final int delayBetweenStepRunsInMilliseconds) {
    return new JobExecutor(step, maxStepRunCount, nextStepRunCondition, delayBetweenStepRunsInMilliseconds);
  }

  /**
   * @param step
   * @param nextStepRunCondition
   * @return a new {@link JobExecutor} with the given step and
   *         nextStepRunCondition
   * @throws RuntimeException if the given step is null
   * @throws RuntimeException if the given nextStepRunCondition is null
   */
  public static JobExecutor forStepAndNextStepRunCondition(
    final Runnable step,
    final BooleanSupplier nextStepRunCondition) {
    return new JobExecutor(step, nextStepRunCondition);
  }

  /**
   * @param step
   * @param nextStepRunCondition
   * @param delayBetweenStepRunsInMilliseconds
   * @return a new {@link JobExecutor} with the given step, nextStepRunCondition
   *         and delayBetweenStepRunsInMilliseconds
   * @throws RuntimeException if the given job is null
   * @throws RuntimeException if the given condition is null
   * @throws RuntimeException if the given delayBetweenStepRunsInMilliseconds is
   *                          negative.
   */
  public static JobExecutor forStepAndNextStepRunConditionAndDelayBetweenStepRunsInMilliseconds(
    final Runnable step,
    final BooleanSupplier nextStepRunCondition,
    final int delayBetweenStepRunsInMilliseconds) {
    return new JobExecutor(step, nextStepRunCondition, delayBetweenStepRunsInMilliseconds);
  }

  public static JobExecutor forJobs(final ExtendedIterable<Runnable> jobs) {
    final var concatenatedJob = JOB_TOOL.createConcatenatedJobFromJobs(jobs);
    final var jobExecutor = new JobExecutor(concatenatedJob, 1);

    jobExecutor.start();

    return jobExecutor;
  }

  public static JobExecutor forJobs(final Runnable... jobs) {
    final var jobContainer = ExtendedIterableView.forArray(jobs);

    return forJobs(jobContainer);
  }

  /**
   * @return true if the current {@link JobExecutor} has caught an error, false
   *         otherwise
   */
  public boolean caughtError() {
    return (optionalCaughtError != null);
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * @return the error of the current {@link JobExecutor}
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link JobExecutor} does not
   *                                               have an error.
   */
  public Throwable getError() {
    // Asserts that the current JobRunner has an error.
    if (optionalCaughtError == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.ERROR);
    }

    return optionalCaughtError;
  }

  /**
   * @return the number of finished steps of the current {@link JobExecutor}.
   */
  public int getFinishedStepRunCount() {
    return finishedStepRunCount;
  }

  /**
   * @return true if the current {@link JobExecutor} has a condition, false
   *         otherwise
   */
  public boolean hasCondition() {
    return (optionalNextStepRunCondition != null);
  }

  /**
   * @return true if the current {@link JobExecutor} has a max run count, false
   *         otherwise
   */
  public boolean hasMaxRunCount() {
    return (optionalMaxStepRunCount != null);
  }

  /**
   * @return true if the current {@link JobExecutor} has a time interval, false
   *         otherwise
   */
  public boolean hasTimeInterval() {
    return (optionalDelayBetweenStepRunsInMilliseconds != null);
  }

  /**
   * @return true if the current {@link JobExecutor} is finished, false otherwise
   */
  public boolean isFinished() {
    return hasStarted() && !isRunning();
  }

  /**
   * @return true if the current {@link JobExecutor} is finished successfully,
   *         false otherwise
   */
  public boolean isFinishedSuccessfully() {
    return (isFinished() && !caughtError());
  }

  /**
   * @return true if the current {@link JobExecutor} is running, false otherwise
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
   *         reached it, false otherwise
   */
  private boolean reachedProbableMaxRunCount() {
    return (hasMaxRunCount() && finishedStepRunCount >= optionalMaxStepRunCount);
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
      finishedStepRunCount++;

      return true;
    } catch (final Throwable paramError) { // NOSONAR: All errors must be caught.

      optionalCaughtError = paramError;
      Logger.logError(paramError);

      return false;
    }
  }

  /**
   * @return true if the current {@link JobExecutor} has a condition and violates
   *         it, false otherwise
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
      BasicFlowController.waitForMilliseconds(optionalDelayBetweenStepRunsInMilliseconds);
    }
  }
}
