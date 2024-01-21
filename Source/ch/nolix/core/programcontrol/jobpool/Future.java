//package declaration
package ch.nolix.core.programcontrol.jobpool;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.future.BaseFuture;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
final class Future extends BaseFuture {

  //attribute
  private final JobWrapper jobWrapper;

  //constructor
  public Future(final JobWrapper jobWrapper) {

    GlobalValidator.assertThat(jobWrapper).thatIsNamed(LowerCaseVariableCatalogue.JOB).isNotNull();

    this.jobWrapper = jobWrapper;
  }

  //method
  @Override
  public boolean caughtError() {
    return jobWrapper.caughtError();
  }

  //method
  @Override
  public Throwable getError() {
    return jobWrapper.getError();
  }

  //method
  @Override
  public boolean isFinished() {
    return jobWrapper.isFinished();
  }

  //method
  @Override
  public void waitUntilIsFinished() {
    jobWrapper.waitUntilIsFinished();
  }

  //method
  @Override
  public void waitUntilIsFinished(final int timeoutInMilliseconds) {
    jobWrapper.waitUntilIsFinished(timeoutInMilliseconds);
  }
}
