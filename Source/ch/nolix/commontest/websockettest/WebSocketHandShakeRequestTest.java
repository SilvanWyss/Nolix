//package declaration
package ch.nolix.commontest.websockettest;

import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.test.Test;
import ch.nolix.common.websocket.WebSocketHandShakeRequest;

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
