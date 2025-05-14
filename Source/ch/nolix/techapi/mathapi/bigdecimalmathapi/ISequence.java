package ch.nolix.techapi.mathapi.bigdecimalmathapi;

import java.math.BigDecimal;

public interface ISequence<V> {

  int getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(BigDecimal limit, int maxIndex);

  BigDecimal getSquaredMagnitudeOfValueAtOneBasedIndex(int oneBasedIndex);

  V getValueAtOneBasedIndex(int oneBasedIndex);
}
