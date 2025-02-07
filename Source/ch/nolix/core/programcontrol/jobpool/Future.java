package ch.nolix.core.programcontrol.jobpool;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.future.AbstractFuture;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

final class Future extends AbstractFuture {

  private final JobWrapper jobWrapper;

  public Future(final JobWrapper jobWrapper) {

    GlobalValidator.assertThat(jobWrapper).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    this.jobWrapper = jobWrapper;
  }

  @Override
  public boolean caughtError() {
    return jobWrapper.caughtError();
  }

  @Override
  public Throwable getError() {
    return jobWrapper.getError();
  }

  @Override
  public boolean isFinished() {
    return jobWrapper.isFinished();
  }

  @Override
  public void waitUntilIsFinished() {
    jobWrapper.waitUntilIsFinished();
  }

  @Override
  public void waitUntilIsFinished(final int timeoutInMilliseconds) {
    jobWrapper.waitUntilIsFinished(timeoutInMilliseconds);
  }
}
