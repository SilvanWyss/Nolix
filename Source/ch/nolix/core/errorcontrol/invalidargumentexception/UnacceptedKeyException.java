package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrolapi.exceptionargumentboxapi.ArgumentNameDto;
import ch.nolix.coreapi.errorcontrolapi.exceptionargumentboxapi.ErrorPredicateDto;

/**
 * A {@link UnacceptedKeyException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when a
 * given key is not accepted.
 * 
 * @author Silvan Wyss
 * @version 2020-12-18
 */
@SuppressWarnings("serial")
public final class UnacceptedKeyException extends AbstractInvalidArgumentException {

  private static final String ARGUMENT_NAME = "key";

  private static final String ERROR_PREDICATE = "is not accepted";

  /**
   * Creates a new {@link UnacceptedKeyException} for the given key.
   * 
   * @param key - Can be null.
   */
  private UnacceptedKeyException(final String key) {
    super(key, new ArgumentNameDto(ARGUMENT_NAME), new ErrorPredicateDto(ERROR_PREDICATE));
  }

  /**
   * @param key - Can be null.
   * @return a new {@link UnacceptedKeyException} for the given key.
   */
  public static UnacceptedKeyException forKey(final String key) {
    return new UnacceptedKeyException(key);
  }
}
