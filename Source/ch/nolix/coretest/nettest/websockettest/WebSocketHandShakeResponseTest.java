//package declaration
package ch.nolix.coretest.nettest.websockettest;

//own imports
import ch.nolix.core.net.websocket.WebSocketHandShakeResponse;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class WebSocketHandShakeResponseTest extends StandardTest {

  //method
  @TestCase
  public void testCase_getSecWebSocketAccept() {

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
  @TestCase
  public void testCase_getSecWebSocketAccept_2() {

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
