package ch.nolix.core.net.websocket;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.misc.dataobject.UnsignedByte;
import ch.nolix.coreapi.net.websocket.WebSocketFrameOpcodeMeaning;
import ch.nolix.coreapi.net.websocket.WebSocketFramePayloadLengthType;

final class WebSocketFrameFirstNibble {
  private final boolean mFINBit;

  private final int opcode;

  private final boolean maskBit;

  private final WebSocketFramePayloadLengthType payloadLengthSpecification;

  private final int m7BitPayloadLength;

  public WebSocketFrameFirstNibble(
    final boolean mFINBit,
    final WebSocketFrameOpcodeMeaning opcodeMeaning,
    final boolean maskBit,
    final int payloadLength) {
    Validator.assertThat(opcodeMeaning).thatIsNamed("opcode meaning").isNotNull();

    this.mFINBit = mFINBit;
    this.opcode = opcodeMeaning.toNumber();
    this.maskBit = maskBit;
    payloadLengthSpecification = WebSocketFramePayloadLengthType.fromPayloadLength(payloadLength);

    if (payloadLengthSpecification == WebSocketFramePayloadLengthType.BITS_7) {
      m7BitPayloadLength = payloadLength;
    } else {
      m7BitPayloadLength = 0;
    }
  }

  public WebSocketFrameFirstNibble(final byte byte1, final byte byte2) {
    final var wrapperByte1 = new UnsignedByte(byte1);
    final var wrapperByte2 = new UnsignedByte(byte2);

    final var rsv1Bit = wrapperByte1.getBitAt(2);
    final var rsv2Bit = wrapperByte1.getBitAt(3);
    final var rsv3Bit = wrapperByte1.getBitAt(4);

    Validator.assertThatTheBit(rsv1Bit).thatIsNamed("RSV1Bit").isCleared();
    Validator.assertThatTheBit(rsv2Bit).thatIsNamed("RSV2Bit").isCleared();
    Validator.assertThatTheBit(rsv3Bit).thatIsNamed("RSV3Bit").isCleared();

    mFINBit = wrapperByte1.getBitAt(1);
    opcode = byte1 & 0b1111;
    maskBit = wrapperByte2.getBitAt(1);
    payloadLengthSpecification = WebSocketFramePayloadLengthType.fromCode(byte2 & 0b01111111);
    m7BitPayloadLength = byte2 & 0x7F;
  }

  public static WebSocketFrameFirstNibble fromNibble(final byte[] nibble) {
    Validator.assertThat(nibble).hasElementCount(2);

    return new WebSocketFrameFirstNibble(nibble[0], nibble[1]);
  }

  public int get7BitsPayloadLength() {
    return m7BitPayloadLength;
  }

  public byte getByte1() {
    var byte1 = 0;

    if (getFINBit()) {
      byte1 |= 0b10000000;
    }

    byte1 |= opcode;

    return (byte) byte1;
  }

  public byte getByte2() {
    var byte2 = 0;

    if (getMaskBit()) {
      byte2 |= 0b1000000;
    }

    final var payloadLengthType = getPayloadLengthType();

    switch (payloadLengthType) {
      case BITS_7:
        byte2 |= m7BitPayloadLength;
        break;
      case BITS_16:
        byte2 |= 0b1111110;
        break;
      case BITS_64:
        byte2 |= 0b1111111;
        break;
      default:
        throw InvalidArgumentException.forArgument(payloadLengthType);
    }

    return (byte) byte2;
  }

  public boolean getFINBit() { //NOSONAR: This method returns a bit as a boolean.
    return mFINBit;
  }

  public boolean getMaskBit() { //NOSONAR: This method returns a bit as a boolean.
    return maskBit;
  }

  public int getOpcode() {
    return opcode;
  }

  public WebSocketFrameOpcodeMeaning getOpcodeMeaning() {
    return WebSocketFrameOpcodeMeaning.fromNumber(opcode);
  }

  public WebSocketFramePayloadLengthType getPayloadLengthType() {
    return payloadLengthSpecification;
  }

  public byte[] toBytes() {
    return new byte[] { getByte1(), getByte2() };
  }
}
