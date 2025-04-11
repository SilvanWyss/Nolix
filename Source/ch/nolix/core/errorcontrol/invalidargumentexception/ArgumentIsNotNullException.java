package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrolapi.exceptionargumentboxapi.ErrorPredicateDto;

/**
 * A {@link ArgumentIsNotNullException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when a
 * given argument is undesirably not (!) null.
 * 
 * @author Silvan Wyss
 * @version 2017-01-08
 */
@SuppressWarnings("serial")
public final class ArgumentIsNotNullException extends AbstractInvalidArgumentException {

  private static final String ERROR_PREDICATE = "is not null";

  /**
   * Creates a new {@link ArgumentIsNotNullException} for the given argument.
   * 
   * @param argument
   */
  private ArgumentIsNotNullException(final Object argument) {
    super(argument, new ErrorPredicateDto(ERROR_PREDICATE));
  }

  /**
   * @param argument
   * @return a new {@link ArgumentIsNotNullException} for the given argument.
   */
  public static ArgumentIsNotNullException forArgument(final Object argument) {
    return new ArgumentIsNotNullException(argument);
  }
}
