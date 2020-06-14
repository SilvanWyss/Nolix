//package declaration
package ch.nolix.common.webSocket;

//Java imports
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.functionAPI.IBooleanGetter;
import ch.nolix.common.functionAPI.IElementTaker;

//class
public final class WebSocketCompleteMessage {
	
	//attribute
	private final LinkedList<Byte> message = new LinkedList<>();
	
	//constructor
	public WebSocketCompleteMessage(
		final IBooleanGetter isOpenFunction,
		final InputStream inputStream,
		final IElementTaker<WebSocketFrame> controlFrameTaker
	) {
		var complete = false;
		while (isOpenFunction.getOutput() && !complete) {
			
			final var frame = new WebSocketFrame(inputStream);
			
			switch (frame.getFrameType()) {
				case CONTROL_FRAME:
					controlFrameTaker.run(frame);
					break;
				case DATA_FRAME:
					
					for (final var b : frame.getPayload()) {
						message.addAtEnd(b);
					}
					
					if (frame.isFinalFragment()) {
						complete = true;
					}
					
					break;
			}
		}
	}
	
	//method
	public String getMessage() {
		return new String(getMessageAsByteArray(), StandardCharsets.UTF_8);
	}
	
	//method
	public byte[] getMessageAsByteArray() {
		
		final var byteArray = new byte[message.getElementCount()];
		var i = 0;
		for (final var b : message) {
			byteArray[i] = b.byteValue();
			i++;
		}
		
		return byteArray;
	}
}
