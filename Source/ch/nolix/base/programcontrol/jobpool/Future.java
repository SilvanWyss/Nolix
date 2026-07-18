/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.jobpool;

import ch.nolix.base.programcontrol.future.AbstractFuture;
import ch.nolix.base.validation.validator.Validator;

public final class Future extends AbstractFuture {
  private final JobWrapper jobWrapper;

  /**
   * Creates a new {@link Future} for the given jobWrapper.
   * 
   * @param jobWrapper
   * @throws RuntimeException if the given jobWrapper is null
   */
  private Future(final JobWrapper jobWrapper) {
    Validator.assertThat(jobWrapper).thatIsNamed(JobWrapper.class).isNotNull();

    this.jobWrapper = jobWrapper;
  }

  /**
   * @param jobWrapper
   * @return a new {@link Future} for the given jobWrapper
   * @throws RuntimeException if the given jobWrapper is null
   */
  public static Future forJobWrapper(final JobWrapper jobWrapper) {
    return new Future(jobWrapper);
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
