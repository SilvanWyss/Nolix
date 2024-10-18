package ch.nolix.core.programcontrol.sequencer;

import java.util.function.Supplier;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.logging.GlobalLogger;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

/**
 * @author Silvan Wyss
 * @version 2017-09-29
 * @param <R> is the type of the result of the resulltJob of a
 *            {@link ResultJobRunner}.
 */
final class ResultJobRunner<R> extends Thread {

  private final Supplier<R> resultJob;
  private R result;
  private boolean running = true;

  private Throwable error;

  /**
   * Creates a {@link ResultJobRunner} with the given resultJob. The
   * {@link ResultJobRunner} will start automatically.
   * 
   * @param resultJob
   * @throws ArgumentIsNullException if the given resultJob is null.
   */
  public ResultJobRunner(final Supplier<R> resultJob) {

    //Asserts that the given resultJob is not null.
    GlobalValidator.assertThat(resultJob).thatIsNamed("result job").isNotNull();

    //Sets the resultJob of the current ResultJobRunner.
    this.resultJob = resultJob;

    //Starts the current ResultJobRunner.
    start();
  }

  /**
   * @return true if the current {@link ResultJobRunner} has caught an error.
   */
  public boolean caughtError() {
    return (error != null);
  }

  //For a better performance, this implementation does not use all comfortable methods.
  /**
   * @return the error of the current {@link ResultJobRunner}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link ResultJobRunner} does
   *                                               not have an error.
   */
  public Throwable getError() {

    //Asserts that the current ResultJobRunner has an error.
    if (error == null) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.ERROR);
    }

    return error;
  }

  /**
   * @return the result of the current {@link ResultJobRunner}
   * @throws InvalidArgumentException if the current {@link ResultJobRunner} is
   *                                  not finished or has caught an error.
   */
  public R getResult() {

    //Asserts that the current ResultJobRunner is finished.
    if (!isFinished()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not finished");
    }

    //Asserts that the current ResultJobRunner has not caught an error.
    if (caughtError()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "has caught an error");
    }

    return result;
  }

  /**
   * @return true if the current {@link ResultJobRunner} is finished.
   */
  public boolean isFinished() {
    return !isRunning();
  }

  /**
   * @return true if the current {@link ResultJobRunner} is finished successfully.
   */
  public boolean isFinsishedSuccessfully() {
    return (isFinished() && !caughtError());
  }

  /**
   * @return true if the current {@link ResultJobRunner} is running.
   */
  public boolean isRunning() {
    return running;
  }

  /**
   * Runs the current {@link ResultJobRunner}.
   */
  @Override
  public void run() {
    try {
      result = resultJob.get();
    } catch (final Throwable paramError) { //NOSONAR: Each Throwable must be caught here.
      error = paramError;
      GlobalLogger.logError(paramError);
    } finally {
      running = false;
    }
  }
}
