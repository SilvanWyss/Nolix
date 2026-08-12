/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.websocket;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import ch.nolix.base.net.websocket.UnsignedByte;
import ch.nolix.base.net.websocket.WebSocketFrame;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.net.websocket.WebSocketFrameOpcodeMeaning;

/**
 * @author Silvan Wyss
 */
final class WebSocketFrameTest extends StandardTest {
  @Test
  void testCase_constructor_whenFinalBitIs1_andOpcodeMeaningIsTextFrame_andMaskBitIs0_andPayloadIs4Bytes() {
    // setup
    final var bytes = //
    new byte[] {
    UnsignedByte.withBits(1, 0, 0, 0, 0, 0, 0, 1).toByte(),
    UnsignedByte.withBits(0, 0, 0, 0, 0, 0, 1, 0).toByte(),
    UnsignedByte.withBits(0, 0, 0, 1, 0, 0, 0, 0).toByte(),
    UnsignedByte.withBits(0, 0, 1, 0, 0, 0, 0, 0).toByte(),
    };

    // setup
    final var inputStream = new InputStream() {
      private int counter;

      @Override
      public int read() throws IOException {
        // The mask 0xFF makes a byte unsigned.
        final var lByte = 0xFF & bytes[counter];

        counter++;

        return lByte;
      }
    };
    final var webSocketFrame = WebSocketFrame.fromInputStream(inputStream);

    // execute
    final var resultFINBit = webSocketFrame.getFINBit();
    final var resultMaskBit = webSocketFrame.getMaskBit();
    final var resultOpcode = webSocketFrame.getOpcodeMeaning();
    final var resultPayload = webSocketFrame.getPayload();

    // verify
    expect(resultFINBit).isTrue();
    expect(resultMaskBit).isFalse();
    expect(resultOpcode).isEqualTo(WebSocketFrameOpcodeMeaning.TEXT_FRAME);
    expect(resultPayload.length).isEqualTo(2);
    expect(UnsignedByte.fromByte(resultPayload[0])).hasStringRepresentation("00010000");
    expect(UnsignedByte.fromByte(resultPayload[1])).hasStringRepresentation("00100000");
  }

  @Test
  void testCase_toBytes_whenFinalBitIs0_andOpcodeMeaningIsTextFrame_andMaskBitIs0_andPayloadIsEmpty() {
    // setup
    final var testUnit = //
    WebSocketFrame.withFinBitAndOpCodeAndMaskBitAndPayload(
      false,
      WebSocketFrameOpcodeMeaning.TEXT_FRAME,
      false,
      new byte[] {});

    // execute
    final var result = testUnit.toBytes();

    // verify
    expect(result.length).isEqualTo(2);
    expect(UnsignedByte.fromByte(result[0])).hasStringRepresentation("00000001");
    expect(UnsignedByte.fromByte(result[1])).hasStringRepresentation("00000000");
  }

  @Test
  void testCase_toBytes_whenFinalBitIs1_andOpcodeMeaningIsTextFrame_andMaskBitIs0_andPayloadIsEmpty() {
    // setup
    final var testUnit = //
    WebSocketFrame.withFinBitAndOpCodeAndMaskBitAndPayload(
      true,
      WebSocketFrameOpcodeMeaning.TEXT_FRAME,
      false,
      new byte[] {});

    // execute
    final var result = testUnit.toBytes();

    // verify
    expect(result.length).isEqualTo(2);
    expect(UnsignedByte.fromByte(result[0])).hasStringRepresentation("10000001");
    expect(UnsignedByte.fromByte(result[1])).hasStringRepresentation("00000000");
  }

  @Test
  void testCase_toBytes_whenFinalBitIs1_andOpcodeMeaningIsTextFrame_andMaskBitIs0_andPayloadIs4Bytes() {
    // setup
    final var testUnit = //
    WebSocketFrame.withFinBitAndOpCodeAndMaskBitAndPayload(
      true,
      WebSocketFrameOpcodeMeaning.TEXT_FRAME,
      false,
      new byte[] { 0b00000001, 0b00000010, 0b00000011, 0b00000100 });

    // execute
    final var result = testUnit.toBytes();

    // verify
    expect(result.length).isEqualTo(6);
    expect(UnsignedByte.fromByte(result[0])).hasStringRepresentation("10000001");
    expect(UnsignedByte.fromByte(result[1])).hasStringRepresentation("00000100");
    expect(UnsignedByte.fromByte(result[2])).hasStringRepresentation("00000001");
    expect(UnsignedByte.fromByte(result[3])).hasStringRepresentation("00000010");
    expect(UnsignedByte.fromByte(result[4])).hasStringRepresentation("00000011");
    expect(UnsignedByte.fromByte(result[5])).hasStringRepresentation("00000100");
  }

