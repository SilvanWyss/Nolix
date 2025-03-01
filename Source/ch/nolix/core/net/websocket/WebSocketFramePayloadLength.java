package ch.nolix.core.net.websocket;

import java.math.BigInteger;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.netapi.websocketapi.WebSocketFramePayloadLengthType;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public record WebSocketFramePayloadLength(long value) {

  public WebSocketFramePayloadLength(final long value) { //NOSONAR: This constructor does more than the default one.

    Validator.assertThat(value).thatIsNamed(LowerCaseVariableCatalog.VALUE).isNotNegative();

    this.value = value;
  }

  public WebSocketFramePayloadLengthType getType() {

    return WebSocketFramePayloadLengthType.fromPayloadLength(value);
  }

  public long getValue() {
    return value;
  }

  public byte[] toBytes() {
    return switch (getType()) {
      case BITS_7 ->
        toBytesWhen7Bits();
      case BITS_16 ->
        toBytesWhen16Bits();
      case BITS_64 ->
        toBytesWhen64Bits();
      default ->
        throw InvalidArgumentException.forArgument(this);
    };
  }

  private byte[] toBytesWhen7Bits() {

    final var byteArray = BigInteger.valueOf(value).toByteArray();

    return new byte[] { byteArray[0] };
  }

  private byte[] toBytesWhen16Bits() {

    final var byteArray = BigInteger.valueOf(value).toByteArray();

    if (byteArray.length == 1) {
      return new byte[] { 0b00000000, byteArray[0] };
    }

    if (byteArray.length == 2) {
      return byteArray;
    }

    return new byte[] { byteArray[1], byteArray[2] };
  }

  private byte[] toBytesWhen64Bits() {

    final var byteArray = BigInteger.valueOf(value).toByteArray();

    final var result = new byte[8];

    for (var i = 0; i < byteArray.length; i++) {
      result[result.length - byteArray.length + i] = byteArray[i];
    }

    return result;
  }
}
