package ch.nolix.core.programcontrol.flowcontrol;

import java.util.function.BooleanSupplier;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programcontrol.flowcontrol.IAfterEveryMediator;

/**
 * A {@link AfterEveryMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2017-01-01
 */
public final class AfterEveryMediator implements IAfterEveryMediator {

  private final int timeIntervalInMilliseconds;

  private final Integer maxRunCount;

  private final BooleanSupplier condition;

  /**
   * Creates a new {@link AfterEveryMediator} with the given condition and time
   * interval in milliseconds.
   * 
   * @param condition
   * @param timeIntervalInMilliseconds
   * @throws ArgumentIsNullException   if the given condition is null.
   * @throws NegativeArgumentException if the given timeIntervalInMilliseconds is
   *                                   negative.
   */
  AfterEveryMediator(final BooleanSupplier condition, final int timeIntervalInMilliseconds) {

    Validator.assertThat(condition).thatIsNamed(LowerCaseVariableCatalog.CONDITION).isNotNull();
    Validator.assertThat(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseconds").isNotNegative();

    maxRunCount = null;
    this.condition = condition;
    this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
  }

  /**
   * Creates a new {@link AfterEveryMediator} with the given maxRunCount,
   * condition and time interval in milliseconds.
   * 
   * @param maxRunCount
   * @param condition
   * @param timeIntervalInMilliseconds
   * @throws NegativeArgumentException if the given max run count is negative.
   * @throws ArgumentIsNullException   if the given condition is null.
   * @throws NegativeArgumentException if the given timeIntervalInMillisecondss is
   *                                   negative.
   */
  AfterEveryMediator(
    final int maxRunCount,
    final BooleanSupplier condition,
    final int timeIntervalInMilliseconds) {

    Validator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();
    Validator.assertThat(condition).thatIsNamed("condition").isNotNull();
    Validator.assertThat(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseconds").isNotNegative();

    this.maxRunCount = maxRunCount;
    this.condition = condition;
    this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(final Runnable job) {

    //Handles the case that the current AfterEveryMediator does not have a max run count.
    if (!hasMaxRunCount()) {
      runWhenDoesNotHaveMaxRunCount(job);

      //Handles the case that the current AfterEveryMediator has a max run count.
    } else {
      runWhenHasMaxRunCount(job);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Future runInBackground(final Runnable job) {

    //Handles the case that the current AfterEveryMediator does not have a max count.
    if (!hasMaxRunCount()) {
      return runInBackgroundWhenDoesNotHaveMaxRunConunt(job);
    }

    //Handles the case that the current AfterEveryMediator has a max count.
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
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.CONDITION);
    }
  }

  /**
   * @return true if the current {@link AfterEveryMediator} has a condition.
   */
  private boolean hasCondition() {
    return (condition != null);
  }

  /**
   * @return true if the current {@link AfterEveryMediator} has a max run count.
   */
  private boolean hasMaxRunCount() {
    return (maxRunCount != null);
  }

  /**
   * Lets the current {@link AfterEveryMediator} run the given job in background
   * for the case when the current {@link AfterEveryMediator} does not have a max
   * run count.
   * 
   * @param job
   * @return a new {@link Future}.
   * @throws ArgumentIsNullException if the given job is null.
   */
  private Future runInBackgroundWhenDoesNotHaveMaxRunConunt(final Runnable job) {

    //Handles the case that the current AfterAllMediator does not have a condition.
    if (!hasCondition()) {
      return Future.forJobExecturor(new JobExecutor(job, timeIntervalInMilliseconds, () -> true));
    }

    //Handles the case that the current AfterAllMediator has a condition.
    return Future.forJobExecturor(new JobExecutor(job, condition, timeIntervalInMilliseconds));
  }

  /**
   * Lets the current {@link AfterEveryMediator} run the given job in background
   * for the case when the current {@link AfterEveryMediator} has a max run count.
   * 
   * @param job
   * @return a new {@link Future}.
   * @throws ArgumentIsNullException if the given job is null.
   */
  private Future runInBackgroundWhenHasMaxRunConunt(final Runnable job) {

    //Handles the case that the current AfterAllMediator does not have a condition.
    if (!hasCondition()) {
      return Future.forJobExecturor(new JobExecutor(job, maxRunCount, timeIntervalInMilliseconds));
    }

    //Handles the case that the current AfterAllMediator has a condition.
    return Future.forJobExecturor(new JobExecutor(job, maxRunCount, condition, timeIntervalInMilliseconds));
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

    Validator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    assertHasCondition();

    while (condition.getAsBoolean()) {
      job.run();
      Waiter.waitForMilliseconds(timeIntervalInMilliseconds);
    }
  }

  /**
   * Lets the current {@link AfterEveryMediator} run the given job for the case
   * when the current {@link AfterEveryMediator} has a max run count.
   * 
   * @param job
   */
  private void runWhenHasMaxRunCount(final Runnable job) {

    Validator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    //Handles the case that the current AfterAllMediator does not have a condition.
    if (!hasCondition()) {
      for (var i = 1; i <= maxRunCount; i++) {

        Waiter.waitForMilliseconds(timeIntervalInMilliseconds);

        job.run();
      }

      //Handles the case that the current AfterAllMediator has a condition.
    } else {
      for (var i = 1; i <= maxRunCount; i++) {

        Waiter.waitForMilliseconds(timeIntervalInMilliseconds);

        if (!condition.getAsBoolean()) {
          break;
        }

        job.run();
      }
    }
  }
}
