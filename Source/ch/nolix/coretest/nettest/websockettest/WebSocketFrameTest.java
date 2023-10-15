//package declaration
package ch.nolix.coretest.nettest.websockettest;

//Java imports
import java.io.IOException;
import java.io.InputStream;

//own imports
import ch.nolix.core.commontype.commontypewrapper.ByteWrapper;
import ch.nolix.core.net.websocket.WebSocketFrame;
import ch.nolix.core.net.websocket.WebSocketFrameOpcodeMeaning;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class WebSocketFrameTest extends Test {

  // method
  @TestCase
  public void testCase_constructor_whenFinalBitIs1_andOpcodeMeaningIsTextFrame_andMaskBitIs0_andPayloadIs4Bytes() {

    // setup
    final var bytes = new byte[] {
        new ByteWrapper(1, 0, 0, 0, 0, 0, 0, 1).toByte(),
        new ByteWrapper(0, 0, 0, 0, 0, 0, 1, 0).toByte(),
        new ByteWrapper(0, 0, 0, 1, 0, 0, 0, 0).toByte(),
        new ByteWrapper(0, 0, 1, 0, 0, 0, 0, 0).toByte(),
    };

    // setup
    final var webSocketFrame = new WebSocketFrame(
        new InputStream() {

          private int counter;

          @Override
          public int read() throws IOException {

            // The mask 0xFF makes a byte unsigned.
            final var lByte = 0xFF & bytes[counter];

            counter++;

            return lByte;
          }
        });

    // execution
    final var resultFINBit = webSocketFrame.getFINBit();
    final var resultMaskBit = webSocketFrame.getMaskBit();
    final var resultOpcode = webSocketFrame.getOpcodeMeaning();
    final var resultPayload = webSocketFrame.getPayload();

    // verification
    expect(resultFINBit);
    expectNot(resultMaskBit);
    expect(resultOpcode).isEqualTo(WebSocketFrameOpcodeMeaning.TEXT_FRAME);
    expect(resultPayload.length).isEqualTo(2);
    expect(new ByteWrapper(resultPayload[0]).toBitString()).isEqualTo("00010000");
    expect(new ByteWrapper(resultPayload[1]).toBitString()).isEqualTo("00100000");
  }

  // method
  @TestCase
  public void testCase_toBytes_whenFinalBitIs0_andOpcodeMeaningIsTextFrame_andMaskBitIs0_andPayloadIsEmpty() {

    // setup
    final var testUnit = new WebSocketFrame(false, WebSocketFrameOpcodeMeaning.TEXT_FRAME, false, new byte[] {});

    // execution
    final var result = testUnit.toBytes();

    // verification
    expect(result.length).isEqualTo(2);
    expect(new ByteWrapper(result[0]).toBitString()).isEqualTo("00000001");
    expect(new ByteWrapper(result[1]).toBitString()).isEqualTo("00000000");
  }

  // method
  @TestCase
  public void testCase_toBytes_whenFinalBitIs1_andOpcodeMeaningIsTextFrame_andMaskBitIs0_andPayloadIsEmpty() {

    // setup
    final var testUnit = new WebSocketFrame(true, WebSocketFrameOpcodeMeaning.TEXT_FRAME, false, new byte[] {});

    // execution
    final var result = testUnit.toBytes();

    // verification
    expect(result.length).isEqualTo(2);
    expect(new ByteWrapper(result[0]).toBitString()).isEqualTo("10000001");
    expect(new ByteWrapper(result[1]).toBitString()).isEqualTo("00000000");
  }

  // method
  @TestCase
  public void testCase_toBytes_whenFinalBitIs1_andOpcodeMeaningIsTextFrame_andMaskBitIs0_andPayloadIs4Bytes() {

    // setup
    final var testUnit = new WebSocketFrame(
        true,
        WebSocketFrameOpcodeMeaning.TEXT_FRAME, false,
        new byte[] { 0b00000001, 0b00000010, 0b00000011, 0b00000100 });

    // execution
    final var result = testUnit.toBytes();

    // verification
    expect(result.length).isEqualTo(6);
    expect(new ByteWrapper(result[0]).toBitString()).isEqualTo("10000001");
    expect(new ByteWrapper(result[1]).toBitString()).isEqualTo("00000100");
    expect(new ByteWrapper(result[2]).toBitString()).isEqualTo("00000001");
    expect(new ByteWrapper(result[3]).toBitString()).isEqualTo("00000010");
    expect(new ByteWrapper(result[4]).toBitString()).isEqualTo("00000011");
    expect(new ByteWrapper(result[5]).toBitString()).isEqualTo("00000100");
  }

  // method
  @TestCase
  public void testCase_toBytes_whenFinalBitIs1_andOpcodeMeaningIsTextFrame_andMaskBitIs0_andPayloadIs65535Bytes() {

    // setup
    final var payload = new byte[65535];
    final var lByte = new ByteWrapper(1, 0, 1, 0, 1, 1, 0, 0).toByte();
    for (var i = 0; i < payload.length; i++) {
      payload[i] = lByte;
    }

    // setup
    final var testUnit = new WebSocketFrame(true, WebSocketFrameOpcodeMeaning.TEXT_FRAME, false, payload);

    // execution
    final var result = testUnit.toBytes();

    // verification
    expect(result.length).isEqualTo(65539);
    expect(new ByteWrapper(result[0]).toBitString()).isEqualTo("10000001");
    expect(new ByteWrapper(result[1]).toBitString()).isEqualTo("01111110");
    expect(new ByteWrapper(result[2]).toBitString()).isEqualTo("11111111");
    expect(new ByteWrapper(result[3]).toBitString()).isEqualTo("11111111");
    for (var i = 4; i < 65539; i++) {
      expect(result[i]).isEqualTo(lByte);
    }
  }

  // method
  @TestCase
  public void testCase_toBytes_whenFinalBitIs1_andOpcodeMeaningIsTextFrame_andMaskBitIs0_andPayloadIs65536Bytes() {

    // setup
    final var payload = new byte[65536];
    final var lByte = new ByteWrapper(1, 0, 1, 0, 1, 1, 0, 0).toByte();
    for (var i = 0; i < payload.length; i++) {
      payload[i] = lByte;
    }

    // setup
    final var testUnit = new WebSocketFrame(true, WebSocketFrameOpcodeMeaning.TEXT_FRAME, false, payload);

    // execution
    final var result = testUnit.toBytes();

    // verification
    expect(result.length).isEqualTo(65546);
    expect(new ByteWrapper(result[0]).toBitString()).isEqualTo("10000001");
    expect(new ByteWrapper(result[1]).toBitString()).isEqualTo("01111111");
    expect(new ByteWrapper(result[2]).toBitString()).isEqualTo("00000000");
    expect(new ByteWrapper(result[3]).toBitString()).isEqualTo("00000000");
    expect(new ByteWrapper(result[4]).toBitString()).isEqualTo("00000000");
    expect(new ByteWrapper(result[5]).toBitString()).isEqualTo("00000000");
    expect(new ByteWrapper(result[6]).toBitString()).isEqualTo("00000000");
    expect(new ByteWrapper(result[7]).toBitString()).isEqualTo("00000001");
    expect(new ByteWrapper(result[8]).toBitString()).isEqualTo("00000000");
    expect(new ByteWrapper(result[9]).toBitString()).isEqualTo("00000000");
    for (var i = 10; i < 65546; i++) {
      expect(result[i]).isEqualTo(lByte);
    }
  }

  // method
  @TestCase
  public void testCase_toBytes_whenFinalBitIs1_andOpcodeMeaningIsTextFrame_andMaskBitIs0_andPayloadIs1000000Bytes() {

    // setup
    final var payload = new byte[1_000_000];
    final var lByte = new ByteWrapper(1, 0, 1, 0, 1, 1, 0, 0).toByte();
    for (var i = 0; i < payload.length; i++) {
      payload[i] = lByte;
    }

    // setup
    final var testUnit = new WebSocketFrame(true, WebSocketFrameOpcodeMeaning.TEXT_FRAME, false, payload);

    // execution
    final var result = testUnit.toBytes();

    // verification
    expect(result.length).isEqualTo(1_000_010);
    expect(new ByteWrapper(result[0]).toBitString()).isEqualTo("10000001");
    expect(new ByteWrapper(result[1]).toBitString()).isEqualTo("01111111");
    expect(new ByteWrapper(result[2]).toBitString()).isEqualTo("00000000");
    expect(new ByteWrapper(result[3]).toBitString()).isEqualTo("00000000");
    expect(new ByteWrapper(result[4]).toBitString()).isEqualTo("00000000");
    expect(new ByteWrapper(result[5]).toBitString()).isEqualTo("00000000");
    expect(new ByteWrapper(result[6]).toBitString()).isEqualTo("00000000");
    expect(new ByteWrapper(result[7]).toBitString()).isEqualTo("00001111");
    expect(new ByteWrapper(result[8]).toBitString()).isEqualTo("01000010");
    expect(new ByteWrapper(result[9]).toBitString()).isEqualTo("01000000");
    for (var i = 10; i < 1_000_010; i++) {
      expect(result[i]).isEqualTo(lByte);
    }
  }
}
