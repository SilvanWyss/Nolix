package ch.nolix.core.programcontrol.flowcontrol;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.future.AbstractFuture;
import ch.nolix.coreapi.programcontrol.future.IResultFuture;

/**
 * @author Silvan Wyss
 * @param <R> is the type of the result of a {@link ResultFuture}.
 */
public final class ResultFuture<R> extends AbstractFuture implements IResultFuture<R> {
  private final ResultJobExecutor<R> resultJobExecutor;

  /**
   * Creates a new {@link ResultFuture} with the given resultJobRunner.
   * 
   * @param resultJobRunner
   * @throws ArgumentIsNullException if the given resultJobRunner is null.
   */
  ResultFuture(final ResultJobExecutor<R> resultJobRunner) {
    //Asserts that the given resultJobRunner is not null.
    Validator.assertThat(resultJobRunner).isOfType(ResultJobExecutor.class);

    //Sets the resultJobRunner of the current ResultFuture.
    this.resultJobExecutor = resultJobRunner;
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
