package ch.nolix.core.errorcontrol.invalidargumentexception;

/**
 * A {@link EmptyArgumentException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument is undesirably empty.
 * 
 * @author Silvan Wyss
 * @version 2016-03-01
 */
@SuppressWarnings("serial")
public final class EmptyArgumentException extends InvalidArgumentException {

  private static final String ERROR_PREDICATE = "is empty";

  /**
   * Creates a new {@link EmptyArgumentException} for the given argument.
   * 
   * @param argument
   */
  private EmptyArgumentException(final Object argument) {

    //Calls constructor of the base class.
    super(argument, ERROR_PREDICATE);
  }

  /**
   * Creates a new {@link EmptyArgumentException} for the given argumentName and
   * argument.
   * 
   * @param argumentName
   * @param argument
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private EmptyArgumentException(final String argumentName, final Object argument) {

    //Calls constructor of the base class.
    super(argumentName, argument, ERROR_PREDICATE);
  }

  /**
   * @param argumentName
   * @param argument
   * @return a new {@link EmptyArgumentException} for the given argumentName and
   *         argument.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static EmptyArgumentException forArgumentNameAndArgument(final String argumentName, final Object argument) {
    return new EmptyArgumentException(argumentName, argument);
  }

  /**
   * @param argument
   * @return a new {@link EmptyArgumentException} for the given argument.
   */
  public static EmptyArgumentException forArgument(final Object argument) {
    return new EmptyArgumentException(argument);
  }
}
