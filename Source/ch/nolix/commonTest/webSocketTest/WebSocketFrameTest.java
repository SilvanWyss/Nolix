//package declaration
package ch.nolix.commonTest.webSocketTest;

//Java imports
import java.io.IOException;
import java.io.InputStream;

//own imports
import ch.nolix.common.commonTypeWrappers.ByteWrapper;
import ch.nolix.common.test.Test;
import ch.nolix.common.webSocket.WebSocketFrame;
import ch.nolix.common.webSocket.WebSocketFrameOpcodeMeaning;

//test class
public final class WebSocketFrameTest extends Test {
	
	//test case
	public void testCase_creation_whenMaskBitIsFalse() {
		
		//setup
			final var bytes =
			new byte[] {
				new ByteWrapper(1, 0, 0, 0, 0, 0, 0, 1).toByte(),
				new ByteWrapper(0, 0, 0, 0, 0, 0, 1, 0).toByte(),
				new ByteWrapper(0, 0, 0, 1, 0, 0, 0, 0).toByte(),
				new ByteWrapper(0, 0, 1, 0, 0, 0, 0, 0).toByte(),
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
		final var resultOpcode = webSocketFrame.getOpcodeMeaning();
		final var resultPayload = webSocketFrame.getPayload();
		
		//verification
		expect(resultFINBit);
		expectNot(resultMaskBit);
		expect(resultOpcode).isEqualTo(WebSocketFrameOpcodeMeaning.TEXT_FRAME);
		expect(resultPayload.length).isEqualTo(2);
		expect(resultPayload[0]).isEqualTo(16);
		expect(resultPayload[1]).isEqualTo(32);
	}
	
	//test case
	public void testCase_toBytes_whenFinalBitIs0_andOpcodeMeaningIsTextFrame_andMaskBitIs0_andPayloadIsEmpty() {
		
		//setup
		final var testUnit = new WebSocketFrame(false, WebSocketFrameOpcodeMeaning.TEXT_FRAME, false, new byte[] {});
		
		//execution
		final var result = testUnit.toBytes();
		
		//verification
		expect(result.length).isEqualTo(2);
		expect(new ByteWrapper(result[0]).toBitString()).isEqualTo("00000001");
		expect(new ByteWrapper(result[1]).toBitString()).isEqualTo("00000000");
	}
	
	//test case
	public void testCase_toBytes_whenFinalBitIs1_andOpcodeMeaningIsTextFrame_andMaskBitIs0_andPayloadIsEmpty() {
		
		//setup
		final var testUnit = new WebSocketFrame(true, WebSocketFrameOpcodeMeaning.TEXT_FRAME, false, new byte[] {});
		
		//execution
		final var result = testUnit.toBytes();
		
		//verification
		expect(result.length).isEqualTo(2);
		expect(new ByteWrapper(result[0]).toBitString()).isEqualTo("10000001");
		expect(new ByteWrapper(result[1]).toBitString()).isEqualTo("00000000");
	}
	
	//test case
	public void testCase_toBytes_whenFinalBitIs1_andOpcodeMeaningIsTextFrame_andMaskBitIs0_andPayloadIs4Bytes() {
		
		//setup
		final var testUnit =
		new WebSocketFrame(
			true,
			WebSocketFrameOpcodeMeaning.TEXT_FRAME, false,
			new byte[] { 0b00000001, 0b00000010, 0b00000011, 0b00000100 }
		);
		
		//execution
		final var result = testUnit.toBytes();
		
		//verification
		expect(result.length).isEqualTo(6);
		expect(new ByteWrapper(result[0]).toBitString()).isEqualTo("10000001");
		expect(new ByteWrapper(result[1]).toBitString()).isEqualTo("00000100");
		expect(new ByteWrapper(result[2]).toBitString()).isEqualTo("00000001");
		expect(new ByteWrapper(result[3]).toBitString()).isEqualTo("00000010");
		expect(new ByteWrapper(result[4]).toBitString()).isEqualTo("00000011");
		expect(new ByteWrapper(result[5]).toBitString()).isEqualTo("00000100");
	}
}
