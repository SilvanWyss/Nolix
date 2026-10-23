/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.jobpool;

import ch.nolix.base.programcontrol.basicflowcontroller.BasicFlowController;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

final class JobWrapper implements Runnable {
  private boolean finished;

  private boolean running;

  private final Runnable job;

  private Throwable error;

  private JobWrapper(final Runnable job) {
    Validator.assertThat(job).thatIsNamed(LowerCaseVariableNameCatalog.JOB).isNotNull();

    this.job = job;
  }

  /**
   * @param job
   * @return a new {@link JobWrapper} with the given job
   */
  public static JobWrapper withJob(final Runnable job) {
    return new JobWrapper(job);
  }

  /**
   * @return true if the current {@link JobWrapper} caught an error, false
   *         otherwise
   */
  public boolean caughtError() {
    return (error != null);
  }

  /**
   * @return the error of the current {@link JobWrapper}
   * @throws RuntimeException if the current {@link JobWrapper} did not catch an
   *                          error
   */
  public Throwable getError() {
    if (!caughtError()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.ERROR);
    }

    return error;
  }

  /**
   * @return true if the current {@link JobWrapper} is finished, false otherwise
   */
  public boolean isFinished() {
    return finished;
  }

  /**
   * @return true if the current {@link JobWrapper} is fresh, false otherwise
   */
  public boolean isFresh() {
    return (!isRunning() && !isFinished());
  }

  /**
   * @return true if the current {@link JobWrapper} is running, false otherwise
   */
  public boolean isRunning() {
    return running;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    assertIsFresh();

    running = true;

    try {
      job.run();
    } catch (final Throwable lError) { // NOSONAR: All errors must be caught.
      error = lError;
    } finally {
      running = false;
      finished = true;
    }
  }

  /**
   * Waits until the current {@link JobWrapper} is finished.
   */
  public void waitUntilIsFinished() {
    BasicFlowController.waitUntil(this::isFinished);
  }

  /**
   * Waits until the current {@link JobWrapper} is finished.
   * 
   * @param timeoutInMilliseconds
   * @throws RuntimeException if the current {@link JobWrapper} is not finished
   *                          within the given timeoutInMilliseconds
   */
  public void waitUntilIsFinished(final int timeoutInMilliseconds) {
    final var startTimeInMilliseconds = System.currentTimeMillis();

    BasicFlowController.waitAsLongAs(
      () -> System.currentTimeMillis() - startTimeInMilliseconds < timeoutInMilliseconds && !isFinished());

    if (!isFinished()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "reached timeout before having finished");
    }
  }

  private void assertIsFresh() {
    if (isRunning()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is already running");
    }

    if (isFinished()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is already finished");
    }
  }
}
