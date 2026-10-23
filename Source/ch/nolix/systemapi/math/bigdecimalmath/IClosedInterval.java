/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.math.bigdecimalmath;

import java.math.BigDecimal;

import ch.nolix.baseapi.datastructure.pair.IPair;

/**
 * @author Silvan Wyss
 */
public interface IClosedInterval {
  /**
   * @param value
   * @return true if the current {@link IClosedInterval} contains the given value,
   *         false otherwise
   */
  boolean containsValue(BigDecimal value);

  /**
   * @return the number of decimal places of the current {@link IClosedInterval}
   */
  int getDecimalPlaceCount();

  /**
   * @return the lower half and the upper half of the current
   *         {@link IClosedInterval}
   */
  IPair<IClosedInterval, IClosedInterval> getHalfs();

  /**
   * @return the length of the current {@link IClosedInterval}
   */
  BigDecimal getLength();

  /**
   * @return the max of the current {@link IClosedInterval}
   */
  BigDecimal getMax();

  /**
   * @return the mid point of the current {@link IClosedInterval}
   */
  BigDecimal getMidPoint();

  /**
   * @return the min of the current {@link IClosedInterval}
   */
  BigDecimal getMin();

  /**
   * @param closedInterval
   * @return true if the current {@link IClosedInterval} intersects with the given
   *         closedInterval, false otherwise
   */
  boolean intersectsWithClosedInterval(IClosedInterval closedInterval);

  /**
   * @param decimalPlaces
   * @return a new {@link IClosedInterval} with the min and max of the current
   *         {@link IClosedInterval} and the given decimalPlaces
   * @throws RuntimeException if the given decimalPlaces is not positive
   */
  IClosedInterval withDecimalPlaceCount(int decimalPlaces);
}
