package ch.nolix.coreapi.netapi.endpoint2protocol;

import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

/**
 * @author Silvan Wyss
 * @version 2016-10-01
 */
public enum MessageRole {
  TARGET_APPLICATION_MESSAGE(MessageRolePrefixCatalogue.TARGET_APPLICATION_PREFIX),
  RESPONSE_EXPECTING_MESSAGE(MessageRolePrefixCatalogue.RESPONSE_EXPECTING_MESSAGE_PREFIX),
  SUCCESS_RESPONSE(MessageRolePrefixCatalogue.SUCCESS_RESPONSE_PREFIX),
  ERROR_RESPONSE(MessageRolePrefixCatalogue.ERROR_RESPONSE_PREFIX);

  private final char prefix;

  /**
   * Creates a new {@link MessageRole} with the given prefix.
   * 
   * @param prefix
   */
  MessageRole(final char prefix) {
    this.prefix = prefix;
  }

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

  /**
   * @param prefix
   * @return a new {@link UnrepresentingArgumentException} for the given prefix
   *         that does not represent a {@link MessageRole}.
   */
  private static UnrepresentingArgumentException createUnrepresentingArgumentExceptionForPrefix(final char prefix) {
    return UnrepresentingArgumentException.forArgumentNameAndArgumentAndType(
      LowerCaseVariableCatalogue.PREFIX,
      prefix,
      MessageRole.class);
  }

  /**
   * @return the prefix of the current {@link MessageRole}.
   */
  public char getPrefix() {
    return prefix;
  }
}
