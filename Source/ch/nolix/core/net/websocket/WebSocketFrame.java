//package declaration
package ch.nolix.core.net.websocket;

//Java imports
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.core.commontypetool.GlobalArrayTool;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnsupportedCaseException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.websocketapi.WebSocketFrameOpcodeMeaning;
import ch.nolix.coreapi.netapi.websocketapi.WebSocketFramePayloadLengthType;
import ch.nolix.coreapi.netapi.websocketapi.WebSocketFrameType;

//class
public final class WebSocketFrame {

  //constant
  private static final int MASK_LENGTH_IN_BYTES = 4;

  //attribute
  private final WebSocketFrameFirstNibble firstNibble;

  //attribute
  private final WebSocketFramePayloadLength payloadLength;

  //attribute
  private final byte[] payload;

  //optional attribute
  private final byte[] maskingKey;

  //constructor
  public WebSocketFrame(
    final boolean mFINBit,
    final WebSocketFrameOpcodeMeaning opcode,
    final boolean maskBit,
    final byte[] payload) {

    GlobalValidator.assertThat(payload).thatIsNamed("payload").isNotNull();

    firstNibble = new WebSocketFrameFirstNibble(
      mFINBit,
      opcode,
      maskBit,
      payload.length);

    payloadLength = new WebSocketFramePayloadLength(payload.length);
    this.payload = payload; //NOSONAR: A WebSocketFrame operates on the original instance.
    maskingKey = null;
  }

  //constructor
  public WebSocketFrame(
    final boolean mFINBit,
    final WebSocketFrameOpcodeMeaning opcode,
    final boolean maskBit,
    final String payload) {
    this(mFINBit, opcode, maskBit, payload.getBytes(StandardCharsets.UTF_8));
  }

  //constructor
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

  //static method
  public static WebSocketFrame createPongFrameFor(final WebSocketFrame pingFrame) {

    if (!pingFrame.isPingFrame()) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        "ping frame", pingFrame,
        "is actually not a ping frame");
    }

    return new WebSocketFrame(true, WebSocketFrameOpcodeMeaning.PONG, false, pingFrame.getPayload());
  }

  //method
  public WebSocketFrame createPongFrame() {

    if (!isPingFrame()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not a ping frame");
    }

    return new WebSocketFrame(true, WebSocketFrameOpcodeMeaning.PONG, false, payload);
  }

  //method
  public boolean getFINBit() { //NOSONAR: This method returns a bit as a boolean.
    return firstNibble.getFINBit();
  }

  //method
  public WebSocketFrameType getFrameType() {

    if (isControlFrame()) {
      return WebSocketFrameType.CONTROL_FRAME;
    }

    if (isDataFrame()) {
      return WebSocketFrameType.DATA_FRAME;
    }

    throw InvalidArgumentException.forArgument(this);
  }

  //method
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

  //method
  public boolean getMaskBit() { //NOSONAR: This method returns a bit as a boolean.
    return firstNibble.getMaskBit();
  }

  //method
  public int getMaskLength() {

    if (masksPayload()) {
      return MASK_LENGTH_IN_BYTES;
    }

    return 0;
  }

  //method
  public WebSocketFrameOpcodeMeaning getOpcodeMeaning() {
    return firstNibble.getOpcodeMeaning();
  }

  //method
  public long getPayloadLength() {
    return payloadLength.getValue();
  }

  //method
  public WebSocketFramePayloadLengthType getPayloadLengthType() {
    return firstNibble.getPayloadLengthType();
  }

  //method
  public byte[] getPayload() {
    return payload; //NOSONAR: A WebSocketFrame returns the original instance.
  }

  //method
  public boolean isControlFrame() {
    return switch (getOpcodeMeaning()) {
      case CONNECTION_CLOSE, PING, PONG ->
        true;
      default ->
        false;
    };
  }

  //method
  public boolean isDataFrame() {
    return switch (getOpcodeMeaning()) {
      case TEXT_FRAME, BINARY_FRAME ->
        true;
      default ->
        false;
    };
  }

  //method
  public boolean isFinalFragment() {
    return getFINBit();
  }

  //method
  public boolean isPingFrame() {
    return (getOpcodeMeaning() == WebSocketFrameOpcodeMeaning.PING);
  }

  //method
  public boolean isPongFrame() {
    return (getOpcodeMeaning() == WebSocketFrameOpcodeMeaning.PONG);
  }

  //method
  public boolean masksPayload() {
    return getMaskBit();
  }

  //method
  public byte[] toBytes() {

    final var bytes = new byte[getLengthInBytes().intValue()];

    bytes[0] = firstNibble.getByte1();
    bytes[1] = firstNibble.getByte2();

    var i = 2;
    final var payloadLengthBytes = payloadLength.toBytes();
    switch (getPayloadLengthType()) {
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
    }

    if (firstNibble.getMaskBit()) {
      i = GlobalArrayTool.onArray(bytes).fromIndex(i).write(maskingKey).andGetNextIndex();
    }

    GlobalArrayTool.onArray(bytes).fromIndex(i).write(payload);

    return bytes;
  }

  //method
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

  //method
  private WebSocketFramePayloadLength calculatePayloadLengthWhenPayloadLengthIs7Bits() {
    return new WebSocketFramePayloadLength(firstNibble.get7BitsPayloadLength());
  }

  //method
  private WebSocketFramePayloadLength calculatePayloadLengthWhenPayloadLengthIs16Bits(
    final InputStream inputStream)
  throws IOException {

    final var headerNext2Bytes = inputStream.readNBytes(2);

    return new WebSocketFramePayloadLength(
      (0x100L * (headerNext2Bytes[0] & 0b11111111))
      + (headerNext2Bytes[1] & 0b11111111));
  }

  //method
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

  //method
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
