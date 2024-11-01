package ch.nolix.core.errorcontrol.invalidargumentexception;

/**
 * A {@link ClosedArgumentException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when an argument is undesirably closed.
 * 
 * @author Silvan Wyss
 * @version 2019-01-26
 */
@SuppressWarnings("serial")
public final class ClosedArgumentException extends InvalidArgumentException {

  private static final String ERROR_PREDICATE = "is closed";

  /**
   * Creates a new {@link ClosedArgumentException} for the given argument.
   * 
   * @param argument
   */
  private ClosedArgumentException(final Object argument) {

    //Calls constructor of the base class.
    super(argument, ERROR_PREDICATE);
  }

  /**
   * Creates a new {@link ClosedArgumentException} for the given argumentName and
   * argument.
   * 
   * @param argumentName
   * @param argument
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private ClosedArgumentException(final String argumentName, final Object argument) {

    //Calls constructor of the base class.
    super(argumentName, argument, ERROR_PREDICATE);
  }

  /**
   * @param argument
   * @return a new {@link ClosedArgumentException} for the given argument.
   */
  public static ClosedArgumentException forArgument(final Object argument) {
    return new ClosedArgumentException(argument);
  }

  /**
   * @param argumentName
   * @param argument
   * @return a new {@link ClosedArgumentException} for the given argumentName and
   *         argument.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static ClosedArgumentException forArgumentNameAndArgument(
    final String argumentName,
    final Object argument) {
    return new ClosedArgumentException(argumentName, argument);
  }
}
