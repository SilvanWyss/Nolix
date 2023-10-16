//package declaration
package ch.nolix.core.independent.containervalidator;

//class
public final class GlobalArrayValidator {

  //static method
  public static void assertDoesNotContainNull(final Object[] array) {

    if (array == null) {
      throw new IllegalArgumentException("The given array is null.");
    }

    for (var i = 0; i < array.length; i++) {
      if (array[i] == null) {
        throw new IllegalArgumentException("The given array contains null at the index " + i + ".");
      }
    }
  }

  //constructor
  private GlobalArrayValidator() {
  }
}
