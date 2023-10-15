//package declaration
package ch.nolix.coretest.documenttest.chainednodetest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class ChainedNodeTestPool extends TestPool {

  // constructor
  public ChainedNodeTestPool() {
    super(ChainedNodeCreationTest.class, ChainedNodeTest.class);
  }
}
