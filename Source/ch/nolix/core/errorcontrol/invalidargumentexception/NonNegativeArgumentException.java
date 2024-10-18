package ch.nolix.core.errorcontrol.invalidargumentexception;

/**
 * A {@link NonNegativeArgumentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given argument is undesirably not (!)
 * negative.
 * 
 * @author Silvan Wyss
 * @version 2016-12-01
 */
@SuppressWarnings("serial")
public final class NonNegativeArgumentException extends InvalidArgumentException {

  private static final String ERROR_PREDICATE = "is not negative";

  /**
   * Creates a new {@link NonNegativeArgumentException} for the given argumentName
   * and argument.
   * 
   * @param argumentName
   * @param argument
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private NonNegativeArgumentException(final String argumentName, final double argument) {

    //Calls constructor of the base class.
    super(argumentName, argument, ERROR_PREDICATE);
  }

  /**
   * Creates a new {@link NonNegativeArgumentException} for the given argumentName
   * and argument.
   * 
   * @param argumentName
   * @param argument
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private NonNegativeArgumentException(final String argumentName, final long argument) {

    //Calls constructor of the base class.
    super(argumentName, argument, ERROR_PREDICATE);
  }

  /**
   * @param argumentName
   * @param argument
   * @return a new {@link NonNegativeArgumentException} for the given argumentName
   *         and argument.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static NonNegativeArgumentException forArgumentNameAndArgument(
    final String argumentName,
    final double argument) {
    return new NonNegativeArgumentException(argumentName, argument);
  }

  /**
   * @param argumentName
   * @param argument
   * @return a new {@link NonNegativeArgumentException} for the given argumentName
   *         and argument.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static NonNegativeArgumentException forArgumentNameAndArgument(
    final String argumentName,
    final long argument) {
    return new NonNegativeArgumentException(argumentName, argument);
  }
}
