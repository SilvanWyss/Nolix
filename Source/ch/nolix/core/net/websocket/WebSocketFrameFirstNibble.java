//package declaration
package ch.nolix.core.net.websocket;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.unsignedbyte.UnsignedByte;

//class
final class WebSocketFrameFirstNibble {

  //attribute
  private final boolean mFINBit;

  //attribute
  private final int opcode;

  //attribute
  private final boolean maskBit;

  //attribute
  private final WebSocketFramePayloadLengthType payloadLengthSpecification;

  //attribute
  private final int m7BitPayloadLength;

  //constructor
  public WebSocketFrameFirstNibble(
    final boolean mFINBit,
    final WebSocketFrameOpcodeMeaning opcodeMeaning,
    final boolean maskBit,
    final int payloadLength) {

    GlobalValidator.assertThat(opcodeMeaning).thatIsNamed("opcode meaning").isNotNull();

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

  //constructor
  public WebSocketFrameFirstNibble(final byte byte1, final byte byte2) {

    final var wrapperByte1 = new UnsignedByte(byte1);
    final var wrapperByte2 = new UnsignedByte(byte2);

    final var RSV1Bit = wrapperByte1.getBitAt(2);
    final var RSV2Bit = wrapperByte1.getBitAt(3);
    final var RSV3Bit = wrapperByte1.getBitAt(4);

    GlobalValidator.assertThatTheBit(RSV1Bit).thatIsNamed("RSV1Bit").isCleared();
    GlobalValidator.assertThatTheBit(RSV2Bit).thatIsNamed("RSV2Bit").isCleared();
    GlobalValidator.assertThatTheBit(RSV3Bit).thatIsNamed("RSV3Bit").isCleared();

    mFINBit = wrapperByte1.getBitAt(1);
    opcode = byte1 & 0b1111;
    maskBit = wrapperByte2.getBitAt(1);
    payloadLengthSpecification = WebSocketFramePayloadLengthType.fromCode(byte2 & 0b01111111);
    m7BitPayloadLength = byte2 & 0x7F;
  }

  //static method
  public static WebSocketFrameFirstNibble fromNibble(final byte[] nibble) {

    GlobalValidator.assertThat(nibble).hasElementCount(2);

    return new WebSocketFrameFirstNibble(nibble[0], nibble[1]);
  }

  //method
  public int get7BitsPayloadLength() {
    return m7BitPayloadLength;
  }

  //method
  public byte getByte1() {

    var byte1 = 0;

    if (getFINBit()) {
      byte1 |= 0b10000000;
    }

    byte1 |= opcode;

    return (byte) byte1;
  }

  //method
  public byte getByte2() {

    var byte2 = 0;

    if (getMaskBit()) {
      byte2 |= 0b1000000;
    }

    switch (getPayloadLengthType()) {
      case BITS_7:
        byte2 |= m7BitPayloadLength;
        break;
      case BITS_16:
        byte2 |= 0b1111110;
        break;
      case BITS_64:
        byte2 |= 0b1111111;
        break;
    }

    return (byte) byte2;
  }

  //method
  public boolean getFINBit() { //NOSONAR: This method returns a bit as a boolean.
    return mFINBit;
  }

  //method
  public boolean getMaskBit() { //NOSONAR: This method returns a bit as a boolean.
    return maskBit;
  }

  //method
  public int getOpcode() {
    return opcode;
  }

  //method
  public WebSocketFrameOpcodeMeaning getOpcodeMeaning() {
    return WebSocketFrameOpcodeMeaning.fromNumber(opcode);
  }

  //method
  public WebSocketFramePayloadLengthType getPayloadLengthType() {
    return payloadLengthSpecification;
  }

  //method
  public byte[] toBytes() {
    return new byte[] { getByte1(), getByte2() };
  }
}
