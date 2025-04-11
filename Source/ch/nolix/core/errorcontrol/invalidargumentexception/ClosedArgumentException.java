package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrolapi.exceptionargumentboxapi.ArgumentNameDto;
import ch.nolix.coreapi.errorcontrolapi.exceptionargumentboxapi.ErrorPredicateDto;

/**
 * A {@link ClosedArgumentException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when
 * an argument is undesirably closed.
 * 
 * @author Silvan Wyss
 * @version 2019-01-26
 */
@SuppressWarnings("serial")
public final class ClosedArgumentException extends AbstractInvalidArgumentException {

  private static final String ERROR_PREDICATE = "is closed";

  /**
   * Creates a new {@link ClosedArgumentException} for the given argument.
   * 
   * @param argument
   */
  private ClosedArgumentException(final Object argument) {
    super(argument, new ErrorPredicateDto(ERROR_PREDICATE));
  }

  /**
   * Creates a new {@link ClosedArgumentException} for the given argument and
   * argumentName.
   * 
   * @param argument
   * @param argumentName
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  private ClosedArgumentException(final Object argument, final String argumentName) {
    super(argument, new ArgumentNameDto(argumentName), new ErrorPredicateDto(ERROR_PREDICATE));
  }

  /**
   * @param argument
   * @return a new {@link ClosedArgumentException} for the given argument.
   */
  public static ClosedArgumentException forArgument(final Object argument) {
    return new ClosedArgumentException(argument);
  }

  /**
   * @param argument
   * @param argumentName
   * @return a new {@link ClosedArgumentException} for the given argument and
   *         argumentName.
   * @throws RuntimeException if the given argumentName is null or blank.
   */
  public static ClosedArgumentException forArgumentAndArgumentName(final Object argument, final String argumentName) {
    return new ClosedArgumentException(argument, argumentName);
  }
}
