package ch.nolix.core.programcontrol.flowcontrol;

import java.util.function.BooleanSupplier;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programcontrolapi.flowcontrolapi.IAsSoonAsMediator;
import ch.nolix.coreapi.programcontrolapi.futureapi.IFuture;

public final class AsSoonAsMediator implements IAsSoonAsMediator {

  private final BooleanSupplier condition;

  /**
   * Creates a new {@link AsSoonAsMediator} with the given condition.
   * 
   * @param condition
   * @throws ArgumentIsNullException if the given condition is null.
   */
  private AsSoonAsMediator(final BooleanSupplier condition) {

    GlobalValidator.assertThat(condition).thatIsNamed(LowerCaseVariableCatalog.CONDITION).isNotNull();

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

    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    return new Future(new JobRunner(() -> runAsSoonAsConditionIsFulfilled(job), 1));
  }

  private void runAsSoonAsConditionIsFulfilled(final Runnable job) {

    GlobalValidator.assertThat(job).thatIsNamed(LowerCaseVariableCatalog.JOB).isNotNull();

    GlobalFlowController.waitUntil(condition);

    job.run();
  }
}
