//package declaration
package ch.nolix.coreapi.netapi.endpointprotocol;

//enum
public enum MessageType {
  TARGET_MESSAGE(MessagePrefixCatalogue.TARGET_MESSAGE),
  DEFAULT_TARGET_MESSAGE(MessagePrefixCatalogue.DEFAULT_TARGET_MESSAGE),
  CONTENT_MESSAGE(MessagePrefixCatalogue.CONTENT_MESSAGE),
  CLOSE_MESSAGE(MessagePrefixCatalogue.CLOSE_MESSAGE);

  //attribute
  private final String prefix;

  //constructor
  MessageType(final String prefix) {
    this.prefix = prefix;
  }

  //static method
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

  //method
  public String getPrefix() {
    return prefix;
  }
}
