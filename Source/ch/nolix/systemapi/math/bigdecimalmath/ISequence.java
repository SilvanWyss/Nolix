/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.math.bigdecimalmath;

import java.math.BigDecimal;

/**
 * @author Silvan Wyss
 * @param <V> the type of the values of a {@link ISequence}.
 */
public interface ISequence<V> {
  /**
   * @param limit
   * @param maxIndex
   * @return the first one-based index, up to the given maxIndex, where the
   *         squared magnitude of the value of the current {@link ISequence}
   *         exceeds the given limit, -1 if there is no such index
   */
  int getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(BigDecimal limit, int maxIndex);

  /**
   * @param oneBasedIndex
   * @return the squared magnitude of the value at the given oneBasedIndex of the
   *         current {@link ISequence}
   * @throws RuntimeException if the given oneBasedIndex is not positive
   */
  BigDecimal getSquaredMagnitudeOfValueAtOneBasedIndex(int oneBasedIndex);

  /**
   * @param oneBasedIndex
   * @return the value at the given oneBasedIndex of the current {@link ISequence}
   * @throws RuntimeException if the given oneBasedIndex is not positive
   */
  V getValueAtOneBasedIndex(int oneBasedIndex);
}
