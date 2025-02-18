package ch.nolix.coreapi.netapi.websocketapi;

public enum WebSocketFrameOpcodeMeaning {
  CONTINUATION_FRAME,
  TEXT_FRAME,
  BINARY_FRAME,
  CONNECTION_CLOSE,
  PING,
  PONG,
  RESERVED;

  public static WebSocketFrameOpcodeMeaning fromNumber(final int number) {
    return switch (number) {
      case 0x0 ->
        CONTINUATION_FRAME;
      case 0x1 ->
        TEXT_FRAME;
      case 0x2 ->
        BINARY_FRAME;
      case 0x3, 0x4, 0x5, 0x6, 0x7 ->
        RESERVED;
      case 0x8 ->
        CONNECTION_CLOSE;
      case 0x9 ->
        PING;
      case 0xA ->
        PONG;
      case 0xB, 0xC, 0xD, 0xE, 0xF ->
        RESERVED;
      default ->
        throw new IllegalArgumentException(
          "The given number '" + number + "' does not represent a WebSocketFrameOpcodeMeaning.");
    };
  }

  public int toNumber() {
    return switch (this) {
      case CONTINUATION_FRAME ->
        0x0;
      case TEXT_FRAME ->
        0x1;
      case BINARY_FRAME ->
        0x2;
      case CONNECTION_CLOSE ->
        0x8;
      case PING ->
        0x9;
      case PONG ->
        0xA;
      case RESERVED ->
        throw new IllegalArgumentException("The current WebSocketFrameOpcodeMeaning does not represent a number.");
      default ->
        throw new IllegalArgumentException("The current WebSocketFrameOpcodeMeaning does not represent a number.");
    };
  }
}
