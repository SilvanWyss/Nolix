package ch.nolix.core.math.main;

import ch.nolix.coreapi.mathapi.machineprecisionapi.ComparsionThresholdCatalog;

/**
 * @author Silvan Wyss
 * @version 2024-12-13
 */
public final class GlobalNumberComparator {

  /**
   * Prevents that an instance of the {@link GlobalNumberComparator} can be
   * created.
   */
  private GlobalNumberComparator() {
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
