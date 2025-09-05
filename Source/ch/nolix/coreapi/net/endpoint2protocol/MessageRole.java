package ch.nolix.coreapi.net.endpoint2protocol;

/**
 * @author Silvan Wyss
 * @version 2016-10-01
 */
public enum MessageRole {
  TARGET_APPLICATION_MESSAGE(MessageRolePrefixCatalog.TARGET_APPLICATION_PREFIX),
  RESPONSE_EXPECTING_MESSAGE(MessageRolePrefixCatalog.RESPONSE_EXPECTING_MESSAGE_PREFIX),
  SUCCESS_RESPONSE(MessageRolePrefixCatalog.SUCCESS_RESPONSE_PREFIX),
  ERROR_RESPONSE(MessageRolePrefixCatalog.ERROR_RESPONSE_PREFIX);

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
   * @throws IllegalArgumentException if the given prefix does not represent a
   *                                  {@link MessageRole}.
   */
  public static MessageRole fromPrefix(final char prefix) {
    //Enumerates the given prefix.
    return switch (prefix) {
      case MessageRolePrefixCatalog.TARGET_APPLICATION_PREFIX ->
        TARGET_APPLICATION_MESSAGE;
      case MessageRolePrefixCatalog.RESPONSE_EXPECTING_MESSAGE_PREFIX ->
        RESPONSE_EXPECTING_MESSAGE;
      case MessageRolePrefixCatalog.SUCCESS_RESPONSE_PREFIX ->
        SUCCESS_RESPONSE;
      case MessageRolePrefixCatalog.ERROR_RESPONSE_PREFIX ->
        ERROR_RESPONSE;
      default ->
        throw new IllegalArgumentException("The given prefix '" + prefix + "' is not valid.");
    };
  }

  /**
   * @return the prefix of the current {@link MessageRole}.
   */
  public char getPrefix() {
    return prefix;
  }
}
