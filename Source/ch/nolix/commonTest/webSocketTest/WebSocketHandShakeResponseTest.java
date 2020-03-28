//package declaration
package ch.nolix.commonTest.webSocketTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.common.webSocket.WebSocketHandShakeResponse;

//class
public final class WebSocketHandShakeResponseTest extends Test {
	
	//method
	@TestCase
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
	
	//method
	@TestCase
	public void testCase_getSecWebSocketAccept_2() {
		
		//parameter definition
		final var secWebSocketKey = "xqBt3ImNzJbYqRINxEFlkg==";
		final var expcetedSecWebSocketAccept = "K7DJLdLooIwIG/MOpvWFB3y3FE8=";

		//setup
		final var webSocketHandShakeResponse = new WebSocketHandShakeResponse(secWebSocketKey);
		
		//execution
		final var secWebSocketAccept = webSocketHandShakeResponse.getSecWebSocketAccept();
		
		//verification
		expect(secWebSocketAccept).isEqualTo(expcetedSecWebSocketAccept);
	}
}
