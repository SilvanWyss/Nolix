//package declaration
package ch.nolix.coretest.nettest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coretest.nettest.messagingtest.MessagingTestPool;
import ch.nolix.coretest.nettest.netpropertytest.NetPropertyTestPool;
import ch.nolix.coretest.nettest.targettest.TargetTestPool;
import ch.nolix.coretest.nettest.websockettest.WebSocketTestPool;

//class
public final class NetTestPool extends TestPool {

  //constructor
  public NetTestPool() {
    super(
      new ch.nolix.coretest.nettest.endpointtest.EndPointTestPool(),
      new ch.nolix.coretest.nettest.endpoint2test.EndPointTestPool(),
      new ch.nolix.coretest.nettest.endpoint3test.EndPointTestPool(),
      new MessagingTestPool(),
      new NetPropertyTestPool(),
      new TargetTestPool(),
      new WebSocketTestPool());
  }
}