  @Test
  void testCase_toBytes_whenFinalBitIs1_andOpcodeMeaningIsTextFrame_andMaskBitIs0_andPayloadIs65535Bytes() {
    // setup
    final var payload = new byte[65535];
    final var lByte = UnsignedByte.withBits(1, 0, 1, 0, 1, 1, 0, 0).toByte();
    for (var i = 0; i < payload.length; i++) {
      payload[i] = lByte;
    }

    // setup
    final var testUnit = //
    WebSocketFrame.withFinBitAndOpCodeAndMaskBitAndPayload(
      true,
      WebSocketFrameOpcodeMeaning.TEXT_FRAME,
      false,
      payload);

    // execute
    final var result = testUnit.toBytes();

    // verify
    expect(result.length).isEqualTo(65539);
    expect(UnsignedByte.fromByte(result[0])).hasStringRepresentation("10000001");
    expect(UnsignedByte.fromByte(result[1])).hasStringRepresentation("01111110");
    expect(UnsignedByte.fromByte(result[2])).hasStringRepresentation("11111111");
    expect(UnsignedByte.fromByte(result[3])).hasStringRepresentation("11111111");
    for (var i = 4; i < 65539; i++) {
      expect(result[i]).isEqualTo(lByte);
    }
  }

  @Test
  void testCase_toBytes_whenFinalBitIs1_andOpcodeMeaningIsTextFrame_andMaskBitIs0_andPayloadIs65536Bytes() {
    // setup
    final var payload = new byte[65536];
    final var lByte = UnsignedByte.withBits(1, 0, 1, 0, 1, 1, 0, 0).toByte();
    for (var i = 0; i < payload.length; i++) {
      payload[i] = lByte;
    }

    // setup
    final var testUnit = //
    WebSocketFrame.withFinBitAndOpCodeAndMaskBitAndPayload(
      true,
      WebSocketFrameOpcodeMeaning.TEXT_FRAME,
      false,
      payload);

    // execute
    final var result = testUnit.toBytes();

    // verify
    expect(result.length).isEqualTo(65546);
    expect(UnsignedByte.fromByte(result[0])).hasStringRepresentation("10000001");
    expect(UnsignedByte.fromByte(result[1])).hasStringRepresentation("01111111");
    expect(UnsignedByte.fromByte(result[2])).hasStringRepresentation("00000000");
    expect(UnsignedByte.fromByte(result[3])).hasStringRepresentation("00000000");
    expect(UnsignedByte.fromByte(result[4])).hasStringRepresentation("00000000");
    expect(UnsignedByte.fromByte(result[5])).hasStringRepresentation("00000000");
    expect(UnsignedByte.fromByte(result[6])).hasStringRepresentation("00000000");
    expect(UnsignedByte.fromByte(result[7])).hasStringRepresentation("00000001");
    expect(UnsignedByte.fromByte(result[8])).hasStringRepresentation("00000000");
    expect(UnsignedByte.fromByte(result[9])).hasStringRepresentation("00000000");
    for (var i = 10; i < 65546; i++) {
      expect(result[i]).isEqualTo(lByte);
    }
  }

  @Test
  void testCase_toBytes_whenFinalBitIs1_andOpcodeMeaningIsTextFrame_andMaskBitIs0_andPayloadIs1000000Bytes() {
    // setup
    final var payload = new byte[1_000_000];
    final var lByte = UnsignedByte.withBits(1, 0, 1, 0, 1, 1, 0, 0).toByte();
    for (var i = 0; i < payload.length; i++) {
      payload[i] = lByte;
    }

    // setup
    final var testUnit = //
    WebSocketFrame.withFinBitAndOpCodeAndMaskBitAndPayload(
      true,
      WebSocketFrameOpcodeMeaning.TEXT_FRAME,
      false,
      payload);

    // execute
    final var result = testUnit.toBytes();

    // verify
    expect(result.length).isEqualTo(1_000_010);
    expect(UnsignedByte.fromByte(result[0])).hasStringRepresentation("10000001");
    expect(UnsignedByte.fromByte(result[1])).hasStringRepresentation("01111111");
    expect(UnsignedByte.fromByte(result[2])).hasStringRepresentation("00000000");
    expect(UnsignedByte.fromByte(result[3])).hasStringRepresentation("00000000");
    expect(UnsignedByte.fromByte(result[4])).hasStringRepresentation("00000000");
    expect(UnsignedByte.fromByte(result[5])).hasStringRepresentation("00000000");
    expect(UnsignedByte.fromByte(result[6])).hasStringRepresentation("00000000");
    expect(UnsignedByte.fromByte(result[7])).hasStringRepresentation("00001111");
    expect(UnsignedByte.fromByte(result[8])).hasStringRepresentation("01000010");
    expect(UnsignedByte.fromByte(result[9])).hasStringRepresentation("01000000");
    for (var i = 10; i < 1_000_010; i++) {
      expect(result[i]).isEqualTo(lByte);
    }
  }
}
