/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.net.websocket;

import org.junit.jupiter.api.Test;

import ch.nolix.base.net.websocket.WebSocketHandShakeResponse;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class WebSocketHandShakeResponseTest extends StandardTest {
  @Test
  void testCase_getSecWebSocketAccept() {
    // parameter definition
    final var secWebSocketKey = "dGhlIHNhbXBsZSBub25jZQ==";
    final var expcetedSecWebSocketAccept = "s3pPLMBiTxaQ9kYGzzhZRbK+xOo=";

    // setup
    final var webSocketHandShakeResponse = WebSocketHandShakeResponse.withSecWebSocketKey(secWebSocketKey);

   // execute
    final var secWebSocketAccept = webSocketHandShakeResponse.getSecWebSocketAccept();

   // verify
    expect(secWebSocketAccept).isEqualTo(expcetedSecWebSocketAccept);
  }

  @Test
  void testCase_getSecWebSocketAccept_2() {
    // parameter definition
    final var secWebSocketKey = "xqBt3ImNzJbYqRINxEFlkg==";
    final var expcetedSecWebSocketAccept = "K7DJLdLooIwIG/MOpvWFB3y3FE8=";

    // setup
    final var webSocketHandShakeResponse = WebSocketHandShakeResponse.withSecWebSocketKey(secWebSocketKey);

   // execute
    final var secWebSocketAccept = webSocketHandShakeResponse.getSecWebSocketAccept();

   // verify
    expect(secWebSocketAccept).isEqualTo(expcetedSecWebSocketAccept);
  }
}
