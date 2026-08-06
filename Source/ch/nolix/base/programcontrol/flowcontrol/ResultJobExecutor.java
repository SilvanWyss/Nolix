/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.flowcontrol;

import java.util.function.Supplier;

import ch.nolix.base.errorcontrol.logging.Logger;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <R> the type of the result of the resulltJob of a
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
   * @throws RuntimeException if the given resultJob is null
   */
  private ResultJobExecutor(final Supplier<R> resultJob) {
    Validator.assertThat(resultJob).thatIsNamed("result job").isNotNull();

    this.resultJob = resultJob;

    start();
  }

  /**
   * @param resultJob
   * @param <R>       the type of the result of the given resulltJob
   * @return a new {@link ResultJobExecutor} for the given resultJob. The
   *         {@link ResultJobExecutor} will start automatically to execute the
   *         given resultJob
   * @throws RuntimeException if the given resultJob is null
   */
  public static <R> ResultJobExecutor<R> forResultJob(final Supplier<R> resultJob) {
    return new ResultJobExecutor<>(resultJob);
  }

  /**
   * @return true if the current {@link ResultJobExecutor} has caught an error,
   *         false otherwise
   */
  public boolean caughtError() {
    return (error != null);
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * @return the error of the current {@link ResultJobExecutor}
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link ResultJobExecutor} does
   *                                               not have an error.
   */
  public Throwable getError() {
    if (error == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.ERROR);
    }

    return error;
  }

  /**
   * @return the result of the current {@link ResultJobExecutor}
   * @throws RuntimeException if the current {@link ResultJobExecutor} is not
   *                          finished or has caught an error.
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
   * @return true if the current {@link ResultJobExecutor} is finished, false
   *         otherwise
   */
  public boolean isFinished() {
    return !isRunning();
  }

  /**
   * @return true if the current {@link ResultJobExecutor} is finished
   *         successfully, false otherwise
   */
  public boolean isFinsishedSuccessfully() {
    return //
    isFinished() &&
    !caughtError();
  }

  /**
   * @return true if the current {@link ResultJobExecutor} is running, false
   *         otherwise
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
    } catch (final Throwable paramError) { // NOSONAR: All errors must be caught.
      error = paramError;
      Logger.logError(paramError);
    } finally {
      running = false;
    }
  }
}
