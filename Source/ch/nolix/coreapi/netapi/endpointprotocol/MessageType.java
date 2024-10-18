package ch.nolix.coreapi.netapi.endpointprotocol;

public enum MessageType {
  TARGET_MESSAGE(MessagePrefixCatalogue.TARGET_MESSAGE),
  DEFAULT_TARGET_MESSAGE(MessagePrefixCatalogue.DEFAULT_TARGET_MESSAGE),
  CONTENT_MESSAGE(MessagePrefixCatalogue.CONTENT_MESSAGE),
  CLOSE_MESSAGE(MessagePrefixCatalogue.CLOSE_MESSAGE);

  private final String prefix;

  MessageType(final String prefix) {
    this.prefix = prefix;
  }

  public static MessageType forPrefix(final String prefix) {
    return switch (prefix) {
      case MessagePrefixCatalogue.TARGET_MESSAGE ->
        TARGET_MESSAGE;
      case MessagePrefixCatalogue.DEFAULT_TARGET_MESSAGE ->
        DEFAULT_TARGET_MESSAGE;
      case MessagePrefixCatalogue.CONTENT_MESSAGE ->
        CONTENT_MESSAGE;
      case MessagePrefixCatalogue.CLOSE_MESSAGE ->
        CLOSE_MESSAGE;
      default ->
        throw new IllegalArgumentException("The given prefix '" + prefix + "' is not valid.");
    };
  }

  public String getPrefix() {
    return prefix;
  }
}
