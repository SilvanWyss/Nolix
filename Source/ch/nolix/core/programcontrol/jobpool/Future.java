package ch.nolix.core.programcontrol.jobpool;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.future.BaseFuture;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

final class Future extends BaseFuture {

  private final JobWrapper jobWrapper;

  public Future(final JobWrapper jobWrapper) {

    GlobalValidator.assertThat(jobWrapper).thatIsNamed(LowerCaseVariableCatalogue.JOB).isNotNull();

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
