/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.flowcontrol;

import ch.nolix.base.programcontrol.future.AbstractFuture;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.programcontrol.future.IResultFuture;

/**
 * @author Silvan Wyss
 * @param <R> the type of the result of a {@link ResultFuture}.
 */
public final class ResultFuture<R> extends AbstractFuture implements IResultFuture<R> {
  private final ResultJobExecutor<R> resultJobExecutor;

  /**
   * Creates a new {@link ResultFuture} with the given resultJobExecutor.
   * 
   * @param resultJobExecutor
   * @throws RuntimeException if the given resultJobExecutor is null.
   */
  private ResultFuture(final ResultJobExecutor<R> resultJobExecutor) {
    Validator.assertThat(resultJobExecutor).isOfType(ResultJobExecutor.class);

    this.resultJobExecutor = resultJobExecutor;
  }

  /**
   * @param resultJobExecutor
   * @param <T>               is the type of the result of created
   *                          {@link ResultFuture}
   * @return a new {@link ResultFuture} with the given resultJobExecutor
   * @throws RuntimeException if the given resultJobExecutor is null.
   */
  public static <T> ResultFuture<T> forResultJobExecutor(final ResultJobExecutor<T> resultJobExecutor) {
    return new ResultFuture<>(resultJobExecutor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean caughtError() {
    return resultJobExecutor.caughtError();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Throwable getError() {
    return resultJobExecutor.getError();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public R getResult() {
    return resultJobExecutor.getResult();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isFinished() {
    return resultJobExecutor.isFinished();
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

    FlowController.waitAsLongAs(
      () -> System.currentTimeMillis() - startTimeInMilliseconds < timeoutInMilliseconds
      && isRunning());

    if (!isFinished()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "reached timeout before having finished");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public R waitUntilIsFinishedAndGetResult() {
    waitUntilIsFinished();

    return getResult();
  }
}
