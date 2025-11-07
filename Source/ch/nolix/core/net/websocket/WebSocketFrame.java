package ch.nolix.core.net.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import ch.nolix.core.commontypetool.arraytool.ArrayTool;
import ch.nolix.core.errorcontrol.generalexception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnsupportedCaseException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.net.websocket.WebSocketFrameOpcodeMeaning;
import ch.nolix.coreapi.net.websocket.WebSocketFramePayloadLengthType;
import ch.nolix.coreapi.net.websocket.WebSocketFrameType;

public final class WebSocketFrame {
  private static final int MASK_LENGTH_IN_BYTES = 4;

  private static final ArrayTool ARRAY_TOOL = new ArrayTool();

  private final WebSocketFrameFirstNibble firstNibble;

  private final WebSocketFramePayloadLength payloadLength;

  private final byte[] payload;

  private final byte[] maskingKey;

  public WebSocketFrame(
    final boolean mFINBit,
    final WebSocketFrameOpcodeMeaning opcode,
    final boolean maskBit,
    final byte[] payload) {
    Validator.assertThat(payload).thatIsNamed("payload").isNotNull();

    firstNibble = new WebSocketFrameFirstNibble(
      mFINBit,
      opcode,
      maskBit,
      payload.length);

    payloadLength = new WebSocketFramePayloadLength(payload.length);
    this.payload = payload; //NOSONAR: A WebSocketFrame operates on the original instance.
    maskingKey = null;
  }

  public WebSocketFrame(
    final boolean mFINBit,
    final WebSocketFrameOpcodeMeaning opcode,
    final boolean maskBit,
    final String payload) {
    this(mFINBit, opcode, maskBit, payload.getBytes(StandardCharsets.UTF_8));
  }

  public WebSocketFrame(final InputStream inputStream) {
    try {
      firstNibble = WebSocketFrameFirstNibble.fromNibble(inputStream.readNBytes(2));
      payloadLength = calculatePayloadLength(inputStream);

      if (getMaskBit()) {
        maskingKey = inputStream.readNBytes(MASK_LENGTH_IN_BYTES);
      } else {
        maskingKey = null;
      }

      if (payloadLength.getValue() > Integer.MAX_VALUE) {
        throw UnsupportedCaseException.forCase("The payload is longer than " + Integer.MAX_VALUE + ".");
      }

      payload = inputStream.readNBytes((int) getPayloadLength());

      if (maskingKey != null) {
        for (var i = 0; i < payload.length; i++) {
          payload[i] = ((byte) (payload[i] ^ maskingKey[i & 0x3]));
        }
      }
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  public static WebSocketFrame createPongFrameFor(final WebSocketFrame pingFrame) {
    if (!pingFrame.isPingFrame()) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        pingFrame,
        "ping frame",
        "is actually not a ping frame");
    }

    return new WebSocketFrame(true, WebSocketFrameOpcodeMeaning.PONG, false, pingFrame.getPayload());
  }

  public WebSocketFrame createPongFrame() {
    if (!isPingFrame()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not a ping frame");
    }

    return new WebSocketFrame(true, WebSocketFrameOpcodeMeaning.PONG, false, payload);
  }

  public boolean getFINBit() { //NOSONAR: This method returns a bit as a boolean.
    return firstNibble.getFINBit();
  }

  public WebSocketFrameType getFrameType() {
    if (isControlFrame()) {
      return WebSocketFrameType.CONTROL_FRAME;
    }

    if (isDataFrame()) {
      return WebSocketFrameType.DATA_FRAME;
    }

    throw InvalidArgumentException.forArgument(this);
  }

  public BigDecimal getLengthInBytes() {
    var byteRepresentationLength = BigDecimal.valueOf(2);

    switch (getPayloadLengthType()) {
      case BITS_16:
        byteRepresentationLength = byteRepresentationLength.add(BigDecimal.valueOf(2));
        break;
      case BITS_64:
        byteRepresentationLength = byteRepresentationLength.add(BigDecimal.valueOf(8));
        break;
      default:
    }

    if (masksPayload()) {
      byteRepresentationLength = byteRepresentationLength.add(BigDecimal.valueOf(MASK_LENGTH_IN_BYTES));
    }

    byteRepresentationLength = byteRepresentationLength.add(BigDecimal.valueOf(getPayloadLength()));

    return byteRepresentationLength;
  }

