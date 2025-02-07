package ch.nolix.core.programcontrol.future;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programcontrolapi.futureapi.IFuture;

/**
 * @author Silvan Wyss
 * @version 2022-06-18
 */
public abstract class AbstractFuture implements IFuture {

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isFinishedSuccessfully() {
    return (isFinished() && !caughtError());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isFinishedWithError() {
    return (isFinished() && caughtError());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void waitUntilIsFinishedSuccessfully() {

    waitUntilIsFinished();

    handleProbableError();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void waitUntilIsFinishedSuccessfully(final int timeoutInMilliseconds) {

    waitUntilIsFinished(timeoutInMilliseconds);

    handleProbableError();
  }

  private void handleError() {

    if (getError().getMessage() == null || getError().getMessage().isBlank()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "has caught a '" + getError().getClass().getName() + "'");
    }

    throw InvalidArgumentException.forArgumentAndErrorPredicate(
      this,
      "has caught the error '" + getError().getClass().getName() + ": " + getError().getMessage() + "'");
  }

  private void handleProbableError() {
    if (caughtError()) {
      handleError();
    }
  }
}
