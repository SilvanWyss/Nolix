/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.websocket;

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

  /**
   * @param lines
   * @return a new {@link WebSocketHandShakeRequest} from the given lines
   */
  public static WebSocketHandShakeRequest fromLines(final ExtendedIterable<String> lines) {
    return new WebSocketHandShakeRequest(lines);
  }

  /**
   * @param lines
   * @return true if the given lines can be a {@link WebSocketHandShakeRequest},
   *         false otherwise
   */
  public static boolean canBe(final ExtendedIterable<String> lines) {
    return lines.containsMatching(l -> l.contains(WebSocketHandShakeRequest.SEC_WEBSOCKET_KEY_HEADER));
  }

  /**
   * @return the Sec-WebSocket-Key of the current
   *         {@link WebSocketHandShakeRequest}
   */
  public String getSecWebSocketKey() {
    return secWebSocketKey;
  }

  /**
   * @return a new {@link WebSocketHandShakeResponse} for the current
   *         {@link WebSocketHandShakeRequest}
   */
  public WebSocketHandShakeResponse getWebSocketHandShakeResponse() {
    return WebSocketHandShakeResponse.withSecWebSocketKey(secWebSocketKey);
  }
}
