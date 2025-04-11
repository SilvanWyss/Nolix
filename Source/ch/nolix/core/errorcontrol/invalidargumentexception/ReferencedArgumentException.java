package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrolapi.exceptionargumentboxapi.ErrorPredicateDto;

/**
 * A {@link ReferencedArgumentException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when a
 * given argument is undesirably referenced.
 * 
 * @author Silvan Wyss
 * @version 2021-07-16
 */
@SuppressWarnings("serial")
public final class ReferencedArgumentException extends AbstractInvalidArgumentException {

  private static final String ERROR_PREDICATE = "is referenced";

  /**
   * Creates a new {@link ReferencedArgumentException} for the given argument.
   * 
   * @param argument - Can be null.
   */
  private ReferencedArgumentException(final Object argument) {
    super(argument, new ErrorPredicateDto(ERROR_PREDICATE));
  }

  /**
   * @param argument - Can be null.
   * @return a new {@link ReferencedArgumentException} for the given argument.
   */
  public static ReferencedArgumentException forArgument(final Object argument) {
    return new ReferencedArgumentException(argument);
  }
}
