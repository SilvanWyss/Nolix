package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ArgumentNameDto;
import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ErrorPredicateDto;

/**
 * A {@link NonNegativeArgumentException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when a
 * given argument is undesirably not (!) negative.
 * 
 * @author Silvan Wyss
 * @version 2016-12-01
 */
@SuppressWarnings("serial")
public final class NonNegativeArgumentException extends AbstractInvalidArgumentException {

  private static final String ERROR_PREDICATE = "is not negative";

  /**
   * Creates a new {@link NonNegativeArgumentException} for the given argument and
   * argumentName.
   * 
   * @param argument
   * @param argumentName
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  private NonNegativeArgumentException(final double argument, final String argumentName) {
    super(argument, new ArgumentNameDto(argumentName), new ErrorPredicateDto(ERROR_PREDICATE));
  }

  /**
   * Creates a new {@link NonNegativeArgumentException} for the given argument and
   * argumentName.
   * 
   * @param argument
   * @param argumentName
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  private NonNegativeArgumentException(final long argument, final String argumentName) {
    super(argument, new ArgumentNameDto(argumentName), new ErrorPredicateDto(ERROR_PREDICATE));
  }

  /**
   * @param argument
   * @param argumentName
   * @return a new {@link NonNegativeArgumentException} for the given argument and
   *         argumentName.
   * @throws RuntimeException if the given argumentName is null or blank.
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
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public static NonNegativeArgumentException forArgumentAndArgumentName(
    final long argument,
    final String argumentName) {
    return new NonNegativeArgumentException(argument, argumentName);
  }
}
