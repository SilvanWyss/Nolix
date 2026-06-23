/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.flowcontrol;

import java.util.function.BooleanSupplier;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.misc.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.programcontrol.flowcontrol.IAsSoonAsMediator;
import ch.nolix.baseapi.programcontrol.future.IFuture;

/**
 * @author Silvan Wyss
 */
public final class AsSoonAsMediator implements IAsSoonAsMediator {
  private final BooleanSupplier condition;

  /**
   * Creates a new {@link AsSoonAsMediator} with the given condition.
   * 
   * @param condition
   * @throws RuntimeException if the given condition is null.
   */
  private AsSoonAsMediator(final BooleanSupplier condition) {
    Validator.assertThat(condition).thatIsNamed(LowerCaseVariableNameCatalog.CONDITION).isNotNull();

    this.condition = condition;
  }

  /**
   * @param condition
   * @return a new {@link AsSoonAsMediator} with the given condition.
   * @throws RuntimeException if the given condition is null.
   */
  public static AsSoonAsMediator withCondition(final BooleanSupplier condition) {
    return new AsSoonAsMediator(condition);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IFuture runInBackground(final Runnable job) {
    Validator.assertThat(job).thatIsNamed(LowerCaseVariableNameCatalog.JOB).isNotNull();

    final Runnable step = () -> runAsSoonAsConditionIsFulfilled(job);
    final var jobExecutor = JobExecutor.forStep(step);

    jobExecutor.start();

    return Future.forJobExecutor(jobExecutor);
  }

  private void runAsSoonAsConditionIsFulfilled(final Runnable job) {
    Validator.assertThat(job).thatIsNamed(LowerCaseVariableNameCatalog.JOB).isNotNull();

    FlowController.waitUntil(condition);

    job.run();
  }
}
