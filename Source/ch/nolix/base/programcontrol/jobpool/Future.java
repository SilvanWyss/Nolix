/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.jobpool;

import ch.nolix.base.programcontrol.future.AbstractFuture;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;

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
