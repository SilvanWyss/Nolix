/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.independent.math;

import ch.nolix.coreapi.math.machineprecision.ComparsionThresholdCatalog;

/**
 * @author Silvan Wyss
 */
public final class NumberComparator {
  /**
   * Prevents that an instance of the {@link NumberComparator} can be created.
   */
  private NumberComparator() {
  }

  /**
   * @param value1
   * @param value2
   * @return true if the given value1 and value2 are equal, false otherwise.
   */
  public static boolean areEqual(final double value1, final double value2) {
    if (value1 < value2) {
      return (value2 - value1) < ComparsionThresholdCatalog.COMMON_DOUBLE_COMPARSION_THRESHOLD;
    }

    return (value1 - value2) < ComparsionThresholdCatalog.COMMON_DOUBLE_COMPARSION_THRESHOLD;
  }

  /**
   * @param value
   * @return true if the given value is 1.0, false otherwise.
   */
  public static boolean isOne(final double value) {
    return //
    value > 1 - ComparsionThresholdCatalog.COMMON_DOUBLE_COMPARSION_THRESHOLD
    && value < 1 + ComparsionThresholdCatalog.COMMON_DOUBLE_COMPARSION_THRESHOLD;
  }

  /**
   * @param value
   * @return true if the given value is 0.0, false otherwise.
   */
  public static boolean isZero(final double value) {
    return //
    value > -ComparsionThresholdCatalog.COMMON_DOUBLE_COMPARSION_THRESHOLD
    && value < ComparsionThresholdCatalog.COMMON_DOUBLE_COMPARSION_THRESHOLD;
  }
}
