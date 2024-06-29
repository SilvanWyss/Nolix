//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link ArgumentIsNullException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument is undesirably null.
 * 
 * @author Silvan Wyss
 * @version 2016-05-01
 */
@SuppressWarnings("serial")
public final class ArgumentIsNullException extends InvalidArgumentException {

  //constant
  private static final String ERROR_PREDICATE = "is null";

  //constructor
  /**
   * Creates a new {@link ArgumentIsNullException} for the given argumentType.
   * 
   * @param argumentType
   * @throws IllegalArgumentException if the given argumentType is null.
   */
  private ArgumentIsNullException(final Class<?> argumentType) {

    //Calls constructor of the base class.
    super(getNameOfArgumentType(argumentType), null, ERROR_PREDICATE);
  }

  //constructor
  /**
   * Creates a new {@link ArgumentIsNullException} for the given argumentName.
   * 
   * @param argumentName
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private ArgumentIsNullException(final String argumentName) {

    //Calls constructor of the base class.
    super(argumentName, null, ERROR_PREDICATE);
  }

  //static method
  /**
   * @param argumentName
   * @return a new {@link ArgumentIsNullException} for the given argumentName.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static ArgumentIsNullException forArgumentName(final String argumentName) {
    return new ArgumentIsNullException(argumentName);
  }

  //static method
  /**
   * @param argumentType
   * @return a new {@link ArgumentIsNullException} for the given argumentType.
   * @throws IllegalArgumentException if the given argumentType is null.
   */
  public static ArgumentIsNullException forArgumentType(final Class<?> argumentType) {
    return new ArgumentIsNullException(argumentType);
  }

  //static method
  /**
   * @param argumentType
   * @return the name of the given argumentType.
   * @throws IllegalArgumentException if the given argumentType is null.
   */
  private static String getNameOfArgumentType(final Class<?> argumentType) {

    //Asserts that the given argumentType is not null.
    if (argumentType == null) {
      throw new IllegalArgumentException("The given argument type is null.");
    }

    return argumentType.getSimpleName();
  }
}
