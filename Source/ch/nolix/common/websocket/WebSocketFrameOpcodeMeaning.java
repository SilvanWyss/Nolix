//package declaration
package ch.nolix.common.websocket;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;

//enum
public enum WebSocketFrameOpcodeMeaning {
	CONTINUATION_FRAME,
	TEXT_FRAME,
	BINARY_FRAME,
	CONNECTION_CLOSE,
	PING,
	PONG,
	RESERVED;
	
	//static method
	public static WebSocketFrameOpcodeMeaning fromNumber(final int number) {
		switch (number) {
			case 0x0:
				return CONTINUATION_FRAME;
			case 0x1:
				return TEXT_FRAME;
			case 0x2:
				return BINARY_FRAME;
			case 0x3:
			case 0x4:
			case 0x5:
			case 0x6:
			case 0x7:
				return RESERVED;
			case 0x8:
				return CONNECTION_CLOSE;
			case 0x9:
				return PING;
			case 0xA:
				return PONG;
			case 0xB:
			case 0xC:
			case 0xD:
			case 0xE:
			case 0xF:
				return RESERVED;
			default:
				throw new InvalidArgumentException(VariableNameCatalogue.NUMBER, number, "is not valid");
		}
	}
	
	//method
	public int toNumber() {
		switch (this) {
			case CONTINUATION_FRAME:
				return 0x0;
			case TEXT_FRAME:
				return 0x1;
			case BINARY_FRAME:
				return 0x2;
			case CONNECTION_CLOSE:
				return 0x8;
			case PING:
				return 0x9;
			case PONG:
				return 0xA;
			case RESERVED:
				throw new InvalidArgumentException(this, "does not represent a single number");
			default:
				throw new InvalidArgumentException(this);
		}
	}
}
