package ch.nolix.core.errorcontrol.invalidargumentexception;

import java.math.BigDecimal;

/**
 * A {@link NonPositiveArgumentException} is a {@link InvalidArgumentException}
 * that is supposed to be thrown when a given argument is undesirably not (!)
 * positive.
 * 
 * @author Silvan Wyss
 * @version 2016-03-01
 */
@SuppressWarnings("serial")
public final class NonPositiveArgumentException extends InvalidArgumentException {

  private static final String ERROR_PREDICATE = "is not positive";

  /**
   * Creates a new {@link NonPositiveArgumentException} for the given argument and
   * argumentName.
   * 
   * @param argument     - Can be null.
   * @param argumentName
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  private NonPositiveArgumentException(final BigDecimal argument, final String argumentName) {
    super(argument, argumentName, ERROR_PREDICATE);
  }

  /**
   * Creates a new {@link NonPositiveArgumentException} for the given argument and
   * argumentName.
   * 
   * @param argument
   * @param argumentName
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  private NonPositiveArgumentException(final double argument, final String argumentName) {
    super(argument, argumentName, ERROR_PREDICATE);
  }

  /**
   * Creates a new {@link NonPositiveArgumentException} for the given argument and
   * argumentName.
   * 
   * @param argument
   * @param argumentName
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  private NonPositiveArgumentException(final long argument, final String argumentName) {
    super(argument, argumentName, ERROR_PREDICATE);
  }

  /**
   * @param argument     - Can be null.
   * @param argumentName
   * @return a new {@link NonPositiveArgumentException} for the given argument and
   *         argumentName.
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  public static NonPositiveArgumentException forArgumentAndArgumentName(
    final BigDecimal argument,
    final String argumentName) {
    return new NonPositiveArgumentException(argument, argumentName);
  }

  /**
   * @param argument
   * @param argumentName
   * @return a new {@link NonPositiveArgumentException} for the given argument and
   *         argumentName.
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  public static NonPositiveArgumentException forArgumentAndArgumentName(
    final double argument,
    final String argumentName) {
    return new NonPositiveArgumentException(argument, argumentName);
  }

  /**
   * @param argument
   * @param argumentName
   * @return a new {@link NonPositiveArgumentException} for the given argument and
   *         argumentName.
   * @throws IllegalArgumentException if the given argumentName is null or blank.
   */
  public static NonPositiveArgumentException forArgumentAndArgumentName(
    final long argument,
    final String argumentName) {
    return new NonPositiveArgumentException(argument, argumentName);
  }
}
