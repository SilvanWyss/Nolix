//package declaration
package ch.nolix.common.net.websocket;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;

//enum
public enum WebSocketFramePayloadLengthType {
	BITS_7,
	BITS_16,
	BITS_64;
	
	//static method
	public static WebSocketFramePayloadLengthType fromCode(final int code) {
		
		Validator.assertThat(code).thatIsNamed(LowerCaseCatalogue.CODE).isNotNegative();
		
		if (code < 126) {
			return BITS_7;
		}
		
		if (code == 126) {
			return BITS_16;
		}
		
		if (code == 127) {
			return BITS_64;
		}
		
		throw new InvalidArgumentException(LowerCaseCatalogue.CODE, code, "is not valid");
	}
	
	//static method
	public static WebSocketFramePayloadLengthType fromPayloadLength(final long payloadLength) {
		
		Validator.assertThat(payloadLength).thatIsNamed("payload length").isNotNegative();
		
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
