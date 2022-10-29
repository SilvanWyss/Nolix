//package declaration
package ch.nolix.core.net.websocket;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

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
			case 0x3, 0x4, 0x5, 0x6, 0x7:
				return RESERVED;
			case 0x8:
				return CONNECTION_CLOSE;
			case 0x9:
				return PING;
			case 0xA:
				return PONG;
			case 0xB, 0xC, 0xD, 0xE, 0xF:
				return RESERVED;
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.NUMBER, number);
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
				throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not represent a single number");
			default:
				throw InvalidArgumentException.forArgument(this);
		}
	}
}
