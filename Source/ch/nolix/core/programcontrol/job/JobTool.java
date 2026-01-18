/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.programcontrol.job;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.misc.variable.PluralLowerCaseVariableCatalog;
import ch.nolix.coreapi.programcontrol.job.IJobTool;

/**
 * @author Silvan Wyss
 */
public final class JobTool implements IJobTool {
  /**
   * {@inheritDoc}
   */
  @Override
  public Runnable createConcatenatedJobFromJobs(final IContainer<Runnable> jobs) {
    Validator.assertThat(jobs).thatIsNamed(PluralLowerCaseVariableCatalog.JOBS).isNotNull();

    return () -> JobToolHelper.runJobs(jobs);
  }
}
