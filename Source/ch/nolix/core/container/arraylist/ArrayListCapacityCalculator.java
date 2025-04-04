package ch.nolix.core.container.arraylist;

import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.math.main.Calculator;
import ch.nolix.coreapi.containerapi.listapi.IArrayList;

public final class ArrayListCapacityCalculator {

  private static final int BILLION = 1_000_000_000;

  /**
   * @param actualCapacity
   * @param requiredCapacity
   * @return the target capacity for a {@link IArrayList} with the given
   *         actualCapacity and the given requiredCapacity.
   * @throws SmallerArgumentException if the given requiredCapacity is not bigger
   *                                  or does not equal the given actualCapacity.
   */
  public int calculateTargetCapacityForActualCapacityAndRequiredCapacity(
    final int actualCapacity,
    final int requiredCapacity) {

    Validator
      .assertThat(requiredCapacity)
      .thatIsNamed("required capacity")
      .isBiggerThanOrEquals(requiredCapacity);

    if (requiredCapacity > BILLION) {
      return Integer.MAX_VALUE;
    }

    return Calculator.getMax(requiredCapacity, 2 * actualCapacity);
  }

  /**
   * @param capacity
   * @param requiredCapacity
   * @return true if a {@link IArrayList} with the given capacity needs to grow to
   *         reach the given requiredCapacity, false otherwise.
   */
  public boolean arrayListNeedsToGrowForRequiredCapacity(final int capacity, final int requiredCapacity) {
    return (requiredCapacity > capacity);
  }
}
