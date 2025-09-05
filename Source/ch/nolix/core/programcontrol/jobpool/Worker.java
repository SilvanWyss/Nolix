package ch.nolix.core.programcontrol.jobpool;

import ch.nolix.core.errorcontrol.validator.Validator;

final class Worker extends Thread {
  private final JobPool parentJobPool;

  public Worker(final JobPool parentJobPool) {
    Validator.assertThat(parentJobPool).thatIsNamed("parent job bool");

    this.parentJobPool = parentJobPool;

    start();
  }

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
