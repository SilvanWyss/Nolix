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
   * Creates a new {@link NonNegativeArgumentException} for the given argument and
   * argumentName.
   * 
   * @param argument
   * @param argumentName
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  private NonNegativeArgumentException(final double argument, final String argumentName) {
    super(argument, argumentName, ERROR_PREDICATE);
  }

  /**
   * Creates a new {@link NonNegativeArgumentException} for the given argument and
   * argumentName.
   * 
   * @param argument
   * @param argumentName
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  private NonNegativeArgumentException(final long argument, final String argumentName) {
    super(argument, argumentName, ERROR_PREDICATE);
  }

  /**
   * @param argument
   * @param argumentName
   * @return a new {@link NonNegativeArgumentException} for the given argument and
   *         argumentName.
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  public static NonNegativeArgumentException forArgumentAndArgumentName(
    final double argument,
    final String argumentName) {
    return new NonNegativeArgumentException(argument, argumentName);
  }

  /**
   * @param argument
   * @param argumentName
   * @return a new {@link NonNegativeArgumentException} for the given argument and
   *         argumentName.
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  public static NonNegativeArgumentException forArgumentAndArgumentName(
    final long argument,
    final String argumentName) {
    return new NonNegativeArgumentException(argument, argumentName);
  }
}
