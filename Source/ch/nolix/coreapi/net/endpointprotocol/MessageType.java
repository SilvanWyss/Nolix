package ch.nolix.coreapi.net.endpointprotocol;

/**
 * @author Silvan Wyss
 */
public enum MessageType {
  TARGET_MESSAGE(MessagePrefixCatalog.TARGET_MESSAGE),
  DEFAULT_TARGET_MESSAGE(MessagePrefixCatalog.DEFAULT_TARGET_MESSAGE),
  CONTENT_MESSAGE(MessagePrefixCatalog.CONTENT_MESSAGE),
  CLOSE_MESSAGE(MessagePrefixCatalog.CLOSE_MESSAGE);

  private final String prefix;

  MessageType(final String prefix) {
    this.prefix = prefix;
  }

  public static MessageType forPrefix(final String prefix) {
    return switch (prefix) {
      case MessagePrefixCatalog.TARGET_MESSAGE ->
        TARGET_MESSAGE;
      case MessagePrefixCatalog.DEFAULT_TARGET_MESSAGE ->
        DEFAULT_TARGET_MESSAGE;
      case MessagePrefixCatalog.CONTENT_MESSAGE ->
        CONTENT_MESSAGE;
      case MessagePrefixCatalog.CLOSE_MESSAGE ->
        CLOSE_MESSAGE;
      default ->
        throw new IllegalArgumentException("The given prefix '" + prefix + "' is not valid.");
    };
  }

  public String getPrefix() {
    return prefix;
  }
}
