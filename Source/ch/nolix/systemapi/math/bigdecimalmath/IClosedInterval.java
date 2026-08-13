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
  boolean containsValue(BigDecimal value);

  int getDecimalPlaceCount();

  IPair<IClosedInterval, IClosedInterval> getHalfs();

  BigDecimal getLength();

  BigDecimal getMax();

  BigDecimal getMidPoint();

  BigDecimal getMin();

  boolean intersectsWithClosedInterval(IClosedInterval closedInterval);

  IClosedInterval withDecimalPlaceCount(int decimalPlaces);
}
