package ch.nolix.core.container.arraylist;

import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.math.main.Calculator;
import ch.nolix.coreapi.containerapi.listapi.IArrayList;
import ch.nolix.coreapi.mathapi.numberapi.IntCatalog;

/**
 * @author Silvan Wyss
 * @version 2024-07-28
 */
public final class ArrayListCapacityCalculator {

  /**
   * @param capacity
   * @param requiredCapacity
   * @return true if a {@link IArrayList} with the given capacity needs to grow to
   *         reach the given requiredCapacity, false otherwise.
   */
  public boolean arrayListNeedsToGrowForRequiredCapacity(final int capacity, final int requiredCapacity) {
    return (requiredCapacity > capacity);
  }

  /**
   * @param actualCapacity
   * @param requiredCapacity
   * @return the target capacity for an array with the given actualCapacity and
   *         the given requiredCapacity.
   * @throws SmallerArgumentException if the given requiredCapacity is not bigger
   *                                  or does not equal the given actualCapacity.
   */
  public int calculateTargetCapacityForActualCapacityAndRequiredCapacity(
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
  
    return Calculator.getMax(requiredCapacity, 2 * actualCapacity);
  }
}
