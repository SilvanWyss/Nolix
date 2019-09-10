//package declaration
package ch.nolix.common.webSocket;

//own imports
import ch.nolix.common.commonTypeWrappers.WrapperByte;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;

//package-visible class
final class WebSocketFrameFirstNibble {
	
	//attributes
	private final boolean mFINBit;
	private final WebSocketFrameOpcode opcode;
	private final boolean maskBit;
	private final WebSocketFramePayloadLengthSpecification payloadLengthSpecification;
	private final int m7BitPayloadLength;
	
	//static method
	public static WebSocketFrameFirstNibble fromNibble(final byte[] nibble) {
		
		Validator.suppose(nibble).hasElementCount(2);
		
		return new WebSocketFrameFirstNibble(nibble[0], nibble[1]);
	}
	
	//constructor
	public WebSocketFrameFirstNibble(
		final boolean mFINBit,
		final WebSocketFrameOpcode opcode,
		final boolean maskBit,
		final int payloadLength
	) {
		
		Validator.suppose(opcode).thatIsNamed(VariableNameCatalogue.OPCODE).isNotNull();
		
		this.mFINBit = mFINBit;
		this.opcode = opcode;
		this.maskBit = maskBit;		
		payloadLengthSpecification = WebSocketFramePayloadLengthSpecification.fromNumber(payloadLength);
		
		this.m7BitPayloadLength =
		payloadLengthSpecification == WebSocketFramePayloadLengthSpecification.IN_7_BITS ? payloadLength : 0;
	}
	
	
	//constructor
	public WebSocketFrameFirstNibble(final byte byte1, final byte byte2) {
		
		final var wrapperByte1 = new WrapperByte(byte1);
		final var wrapperByte2 = new WrapperByte(byte2);
		
		mFINBit = wrapperByte1.getBitAt(1);
		
		final var RSV1BIt = wrapperByte1.getBitAt(2);
		final var RSV2BIt = wrapperByte1.getBitAt(3);
		final var RSV3BIt = wrapperByte1.getBitAt(4);
		
		//TODO: Implement Validator.supposeTheBit(b).thatIsNamed("B").isFalse();
		Validator.supposeNot(RSV1BIt);
		Validator.supposeNot(RSV2BIt);
		Validator.supposeNot(RSV3BIt);
		
		opcode = WebSocketFrameOpcode.fromNumber(byte1 & 0xF);
		maskBit = wrapperByte2.getBitAt(1);
		payloadLengthSpecification = WebSocketFramePayloadLengthSpecification.fromNumber(byte2 & 0x7F);
		m7BitPayloadLength = byte2 & 0x7F;
	}
	
	//method
	public int get7BitsPayloadLength() {
		return m7BitPayloadLength;
	}
	
	//method
	public boolean getFINBit() {
		return mFINBit;
	}
	
	//method
	public boolean getMaskBit() {
		return maskBit;
	}
	
	//method
	public WebSocketFrameOpcode getOpcode() {
		return opcode;
	}
	
	//method
	public WebSocketFramePayloadLengthSpecification getPayloadLengthSpecification() {
		return payloadLengthSpecification;
	}
}
