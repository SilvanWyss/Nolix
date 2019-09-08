//package declaration
package ch.nolix.commonTest.webSocketTest;

//Java imports
import java.io.IOException;
import java.io.InputStream;

//own imports
import ch.nolix.common.commonTypeWrappers.WrapperByte;
import ch.nolix.common.test.Test;
import ch.nolix.common.webSocket.WebSocketFrame;
import ch.nolix.common.webSocket.WebSocketFrameOpcode;

//test class
public final class WebSocketFrameTest extends Test {
	
	//test case
	public void testCase_creation_whenMaskBitIsFalse() {
		
		//setup
			final var bytes =
			new byte[] {
				new WrapperByte(1, 0, 0, 0, 0, 0, 0, 1).toByte(),
				new WrapperByte(0, 0, 0, 0, 0, 0, 1, 0).toByte(),
				new WrapperByte(0, 0, 0, 1, 0, 0, 0, 0).toByte(),
				new WrapperByte(0, 0, 1, 0, 0, 0, 0, 0).toByte(),
			};
			
			final var webSocketFrame = new WebSocketFrame(
				new InputStream() {
					
					private int counter = 0;
					
					@Override
					public int read() throws IOException {
						return bytes[counter++];
					}
				}
			);
		
		//execution
		final var resultFINBit = webSocketFrame.getFINBit();
		final var resultMaskBit = webSocketFrame.getMaskBit();
		final var resultOpcode = webSocketFrame.getOpcode();
		final var resultPayload = webSocketFrame.getPayload();
		
		//verification
		expect(resultFINBit);
		expectNot(resultMaskBit);
		expect(resultOpcode).isEqualTo(WebSocketFrameOpcode.TEXT_FRAME);
		expect(resultPayload.length).isEqualTo(2);
		expect(resultPayload[0]).isEqualTo(16);
		expect(resultPayload[1]).isEqualTo(32);
	}
}
