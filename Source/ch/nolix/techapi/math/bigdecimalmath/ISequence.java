/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.techapi.math.bigdecimalmath;

import java.math.BigDecimal;

/**
 * @author Silvan Wyss
 * @param <V> is the type of the values of a {@link ISequence}.
 */
public interface ISequence<V> {
  int getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(BigDecimal limit, int maxIndex);

  BigDecimal getSquaredMagnitudeOfValueAtOneBasedIndex(int oneBasedIndex);

  V getValueAtOneBasedIndex(int oneBasedIndex);
}
