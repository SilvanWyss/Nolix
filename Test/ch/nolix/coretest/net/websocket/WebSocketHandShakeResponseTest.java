package ch.nolix.coretest.net.websocket;

import org.junit.jupiter.api.Test;

import ch.nolix.core.net.websocket.WebSocketHandShakeResponse;
import ch.nolix.core.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class WebSocketHandShakeResponseTest extends StandardTest {
  @Test
  void testCase_getSecWebSocketAccept() {
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

  @Test
  void testCase_getSecWebSocketAccept_2() {
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
