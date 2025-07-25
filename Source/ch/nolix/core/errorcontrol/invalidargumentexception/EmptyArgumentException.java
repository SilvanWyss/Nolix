package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ArgumentNameDto;
import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ErrorPredicateDto;

/**
 * A {@link EmptyArgumentException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when a
 * given argument is undesirably empty.
 * 
 * @author Silvan Wyss
 * @version 2016-03-01
 */
@SuppressWarnings("serial")
public final class EmptyArgumentException extends AbstractInvalidArgumentException {

  private static final String ERROR_PREDICATE = "is empty";

  /**
   * Creates a new {@link EmptyArgumentException} for the given argument.
   * 
   * @param argument - Can be null.
   */
  private EmptyArgumentException(final Object argument) {
    super(argument, new ErrorPredicateDto(ERROR_PREDICATE));
  }

  /**
   * Creates a new {@link EmptyArgumentException} for the given argument and
   * argumentName.
   * 
   * @param argument     - Can be null.
   * @param argumentName
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  private EmptyArgumentException(final Object argument, final String argumentName) {
    super(argument, new ArgumentNameDto(argumentName), new ErrorPredicateDto(ERROR_PREDICATE));
  }

  /**
   * @param argument - Can be null.
   * @return a new {@link EmptyArgumentException} for the given argument.
   */
  public static EmptyArgumentException forArgument(final Object argument) {
    return new EmptyArgumentException(argument);
  }

  /**
   * @param argument     - Can be null.
   * @param argumentName
   * @return a new {@link EmptyArgumentException} for the given argumente and
   *         argumentName.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public static EmptyArgumentException forArgumentAndArgumentName(final Object argument, final String argumentName) {
    return new EmptyArgumentException(argument, argumentName);
  }
}
