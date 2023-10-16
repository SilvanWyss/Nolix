//package declaration
package ch.nolix.core.errorcontrol.invalidargumentexception;

//class
/**
 * A {@link UnacceptedKeyException} is a {@link InvalidArgumentException} that
 * is supposed to be thrown when a given key is not accepted.
 * 
 * @author Silvan Wyss
 * @date 2020-12-18
 */
@SuppressWarnings("serial")
public final class UnacceptedKeyException extends InvalidArgumentException {

  //constant
  private static final String ARGUMENT_NAME = "key";

  //constant
  private static final String ERROR_PREDICATE = "is not accepted";

  //static method
  /**
   * @param key
   * @return a new {@link UnacceptedKeyException} for the given key.
   */
  public static UnacceptedKeyException forKey(final String key) {
    return new UnacceptedKeyException(key);
  }

  //constructor
  /**
   * Creates a new {@link UnacceptedKeyException} for the given key.
   * 
   * @param key
   */
  private UnacceptedKeyException(final String key) {

    //Calls constructor of the base class.
    super(ARGUMENT_NAME, key, ERROR_PREDICATE);
  }
}
