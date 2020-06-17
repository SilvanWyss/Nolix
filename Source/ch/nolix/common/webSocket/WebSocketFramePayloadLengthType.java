//package declaration
package ch.nolix.common.webSocket;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.validator.Validator;

//enum
public enum WebSocketFramePayloadLengthType {
	_7_BITS,
	_16_BITS,
	_64_BITS;
	
	//static method
	public static WebSocketFramePayloadLengthType fromCode(final int code) {
		
		Validator.assertThat(code).thatIsNamed(VariableNameCatalogue.CODE).isNotNegative();
		
		if (code < 126) {
			return _7_BITS;
		}
		
		if (code == 126) {
			return _16_BITS;
		}
		
		if (code == 127) {
			return _64_BITS;
		}
		
		throw new InvalidArgumentException(VariableNameCatalogue.CODE, code, "is not valid");
	}
	
	//static method
	public static WebSocketFramePayloadLengthType fromPayloadLength(final long payloadLength) {
		
		Validator.assertThat(payloadLength).thatIsNamed("payload length").isNotNegative();
		
		if (payloadLength < 126) {
			return _7_BITS;
		}
		
		if (payloadLength < 65536) {
			return _16_BITS;
		}
		
		//payloadLength < 2^63
		return WebSocketFramePayloadLengthType._64_BITS;
	}
}
