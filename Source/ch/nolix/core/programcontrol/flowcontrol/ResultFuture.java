package ch.nolix.core.programcontrol.flowcontrol;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.future.AbstractFuture;
import ch.nolix.coreapi.programcontrolapi.futureapi.IResultFuture;

/**
 * @author Silvan Wyss
 * @version 2017-09-29
 * @param <R> is the type of the result of a {@link ResultFuture}.
 */
public final class ResultFuture<R> extends AbstractFuture implements IResultFuture<R> {

  private final ResultJobRunner<R> resultJobRunner;

  /**
   * Creates a new {@link ResultFuture} with the given resultJobRunner.
   * 
   * @param resultJobRunner
   * @throws ArgumentIsNullException if the given resultJobRunner is null.
   */
  ResultFuture(final ResultJobRunner<R> resultJobRunner) {

    //Asserts that the given resultJobRunner is not null.
    GlobalValidator.assertThat(resultJobRunner).isOfType(ResultJobRunner.class);

    //Sets the resultJobRunner of the current ResultFuture.
    this.resultJobRunner = resultJobRunner;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean caughtError() {
    return resultJobRunner.caughtError();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Throwable getError() {
    return resultJobRunner.getError();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public R getResult() {
    return resultJobRunner.getResult();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isFinished() {
    return resultJobRunner.isFinished();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitUntilIsFinished() {
    GlobalFlowController.waitUntil(this::isFinished);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitUntilIsFinished(final int timeoutInMilliseconds) {

    final var startTimeInMilliseconds = System.currentTimeMillis();

    GlobalFlowController.waitAsLongAs(
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
