package ch.nolix.core.net.websocket;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.commoncontainerapi.StoringRequestable;

public final class WebSocketHandShakeRequest {

  private static final String SEC_WEBSOCKET_KEY_HEADER = "Sec-WebSocket-Key";

  private final String secWebSocketKey;

  public WebSocketHandShakeRequest(final IContainer<String> lines) {
    secWebSocketKey = lines
      .getStoredFirst(l -> l.startsWith(SEC_WEBSOCKET_KEY_HEADER))
      .substring(SEC_WEBSOCKET_KEY_HEADER.length() + 2);
  }

  public static boolean canBe(final StoringRequestable<String> lines) {
    return lines.containsAny(l -> l.contains(WebSocketHandShakeRequest.SEC_WEBSOCKET_KEY_HEADER));
  }

  public WebSocketHandShakeResponse getWebSocketHandShakeResponse() {
    return new WebSocketHandShakeResponse(secWebSocketKey);
  }

  public String getSecWebSocketKey() {
    return secWebSocketKey;
  }
}
