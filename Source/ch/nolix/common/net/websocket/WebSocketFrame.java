//package declaration
package ch.nolix.common.net.websocket;

//Java imports
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.common.commontype.commontypehelper.GlobalArrayHelper;
import ch.nolix.common.errorcontrol.exception.WrapperException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
public final class WebSocketFrame {
	
	//constant
	private static final int MASK_LENGTH_IN_BYTES = 4;
	
	//static method
	public static WebSocketFrame createPongFrameFor(final WebSocketFrame pingFrame) {
		
		if (!pingFrame.isPingFrame()) {
			throw new InvalidArgumentException("ping frame", pingFrame, "is actually not a ping frame");
		}
		
		return new WebSocketFrame(true, WebSocketFrameOpcodeMeaning.PONG,	false, pingFrame.getPayload());
	}
	
	//attributes
	private final WebSocketFrameFirstNibble firstNibble;
	private final WebSocketFramePayloadLength payloadLength;
	private final byte[] payload;
	
	//optional attribute
	private final byte[] maskingKey;
	
	//constructor
	public WebSocketFrame(
		final boolean mFINBit,
		final WebSocketFrameOpcodeMeaning opcode,
		final boolean maskBit,
		final byte[] payload
	) {
		
		Validator.assertThat(payload).thatIsNamed("payload").isNotNull();
		
		firstNibble =
		new WebSocketFrameFirstNibble(
			mFINBit,
			opcode,
			maskBit,
			payload.length
		);
		
		payloadLength = new WebSocketFramePayloadLength(payload.length);
		this.payload = payload;
		maskingKey = null;
	}
	
	//constructor
	public WebSocketFrame(
		final boolean mFINBit,
		final WebSocketFrameOpcodeMeaning opcode,
		final boolean maskBit,
		final String payload
	) {
		this(mFINBit, opcode, maskBit, payload.getBytes(StandardCharsets.UTF_8));
	}
	
