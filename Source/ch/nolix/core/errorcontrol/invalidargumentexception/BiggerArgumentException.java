package ch.nolix.core.errorcontrol.invalidargumentexception;

/**
 * A {@link BiggerArgumentException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument is undesirably bigger than a
 * given max.
 * 
 * @author Silvan Wyss
 * @version 2016-03-01
 */
@SuppressWarnings("serial")
public final class BiggerArgumentException extends InvalidArgumentException {

  /**
   * Creates a new {@link BiggerArgumentException} for the given argument,
   * argumentName and max.
   * 
   * @param argument
   * @param argumentName
   * @param max
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  private BiggerArgumentException(final double argument, final String argumentName, final double max) {
    super(argument, argumentName, "is bigger than " + max);
  }

  /**
   * Creates a new {@link BiggerArgumentException} for the given argument,
   * argumentName and max.
   * 
   * @param argument
   * @param argumentName
   * @param max
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  private BiggerArgumentException(final long argument, final String argumentName, final long max) {
    super(argument, argumentName, "is bigger than " + max);
  }

  /**
   * @param argument
   * @param argumentName
   * @param max
   * @return a new {@link BiggerArgumentException} for the given argument,
   *         argumentName and max.
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  public static BiggerArgumentException forArgumentAndArgumentNameAndMax(
    final double argument,
    final String argumentName,
    final double max) {
    return new BiggerArgumentException(argument, argumentName, max);
  }

  /**
   * @param argument
   * @param argumentName
   * @param max
   * @return a new {@link BiggerArgumentException} for the given argument,
   *         argumentName and max.
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  public static BiggerArgumentException forArgumentAndArgumentNameAndMax(
    final long argument,
    final String argumentName,
    final long max) {
    return new BiggerArgumentException(argument, argumentName, max);
  }
}
