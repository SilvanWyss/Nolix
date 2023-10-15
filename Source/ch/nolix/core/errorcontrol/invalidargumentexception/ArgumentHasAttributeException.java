//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link ArgumentHasAttributeException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given argument has undesirably a certain
 * attribute.
 * 
 * @author Silvan Wyss
 * @date 2022-01-30
 */
@SuppressWarnings("serial")
public final class ArgumentHasAttributeException extends InvalidArgumentException {

  // static method
  /**
   * @param argument
   * @param attributeName
   * @return a new {@link ArgumentHasAttributeException} for the given argument
   *         and attributeName.
   * @throws IllegalArgumentException if the given attributeName is null.
   * @throws IllegalArgumentException if the given attributeName is blank.
   */
  public static ArgumentHasAttributeException forArgumentAndAttributeName(
      final Object argument,
      final String attributeName) {
    return new ArgumentHasAttributeException(argument, attributeName);
  }

  // static method
  /**
   * @param attributeName
   * @return a valid attribute name of the given attribtueName.
   * @throws IllegalArgumentException if the given attributeName is null.
   * @throws IllegalArgumentException if the given attributeName is blank.
   */
  private static String getValidttributeNameOfAttributeName(final String attributeName) {

    // Asserts that the given attributeName is not null.
    if (attributeName == null) {
      throw new IllegalArgumentException("The given attribute name is null.");
    }

    // Asserts that the given attributeName is not blank.
    if (attributeName.isBlank()) {
      throw new IllegalArgumentException("The given attribute name is blank.");
    }

    return attributeName;
  }

  // constructor
  /**
   * Creates a new {@link ArgumentHasAttributeException} for the given argument
   * and attributeName.
   * 
   * @param argument
   * @param attributeName
   * @throws IllegalArgumentException if the given attributeName is null.
   * @throws IllegalArgumentException if the given attributeName is blank.
   */
  private ArgumentHasAttributeException(final Object argument, final String attributeName) {

    // Calls constructor of the base class.
    super(argument, "has a " + getValidttributeNameOfAttributeName(attributeName));
  }
}
