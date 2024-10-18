package ch.nolix.core.errorcontrol.invalidargumentexception;

/**
 * A {@link PositiveArgumentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given argument is undesirably positive.
 * 
 * @author Silvan Wyss
 * @version 2017-01-01
 */
@SuppressWarnings("serial")
public final class PositiveArgumentException extends InvalidArgumentException {

  private static final String ERROR_PREDICATE = "is positive";

  /**
   * Creates a new {@link PositiveArgumentException} for the given argumentName
   * and argument.
   * 
   * @param argumentName
   * @param argument
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private PositiveArgumentException(final String argumentName, final long argument) {

    //Calls constructor of the base class.
    super(argumentName, argument, ERROR_PREDICATE);
  }

  /**
   * @param argumentName
   * @param argument
   * @return a new {@link PositiveArgumentException} for the given argumentName
   *         and argument.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static PositiveArgumentException forArgumentNameAndArgument(
    final String argumentName,
    final long argument) {
    return new PositiveArgumentException(argumentName, argument);
  }
}
