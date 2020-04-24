//package declaration
package ch.nolix.common.webSocket;

//Java import
import java.io.InputStream;

import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.functionAPI.IElementTaker;

//class
public final class WebSocketCompleteMessage {
	
	//attribute
	private final LinkedList<Byte> message = new LinkedList<>();
	
	//constructor
	public WebSocketCompleteMessage(
		final InputStream inputStream,
		final IElementTaker<WebSocketFrame> controlFrameTaker
	) {
		while (true) {
			
			final var frame = new WebSocketFrame(inputStream);
				
			if (frame.isDataFrame()) {
				
				for (final var b : frame.getPayload()) {
					message.addAtEnd(b);
				}
				
				if (frame.isFinalFragment()) {
					break;
				}
			}
			
			if (frame.isControlFrame()) {
				controlFrameTaker.run(frame);
			}
		}
	}
	
	//method
	public IContainer<Byte> getMessage() {
		return message;
	}
	
	//method
	public byte[] getMessageAsByteArray() {
		
		final var byteArray = new byte[message.getSize()];
		var i = 0;
		for (final var b : message) {
			byteArray[i] = b.byteValue();
			i++;
		}
		
		return byteArray;
	}
}
