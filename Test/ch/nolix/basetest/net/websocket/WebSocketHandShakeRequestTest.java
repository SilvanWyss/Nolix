/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.websocket;

import org.junit.jupiter.api.Test;

import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.base.net.websocket.WebSocketHandShakeRequest;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class WebSocketHandShakeRequestTest extends StandardTest {
  @Test
  void testCase() {
    //setup
    final var webSocketHandShakeRequest = new WebSocketHandShakeRequest(
      LinkedList.withElement("Sec-WebSocket-Key: dGhlIHNhbXBsZSBub25jZQ=="));

    //execution
    final var result = webSocketHandShakeRequest.getSecWebSocketKey();

    //verification
    expect(result).isEqualTo("dGhlIHNhbXBsZSBub25jZQ==");
  }
}
