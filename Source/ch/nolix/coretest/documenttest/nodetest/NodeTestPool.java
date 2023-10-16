//package declaration
package ch.nolix.coretest.documenttest.nodetest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @date 2017-03-01
 */
public final class NodeTestPool extends TestPool {

  //constructor
  /**
   * Creates a new {@link NodeTestPool}.
   */
  public NodeTestPool() {
    super(NodeTest.class, FileNodeTest.class, MutableNodeTest.class);
  }
}
