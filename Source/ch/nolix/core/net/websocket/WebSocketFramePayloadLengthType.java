//package declaration
package ch.nolix.core.net.websocket;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;

//enum
public enum WebSocketFramePayloadLengthType {
  BITS_7,
  BITS_16,
  BITS_64;

  //static method
  public static WebSocketFramePayloadLengthType fromCode(final int code) {

    GlobalValidator.assertThat(code).thatIsNamed(LowerCaseCatalogue.CODE).isNotNegative();

    if (code < 126) {
      return BITS_7;
    }

    if (code == 126) {
      return BITS_16;
    }

    if (code == 127) {
      return BITS_64;
    }

    throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.CODE, code);
  }

  //static method
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
