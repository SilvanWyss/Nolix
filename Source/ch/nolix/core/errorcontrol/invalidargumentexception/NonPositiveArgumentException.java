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
   * Creates a new {@link NonPositiveArgumentException} for the given argumentName
   * and argument.
   * 
   * @param argumentName
   * @param argument
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private NonPositiveArgumentException(final String argumentName, final BigDecimal argument) {

    //Calls constructor of the base class.
    super(argumentName, argument, ERROR_PREDICATE);
  }

  /**
   * Creates a new {@link NonPositiveArgumentException} for the given argumentName
   * and argument.
   * 
   * @param argumentName
   * @param argument
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private NonPositiveArgumentException(final String argumentName, final double argument) {

    //Calls constructor of the base class.
    super(argumentName, argument, ERROR_PREDICATE);
  }

  /**
   * Creates a new {@link NonPositiveArgumentException} for the given argumentName
   * and argument.
   * 
   * @param argumentName
   * @param argument
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private NonPositiveArgumentException(final String argumentName, final long argument) {

    //Calls constructor of the base class.
    super(argumentName, argument, ERROR_PREDICATE);
  }

  /**
   * @param argumentName
   * @param argument
   * @return a new {@link NonPositiveArgumentException} for the given argumentName
   *         and argument.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static NonPositiveArgumentException forArgumentNameAndArgument(
    final String argumentName,
    final BigDecimal argument) {
    return new NonPositiveArgumentException(argumentName, argument);
  }

  /**
   * @param argumentName
   * @param argument
   * @return a new {@link NonPositiveArgumentException} for the given argumentName
   *         and argument.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static NonPositiveArgumentException forArgumentNameAndArgument(
    final String argumentName,
    final double argument) {
    return new NonPositiveArgumentException(argumentName, argument);
  }

  /**
   * @param argumentName
   * @param argument
   * @return a new {@link NonPositiveArgumentException} for the given argumentName
   *         and argument.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static NonPositiveArgumentException forArgumentNameAndArgument(
    final String argumentName,
    final long argument) {
    return new NonPositiveArgumentException(argumentName, argument);
  }
}
