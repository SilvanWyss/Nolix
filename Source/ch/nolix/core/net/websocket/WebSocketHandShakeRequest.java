//package declaration
package ch.nolix.core.net.websocket;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//class
public final class WebSocketHandShakeRequest {

  //constant
  private static final String SEC_WEBSOCKET_KEY_HEADER = "Sec-WebSocket-Key";

  //attribute
  private final String secWebSocketKey;

  //constructor
  public WebSocketHandShakeRequest(final IContainer<String> lines) {
    secWebSocketKey = lines
      .getStoredFirst(l -> l.startsWith(SEC_WEBSOCKET_KEY_HEADER))
      .substring(SEC_WEBSOCKET_KEY_HEADER.length() + 2);
  }

  //static method
  public static boolean canBe(final IContainer<String> lines) {
    return lines.containsAny(l -> l.contains(WebSocketHandShakeRequest.SEC_WEBSOCKET_KEY_HEADER));
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
