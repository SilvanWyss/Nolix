package ch.nolix.core.independent.arrayvalidator;

public final class ArrayValidator {

  public void assertDoesNotContainNull(final Object[] array) {

    if (array == null) {
      throw new IllegalArgumentException("The given array is null.");
    }

    for (var i = 0; i < array.length; i++) {
      if (array[i] == null) {
        throw new IllegalArgumentException("The given array contains null at the index " + i + ".");
      }
    }
  }
}
