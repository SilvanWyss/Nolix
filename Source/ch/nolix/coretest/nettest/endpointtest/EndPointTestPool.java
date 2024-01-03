//package declaration
package ch.nolix.coretest.nettest.endpointtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class EndPointTestPool extends TestPool {

  //constructor
  public EndPointTestPool() {
    super(LocalEndPointTest.class, SocketEndPointTest.class, ServerTest.class);
  }
}
