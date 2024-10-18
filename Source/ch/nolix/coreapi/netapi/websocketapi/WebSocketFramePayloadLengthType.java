package ch.nolix.coreapi.netapi.websocketapi;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public enum WebSocketFramePayloadLengthType {
  BITS_7,
  BITS_16,
  BITS_64;

  public static WebSocketFramePayloadLengthType fromCode(final int code) {

    GlobalValidator.assertThat(code).thatIsNamed(LowerCaseVariableCatalogue.CODE).isNotNegative();

    if (code < 126) {
      return BITS_7;
    }

    if (code == 126) {
      return BITS_16;
    }

    if (code == 127) {
      return BITS_64;
    }

    throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.CODE, code);
  }

  public static WebSocketFramePayloadLengthType fromPayloadLength(final long payloadLength) {

    GlobalValidator.assertThat(payloadLength).thatIsNamed("payload length").isNotNegative();

    if (payloadLength < 126) {
      return BITS_7;
    }

    if (payloadLength < 65536) {
      return BITS_16;
    }

    //payloadLength < 2^63
    return WebSocketFramePayloadLengthType.BITS_64;
  }
}
