//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link ArgumentIsZeroException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument is undesirably 0.
 * 
 * @author Silvan Wyss
 * @date 2016-03-01
 */
@SuppressWarnings("serial")
public final class ArgumentIsZeroException extends InvalidArgumentException {

  //constant
  private static final String ERROR_PREDICATE = "is 0";

  //constructor
  /**
   * Creates a new {@link ArgumentIsZeroException} for the given argumentName and
   * argument.
   * 
   * @param argumentName
   * @param argument
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private ArgumentIsZeroException(final String argumentName, final double argument) {

    //Calls constructor of the base class.
    super(argumentName, argument, ERROR_PREDICATE);
  }

  //constructor
  /**
   * Creates a new {@link ArgumentIsZeroException} for the given argumentName and
   * argument.
   * 
   * @param argumentName
   * @param argument
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private ArgumentIsZeroException(final String argumentName, final long argument) {

    //Calls constructor of the base class.
    super(argumentName, argument, ERROR_PREDICATE);
  }

  //static method
  /**
   * @param argumentName
   * @param argument
   * @return a new {@link ArgumentIsZeroException} for the given argumentName and
   *         argument.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static ArgumentIsZeroException forAgumentNameAndArgument(final String argumentName, final double argument) {
    return new ArgumentIsZeroException(argumentName, argument);
  }

  //static method
  /**
   * @param argumentName
   * @param argument
   * @return a new {@link ArgumentIsZeroException} for the given argumentName and
   *         argument.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static ArgumentIsZeroException forAgumentNameAndArgument(final String argumentName, final long argument) {
    return new ArgumentIsZeroException(argumentName, argument);
  }
}
