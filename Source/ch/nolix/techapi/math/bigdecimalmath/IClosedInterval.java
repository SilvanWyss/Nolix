/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.techapi.math.bigdecimalmath;

import java.math.BigDecimal;

import ch.nolix.baseapi.datastructure.pair.IPair;

/**
 * @author Silvan Wyss
 */
public interface IClosedInterval {
  boolean containsValue(BigDecimal value);

  int getDecimalPlaces();

  IPair<IClosedInterval, IClosedInterval> getHalfs();

  BigDecimal getLength();

  BigDecimal getMax();

  BigDecimal getMidPoint();

  BigDecimal getMin();

  IClosedInterval inDecimalPlaces(int decimalPlaces);

  boolean intersectsWith(IClosedInterval closedInterval);
}
