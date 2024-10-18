package ch.nolix.techapi.mathapi.bigdecimalmathapi;

import java.math.BigDecimal;

import ch.nolix.coreapi.containerapi.pairapi.IPair;

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
