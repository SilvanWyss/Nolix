//package declaration
package ch.nolix.coretest.nettest.websockettest;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.net.websocket.WebSocketHandShakeRequest;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class WebSocketHandShakeRequestTest extends Test {

  //method
  @TestCase
  public void testCase() {

    //setup
    final var webSocketHandShakeRequest = new WebSocketHandShakeRequest(
      LinkedList.withElement("Sec-WebSocket-Key: dGhlIHNhbXBsZSBub25jZQ=="));

    //execution
    final var result = webSocketHandShakeRequest.getSecWebSocketKey();

    //verification
    expect(result).isEqualTo("dGhlIHNhbXBsZSBub25jZQ==");
  }
}
