package ch.nolix.core.programcontrol.flowcontrol;

import java.util.function.BooleanSupplier;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.timeunitapi.TimeUnitConversionCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programcontrolapi.flowcontrolapi.IAfterEveryMediator;
import ch.nolix.coreapi.programcontrolapi.flowcontrolapi.IAsLongAsMediator;
import ch.nolix.coreapi.programcontrolapi.futureapi.IFuture;

/**
 * A {@link AsLongAsMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2017-06-01
 */
public final class AsLongAsMediator implements IAsLongAsMediator {

  private final BooleanSupplier condition;

  /**
   * Creates a new {@link AsLongAsMediator} with the given condition.
   * 
   * @param condition
   * @throws ArgumentIsNullException if the given condition is null.
   */
  private AsLongAsMediator(final BooleanSupplier condition) {

    GlobalValidator.assertThat(condition).thatIsNamed(LowerCaseVariableCatalog.CONDITION).isNotNull();

    this.condition = condition;
  }

  /**
   * @param condition
   * @return a new {@link AsLongAsMediator} with the given condition.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public static AsLongAsMediator withCondition(final BooleanSupplier condition) {
    return new AsLongAsMediator(condition);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IAfterEveryMediator afterEveryMilliseconds(final int timeIntervalInMilliseconds) {
    return new AfterEveryMediator(condition, timeIntervalInMilliseconds);
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
  public IFuture runInBackground(final Runnable job) {
    return new Future(new JobRunner(job, condition));
  }
}
