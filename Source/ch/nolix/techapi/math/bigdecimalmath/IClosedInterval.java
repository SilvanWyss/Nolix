package ch.nolix.techapi.math.bigdecimalmath;

import java.math.BigDecimal;

import ch.nolix.coreapi.datastructure.pair.IPair;

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
