package ch.nolix.core.programcontrol.flowcontrol;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.future.AbstractFuture;
import ch.nolix.coreapi.programcontrol.future.IFuture;

/**
 * @author Silvan Wyss
 * @version 2017-05-23
 */
public final class Future extends AbstractFuture {

  private final JobRunner jobExecutor;

  /**
   * Creates a new {@link Future} with the given jobExecutor.
   * 
   * @param jobExecutor
   * @throws ArgumentIsNullException if the given jobExecutor is null.
   */
  private Future(final JobRunner jobExecutor) {

    Validator.assertThat(jobExecutor).isNotNull();

    this.jobExecutor = jobExecutor;
  }

  /**
   * @param jobExecutor
   * @return a new {@link Future} with the given jobExecutor.
   * @throws ArgumentIsNullException if the given jobExecutor is null.
   */
  public static Future forJobExecturor(final JobRunner jobExecutor) {
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
    return jobExecutor.getFinishedJobCount();
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
