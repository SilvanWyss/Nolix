package ch.nolix.core.independent.arrayvalidator;

import ch.nolix.coreapi.independentapi.arrayvalidatorapi.IArrayValidator;

/**
 * @author Silvan Wyss
 * @version 2023-02-17
 */
public final class ArrayValidator implements IArrayValidator {

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertDoesNotContainNull(final Object[] array) {

    if (array == null) {
      throw new IllegalArgumentException("The given array is null.");
    }

    for (var i = 0; i < array.length; i++) {
      if (array[i] == null) {
        throw new IllegalArgumentException("The given array contains a null element at the index " + i + ".");
      }
    }
  }
}
