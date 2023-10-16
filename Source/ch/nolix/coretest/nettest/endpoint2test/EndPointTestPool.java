//package declaration
package ch.nolix.coretest.nettest.endpoint2test;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
/**
 * @author Silvan
 * @date 2017-03-01
 */
public final class EndPointTestPool extends TestPool {

  //constructor
  /**
   * Creates a new {@link EndPointTestPool}.
   */
  public EndPointTestPool() {
    super(NetEndPointTest.class);
  }
}
