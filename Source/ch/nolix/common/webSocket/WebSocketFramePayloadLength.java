//package declaration
package ch.nolix.common.webSocket;

//Java import
import java.math.BigInteger;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.validator.Validator;

//class
public final class WebSocketFramePayloadLength {
	
	//attribute
	private final long value;
	
	//constructor
	public WebSocketFramePayloadLength(final long value) {
		
		Validator.assertThat(value).thatIsNamed(VariableNameCatalogue.VALUE).isNotNegative();
		
		this.value = value;
	}
	
	//method
	public WebSocketFramePayloadLengthType getType() {
		
		
		return WebSocketFramePayloadLengthType.fromPayloadLength(value);
	}
	
	//method
	public long getValue() {
		return value;
	}
	
	//method
	public byte[] toBytes() {
		switch (getType()) {
			case _7_BITS:
				return toBytesWhen7Bits();
			case _16_BITS:
				return toBytesWhen16Bits();
			case _64_BITS:
				return toBytesWhen64Bits();
			default:
				throw new InvalidArgumentException(this);
		}
	}
	
	//method
	private byte[] toBytesWhen7Bits() {
		
		final var byteArray = BigInteger.valueOf(value).toByteArray();
		
		return new byte[] { byteArray[0] };
	}
	
	//method
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
	
	//method
	private byte[] toBytesWhen64Bits() {
		
		final var byteArray = BigInteger.valueOf(value).toByteArray();
		
		final var result = new byte[8];
		
		for (var i = 0; i < byteArray.length; i++) {
			result[result.length - byteArray.length + i] = byteArray[i];
		}
		
		return result;
	}
}
