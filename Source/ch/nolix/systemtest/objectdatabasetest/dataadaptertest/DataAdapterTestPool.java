//package declaration
package ch.nolix.systemtest.objectdatabasetest.dataadaptertest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class DataAdapterTestPool extends TestPool {

  // constructor
  public DataAdapterTestPool() {
    super(NodeDataAdapterTest.class);
  }
}
