package ch.nolix.core.programcontrol.flowcontrol;

import java.util.function.Supplier;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.logging.Logger;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2017-09-29
 * @param <R> is the type of the result of the resulltJob of a
 *            {@link ResultJobExecutor}.
 */
final class ResultJobExecutor<R> extends Thread {
  private final Supplier<R> resultJob;
  private R result;
  private boolean running = true;

  private Throwable error;

  /**
   * Creates a new {@link ResultJobExecutor} for the given resultJob. The
   * {@link ResultJobExecutor} will start automatically to execute the given
   * resultJob.
   * 
   * @param resultJob
   * @throws ArgumentIsNullException if the given resultJob is null.
   */
  private ResultJobExecutor(final Supplier<R> resultJob) {
    Validator.assertThat(resultJob).thatIsNamed("result job").isNotNull();

    this.resultJob = resultJob;

    start();
  }

  /**
   * @param resultJob
   * @param <R>       is the type of the result of the given resulltJob.
   * @return a new {@link ResultJobExecutor} for the given resultJob. The
   *         {@link ResultJobExecutor} will start automatically to execute the
   *         given resultJob.
   * @throws ArgumentIsNullException if the given resultJob is null.
   */
  public static <R> ResultJobExecutor<R> forResultJob(final Supplier<R> resultJob) {
    return new ResultJobExecutor<>(resultJob);
  }

  /**
   * @return true if the current {@link ResultJobExecutor} has caught an error.
   */
  public boolean caughtError() {
    return (error != null);
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * @return the error of the current {@link ResultJobExecutor}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link ResultJobExecutor} does
   *                                               not have an error.
   */
  public Throwable getError() {
    if (error == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.ERROR);
    }

    return error;
  }

  /**
   * @return the result of the current {@link ResultJobExecutor}
   * @throws InvalidArgumentException if the current {@link ResultJobExecutor} is
   *                                  not finished or has caught an error.
   */
  public R getResult() {
    if (!isFinished()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not finished");
    }

    if (caughtError()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "has caught an error");
    }

    return result;
  }

  /**
   * @return true if the current {@link ResultJobExecutor} is finished.
   */
  public boolean isFinished() {
    return !isRunning();
  }

  /**
   * @return true if the current {@link ResultJobExecutor} is finished
   *         successfully.
   */
  public boolean isFinsishedSuccessfully() {
    return //
    isFinished() &&
    !caughtError();
  }

  /**
   * @return true if the current {@link ResultJobExecutor} is running.
   */
  public boolean isRunning() {
    return running;
  }

  /**
   * Runs the current {@link ResultJobExecutor}.
   */
  @Override
  public void run() {
    try {
      result = resultJob.get();
    } catch (final Throwable paramError) { //NOSONAR: All Throwables must be caught.
      error = paramError;
      Logger.logError(paramError);
    } finally {
      running = false;
    }
  }
}
