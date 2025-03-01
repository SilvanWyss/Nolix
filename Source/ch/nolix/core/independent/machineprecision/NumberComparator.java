package ch.nolix.core.independent.machineprecision;

/**
 * @author Silvan Wyss
 * @version 2024-12-12
 */
public final class NumberComparator {

  public static final double COMMON_DOUBLE_COMPARSION_THRESHOLD = 0.000_001; //10E^6

  /**
   * Prevents that an instance of the {@link NumberComparator} can be
   * created.
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
      return (value2 - value1) < COMMON_DOUBLE_COMPARSION_THRESHOLD;
    }

    return (value1 - value2) < COMMON_DOUBLE_COMPARSION_THRESHOLD;
  }
}
