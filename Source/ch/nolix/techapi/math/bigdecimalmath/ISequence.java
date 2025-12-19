package ch.nolix.techapi.math.bigdecimalmath;

import java.math.BigDecimal;

/**
 * @author Silvan Wyss
 */
public interface ISequence<V> {
  int getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(BigDecimal limit, int maxIndex);

  BigDecimal getSquaredMagnitudeOfValueAtOneBasedIndex(int oneBasedIndex);

  V getValueAtOneBasedIndex(int oneBasedIndex);
}
