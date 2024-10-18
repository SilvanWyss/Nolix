package ch.nolix.techapi.mathapi.bigdecimalmathapi;

import java.math.BigDecimal;

public interface ISequence<V> {

  int getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(BigDecimal limit, int maxIndex);

  BigDecimal getSquaredMagnitudeOfValueAt1BasedIndex(int param1BasedIndex);

  V getValueAt1BasedIndex(int param1BasedIndex);
}
