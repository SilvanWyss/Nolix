package ch.nolix.core.programcontrol.sequencer;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.future.AbstractFuture;
import ch.nolix.coreapi.programcontrolapi.futureapi.IFuture;

/**
 * @author Silvan Wyss
 * @version 2017-05-23
 */
public final class Future extends AbstractFuture {

  private final JobRunner jobRunner;

  /**
   * Creates a new {@link Future} with the given jobRunner.
   * 
   * @param jobRunner
   * @throws ArgumentIsNullException if the given jobRunner is null.
   */
  Future(final JobRunner jobRunner) {

    //Asserts that the given jobRunner is not null.
    GlobalValidator.assertThat(jobRunner).isOfType(JobRunner.class);

    //Sets the jobRunner of the current Future.
    this.jobRunner = jobRunner;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean caughtError() {
    return jobRunner.caughtError();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Throwable getError() {
    return jobRunner.getError();
  }

  /**
   * @return the number of finished jobs of the current {@link IFuture}.
   */
  public int getFinishedJobCount() {
    return jobRunner.getFinishedJobCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isFinished() {
    return jobRunner.isFinished();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitUntilIsFinished() {
    GlobalSequencer.waitUntil(this::isFinished);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitUntilIsFinished(final int timeoutInMilliseconds) {

    final var startTimeInMilliseconds = System.currentTimeMillis();

    GlobalSequencer.asLongAs(
      () -> System.currentTimeMillis() - startTimeInMilliseconds < timeoutInMilliseconds
      && isRunning());

    if (!isFinished()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "reached timeout before having finished");
    }
  }
}
