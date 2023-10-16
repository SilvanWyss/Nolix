//package declaration
package ch.nolix.coretest.nettest.websockettest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class WebSocketTestPool extends TestPool {

  //constructor
  public WebSocketTestPool() {
    super(
        WebSocketFramePayloadLengthTest.class,
        WebSocketFrameTest.class,
        WebSocketHandShakeRequestTest.class,
        WebSocketHandShakeResponseTest.class);
  }
}
