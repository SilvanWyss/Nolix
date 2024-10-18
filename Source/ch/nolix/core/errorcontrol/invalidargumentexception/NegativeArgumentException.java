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
   * Creates a new {@link NegativeArgumentException} for the given argumentName
   * and argument.
   * 
   * @param argumentName
   * @param argument
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private NegativeArgumentException(final String argumentName, final BigDecimal argument) {

    //Calls constructor of the base class.
    super(argumentName, argument, ERROR_PREDICATE);
  }

  /**
   * Creates a new {@link NegativeArgumentException} for the given argumentName
   * and argument.
   * 
   * @param argumentName
   * @param argument
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private NegativeArgumentException(final String argumentName, final double argument) {

    //Calls constructor of the base class.
    super(argumentName, argument, ERROR_PREDICATE);
  }

  /**
   * Creates a new {@link NegativeArgumentException} for the given argumentName
   * and argument.
   * 
   * @param argumentName
   * @param argument
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  private NegativeArgumentException(final String argumentName, final long argument) {

    //Calls constructor of the base class.
    super(argumentName, argument, ERROR_PREDICATE);
  }

  /**
   * @param argumentName
   * @param argument
   * @return a new {@link NegativeArgumentException} for the given argumentName
   *         and argument.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static NegativeArgumentException forArgumentNameAndArgument(
    final String argumentName,
    final BigDecimal argument) {
    return new NegativeArgumentException(argumentName, argument);
  }

  /**
   * @param argumentName
   * @param argument
   * @return a new {@link NegativeArgumentException} for the given argumentName
   *         and argument.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static NegativeArgumentException forArgumentNameAndArgument(
    final String argumentName,
    final double argument) {
    return new NegativeArgumentException(argumentName, argument);
  }

  /**
   * @param argumentName
   * @param argument
   * @return a new {@link NegativeArgumentException} for the given argumentName
   *         and argument.
   * @throws IllegalArgumentException if the given argumentName is null.
   * @throws IllegalArgumentException if the given argumentName is blank.
   */
  public static NegativeArgumentException forArgumentNameAndArgument(
    final String argumentName,
    final long argument) {
    return new NegativeArgumentException(argumentName, argument);
  }
}
