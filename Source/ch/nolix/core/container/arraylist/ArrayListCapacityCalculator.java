package ch.nolix.core.container.arraylist;

import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.math.number.IntCatalog;

/**
 * Of the {@link ArrayListCapacityCalculator} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2024-07-28
 */
public final class ArrayListCapacityCalculator {
  /**
   * Prevents that an instance of the {@link ArrayListCapacityCalculator} can be
   * created.
   */
  private ArrayListCapacityCalculator() {
  }

  /**
   * @param capacity
   * @param requiredCapacity
   * @return true if an array list with the given capacity needs to grow to reach
   *         the given requiredCapacity, false otherwise.
   */
  public static boolean arrayListNeedsToGrowForRequiredCapacity(final int capacity, final int requiredCapacity) {
    return (requiredCapacity > capacity);
  }

  /**
   * @param actualCapacity
   * @param requiredCapacity
   * @return the target capacity for an array list with the given actualCapacity
   *         and the given requiredCapacity.
   * @throws SmallerArgumentException if the given requiredCapacity is not bigger
   *                                  or does not equal the given actualCapacity.
   */
  public static int calculateTargetCapacityForActualCapacityAndRequiredCapacity(
    final int actualCapacity,
    final int requiredCapacity) {
    Validator.assertThat(actualCapacity).thatIsNamed("actial capacity").isNotNegative();
    Validator.assertThat(requiredCapacity).thatIsNamed("required capacity").isNotNegative();

    if (actualCapacity > requiredCapacity) {
      if (actualCapacity / 3 > requiredCapacity) {
        return (2 * requiredCapacity);
      }

      return actualCapacity;
    }

    if (requiredCapacity > IntCatalog.BILLION) {
      return Integer.MAX_VALUE;
    }

    return getMax(requiredCapacity, 2 * actualCapacity);
  }

  /**
   * @param value1
   * @param value2
   * @return the bigger value of the given value1 and value2.
   */
  private static int getMax(int value1, int value2) {
    if (value1 > value2) {
      return value1;
    }

    return value2;
  }
}
