//package declaration
package ch.nolix.coretest.nettest.websockettest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.net.websocket.WebSocketHandShakeResponse;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class WebSocketHandShakeResponseTest extends StandardTest {

  //method
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

  //method
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
