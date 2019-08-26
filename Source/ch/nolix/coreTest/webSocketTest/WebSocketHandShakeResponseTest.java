//package declaration
package ch.nolix.coreTest.webSocketTest;

//own imports
import ch.nolix.core.test.Test;
import ch.nolix.core.webSocket.WebSocketHandShakeResponse;

//test class
public final class WebSocketHandShakeResponseTest extends Test {
	
	//test case
	public void testCase_getSecWebSocketAccept() {
		
		//parameter definition
		final var secWebSocketKey = "dGhlIHNhbXBsZSBub25jZQ==";
		final var expcetedSecWebSocketAccept = "s3pPLMBiTxaQ9kYGzzhZRbK+xOo=";

		//setup
		final var webSocketHandShakeResponse = new WebSocketHandShakeResponse(secWebSocketKey);
		
		//execution
		final var secWebSocketAccept = webSocketHandShakeResponse.getSecWebSocketAccept();
		
		//verification
		expect(secWebSocketAccept).isEqualTo(expcetedSecWebSocketAccept);
	}
}
