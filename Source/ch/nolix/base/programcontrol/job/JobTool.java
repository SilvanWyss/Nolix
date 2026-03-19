/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.job;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.misc.variable.PluralLowerCaseVariableCatalog;
import ch.nolix.baseapi.programcontrol.job.IJobTool;

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
