//package declaration
package ch.nolix.commonTest.webSocketTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.test.Test;
import ch.nolix.common.webSocket.WebSocketHandShakeRequest;

//class
public final class WebSocketHandShakeRequestTest extends Test {
	
	//method
	@TestCase
	public void testCase() {
		
		//setup
		final var webSocketHandShakeRequest =
		new WebSocketHandShakeRequest(new LinkedList<>("Sec-WebSocket-Key: dGhlIHNhbXBsZSBub25jZQ=="));
		
		//execution
		final var result = webSocketHandShakeRequest.getSecWebSocketKey();
		
		//verification
		expect(result).isEqualTo("dGhlIHNhbXBsZSBub25jZQ==");
	}
}
