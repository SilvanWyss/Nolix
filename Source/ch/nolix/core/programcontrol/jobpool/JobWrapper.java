package ch.nolix.core.programcontrol.jobpool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

final class JobWrapper implements Runnable {

  private boolean finished;

  private boolean running;

  private final Runnable job;

  private Throwable error;

  public JobWrapper(final Runnable job) {

    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseVariableCatalogue.JOB).isNotNull();

    this.job = job;
  }

  public boolean caughtError() {
    return (error != null);
  }

  public Throwable getError() {

    if (!caughtError()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.ERROR);
    }

    return error;
  }

  public boolean isFinished() {
    return finished;
  }

  public boolean isFresh() {
    return (!isRunning() && !isFinished());
  }

  public boolean isRunning() {
    return running;
  }

  @Override
  public void run() {

    assertIsFresh();

    running = true;

    try {
      job.run();
    } catch (final Throwable lError) { //NOSONAR: All Throwables must be caught here.
      error = lError;
    } finally {
      running = false;
      finished = true;
    }
  }

  public void waitUntilIsFinished() {
    GlobalSequencer.waitUntil(this::isFinished);
  }

  public void waitUntilIsFinished(final int timeoutInMilliseconds) {

    final var startTimeInMilliseconds = System.currentTimeMillis();

    GlobalSequencer.waitAsLongAs(
      () -> System.currentTimeMillis() - startTimeInMilliseconds < timeoutInMilliseconds && !isFinished());

    if (!isFinished()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "reached timeout before having finished");
    }
  }

  private void assertIsFresh() {

    if (isRunning()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is already running");
    }

    if (isFinished()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is already finished");
    }
  }
}