	//constructor
	public WebSocketFrame(final InputStream inputStream) {
		try {
			
			firstNibble = WebSocketFrameFirstNibble.fromNibble(inputStream.readNBytes(2));
			payloadLength = calculatePayloadLength(inputStream);
			
			if (getMaskBit()) {
				maskingKey = inputStream.readNBytes(MASK_LENGTH_IN_BYTES);
			} else {
				maskingKey = null;
			}
			
			//TODO: Handle payloadLength > MAX_INT.
			payload = inputStream.readNBytes((int)getPayloadLength());
			
			if (masksPayload()) {
				for (var i = 0; i < payload.length; i++) {
					payload[i] = ((byte)(payload[i] ^ maskingKey[i & 0x3]));
				}
			}
		} catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//method
	public WebSocketFrame createPongFrame() {
		
		if (!isPingFrame()) {
			throw new InvalidArgumentException(this, "is not a ping frame"); 
		}
		
		return new WebSocketFrame(true, WebSocketFrameOpcodeMeaning.PONG, false, payload);
	}
	
	//method
	public boolean getFINBit() {
		return firstNibble.getFINBit();
	}
	
	//method
	public WebSocketFrameType getFrameType() {
		
		if (isControlFrame()) {
			return WebSocketFrameType.CONTROL_FRAME;
		}
		
		if (isDataFrame()) {
			return WebSocketFrameType.DATA_FRAME;
		}
		
		throw new InvalidArgumentException(this);
	}
	
	//method
	public BigDecimal getLengthInBytes() {
		
		var byteRepresentationLength = BigDecimal.valueOf(2);
		
		switch (getPayloadLengthType()) {
			case BITS_16:
				byteRepresentationLength = byteRepresentationLength.add(BigDecimal.valueOf(2));
				break;
			case BITS_64:
				byteRepresentationLength = byteRepresentationLength.add(BigDecimal.valueOf(8));
				break;
			default:
		}
		
		if (masksPayload()) {
			byteRepresentationLength = byteRepresentationLength.add(BigDecimal.valueOf(MASK_LENGTH_IN_BYTES));
		}
		
		byteRepresentationLength = byteRepresentationLength.add(BigDecimal.valueOf(getPayloadLength()));
		
		return byteRepresentationLength;
	}
	
	//method
	public boolean getMaskBit() {
		return firstNibble.getMaskBit();
	}
	
	//method
	public int getMaskLength() {
		
		if (masksPayload()) {
			return MASK_LENGTH_IN_BYTES;
		}
		
		return 0;
	}
	
	//method
	public WebSocketFrameOpcodeMeaning getOpcodeMeaning() {
		return firstNibble.getOpcodeMeaning();
	}
		
	//method
	public long getPayloadLength() {
		return payloadLength.getValue();
	}
	
	//method
	public WebSocketFramePayloadLengthType getPayloadLengthType() {
		return firstNibble.getPayloadLengthType();
	}
	
	//method
	public byte[] getPayload() {
		return payload;
	}
	
	//method
	public boolean isControlFrame() {
		switch (getOpcodeMeaning()) {
			case CONNECTION_CLOSE:
			case PING:
			case PONG:
				return true;
			default:
				return false;
		}
	}
	
	//method
	public boolean isDataFrame() {
		switch (getOpcodeMeaning()) {
			case TEXT_FRAME:
			case BINARY_FRAME:
				return true;
			default:
				return false;
		}
	}
	
	//method
	public boolean isFinalFragment() {
		return getFINBit();
	}
	
	//method
	public boolean isPingFrame() {
		return (getOpcodeMeaning() == WebSocketFrameOpcodeMeaning.PING);
	}
	
	//method
	public boolean isPongFrame() {
		return (getOpcodeMeaning() == WebSocketFrameOpcodeMeaning.PONG);
	}
	
	//method
	public boolean masksPayload() {
		return getMaskBit();
	}
	
	//method
	public byte[] toBytes() {
		
		final var bytes = new byte[getLengthInBytes().intValue()];
		
		bytes[0] = firstNibble.getByte1();
		bytes[1] = firstNibble.getByte2();
		
		var i = 2;
		final var payloadLengthBytes = payloadLength.toBytes();
		switch (getPayloadLengthType()) {
			case BITS_7:
				break;
			case BITS_16:
				
				bytes[2] = payloadLengthBytes[0];
				bytes[3] = payloadLengthBytes[1];
				
				i += 2;
				
				break;
			case BITS_64:
				
				bytes[2] = payloadLengthBytes[0];
				bytes[3] = payloadLengthBytes[1];
				bytes[4] = payloadLengthBytes[2];
				bytes[5] = payloadLengthBytes[3];
				bytes[6] = payloadLengthBytes[4];
				bytes[7] = payloadLengthBytes[5];
				bytes[8] = payloadLengthBytes[6];
				bytes[9] = payloadLengthBytes[7];
				
				i += 8;
				
				break;
		}
		
		if (firstNibble.getMaskBit()) {
			i = GlobalArrayHelper.on(bytes).fromIndex(i).write(maskingKey).andGetNextIndex();
		}
		
		GlobalArrayHelper.on(bytes).fromIndex(i).write(payload);
		
		return bytes;
	}

	//method
	private WebSocketFramePayloadLength calculatePayloadLength(final InputStream inputStream) throws IOException {
		switch (getPayloadLengthType()) {
			case BITS_7:
				return calculatePayloadLengthWhenPayloadLengthIs7Bits();
			case BITS_16:
				return calculatePayloadLengthWhenPayloadLengthIs16Bits(inputStream);
			case BITS_64:
				return calculatePayloadLengthWhenPayloadLengthI64Bits(inputStream);
			default:
				throw new InvalidArgumentException(getPayloadLengthType());
		}
	}
	
	//method
	private WebSocketFramePayloadLength calculatePayloadLengthWhenPayloadLengthIs7Bits() {
		return new WebSocketFramePayloadLength(firstNibble.get7BitsPayloadLength());
	}
	
	//method
	private WebSocketFramePayloadLength calculatePayloadLengthWhenPayloadLengthIs16Bits(
		final InputStream inputStream
	) throws IOException {
		
		final var headerNext2Bytes = inputStream.readNBytes(2);
		
		return
		new WebSocketFramePayloadLength(
			(0x100L * (headerNext2Bytes[0] & 0b11111111))
			+ (headerNext2Bytes[1] & 0b11111111)
		);
	}
	
	//method
	private WebSocketFramePayloadLength calculatePayloadLengthWhenPayloadLengthI64Bits(
		final InputStream inputStream
	) throws IOException {
		
		final var headerNext4Bytes = inputStream.readNBytes(2);
		
		return
		new WebSocketFramePayloadLength(
			(headerNext4Bytes[0] & 0xFF)
			+ (0x100L * (headerNext4Bytes[1] & 0b11111111))
			+ (0x10000L * (headerNext4Bytes[2] & 0b11111111))
			+ (0x1000000L * (headerNext4Bytes[3] & 0b11111111))
		);
	}
}
