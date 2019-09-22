//package declaration
package ch.nolix.common.webSocket;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.validator.Validator;

//enum
public enum WebSocketFramePayloadLengthSpecification {
	IN_7_BITS,
	IN_16_BITS,
	IN_64_BITS;
	
	//static method
	public static WebSocketFramePayloadLengthSpecification fromCode(final int number) {
		
		Validator.suppose(number).thatIsNamed(VariableNameCatalogue.NUMBER).isNotNegative();
		
		if (number <= 125) {
			return IN_7_BITS;
		}
		
		if (number == 126) {
			return IN_16_BITS;
		}
		
		if (number == 127) {
			return IN_64_BITS;
		}
		
		throw new InvalidArgumentException(VariableNameCatalogue.NUMBER, "number", "is not valid");
	}
	
	//static method
	public static WebSocketFramePayloadLengthSpecification fromPayloadLength(final int payloadLength) {
		
		Validator.suppose(payloadLength).thatIsNamed("payload length").isNotNegative();
		
		if (payloadLength <= 125) {
			return IN_7_BITS;
		}
		
		if (payloadLength <= 65536) {
			return IN_16_BITS;
		}
		
		return WebSocketFramePayloadLengthSpecification.IN_64_BITS;
	}
}
