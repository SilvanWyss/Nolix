package ch.nolix.core.errorcontrol.invalidargumentexception;

import java.math.BigDecimal;

/**
 * A {@link NegativeArgumentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given argument is undesirably negative.
 * 
 * @author Silvan Wyss
 * @version 2016-03-01
 */
@SuppressWarnings("serial")
public final class NegativeArgumentException extends InvalidArgumentException {

  private static final String ERROR_PREDICATE = "is negative";

  /**
   * Creates a new {@link NegativeArgumentException} for the given argument and
   * argumentName.
   * 
   * @param argument     - Can be null.
   * @param argumentName
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  private NegativeArgumentException(final BigDecimal argument, final String argumentName) {
    super(argument, argumentName, ERROR_PREDICATE);
  }

  /**
   * Creates a new {@link NegativeArgumentException} for the given argument and
   * argumentName.
   * 
   * @param argument     - Can be null.
   * @param argumentName
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  private NegativeArgumentException(final double argument, final String argumentName) {
    super(argument, argumentName, ERROR_PREDICATE);
  }

  /**
   * Creates a new {@link NegativeArgumentException} for the given argument and
   * argumentName.
   * 
   * @param argument     - Can be null.
   * @param argumentName
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  private NegativeArgumentException(final long argument, final String argumentName) {
    super(argument, argumentName, ERROR_PREDICATE);
  }

  /**
   * @param argument
   * @param argumentName - Can be null.
   * @return a new {@link NegativeArgumentException} for the given argument and
   *         argumentName.
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  public static NegativeArgumentException forArgumentAndArgumentName(
    final BigDecimal argument,
    final String argumentName) {
    return new NegativeArgumentException(argument, argumentName);
  }

  /**
   * @param argument
   * @param argumentName - Can be null.
   * @return a new {@link NegativeArgumentException} for the given argument and
   *         argumentName.
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  public static NegativeArgumentException forArgumentAndArgumentName(
    final double argument,
    final String argumentName) {
    return new NegativeArgumentException(argument, argumentName);
  }

  /**
   * @param argument
   * @param argumentName - Can be null.
   * @return a new {@link NegativeArgumentException} for the given argument and
   *         argumentName.
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  public static NegativeArgumentException forArgumentAndArgumentName(
    final long argument,
    final String argumentName) {
    return new NegativeArgumentException(argument, argumentName);
  }
}
