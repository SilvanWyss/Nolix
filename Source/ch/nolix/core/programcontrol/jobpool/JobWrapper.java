package ch.nolix.core.programcontrol.jobpool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.flowcontrol.FlowController;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

final class JobWrapper implements Runnable {

  private boolean finished;

  private boolean running;

  private final Runnable job;

  private Throwable error;

  public JobWrapper(final Runnable job) {

    Validator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    this.job = job;
  }

  public boolean caughtError() {
    return (error != null);
  }

  public Throwable getError() {

    if (!caughtError()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalog.ERROR);
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
    } catch (final Throwable lError) { //NOSONAR: All Throwables must be caught.
      error = lError;
    } finally {
      running = false;
      finished = true;
    }
  }

  public void waitUntilIsFinished() {
    FlowController.waitUntil(this::isFinished);
  }

  public void waitUntilIsFinished(final int timeoutInMilliseconds) {

    final var startTimeInMilliseconds = System.currentTimeMillis();

    FlowController.waitAsLongAs(
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
