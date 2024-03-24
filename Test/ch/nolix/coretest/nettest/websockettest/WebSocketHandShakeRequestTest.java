//package declaration
package ch.nolix.coretest.nettest.websockettest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.net.websocket.WebSocketHandShakeRequest;
import ch.nolix.core.testing.test.StandardTest;

//class
final class WebSocketHandShakeRequestTest extends StandardTest {

  //method
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
