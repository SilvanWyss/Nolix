//package declaration
package ch.nolix.core.programcontrol.future;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programcontrolapi.futureapi.IFuture;

//class
/**
 * @author Silvan Wyss
 * @date 2022-06-18
 */
public abstract class BaseFuture implements IFuture {

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isFinishedSuccessfully() {
    return (isFinished() && !caughtError());
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isFinishedWithError() {
    return (isFinished() && caughtError());
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final void waitUntilIsFinishedSuccessfully() {

    waitUntilIsFinished();

    handleProbableError();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final void waitUntilIsFinishedSuccessfully(final int timeoutInMilliseconds) {

    waitUntilIsFinished(timeoutInMilliseconds);

    handleProbableError();
  }

  //method
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

  //method
  private void handleProbableError() {
    if (caughtError()) {
      handleError();
    }
  }
}
