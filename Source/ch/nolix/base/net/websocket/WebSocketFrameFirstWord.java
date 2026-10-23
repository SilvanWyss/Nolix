/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.websocket;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.net.websocket.WebSocketFrameOpcodeMeaning;
import ch.nolix.baseapi.net.websocket.WebSocketFramePayloadLengthType;

final class WebSocketFrameFirstWord {
  private final boolean mFINBit;

  private final int opcode;

  private final boolean maskBit;

  private final WebSocketFramePayloadLengthType payloadLengthSpecification;

  private final int m7BitPayloadLength;

  private WebSocketFrameFirstWord(
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

  private WebSocketFrameFirstWord(final byte byte1, final byte byte2) {
    final var wrapperByte1 = UnsignedByte.fromByte(byte1);
    final var wrapperByte2 = UnsignedByte.fromByte(byte2);

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

  /**
   * @param nibble
   * @return a new {@link WebSocketFrameFirstWord} from the given nibble
   * @throws RuntimeException if the given nibble does not consist of 2 bytes
   */
  public static WebSocketFrameFirstWord fromBytes(final byte[] nibble) {
    Validator.assertThat(nibble).hasElementCount(2);

    return new WebSocketFrameFirstWord(nibble[0], nibble[1]);
  }

  /**
   * @param mFINBit
   * @param opcodeMeaning
   * @param maskBit
   * @param payloadLength
   * @return a new {@link WebSocketFrameFirstWord} with the given mFINBit,
   *         opcodeMeaning, maskBit and payloadLength
   */
  public static WebSocketFrameFirstWord withFinBitAndOpCodeMeaningAndMaskBitAndPaylaodLength(
    final boolean mFINBit,
    final WebSocketFrameOpcodeMeaning opcodeMeaning,
    final boolean maskBit,
    final int payloadLength) {
    return new WebSocketFrameFirstWord(mFINBit, opcodeMeaning, maskBit, payloadLength);
  }

  /**
   * @return the 7 bits payload length of the current
   *         {@link WebSocketFrameFirstWord}
   */
  public int get7BitsPayloadLength() {
    return m7BitPayloadLength;
  }

  /**
   * @return the first byte of the current {@link WebSocketFrameFirstWord}
   */
  public byte getByte1() {
    var byte1 = 0;

    if (getFINBit()) {
      byte1 |= 0b10000000;
    }

    byte1 |= opcode;

    return (byte) byte1;
  }

  /**
   * @return the second byte of the current {@link WebSocketFrameFirstWord}
   */
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

  /**
   * @return the FIN bit of the current {@link WebSocketFrameFirstWord}
   */
  public boolean getFINBit() { // NOSONAR: This method returns a bit as a boolean.
    return mFINBit;
  }

  /**
   * @return the mask bit of the current {@link WebSocketFrameFirstWord}
   */
  public boolean getMaskBit() { // NOSONAR: This method returns a bit as a boolean.
    return maskBit;
  }

  /**
   * @return the opcode of the current {@link WebSocketFrameFirstWord}
   */
  public int getOpcode() {
    return opcode;
  }

  /**
   * @return the {@link WebSocketFrameOpcodeMeaning} of the current
   *         {@link WebSocketFrameFirstWord}
   */
  public WebSocketFrameOpcodeMeaning getOpcodeMeaning() {
    return WebSocketFrameOpcodeMeaning.fromNumber(opcode);
  }

  /**
   * @return the {@link WebSocketFramePayloadLengthType} of the current
   *         {@link WebSocketFrameFirstWord}
   */
  public WebSocketFramePayloadLengthType getPayloadLengthType() {
    return payloadLengthSpecification;
  }

  /**
   * @return the bytes of the current {@link WebSocketFrameFirstWord}
   */
  public byte[] toBytes() {
    return new byte[] { getByte1(), getByte2() };
  }
}
