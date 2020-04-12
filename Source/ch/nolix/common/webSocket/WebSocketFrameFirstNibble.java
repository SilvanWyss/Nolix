//package declaration
package ch.nolix.common.webSocket;

//own imports
import ch.nolix.common.commonTypeWrappers.ByteWrapper;
import ch.nolix.common.validator.Validator;

//class
final class WebSocketFrameFirstNibble {
	
	//attributes
	private final boolean mFINBit;
	private final int opcode;
	private final boolean maskBit;
	private final WebSocketFramePayloadLengthSpecification payloadLengthSpecification;
	private final int m7BitPayloadLength;
	
	//static method
	public static WebSocketFrameFirstNibble fromNibble(final byte[] nibble) {
		
		Validator.assertThat(nibble).hasElementCount(2);
		
		return new WebSocketFrameFirstNibble(nibble[0], nibble[1]);
	}
	
	//constructor
	public WebSocketFrameFirstNibble(
		final boolean mFINBit,
		final WebSocketFrameOpcodeMeaning opcodeMeaning,
		final boolean maskBit,
		final int payloadLength
	) {
		
		Validator.assertThat(opcodeMeaning).thatIsNamed("opcode meaning").isNotNull();
		
		this.mFINBit = mFINBit;
		this.opcode = opcodeMeaning.toNumber();
		this.maskBit = maskBit;		
		payloadLengthSpecification = WebSocketFramePayloadLengthSpecification.fromPayloadLength(payloadLength);
		
		this.m7BitPayloadLength =
		payloadLengthSpecification == WebSocketFramePayloadLengthSpecification.IN_7_BITS ? payloadLength : 0;
	}
	
	//constructor
	public WebSocketFrameFirstNibble(final byte byte1, final byte byte2) {
		
		final var wrapperByte1 = new ByteWrapper(byte1);
		final var wrapperByte2 = new ByteWrapper(byte2);
		
		mFINBit = wrapperByte1.getBitAt(1);
		
		//TODO: Implement Validator.assertThatTheBit(b).thatIsNamed("B").isFalse()
		/*
		final var RSV1BIt = wrapperByte1.getBitAt(2);
		final var RSV2BIt = wrapperByte1.getBitAt(3);
		final var RSV3BIt = wrapperByte1.getBitAt(4);
		
		Validator.assertThatTheBit(RSV1BIt).thatIsNamed("RSV1BIt").isFalse();
		Validator.assertThatTheBit(RSV2BIt).thatIsNamed("RSV2BIt").isFalse();
		Validator.assertThatTheBit(RSV3BIt).thatIsNamed("RSV3BIt").isFalse();
		 */
		
		opcode = byte1 & 0b1111;
		maskBit = wrapperByte2.getBitAt(1);
		payloadLengthSpecification = WebSocketFramePayloadLengthSpecification.fromCode(byte2 & 0b01111111);
		m7BitPayloadLength = byte2 & 0x7F;
	}
	
	//method
	public int get7BitsPayloadLength() {
		return m7BitPayloadLength;
	}
	
	//method
	public byte getByte1() {
		
		var byte1 = 0;
		
		if (getFINBit()) {
			byte1 |= 0b10000000;
		}
		
		byte1 |= opcode;
		
		return (byte)byte1;
	}
	
	//method
	public byte getByte2() {

		var byte2 = 0;
		
		if (getMaskBit()) {
			byte2 |= 0b1000000;
		}
		
		switch (getPayloadLengthSpecification()) {
			case IN_7_BITS:
				byte2 |= m7BitPayloadLength;
				break;
			case IN_16_BITS:
				byte2 |= 0b1111110;
				break;
			case IN_64_BITS:
				byte2 |= 0b1111111;
				break;
		}
				
		return (byte)byte2;
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
	public int getOpcode() {
		return opcode;
	}
	
	//method
	public WebSocketFrameOpcodeMeaning getOpcodeMeaning() {
		return WebSocketFrameOpcodeMeaning.fromNumber(opcode);
	}
	
	//method
	public WebSocketFramePayloadLengthSpecification getPayloadLengthSpecification() {
		return payloadLengthSpecification;
	}
	
	//method
	public byte[] toBytes() {
		return new byte[] {getByte1(), getByte2()};
	}
}
