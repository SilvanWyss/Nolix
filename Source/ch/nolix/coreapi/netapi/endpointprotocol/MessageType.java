//package declaration
package ch.nolix.coreapi.netapi.endpointprotocol;

//enum
public enum MessageType {
  TARGET_MESSAGE("T"),
  DEFAULT_TARGET_MESSAGE("A"),
  CONTENT_MESSAGE("M"),
  CLOSE_MESSAGE("C");

  //attribute
  private final String prefix;

  //constructor
  MessageType(final String prefix) {
    this.prefix = prefix;
  }

  //static method
  public static MessageType forPrefix(final String prefix) {
    return switch (prefix) {
      case "T" ->
        TARGET_MESSAGE;
      case "A" ->
        DEFAULT_TARGET_MESSAGE;
      case "M" ->
        CONTENT_MESSAGE;
      case "C" ->
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
