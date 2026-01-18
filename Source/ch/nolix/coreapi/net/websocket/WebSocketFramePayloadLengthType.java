/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.net.websocket;

/**
 * @author Silvan Wyss
 */
public enum WebSocketFramePayloadLengthType {
  BITS_7,
  BITS_16,
  BITS_64;

  public static WebSocketFramePayloadLengthType fromCode(final int code) {
    if (code < 0) {
      throw new IllegalArgumentException("The given code '" + code + "' is negative.");
    }

    if (code < 126) {
      return BITS_7;
    }

    if (code == 126) {
      return BITS_16;
    }

    if (code == 127) {
      return BITS_64;
    }

    throw new IllegalArgumentException("The given code '" + code + "' is not valid.");
  }

  public static WebSocketFramePayloadLengthType fromPayloadLength(final long payloadLength) {
    if (payloadLength < 0) {
      throw new IllegalArgumentException("The given payload length '" + payloadLength + "' is negative.");
    }

    if (payloadLength < 126) {
      return BITS_7;
    }

    if (payloadLength < 65536) {
      return BITS_16;
    }

    //payloadLength < 2^63
    return WebSocketFramePayloadLengthType.BITS_64;
  }
}
