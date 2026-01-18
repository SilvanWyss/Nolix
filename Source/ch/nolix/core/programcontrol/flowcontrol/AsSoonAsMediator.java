/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.programcontrol.flowcontrol;

import java.util.function.BooleanSupplier;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programcontrol.flowcontrol.IAsSoonAsMediator;
import ch.nolix.coreapi.programcontrol.future.IFuture;

/**
 * @author Silvan Wyss
 */
public final class AsSoonAsMediator implements IAsSoonAsMediator {
  private final BooleanSupplier condition;

  /**
   * Creates a new {@link AsSoonAsMediator} with the given condition.
   * 
   * @param condition
   * @throws ArgumentIsNullException if the given condition is null.
   */
  private AsSoonAsMediator(final BooleanSupplier condition) {
    Validator.assertThat(condition).thatIsNamed(LowerCaseVariableCatalog.CONDITION).isNotNull();

    this.condition = condition;
  }

  /**
   * @param condition
   * @return a new {@link AsSoonAsMediator} with the given condition.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static AsSoonAsMediator withCondition(final BooleanSupplier condition) {
    return new AsSoonAsMediator(condition);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IFuture runInBackground(final Runnable job) {
    Validator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    return Future.forJobExecturor(new JobExecutor(() -> runAsSoonAsConditionIsFulfilled(job), 1));
  }

  private void runAsSoonAsConditionIsFulfilled(final Runnable job) {
    Validator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    FlowController.waitUntil(condition);

    job.run();
  }
}
