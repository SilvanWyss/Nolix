/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.flowcontrol;

import java.util.function.BooleanSupplier;

import ch.nolix.base.programcontrol.basicflowcontroller.BasicFlowController;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.programcontrol.flowcontrol.IAfterEveryMediator;
import ch.nolix.baseapi.programcontrol.future.IFuture;

/**
 * A {@link AfterEveryMediator} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class AfterEveryMediator implements IAfterEveryMediator {
  private final int timeIntervalInMilliseconds;

  private final Integer maxRunCount;

  private final BooleanSupplier condition;

  /**
   * Creates a new {@link AfterEveryMediator} with the given condition and
   * timeIntervalInMilliseconds.
   * 
   * @param condition
   * @param timeIntervalInMilliseconds
   * @throws RuntimeException if the given condition is null
   * @throws RuntimeException if the given timeIntervalInMilliseconds is negative
   */
  private AfterEveryMediator(final BooleanSupplier condition, final int timeIntervalInMilliseconds) {
    Validator.assertThat(condition).thatIsNamed(LowerCaseVariableNameCatalog.CONDITION).isNotNull();
    Validator.assertThat(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseconds").isNotNegative();

    maxRunCount = null;
    this.condition = condition;
    this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
  }

  /**
   * @param condition
   * @param timeIntervalInMilliseconds
   * @return a new {@link AfterEveryMediator} with the given condition and
   *         timeIntervalInMilliseconds
   * @throws RuntimeException if the given condition is null
   * @throws RuntimeException if the given timeIntervalInMilliseconds is negative
   */
  public static AfterEveryMediator withConditionAndTimeIntervalInMilliSeconds(
    final BooleanSupplier condition,
    final int timeIntervalInMilliseconds) {
    return new AfterEveryMediator(condition, timeIntervalInMilliseconds);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(final Runnable job) {
    // Handles the case that the current AfterEveryMediator does not have a max run count.
    if (!hasMaxRunCount()) {
      runWhenDoesNotHaveMaxRunCount(job);

      // Handles the case that the current AfterEveryMediator has a max run count.
    } else {
      runWhenHasMaxRunCount(job);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IFuture runInBackground(final Runnable job) {
    // Handles the case that the current AfterEveryMediator does not have a max count.
    if (!hasMaxRunCount()) {
      return runInBackgroundWhenDoesNotHaveMaxRunConunt(job);
    }

    // Handles the case that the current AfterEveryMediator has a max count.
    return runInBackgroundWhenHasMaxRunConunt(job);
  }

  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AfterEveryMediator} does
   *                                               not have condition.
   */
  private void assertHasCondition() {
    if (!hasCondition()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.CONDITION);
    }
  }

  /**
   * @return true if the current {@link AfterEveryMediator} has a condition, false
   *         otherwise
   */
  private boolean hasCondition() {
    return (condition != null);
  }

  /**
   * @return true if the current {@link AfterEveryMediator} has a max run count,
   *         false otherwise
   */
  private boolean hasMaxRunCount() {
    return (maxRunCount != null);
  }

  /**
   * Lets the current {@link AfterEveryMediator} run the given step in background
   * for the case when the current {@link AfterEveryMediator} does not have a max
   * run count.
   * 
   * @param step
   * @return a new {@link Future}
   * @throws RuntimeException if the given job is null
   */
  private IFuture runInBackgroundWhenDoesNotHaveMaxRunConunt(final Runnable step) {
    // Handles the case that the current AfterAllMediator does not have a condition.
    if (!hasCondition()) {
      final var jobExecutor = //
      JobExecutor.forStepAndMaxStepRunCountAndDelayBetweenStepRunsInMilliseconds(step, maxRunCount,
        timeIntervalInMilliseconds);

      jobExecutor.start();

      return Future.forJobExecutor(jobExecutor);
    }

    // Handles the case that the current AfterAllMediator has a condition.
    final var jobExecutor = //
    JobExecutor.forStepAndNextStepRunConditionAndDelayBetweenStepRunsInMilliseconds(
      step,
      condition,
      timeIntervalInMilliseconds);

    jobExecutor.start();

    return Future.forJobExecutor(jobExecutor);
  }

  /**
   * Runs the given step in background for the case when the current
   * {@link AfterEveryMediator} has a max step run count.
   * 
   * @param step
   * @return a new {@link Future}
   * @throws RuntimeException if the given job is null
   */
  private IFuture runInBackgroundWhenHasMaxRunConunt(final Runnable step) {
    // Handles the case that the current AfterAllMediator does not have a condition.
    if (!hasCondition()) {
      final var jobExecutor = //
      JobExecutor.forStepAndMaxStepRunCountAndDelayBetweenStepRunsInMilliseconds(
        step,
        maxRunCount,
        timeIntervalInMilliseconds);

      jobExecutor.start();

      return Future.forJobExecutor(jobExecutor);
    }

    // Handles the case that the current AfterAllMediator has a condition.
    final var jobExecutor = //
    JobExecutor
      .forStepAndMaxStepRunCountAndNextStepRunConditionAndDelayBetweenStepRunsInMilliseconds(
        step,
        maxRunCount,
        condition,
        timeIntervalInMilliseconds);
    jobExecutor.start();
    return Future.forJobExecutor(jobExecutor);
  }

  /**
   * Lets the current {@link AfterEveryMediator} run the given job for the case
   * when the current {@link AfterEveryMediator} does not have a max run count.
   * 
   * @param job
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AfterEveryMediator} does
   *                                               not have condition.
   */
  private void runWhenDoesNotHaveMaxRunCount(final Runnable job) {
    Validator.assertThat(job).thatIsNamed(LowerCaseVariableNameCatalog.JOB).isNotNull();

    assertHasCondition();

    while (condition.getAsBoolean()) {
      job.run();
      BasicFlowController.waitForMilliseconds(timeIntervalInMilliseconds);
    }
  }

  /**
   * Lets the current {@link AfterEveryMediator} run the given job for the case
   * when the current {@link AfterEveryMediator} has a max run count.
   * 
   * @param job
   */
  private void runWhenHasMaxRunCount(final Runnable job) {
    Validator.assertThat(job).thatIsNamed(LowerCaseVariableNameCatalog.JOB).isNotNull();

    // Handles the case that the current AfterAllMediator does not have a condition.
    if (!hasCondition()) {
      for (var i = 1; i <= maxRunCount; i++) {
        BasicFlowController.waitForMilliseconds(timeIntervalInMilliseconds);

        job.run();
      }

      // Handles the case that the current AfterAllMediator has a condition.
    } else {
      for (var i = 1; i <= maxRunCount; i++) {
        BasicFlowController.waitForMilliseconds(timeIntervalInMilliseconds);

        if (!condition.getAsBoolean()) {
          break;
        }

        job.run();
      }
    }
  }
}
