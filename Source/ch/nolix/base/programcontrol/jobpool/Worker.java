/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.jobpool;

import ch.nolix.base.validation.validator.Validator;

final class Worker extends Thread {
  private final JobPool parentJobPool;

  private Worker(final JobPool parentJobPool) {
    Validator.assertThat(parentJobPool).thatIsNamed("parent job bool");

    this.parentJobPool = parentJobPool;

    start();
  }

  public static Worker forJobPool(final JobPool jobPool) {
    return new Worker(jobPool);
  }

  /**
   * {@inheritDoc}
   */
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
