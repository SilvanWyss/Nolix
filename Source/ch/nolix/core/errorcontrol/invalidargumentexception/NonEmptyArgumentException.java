package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ArgumentNameDto;
import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ErrorPredicateDto;

/**
 * A {@link NonEmptyArgumentException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when a
 * given argument is undesirably not (!) empty.
 * 
 * @author Silvan Wyss
 * @version 2017-01-01
 */
@SuppressWarnings("serial")
public final class NonEmptyArgumentException extends AbstractInvalidArgumentException {
  private static final String ERROR_PREDICATE = "is not empty";

  /**
   * Creates a new {@link NonEmptyArgumentException} for the given argument.
   * 
   * @param argument - Can be null.
   */
  private NonEmptyArgumentException(final Object argument) {
    super(argument, new ErrorPredicateDto(ERROR_PREDICATE));
  }

  /**
   * Creates a new {@link NonEmptyArgumentException} for the given argument and
   * argumentName.
   * 
   * @param argument     - Can be null.
   * @param argumentName
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  private NonEmptyArgumentException(final Object argument, final String argumentName) {
    super(argument, new ArgumentNameDto(argumentName), new ErrorPredicateDto(ERROR_PREDICATE));
  }

  /**
   * @param argument - Can be null.
   * @return a new {@link NonEmptyArgumentException} for the given argument.
   */
  public static NonEmptyArgumentException forArgument(final Object argument) {
    return new NonEmptyArgumentException(argument);
  }

  /**
   * @param argument     - Can be null.
   * @param argumentName
   * @return a new {@link NonEmptyArgumentException} for the given argument and
   *         argumentName.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public static NonEmptyArgumentException forArgumentAndArgumentName(
    final Object argument,
    final String argumentName) {
    return new NonEmptyArgumentException(argument, argumentName);
  }
}
