/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.net.websocket;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Silvan Wyss
 */
public final class WebSocketFrameHelper {
  private WebSocketFrameHelper() {
  }

  public static WebSocketFramePayloadLength calculatePayloadLengthWhenPayloadLengthIs16Bits(
    final InputStream inputStream)
  throws IOException {
    final var headerNext2Bytes = inputStream.readNBytes(2);

    return new WebSocketFramePayloadLength(
      (0x100L * (headerNext2Bytes[0] & 0b11111111))
      + (headerNext2Bytes[1] & 0b11111111));
  }

  public static WebSocketFramePayloadLength calculatePayloadLengthWhenPayloadLengthIs64Bits(
    final InputStream inputStream)
  throws IOException {
    final var headerNext8Bytes = inputStream.readNBytes(8);

    return new WebSocketFramePayloadLength(
      (0x100_000_000_000_000L * (headerNext8Bytes[0] & 0b11111111))
      + (0x1_000_000_000_000L * (headerNext8Bytes[1] & 0b11111111))
      + (0x10_000_000_000L * (headerNext8Bytes[2] & 0b11111111))
      + (0x100_000_000L * (headerNext8Bytes[3] & 0b11111111))
      + (0x1_000_000L * (headerNext8Bytes[4] & 0b11111111))
      + (0x10_000L * (headerNext8Bytes[5] & 0b11111111))
      + (0x100L * (headerNext8Bytes[6] & 0b11111111))
      + (headerNext8Bytes[7] & 0b11111111));
  }

  public static void writePayloadLengthIntoBytesWhenPayloadLengthTypeIsBits64(
    final byte[] bytes,
    final byte[] payloadLengthBytes) {
    bytes[2] = payloadLengthBytes[0];
    bytes[3] = payloadLengthBytes[1];
    bytes[4] = payloadLengthBytes[2];
    bytes[5] = payloadLengthBytes[3];
    bytes[6] = payloadLengthBytes[4];
    bytes[7] = payloadLengthBytes[5];
    bytes[8] = payloadLengthBytes[6];
    bytes[9] = payloadLengthBytes[7];
  }
}
