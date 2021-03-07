//package declaration
package ch.nolix.commontest.nettest.websockettest;

import ch.nolix.common.container.LinkedList;
import ch.nolix.common.net.websocket.WebSocketHandShakeRequest;
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;

//class
public final class WebSocketHandShakeRequestTest extends Test {
	
	//method
	@TestCase
	public void testCase() {
		
		//setup
		final var webSocketHandShakeRequest =
		new WebSocketHandShakeRequest(LinkedList.withElements("Sec-WebSocket-Key: dGhlIHNhbXBsZSBub25jZQ=="));
		
		//execution
		final var result = webSocketHandShakeRequest.getSecWebSocketKey();
		
		//verification
		expect(result).isEqualTo("dGhlIHNhbXBsZSBub25jZQ==");
	}
}
