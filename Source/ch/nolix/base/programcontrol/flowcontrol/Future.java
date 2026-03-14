/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.flowcontrol;

import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.base.programcontrol.future.AbstractFuture;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.programcontrol.future.IFuture;

/**
 * @author Silvan Wyss
 */
public final class Future extends AbstractFuture {
  private final JobExecutor jobExecutor;

  /**
   * Creates a new {@link Future} with the given jobExecutor.
   * 
   * @param jobExecutor
   * @throws RuntimeException if the given jobExecutor is null.
   */
  private Future(final JobExecutor jobExecutor) {
    Validator.assertThat(jobExecutor).isNotNull();

    this.jobExecutor = jobExecutor;
  }

  /**
   * @param jobExecutor
   * @return a new {@link Future} with the given jobExecutor.
   * @throws RuntimeException if the given jobExecutor is null.
   */
  public static Future forJobExecutor(final JobExecutor jobExecutor) {
    return new Future(jobExecutor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean caughtError() {
    return jobExecutor.caughtError();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Throwable getError() {
    return jobExecutor.getError();
  }

  /**
   * @return the number of finished jobs of the current {@link IFuture}.
   */
  public int getFinishedJobCount() {
    return jobExecutor.getFinishedStepRunCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isFinished() {
    return jobExecutor.isFinished();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitUntilIsFinished() {
    FlowController.waitUntil(this::isFinished);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitUntilIsFinished(final int timeoutInMilliseconds) {
    final var startTimeInMilliseconds = System.currentTimeMillis();

    FlowController.asLongAs(
      () -> //
      System.currentTimeMillis() - startTimeInMilliseconds < timeoutInMilliseconds
      && isRunning());

    if (!isFinished()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "reached timeout before having finished");
    }
  }
}
