//package declaration
package ch.nolix.commonTest.webSocketTest;

//own imports
import ch.nolix.common.containers.List;
import ch.nolix.common.test.Test;
import ch.nolix.common.webSocket.WebSocketHandShakeRequest;

//test class
public final class WebSocketHandShakeRequestTest extends Test {
	
	//test case
	public void testCase() {
		
		//setup
		final var webSocketHandShakeRequest =
		new WebSocketHandShakeRequest(new List<>("Sec-WebSocket-Key: dGhlIHNhbXBsZSBub25jZQ=="));
		
		//execution
		final var result = webSocketHandShakeRequest.getSecWebSocketKey();
		
		//verification
		expect(result).isEqualTo("dGhlIHNhbXBsZSBub25jZQ==");
	}
}
