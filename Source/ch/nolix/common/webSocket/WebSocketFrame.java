//package declaration
package ch.nolix.common.webSocket;

//Java imports
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

//own imports
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.validator.Validator;

//class
public final class WebSocketFrame {
	
	//static method
	public static WebSocketFrame createPongFrameFor(final WebSocketFrame pingFrame) {
		
		if (!pingFrame.isPingFrame()) {
			throw new InvalidArgumentException("ping frame", pingFrame, "is actually not a ping frame");
		}
		
		return new WebSocketFrame(true, WebSocketFrameOpcodeMeaning.PONG,	false, pingFrame.getPayload());
	}
	
	//attributes
	private final WebSocketFrameFirstNibble firstNibble;
	private final BigDecimal payloadLength;
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
		
		Validator.suppose(payload).thatIsNamed("payload").isNotNull();
		
		firstNibble =
		new WebSocketFrameFirstNibble(
			mFINBit,
			opcode,
			maskBit,
			payload.length
		);
		
		payloadLength = BigDecimal.valueOf(payload.length);
		this.payload = payload;
		maskingKey = null;
	}

	//constructor
	public WebSocketFrame(final InputStream inputStream) {
		try {
			
			firstNibble = WebSocketFrameFirstNibble.fromNibble(inputStream.readNBytes(2));
			payloadLength = determinePayloadLength(inputStream);
			maskingKey = getMaskBit() ? inputStream.readNBytes(4) : null;
			
			//TODO Handle payload length > MAX_INT.
			payload = inputStream.readNBytes(getPayloadLength().intValue());
			
			if (masksPayload()) {
				for (var i = 0; i < payload.length; i++) {
					payload[i] = ((byte)(payload[i] ^ maskingKey[i & 0x3]));
				}
			}
		}
		catch (final IOException IOException) {
			throw new RuntimeException(IOException);
		}
	}
	
	//method
	public boolean getFINBit() {
		return firstNibble.getFINBit();
	}
	
	//method
	public boolean getMaskBit() {
		return firstNibble.getMaskBit();
	}
	
	//method
	public WebSocketFrameOpcodeMeaning getOpcodeMeaning() {
		return firstNibble.getOpcodeMeaning();
	}
		
	//method
	public BigDecimal getPayloadLength() {
		return payloadLength;
	}
	
	//method
	public WebSocketFramePayloadLengthSpecification getPayloadLengthSpecification() {
		return firstNibble.getPayloadLengthSpecification();
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
		
		final var bytes = new byte[calculateByteRepresentationLength().intValue()];
		
		bytes[0] = firstNibble.getByte1();
		bytes[1] = firstNibble.getByte2();
		
		//TODO: Use: ArrayHelper.on(bytes).fromIndex(2).write(maskingKey)
		var i = 2;
		if (firstNibble.getMaskBit()) {
			for (final var b : maskingKey) {
				bytes[i] = b;
				i++;
			}
		}
		
		//TODO: Use: ArrayHelper.on(bytes).fromIndex(nextIndex).write(maskingKey)
		for (final var b : payload) {
			bytes[i] = b;
			i++;
		}
				
		return bytes;
	}
	
	//method
	private BigDecimal calculateByteRepresentationLength() {
		
		var byteRepresentationLength = BigDecimal.valueOf(2);
		
		switch (getPayloadLengthSpecification()) {
			case IN_16_BITS:
				byteRepresentationLength = byteRepresentationLength.add(BigDecimal.valueOf(2));
				break;
			case IN_64_BITS:
				byteRepresentationLength = byteRepresentationLength.add(BigDecimal.valueOf(4));
				break;
			default:
		}
		
		if (masksPayload()) {
			byteRepresentationLength =  byteRepresentationLength.add(BigDecimal.valueOf(2));
		}
		
		byteRepresentationLength = byteRepresentationLength.add(getPayloadLength());
		
		return byteRepresentationLength;
	}

	//method
	private BigDecimal determinePayloadLength(final InputStream inputStream) throws IOException {
		switch (getPayloadLengthSpecification()) {
			case IN_7_BITS:
				return BigDecimal.valueOf(get7BitsPayloadLength());
			case IN_16_BITS:
				
				final var headerNext2Bytes = inputStream.readNBytes(2);
				
				return
				BigDecimal.valueOf(0x100 * (headerNext2Bytes[0] & 0xFF))
				.add(BigDecimal.valueOf(headerNext2Bytes[1] & 0xFF));
			case IN_64_BITS:
				
				final var headerNext4Bytes = inputStream.readNBytes(2);
				
				return
				BigDecimal.valueOf(headerNext4Bytes[0] & 0xFF)
				.add(BigDecimal.valueOf(0x100 * (headerNext4Bytes[1] & 0xFF)))
				.add(BigDecimal.valueOf(0x10000 * (headerNext4Bytes[2] & 0xFF)))
				.add(BigDecimal.valueOf(0x1000000 * (headerNext4Bytes[3] & 0xFF)));
			default:
				throw
				new InvalidArgumentException(
					"payload length specification",
					getPayloadLengthSpecification(),
					"is not valid"
				);
		}
	}
	
	//method
	private int get7BitsPayloadLength() {
		return firstNibble.get7BitsPayloadLength();
	}
}
