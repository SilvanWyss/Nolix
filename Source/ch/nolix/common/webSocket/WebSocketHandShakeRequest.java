//package declaration
package ch.nolix.common.webSocket;

import ch.nolix.common.container.IContainer;

//class
public final class WebSocketHandShakeRequest {
	
	//constant
	private static final String SEC_WEBSOCKET_KEY_HEADER = "Sec-WebSocket-Key";
	
	//static method
	public static boolean canBe(final IContainer<String> lines) {
		return lines.contains(l -> l.contains(WebSocketHandShakeRequest.SEC_WEBSOCKET_KEY_HEADER));
	}
	
	//attribute
	private final String secWebSocketKey;
	
	//constructor
	public WebSocketHandShakeRequest(final IContainer<String> lines) {
		secWebSocketKey =
		lines
		.getRefFirst(l -> l.startsWith(SEC_WEBSOCKET_KEY_HEADER))
		.substring(SEC_WEBSOCKET_KEY_HEADER.length() + 2);
	}
	
	//method
	public WebSocketHandShakeResponse getWebSocketHandShakeResponse() {
		return new WebSocketHandShakeResponse(secWebSocketKey);
	}
	
	//method
	public String getSecWebSocketKey() {
		return secWebSocketKey;
	}
}
