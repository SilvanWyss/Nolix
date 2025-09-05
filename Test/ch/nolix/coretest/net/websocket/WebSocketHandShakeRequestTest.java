package ch.nolix.coretest.net.websocket;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.net.websocket.WebSocketHandShakeRequest;
import ch.nolix.core.testing.standardtest.StandardTest;

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
