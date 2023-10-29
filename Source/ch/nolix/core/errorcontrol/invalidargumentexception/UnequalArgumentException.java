//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link UnequalArgumentException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument does undesirably not equal a
 * given value.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 */
@SuppressWarnings("serial")
public final class UnequalArgumentException extends InvalidArgumentException {

  //constructor
  /**
   * Creates a new {@link UnequalArgumentException} for the given argument and
   * value.
   * 
   * @param argument
   * @param value
   */
  private UnequalArgumentException(final Object argument, final Object value) {

    //Calls constructor of the base class.
    super(argument, "does not equal the " + getNameForValue(value) + " '" + value + "'");
  }

  //constructor
  /**
   * Creates a new {@link UnequalArgumentException} for the given argument and
   * value.
   * 
   * @param argumentName
   * @param argument
   * @param value
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private UnequalArgumentException(final String argumentName, final Object argument, final Object value) {

    //Calls constructor of the base class.
    super(argumentName, argument, "does not equal the " + getNameForValue(value) + " '" + value + "'");
  }

  //static method
  /**
   * @param argument
   * @param value
   * @return a new {@link UnequalArgumentException} for the given argument and
   *         value.
   */
  public static UnequalArgumentException forArgumentAndValue(final Object argument, final Object value) {
    return new UnequalArgumentException(argument, value);
  }

  //static method
  /**
   * @param argumentName
   * @param argument
   * @param value
   * @return a new {@link UnequalArgumentException} for the given argumentName,
   *         argument and value.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static UnequalArgumentException forArgumentNameAndArgumentAndValue(
    final String argumentName,
    final Object argument,
    final Object value) {
    return new UnequalArgumentException(argumentName, argument, value);
  }

  //static method
  /**
   * @param value
   * @return a name for the given value.
   * @throws IllegalArgumentException if the given value is null.
   */
  private static String getNameForValue(final Object value) {

    //Asserts that the given argument is not null.
    if (value == null) {
      throw new IllegalArgumentException("The given value is null");
    }

    //Returns a name for the given value.
    return value.getClass().getSimpleName();
  }
}
