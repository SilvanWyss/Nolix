//package declaration
package ch.nolix.systemtest.objectdatatest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.objectdatatest.dataadaptertest.DataAdapterTestPool;
import ch.nolix.systemtest.objectdatatest.datatest.DataTestPool;

//class
public final class ObjectDataTestPool extends TestPool {

  //constructor
  public ObjectDataTestPool() {
    super(new DataTestPool(), new DataAdapterTestPool());
  }
}
