//package declaration
package ch.nolix.techapi.mathapi.bigdecimalmathapi;

//Java imports
import java.math.BigDecimal;

//interface
public interface ISequence<V> {

  //method declaration
  int getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(BigDecimal limit, int maxIndex);

  //method declaration
  BigDecimal getSquaredMagnitudeOfValueAt1BasedIndex(int p1BasedIndex);

  //method declaration
  V getValueAt1BasedIndex(int p1BasedIndex);
}
