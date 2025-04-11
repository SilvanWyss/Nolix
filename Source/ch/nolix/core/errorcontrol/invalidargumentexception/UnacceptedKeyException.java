package ch.nolix.core.errorcontrol.invalidargumentexception;

/**
 * A {@link UnacceptedKeyException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given key is not accepted.
 * 
 * @author Silvan Wyss
 * @version 2020-12-18
 */
@SuppressWarnings("serial")
public final class UnacceptedKeyException extends InvalidArgumentException {

  private static final String ARGUMENT_NAME = "key";

  private static final String ERROR_PREDICATE = "is not accepted";

  /**
   * Creates a new {@link UnacceptedKeyException} for the given key.
   * 
   * @param key - Can be null.
   */
  private UnacceptedKeyException(final String key) {
    super((Object) key, ARGUMENT_NAME, ERROR_PREDICATE);
  }

  /**
   * @param key - Can be null.
   * @return a new {@link UnacceptedKeyException} for the given key.
   */
  public static UnacceptedKeyException forKey(final String key) {
    return new UnacceptedKeyException(key);
  }
}
