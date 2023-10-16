//package declaration
package ch.nolix.core.net.endpoint2;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//enum
/**
 * @author Silvan Wyss
 * @date 2016-10-01
 */
public enum MessageRole {
  TARGET_APPLICATION_MESSAGE(MessageRolePrefixCatalogue.TARGET_APPLICATION_PREFIX),
  RESPONSE_EXPECTING_MESSAGE(MessageRolePrefixCatalogue.RESPONSE_EXPECTING_MESSAGE_PREFIX),
  SUCCESS_RESPONSE(MessageRolePrefixCatalogue.SUCCESS_RESPONSE_PREFIX),
  ERROR_RESPONSE(MessageRolePrefixCatalogue.ERROR_RESPONSE_PREFIX);

  //static method
  /**
   * @param prefix
   * @return a new {@link MessageRole} from the given prefix
   * @throws UnrepresentingArgumentException if the given prefix does not
   *                                         represent a {@link MessageRole}.
   */
  public static MessageRole fromPrefix(final char prefix) {

    //Enumerates the given prefix.
    return switch (prefix) {
      case MessageRolePrefixCatalogue.TARGET_APPLICATION_PREFIX ->
        TARGET_APPLICATION_MESSAGE;
      case MessageRolePrefixCatalogue.RESPONSE_EXPECTING_MESSAGE_PREFIX ->
        RESPONSE_EXPECTING_MESSAGE;
      case MessageRolePrefixCatalogue.SUCCESS_RESPONSE_PREFIX ->
        SUCCESS_RESPONSE;
      case MessageRolePrefixCatalogue.ERROR_RESPONSE_PREFIX ->
        ERROR_RESPONSE;
      default ->
        throw createUnrepresentingArgumentExceptionForPrefix(prefix);
    };
  }

  //static method
  /**
   * @param prefix
   * @return a new {@link UnrepresentingArgumentException} for the given prefix
   *         that does not represent a {@link MessageRole}.
   */
  private static UnrepresentingArgumentException createUnrepresentingArgumentExceptionForPrefix(final char prefix) {
    return UnrepresentingArgumentException.forArgumentNameAndArgumentAndType(
        LowerCaseCatalogue.PREFIX,
        prefix,
        MessageRole.class);
  }

  //attribute
  private final char prefix;

  //constructor
  /**
   * Creates a new {@link MessageRole} with the given prefix.
   * 
   * @param prefix
   */
  MessageRole(final char prefix) {
    this.prefix = prefix;
  }

  //method
  /**
   * @return the prefix of the current {@link MessageRole}.
   */
  public char getPrefix() {
    return prefix;
  }
}
