/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.websocket;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datamodel.dataobject.UnsignedByte;
import ch.nolix.base.net.websocket.WebSocketFramePayloadLength;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.net.websocket.WebSocketFramePayloadLengthType;

/**
 * @author Silvan Wyss
 */
final class WebSocketFramePayloadLengthTest extends StandardTest {
  @Test
  void testCase_getType_whenPayloadLengthIs0() {
    // setup
    final var testUnit = new WebSocketFramePayloadLength(0);

    // execute
    final var result = testUnit.getType();

    // verify
    expect(result).is(WebSocketFramePayloadLengthType.BITS_7);
  }

  @Test
  void testCase_getType_whenPayloadLengthIs125() {
    // setup
    final var testUnit = new WebSocketFramePayloadLength(125);

    // execute
    final var result = testUnit.getType();

    // verify
    expect(result).is(WebSocketFramePayloadLengthType.BITS_7);
  }

  @Test
  void testCase_getType_whenPayloadLengthIs126() {
    // setup
    final var testUnit = new WebSocketFramePayloadLength(126);

    // execute
    final var result = testUnit.getType();

    // verify
    expect(result).is(WebSocketFramePayloadLengthType.BITS_16);
  }

  @Test
  void testCase_getType_whenPayloadLengthIs65535() {
    // setup
    final var testUnit = new WebSocketFramePayloadLength(65535);

    // execute
    final var result = testUnit.getType();

    // verify
    expect(result).is(WebSocketFramePayloadLengthType.BITS_16);
  }

  @Test
  void testCase_getType_whenPayloadLengthIs65536() {
    // setup
    final var testUnit = new WebSocketFramePayloadLength(65536);

    // execute
    final var result = testUnit.getType();

    // verify
    expect(result).is(WebSocketFramePayloadLengthType.BITS_64);
  }

  @Test
  void testCase_getType_whenPayloadLengthIs9223372036854775807() {
    // setup
    final var testUnit = new WebSocketFramePayloadLength(9_223_372_036_854_775_807L);

    // execute
    final var result = testUnit.getType();

    // verify
    expect(result).is(WebSocketFramePayloadLengthType.BITS_64);
  }

  @Test
  void testCase_toBytes_whenPayloadLengthIs125() {
    // setup
    final var testUnit = new WebSocketFramePayloadLength(125);

    // execute
    final var result = testUnit.toBytes();

    // verify
    expect(result.length).isEqualTo(1);
    expect(UnsignedByte.fromByte(result[0]).toBitString()).isEqualTo("01111101");
  }

  @Test
  void testCase_toBytes_whenPayloadLengthIs126() {
    // setup
    final var testUnit = new WebSocketFramePayloadLength(126);

    // execute
    final var result = testUnit.toBytes();

    // verify
    expect(result.length).isEqualTo(2);
    expect(UnsignedByte.fromByte(result[0]).toBitString()).isEqualTo("00000000");
    expect(UnsignedByte.fromByte(result[1]).toBitString()).isEqualTo("01111110");
  }

  @Test
  void testCase_toBytes_whenPayloadLengthIs65535() {
    // setup
    final var testUnit = new WebSocketFramePayloadLength(65535);

    // execute
    final var result = testUnit.toBytes();

    // verify
    expect(result.length).isEqualTo(2);
    expect(UnsignedByte.fromByte(result[0]).toBitString()).isEqualTo("11111111");
    expect(UnsignedByte.fromByte(result[1]).toBitString()).isEqualTo("11111111");
  }

  @Test
  void testCase_toBytes_whenPayloadLengthIs65536() {
    // setup
    final var testUnit = new WebSocketFramePayloadLength(65536);

    // execute
    final var result = testUnit.toBytes();

    // verify
    expect(result.length).isEqualTo(8);
    expect(UnsignedByte.fromByte(result[0]).toBitString()).isEqualTo("00000000");
    expect(UnsignedByte.fromByte(result[1]).toBitString()).isEqualTo("00000000");
    expect(UnsignedByte.fromByte(result[2]).toBitString()).isEqualTo("00000000");
    expect(UnsignedByte.fromByte(result[3]).toBitString()).isEqualTo("00000000");
    expect(UnsignedByte.fromByte(result[4]).toBitString()).isEqualTo("00000000");
    expect(UnsignedByte.fromByte(result[5]).toBitString()).isEqualTo("00000001");
    expect(UnsignedByte.fromByte(result[6]).toBitString()).isEqualTo("00000000");
    expect(UnsignedByte.fromByte(result[7]).toBitString()).isEqualTo("00000000");
  }

  @Test
  void testCase_toBytes_whenPayloadLengthIs9223372036854775807() {
    // setup
    final var testUnit = new WebSocketFramePayloadLength(9_223_372_036_854_775_807L);

    // execute
    final var result = testUnit.toBytes();

    // verify
    expect(result.length).isEqualTo(8);
    expect(UnsignedByte.fromByte(result[0]).toBitString()).isEqualTo("01111111");
    expect(UnsignedByte.fromByte(result[1]).toBitString()).isEqualTo("11111111");
    expect(UnsignedByte.fromByte(result[2]).toBitString()).isEqualTo("11111111");
    expect(UnsignedByte.fromByte(result[3]).toBitString()).isEqualTo("11111111");
    expect(UnsignedByte.fromByte(result[4]).toBitString()).isEqualTo("11111111");
    expect(UnsignedByte.fromByte(result[5]).toBitString()).isEqualTo("11111111");
    expect(UnsignedByte.fromByte(result[6]).toBitString()).isEqualTo("11111111");
    expect(UnsignedByte.fromByte(result[7]).toBitString()).isEqualTo("11111111");
  }
}
