//package declaration
package ch.nolix.core.container.arraylist;

import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
//own imports
import ch.nolix.core.math.main.GlobalCalculator;
import ch.nolix.coreapi.containerapi.listapi.IArrayList;

//class
public final class ArrayListCapacityCalculator {

  //constant
  private static final int BILLION = 1_000_000_000;

  //method
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

    GlobalValidator
      .assertThat(requiredCapacity)
      .thatIsNamed("required capacity")
      .isBiggerThanOrEquals(requiredCapacity);

    if (requiredCapacity > BILLION) {
      return Integer.MAX_VALUE;
    }

    return GlobalCalculator.getMax(requiredCapacity, 2 * actualCapacity);
  }

  //method
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
