/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.websocket;

import ch.nolix.baseapi.datastructure.baseextendediterable.StoringRequestable;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public final class WebSocketHandShakeRequest {
  private static final String SEC_WEBSOCKET_KEY_HEADER = "Sec-WebSocket-Key";

  private final String secWebSocketKey;

  private WebSocketHandShakeRequest(final ExtendedIterable<String> lines) {
    secWebSocketKey = lines
      .getStoredFirst(l -> l.startsWith(SEC_WEBSOCKET_KEY_HEADER))
      .substring(SEC_WEBSOCKET_KEY_HEADER.length() + 2);
  }

  public static WebSocketHandShakeRequest fromLines(final ExtendedIterable<String> lines) {
    return new WebSocketHandShakeRequest(lines);
  }

  public static boolean canBe(final StoringRequestable<String> lines) {
    return lines.containsAny(l -> l.contains(WebSocketHandShakeRequest.SEC_WEBSOCKET_KEY_HEADER));
  }

  public WebSocketHandShakeResponse getWebSocketHandShakeResponse() {
    return WebSocketHandShakeResponse.withSecWebSocketKey(secWebSocketKey);
  }

  public String getSecWebSocketKey() {
    return secWebSocketKey;
  }
}
