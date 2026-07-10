/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.flowcontrol;

import java.util.function.BooleanSupplier;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.unitconversioncatalog.TimeUnitConversionCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.programcontrol.flowcontrol.IAfterEveryMediator;
import ch.nolix.baseapi.programcontrol.flowcontrol.IAsLongAsMediator;
import ch.nolix.baseapi.programcontrol.future.IFuture;

/**
 * A {@link AsLongAsMediator} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class AsLongAsMediator implements IAsLongAsMediator {
  private final BooleanSupplier condition;

  /**
   * Creates a new {@link AsLongAsMediator} with the given condition.
   * 
   * @param condition
   * @throws RuntimeException if the given condition is null.
   */
  private AsLongAsMediator(final BooleanSupplier condition) {
    Validator.assertThat(condition).thatIsNamed(LowerCaseVariableNameCatalog.CONDITION).isNotNull();

    this.condition = condition;
  }

  /**
   * @param condition
   * @return a new {@link AsLongAsMediator} with the given condition.
   * @throws RuntimeException if the given condition is null.
   */
  public static AsLongAsMediator withCondition(final BooleanSupplier condition) {
    return new AsLongAsMediator(condition);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IAfterEveryMediator afterEveryMilliseconds(final int timeIntervalInMilliseconds) {
    return AfterEveryMediator.withConditionAndTimeIntervalInMilliSeconds(condition, timeIntervalInMilliseconds);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IAfterEveryMediator afterEverySecond() {
    return afterEveryMilliseconds(TimeUnitConversionCatalog.MILLISECONDS_PER_SECOND);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run(final Runnable job) {
    while (condition.getAsBoolean()) {
      job.run();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IFuture runInBackground(final Runnable step) {
    final var jobExecutor = JobExecutor.forStepAndNextStepRunCondition(step, condition);

    jobExecutor.start();

    return Future.forJobExecutor(jobExecutor);
  }
}
