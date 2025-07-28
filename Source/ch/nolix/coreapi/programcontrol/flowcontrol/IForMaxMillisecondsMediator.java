package ch.nolix.coreapi.programcontrol.flowcontrol;

import java.util.function.BooleanSupplier;

/**
 * @author Silvan Wyss
 * @version 2025-07-28
 */
public interface IForMaxMillisecondsMediator {

  /**
   * @param condition
   * @return a new {@link IAsLongAsMediator} for the maxDurationInMilliseconds of
   *         the current {@link IForMaxMillisecondsMediator} and for the given
   *         condition.
   * @throws RuntimeException if the given condition is null.
   */
  IAsLongAsMediator asLongAs(BooleanSupplier condition);

  /**
   * @param condition
   * @return a new {@link IAsLongAsMediator} for the maxDurationInMilliseconds of
   *         the current {@link IForMaxMillisecondsMediator} and for the given
   *         condition.
   * @throws RuntimeException if the given condition is null.
   */
  IAsLongAsMediator until(BooleanSupplier condition);

  /**
   * Waits until the maxDurationInMilliseconds of the current
   * {@link IForMaxMillisecondsMediator} is reached or the given condition is
   * unfulfilled.
   * 
   * @param condition
   * @throws RuntimeException if the given condition is null.
   */
  void waitAsLongAs(BooleanSupplier condition);

  /**
   * Waits until the maxDurationInMilliseconds of the current
   * {@link IForMaxMillisecondsMediator} is reached or the given condition is
   * fulfilled.
   * 
   * @param condition
   * @throws RuntimeException if the given condition is null.
   */
  void waitUntil(BooleanSupplier condition);
}
