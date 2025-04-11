package ch.nolix.core.errorcontrol.invalidargumentexception;

import java.math.BigDecimal;

/**
 * A {@link SmallerArgumentException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given argument is undesirably smaller than a
 * given min.
 * 
 * @author Silvan Wyss
 * @version 2016-03-01
 */
@SuppressWarnings("serial")
public final class SmallerArgumentException extends InvalidArgumentException {

  /**
   * Creates a new {@link SmallerArgumentException} for the given argument,
   * argumentName and min.
   * 
   * @param argument     - Can be null.
   * @param argumentName
   * @param min
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   * @throws IllegalArgumentException if the given min is null.
   */
  private SmallerArgumentException(final BigDecimal argument, final String argumentName, final BigDecimal min) {
    super(argument, argumentName, "is smaller than " + getValidatedMinFromMin(min));
  }

  /**
   * Creates a new {@link SmallerArgumentException} for the given argument,
   * argumentName and min.
   * 
   * @param argument
   * @param argumentName
   * @param min
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  private SmallerArgumentException(final double argument, final String argumentName, final double min) {
    super(argument, argumentName, "is smaller than " + min);
  }

  /**
   * @param argument     - Can be null.
   * @param argumentName
   * @param min
   * @return a new {@link SmallerArgumentException} for the given argument,
   *         argumentName and mint.
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   * @throws IllegalArgumentException if the given min is null.
   */
  public static SmallerArgumentException forArgumentNameAndArgumentAndLimit(
    final BigDecimal argument,
    final String argumentName,
    final BigDecimal min) {
    return new SmallerArgumentException(argument, argumentName, min);
  }

  /**
   * @param argument
   * @param argumentName
   * @param min
   * @return a new {@link SmallerArgumentException} for the given argument,
   *         argumentName and mint.
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  public static SmallerArgumentException forArgumentNameAndArgumentAndLimit(
    final double argument,
    final String argumentName,
    final double min) {
    return new SmallerArgumentException(argument, argumentName, min);
  }

  /**
   * @param min
   * @return a validated min from the given min.
   * @throws IllegalArgumentException if the given min is null.
   */
  private static BigDecimal getValidatedMinFromMin(final BigDecimal min) {

    if (min == null) {
      throw new IllegalArgumentException("The given minimum is null.");
    }

    return min;
  }
}
