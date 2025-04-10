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
   * Creates a new {@link PositiveArgumentException} for the given argument and
   * argumentName.
   * 
   * @param argument
   * @param argumentName
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  private PositiveArgumentException(final long argument, final String argumentName) {
    super(argument, argumentName, ERROR_PREDICATE);
  }

  /**
   * @param argument
   * @param argumentName
   * @return a new {@link PositiveArgumentException} for the given argument and
   *         argumentName.
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  public static PositiveArgumentException forArgumentAndArgumentName(final long argument, final String argumentName) {
    return new PositiveArgumentException(argument, argumentName);
  }
}
