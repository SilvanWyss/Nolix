package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ErrorPredicateDto;

/**
 * A {@link NewArgumentException} is a {@link AbstractInvalidArgumentException}
 * that is supposed to be thrown when a given argument is undesirably new.
 * 
 * @author Silvan Wyss
 */
@SuppressWarnings("serial")
public final class NewArgumentException extends AbstractInvalidArgumentException {
  private static final String ERROR_PREDICATE = "is new";

  /**
   * Creates a new {@link NewArgumentException} for the given argument.
   * 
   * @param argument - Can be null.
   */
  private NewArgumentException(final Object argument) {
    super(argument, new ErrorPredicateDto(ERROR_PREDICATE));
  }

  /**
   * @param argument - Can be null.
   * @return a new {@link NewArgumentException} for the given argument.
   */
  public static NewArgumentException forArgument(final Object argument) {
    return new NewArgumentException(argument);
  }
}
