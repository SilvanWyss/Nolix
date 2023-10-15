//package declaration
package ch.nolix.techapi.mathapi.bigdecimalmathapi;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.coreapi.containerapi.pairapi.IPair;

//interface
public interface IClosedInterval {

  // method declaration
  boolean containsValue(BigDecimal value);

  // method declaration
  int getBigDecimalScale();

  // method declaration
  IPair<IClosedInterval, IClosedInterval> getHalfs();

  // method declaration
  BigDecimal getLength();

  // method declaration
  BigDecimal getMax();

  // method declaration
  BigDecimal getMidPoint();

  // method declaration
  BigDecimal getMin();

  // method declaration
  IClosedInterval inBigDecimalScale(int bigDecimalScale);

  // method declaration
  boolean intersectsWith(IClosedInterval closedInterval);
}
