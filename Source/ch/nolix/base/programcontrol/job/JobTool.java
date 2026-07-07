/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.job;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PluralLowerCaseVariableNameCatalog;
import ch.nolix.baseapi.programcontrol.job.IJobTool;

/**
 * @author Silvan Wyss
 */
public final class JobTool implements IJobTool {
  /**
   * {@inheritDoc}
   */
  @Override
  public Runnable createConcatenatedJobFromJobs(final ExtendedIterable<Runnable> jobs) {
    Validator.assertThat(jobs).thatIsNamed(PluralLowerCaseVariableNameCatalog.JOBS).isNotNull();

    return () -> JobToolHelper.runJobs(jobs);
  }
}
