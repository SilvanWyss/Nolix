//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link ArgumentIsNotNullException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given argument is undesirably not (!)
 * null.
 * 
 * @author Silvan Wyss
 * @version 2017-01-08
 */
@SuppressWarnings("serial")
public final class ArgumentIsNotNullException extends InvalidArgumentException {

  //constant
  private static final String ERROR_PREDICATE = "is not null";

  //constructor
  /**
   * Creates a new {@link ArgumentIsNotNullException} for the given argument.
   * 
   * @param argument
   * @throws IllegalArgumentException if the given argument is null.
   */
  private ArgumentIsNotNullException(final Object argument) {

    //Calls constructor of the base class.
    super(argument, ERROR_PREDICATE);

    //Asserts that the given argument is not null.
    if (argument == null) {
      throw new IllegalArgumentException("The given argument is null.");
    }
  }

  //static method
  /**
   * @param argument
   * @return a new {@link ArgumentIsNotNullException} for the given argument.
   * @throws IllegalArgumentException if the given argument is null.
   */
  public static ArgumentIsNotNullException forArgument(final Object argument) {
    return new ArgumentIsNotNullException(argument);
  }
}
