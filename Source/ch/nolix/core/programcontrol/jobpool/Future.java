package ch.nolix.core.programcontrol.jobpool;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.future.AbstractFuture;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

final class Future extends AbstractFuture {
  private final JobWrapper jobWrapper;

  public Future(final JobWrapper jobWrapper) {
    Validator.assertThat(jobWrapper).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    this.jobWrapper = jobWrapper;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean caughtError() {
    return jobWrapper.caughtError();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Throwable getError() {
    return jobWrapper.getError();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isFinished() {
    return jobWrapper.isFinished();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitUntilIsFinished() {
    jobWrapper.waitUntilIsFinished();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitUntilIsFinished(final int timeoutInMilliseconds) {
    jobWrapper.waitUntilIsFinished(timeoutInMilliseconds);
  }
}
