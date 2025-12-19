package ch.nolix.core.programcontrol.flowcontrol;

import java.util.function.BooleanSupplier;

import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.time.TimeUnitConversionCatalog;
import ch.nolix.coreapi.programcontrol.flowcontrol.IForMaxMillisecondsMediator;

/**
 * A {@link ForMaxMillisecondsMediator} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class ForMaxMillisecondsMediator implements IForMaxMillisecondsMediator {
  private final int maxDurationInMilliseconds;

  /**
   * Creates a new {@link ForMaxMillisecondsMediator} for the given
   * maxDurationInMilliseconds.
   * 
   * @param maxDurationInMilliseconds
   * @throws NegativeArgumentException if the given maxDurationInMilliseconds is
   *                                   negative.
   */
  private ForMaxMillisecondsMediator(final int maxDurationInMilliseconds) {
    //Asserts that the given maxDurationInMilliseconds is not negative.
    Validator.assertThat(maxDurationInMilliseconds).thatIsNamed("max duration in milliseconds").isNotNegative();

    //Sets the maxDurationInMilliseconds of the current ForMaxMillisecondsMediator.
    this.maxDurationInMilliseconds = maxDurationInMilliseconds;
  }

  /**
   * @param maxDurationInMilliseconds
   * @return a new {@link ForMaxMillisecondsMediator} for the given
   *         maxDurationInMilliseconds.
   * @throws NegativeArgumentException if the given maxDurationInMilliseconds is
   *                                   negative.
   */
  static ForMaxMillisecondsMediator forMaxMilliseconds(final int maxDurationInMilliseconds) {
    //Creates and returns a new ForMaxMillisecondsMediator.
    return new ForMaxMillisecondsMediator(maxDurationInMilliseconds);
  }

  /**
   * @param maxDurationInSeconds
   * @return a new {@link ForMaxMillisecondsMediator} for the given
   *         maxDurationInSeconds.
   * @throws NegativeArgumentException if the given maxDurationInSeconds is
   *                                   negative.
   */
  static ForMaxMillisecondsMediator forMaxSeconds(final int maxDurationInSeconds) {
    //Asserts that the given maxDurationInSeconds is not negative.
    Validator.assertThat(maxDurationInSeconds).thatIsNamed("max duration in seconds").isNotNegative();

    //Creates and returns a new ForMaxMillisecondsMediator.
    return new ForMaxMillisecondsMediator(maxDurationInSeconds * TimeUnitConversionCatalog.MILLISECONDS_PER_SECOND);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public AsLongAsMediator asLongAs(final BooleanSupplier condition) {
    //Asserts that the given condition is not null.
    Validator.assertThat(condition).thatIsNamed("condition").isNotNull();

    final var startTimeInMilliseconds = System.currentTimeMillis();
    final var endTimeInMilliseconds = startTimeInMilliseconds + maxDurationInMilliseconds;

    return //
    AsLongAsMediator.withCondition(
      () -> System.currentTimeMillis() < endTimeInMilliseconds || condition.getAsBoolean());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public AsLongAsMediator until(final BooleanSupplier condition) {
    //Calls other method.
    return asLongAs(() -> !condition.getAsBoolean());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitAsLongAs(final BooleanSupplier condition) {
    final var startTimeInMilliseconds = System.currentTimeMillis();
    final var endTimeInMilliseconds = startTimeInMilliseconds + maxDurationInMilliseconds;

    FlowController.waitAsLongAs(
      () -> System.currentTimeMillis() < endTimeInMilliseconds && condition.getAsBoolean());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void waitUntil(final BooleanSupplier condition) {
    //Calls other method.
    waitAsLongAs(() -> !condition.getAsBoolean());
  }
}