  public boolean getMaskBit() { //NOSONAR: This method returns a bit as a boolean.
    return firstNibble.getMaskBit();
  }

  public int getMaskLength() {
    if (masksPayload()) {
      return MASK_LENGTH_IN_BYTES;
    }

    return 0;
  }

  public WebSocketFrameOpcodeMeaning getOpcodeMeaning() {
    return firstNibble.getOpcodeMeaning();
  }

  public long getPayloadLength() {
    return payloadLength.getValue();
  }

  public WebSocketFramePayloadLengthType getPayloadLengthType() {
    return firstNibble.getPayloadLengthType();
  }

  public byte[] getPayload() {
    return payload; //NOSONAR: A WebSocketFrame returns the original instance.
  }

  public boolean isControlFrame() {
    return switch (getOpcodeMeaning()) {
      case CONNECTION_CLOSE, PING, PONG ->
        true;
      default ->
        false;
    };
  }

  public boolean isDataFrame() {
    return switch (getOpcodeMeaning()) {
      case TEXT_FRAME, BINARY_FRAME ->
        true;
      default ->
        false;
    };
  }

  public boolean isFinalFragment() {
    return getFINBit();
  }

  public boolean isPingFrame() {
    return (getOpcodeMeaning() == WebSocketFrameOpcodeMeaning.PING);
  }

  public boolean isPongFrame() {
    return (getOpcodeMeaning() == WebSocketFrameOpcodeMeaning.PONG);
  }

  public boolean masksPayload() {
    return getMaskBit();
  }

  public byte[] toBytes() {
    final var bytes = new byte[getLengthInBytes().intValue()];

    bytes[0] = firstNibble.getByte1();
    bytes[1] = firstNibble.getByte2();

    var i = 2;
    final var payloadLengthBytes = payloadLength.toBytes();
    final var payloadLengthType = getPayloadLengthType();

    switch (payloadLengthType) {
      case BITS_7:
        break;
      case BITS_16:

        bytes[2] = payloadLengthBytes[0];
        bytes[3] = payloadLengthBytes[1];

        i += 2;

        break;
      case BITS_64:

        writePayloadLengthIntoBytesWhenPayloadLengthTypeisBits64(bytes, payloadLengthBytes);

        i += 8;

        break;
      default:
        throw InvalidArgumentException.forArgument(payloadLengthType);
    }

    if (firstNibble.getMaskBit()) {
      i = ARRAY_TOOL.onArray(bytes).fromIndex(i).write(maskingKey).andGetNextIndex();
    }

    ARRAY_TOOL.onArray(bytes).fromIndex(i).write(payload);

    return bytes;
  }

  private WebSocketFramePayloadLength calculatePayloadLength(final InputStream inputStream) throws IOException {
    return switch (getPayloadLengthType()) {
      case BITS_7 ->
        calculatePayloadLengthWhenPayloadLengthIs7Bits();
      case BITS_16 ->
        calculatePayloadLengthWhenPayloadLengthIs16Bits(inputStream);
      case BITS_64 ->
        calculatePayloadLengthWhenPayloadLengthIs64Bits(inputStream);
      default ->
        throw InvalidArgumentException.forArgument(getPayloadLengthType());
    };
  }

  private WebSocketFramePayloadLength calculatePayloadLengthWhenPayloadLengthIs7Bits() {
    return new WebSocketFramePayloadLength(firstNibble.get7BitsPayloadLength());
  }

  private WebSocketFramePayloadLength calculatePayloadLengthWhenPayloadLengthIs16Bits(
    final InputStream inputStream)
  throws IOException {
    final var headerNext2Bytes = inputStream.readNBytes(2);

    return new WebSocketFramePayloadLength(
      (0x100L * (headerNext2Bytes[0] & 0b11111111))
      + (headerNext2Bytes[1] & 0b11111111));
  }

  private WebSocketFramePayloadLength calculatePayloadLengthWhenPayloadLengthIs64Bits(
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

  private void writePayloadLengthIntoBytesWhenPayloadLengthTypeisBits64(
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
