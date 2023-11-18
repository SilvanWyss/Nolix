//package declaration
package ch.nolix.coretest.nettest.websockettest;

import ch.nolix.core.commontype.unsignedbyte.UnsignedByte;
import ch.nolix.core.net.websocket.WebSocketFramePayloadLength;
import ch.nolix.core.net.websocket.WebSocketFramePayloadLengthType;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class WebSocketFramePayloadLengthTest extends Test {

  //method
  @TestCase
  public void testCase_getType_whenPayloadLengthIs0() {

    //setup
    final var testUnit = new WebSocketFramePayloadLength(0);

    //execution
    final var result = testUnit.getType();

    //verification
    expect(result).is(WebSocketFramePayloadLengthType.BITS_7);
  }

  //method
  @TestCase
  public void testCase_getType_whenPayloadLengthIs125() {

    //setup
    final var testUnit = new WebSocketFramePayloadLength(125);

    //execution
    final var result = testUnit.getType();

    //verification
    expect(result).is(WebSocketFramePayloadLengthType.BITS_7);
  }

  //method
  @TestCase
  public void testCase_getType_whenPayloadLengthIs126() {

    //setup
    final var testUnit = new WebSocketFramePayloadLength(126);

    //execution
    final var result = testUnit.getType();

    //verification
    expect(result).is(WebSocketFramePayloadLengthType.BITS_16);
  }

  //method
  @TestCase
  public void testCase_getType_whenPayloadLengthIs65535() {

    //setup
    final var testUnit = new WebSocketFramePayloadLength(65535);

    //execution
    final var result = testUnit.getType();

    //verification
    expect(result).is(WebSocketFramePayloadLengthType.BITS_16);
  }

  //method
  @TestCase
  public void testCase_getType_whenPayloadLengthIs65536() {

    //setup
    final var testUnit = new WebSocketFramePayloadLength(65536);

    //execution
    final var result = testUnit.getType();

    //verification
    expect(result).is(WebSocketFramePayloadLengthType.BITS_64);
  }

  //method
  @TestCase
  public void testCase_getType_whenPayloadLengthIs9223372036854775807() {

    //setup
    final var testUnit = new WebSocketFramePayloadLength(9_223_372_036_854_775_807L);

    //execution
    final var result = testUnit.getType();

    //verification
    expect(result).is(WebSocketFramePayloadLengthType.BITS_64);
  }

  //method
  @TestCase
  public void testCase_toBytes_whenPayloadLengthIs0() {

    //setup
    final var testUnit = new WebSocketFramePayloadLength(0);

    //execution
    final var result = testUnit.toBytes();

    //verification
    expect(result[0]).consistsOfBits("00000000");
  }

  //method
  @TestCase
  public void testCase_toBytes_whenPayloadLengthIs125() {

    //setup
    final var testUnit = new WebSocketFramePayloadLength(125);

    //execution
    final var result = testUnit.toBytes();

    //verification
    expect(result.length).isEqualTo(1);
    expect(new UnsignedByte(result[0]).toBitString()).isEqualTo("01111101");
  }

  //method
  @TestCase
  public void testCase_toBytes_whenPayloadLengthIs126() {

    //setup
    final var testUnit = new WebSocketFramePayloadLength(126);

    //execution
    final var result = testUnit.toBytes();

    //verification
    expect(result.length).isEqualTo(2);
    expect(new UnsignedByte(result[0]).toBitString()).isEqualTo("00000000");
    expect(new UnsignedByte(result[1]).toBitString()).isEqualTo("01111110");
  }

  //method
  @TestCase
  public void testCase_toBytes_whenPayloadLengthIs65535() {

    //setup
    final var testUnit = new WebSocketFramePayloadLength(65535);

    //execution
    final var result = testUnit.toBytes();

    //verification
    expect(result.length).isEqualTo(2);
    expect(new UnsignedByte(result[0]).toBitString()).isEqualTo("11111111");
    expect(new UnsignedByte(result[1]).toBitString()).isEqualTo("11111111");
  }

  //method
  @TestCase
  public void testCase_toBytes_whenPayloadLengthIs65536() {

    //setup
    final var testUnit = new WebSocketFramePayloadLength(65536);

    //execution
    final var result = testUnit.toBytes();

    //verification
    expect(result.length).isEqualTo(8);
    expect(new UnsignedByte(result[0]).toBitString()).isEqualTo("00000000");
    expect(new UnsignedByte(result[1]).toBitString()).isEqualTo("00000000");
    expect(new UnsignedByte(result[2]).toBitString()).isEqualTo("00000000");
    expect(new UnsignedByte(result[3]).toBitString()).isEqualTo("00000000");
    expect(new UnsignedByte(result[4]).toBitString()).isEqualTo("00000000");
    expect(new UnsignedByte(result[5]).toBitString()).isEqualTo("00000001");
    expect(new UnsignedByte(result[6]).toBitString()).isEqualTo("00000000");
    expect(new UnsignedByte(result[7]).toBitString()).isEqualTo("00000000");
  }

  //method
  @TestCase
  public void testCase_toBytes_whenPayloadLengthIs9223372036854775807() {

    //setup
    final var testUnit = new WebSocketFramePayloadLength(9_223_372_036_854_775_807L);

    //execution
    final var result = testUnit.toBytes();

    //verification
    expect(result.length).isEqualTo(8);
    expect(new UnsignedByte(result[0]).toBitString()).isEqualTo("01111111");
    expect(new UnsignedByte(result[1]).toBitString()).isEqualTo("11111111");
    expect(new UnsignedByte(result[2]).toBitString()).isEqualTo("11111111");
    expect(new UnsignedByte(result[3]).toBitString()).isEqualTo("11111111");
    expect(new UnsignedByte(result[4]).toBitString()).isEqualTo("11111111");
    expect(new UnsignedByte(result[5]).toBitString()).isEqualTo("11111111");
    expect(new UnsignedByte(result[6]).toBitString()).isEqualTo("11111111");
    expect(new UnsignedByte(result[7]).toBitString()).isEqualTo("11111111");
  }
}
