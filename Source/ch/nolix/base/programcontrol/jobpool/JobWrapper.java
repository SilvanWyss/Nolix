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

  public static JobWrapper withJob(final Runnable job) {
    return new JobWrapper(job);
  }

  public boolean caughtError() {
    return (error != null);
  }

  public Throwable getError() {
    if (!caughtError()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.ERROR);
    }

    return error;
  }

  public boolean isFinished() {
    return finished;
  }

  public boolean isFresh() {
    return (!isRunning() && !isFinished());
  }

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
    } catch (final Throwable lError) { // NOSONAR: All Throwables must be caught.
      error = lError;
    } finally {
      running = false;
      finished = true;
    }
  }

  public void waitUntilIsFinished() {
    BasicFlowController.waitUntil(this::isFinished);
  }

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
