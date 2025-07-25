package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ErrorPredicateDto;

/**
 * A {@link DeletedArgumentException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when a
 * given argument is undesirably deleted.
 * 
 * @author Silvan Wyss
 * @version 2021-07-12
 */
@SuppressWarnings("serial")
public final class DeletedArgumentException extends AbstractInvalidArgumentException {

  private static final String ERROR_PREDICATE = "is deleted";

  /**
   * Creates a new {@link DeletedArgumentException} for the given argument.
   * 
   * @param argument - Can be null.
   */
  private DeletedArgumentException(final Object argument) {
    super(argument, new ErrorPredicateDto(ERROR_PREDICATE));
  }

  /**
   * @param argument - Can be null.
   * @return a new {@link DeletedArgumentException} for the given argument.
   */
  public static DeletedArgumentException forArgument(final Object argument) {
    return new DeletedArgumentException(argument);
  }
}
