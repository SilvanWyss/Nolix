//package declaration
package ch.nolix.core.programcontrol.jobpool;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
final class Worker extends Thread {

  //attribute
  private final JobPool parentJobPool;

  //constructor
  public Worker(final JobPool parentJobPool) {

    GlobalValidator.assertThat(parentJobPool).thatIsNamed("parent job bool");

    this.parentJobPool = parentJobPool;

    start();
  }

  //method
  @Override
  public void run() {

    while (true) {

      final var jobWrapperContainer = parentJobPool.removeAndGetOptionalRefNextFreshJobWrapper();

      if (jobWrapperContainer.isEmpty()) {
        break;
      }

      final var jobWrapper = jobWrapperContainer.get();
      jobWrapper.run();
    }

    parentJobPool.removeWorker(this);
  }
}
